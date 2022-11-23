package com.uom.icar.ui.manageFuel;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uom.icar.R;
import com.uom.icar.SharedPreference;
import com.uom.icar.Temp;
import com.uom.icar.model.Fuel;
import com.uom.icar.model.Service;
import com.uom.icar.model.Vehicle;
import com.uom.icar.ui.home.VehicleAdapter;
import com.uom.icar.ui.manageVehicle.EditVehicleFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddFuelFragment extends Fragment {

    private AddFuelViewModel mViewModel;
    EditText date,amount,price;
    CardView addRecord;
    String nic="";
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();

    public static AddFuelFragment newInstance() {
        return new AddFuelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_add_fuel, container, false);

        SharedPreference preference=new SharedPreference();
        nic=preference.GetString(getContext(),SharedPreference.USER_NIC);

       date=view.findViewById(R.id.addDate);
       amount=view.findViewById(R.id.addFuelLit);
       price=view.findViewById(R.id.addFuelCharge);
       addRecord=view.findViewById(R.id.btnCreate);
       String vehicleNo= Temp.getVehicleNo();



        RecyclerView recyclerView = view.findViewById(R.id.rcvFR);
        List<Fuel> fuelList = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Fuel");
        Query getFuelRecords = rootRef.orderByChild("vehicleNo").equalTo(vehicleNo);

        getFuelRecords.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                preloader.dismissDialog();
                if (snapshot.exists()) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Fuel fuel=postSnapshot.getValue(Fuel.class);
                        fuelList.add(fuel);
                    }

                    FuelAdapter adapter= new FuelAdapter(fuelList,fdb);
                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

       date.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Calendar c = Calendar.getInstance();
               int year = c.get(Calendar.YEAR);
               int month = c.get(Calendar.MONTH);
               int day = c.get(Calendar.DAY_OF_MONTH);
               DatePickerDialog datePickerDialog = new DatePickerDialog(
                       getContext(),
                       new DatePickerDialog.OnDateSetListener() {
                           @Override
                           public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth) {
                               date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                           }
                       },
                       year, month, day);
               datePickerDialog.show();
           }
       });

       addRecord.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if (checkValid()){

                   DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Fuel");

                   String sAmount= amount.getText().toString();
                   String sPrice= price.getText().toString();
                   String sDate=date.getText().toString();



                   //add fuel
                   int val=Integer.valueOf(amount.getText().toString())*Integer.valueOf(price.getText().toString());
                   String total =String.valueOf(val)  ;
                   String key = reference.push().getKey();
                   Fuel fuel=new Fuel(key,nic,vehicleNo,sAmount,sPrice,sDate,total);
                   reference.child(key).setValue(fuel);
                   Toast.makeText(getActivity().getApplicationContext(), "Fuel information Added!", Toast.LENGTH_LONG).show();

                   FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                   AddFuelFragment fragment = new AddFuelFragment();
                   trans.replace(R.id.nav_host_fragment_content_main, fragment);
                   trans.addToBackStack(null);
                   trans.commit();


               }

           }
       });





       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddFuelViewModel.class);
        // TODO: Use the ViewModel
    }

    private boolean checkValid() {
        if (amount.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Fuel amount cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (price.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Fuel charge cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (date.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Date cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }

}
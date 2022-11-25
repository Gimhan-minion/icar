package com.uom.icar.ui.manageService;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uom.icar.PreLoader;
import com.uom.icar.R;
import com.uom.icar.SharedPreference;
import com.uom.icar.Temp;
import com.uom.icar.model.Fuel;
import com.uom.icar.model.Service;
import com.uom.icar.pashwrdHash.passwordHash;
import com.uom.icar.ui.home.HomeFragment;
import com.uom.icar.ui.manageFuel.AddFuelFragment;
import com.uom.icar.ui.manageFuel.FuelAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddServiceFragment extends Fragment {

    private AddViewModel mViewModel;
    CardView create;
    EditText title,des,date,mileage;
    String nic="";
    TextView nodata;
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();

    public static AddServiceFragment newInstance() {
        return new AddServiceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_add_service, container, false);
        SharedPreference preference=new SharedPreference();
        nic=preference.GetString(getContext(),SharedPreference.USER_NIC);

       create=view.findViewById(R.id.btnCreate);
       title=view.findViewById(R.id.addServiceTitle);
       des=view.findViewById(R.id.addServiceDescription);
       date=view.findViewById(R.id.addServiceDate);
       mileage=view.findViewById(R.id.addServiceMileage);
       nodata=view.findViewById(R.id.nodata);
       nodata.setVisibility(View.GONE);
        String vehicleNo= Temp.getVehicleNo();

        final PreLoader preloader = new PreLoader(getActivity());
        preloader.startLoadingDialog();



        RecyclerView recyclerView = view.findViewById(R.id.rcvSR);
        List<Service> serviceList = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Service");
        Query getServiceRecords = rootRef.orderByChild("vehicleNo").equalTo(vehicleNo);

        getServiceRecords.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                preloader.dismissDialog();
                if (snapshot.exists()) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Service service=postSnapshot.getValue(Service.class);
                        serviceList.add(service);
                    }
                    ServiceAdapter adapter= new ServiceAdapter(serviceList,fdb);
                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    recyclerView.setAdapter(adapter);
                }else{
                    nodata.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //date picker
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


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validation
                if (checkValid()){

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Service");

                    String sTitle=title.getText().toString();
                    String sDes=des.getText().toString();
                    String sDate=date.getText().toString();
                    String sMileage= mileage.getText().toString();


                    //add service
                    String key = reference.push().getKey();
                    Service service = new Service(key,vehicleNo,nic,sTitle,sDes,sDate,sMileage);
                    reference.child(key).setValue(service);
                    Toast.makeText(getActivity().getApplicationContext(), "Service information Added!", Toast.LENGTH_LONG).show();

                    FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                    AddServiceFragment fragment = new AddServiceFragment();
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
        mViewModel = new ViewModelProvider(this).get(AddViewModel.class);
        // TODO: Use the ViewModel
    }

    private boolean checkValid() {
        if (title.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Title cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (des.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Description cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (date.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Date cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (mileage.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Mileage cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }

}
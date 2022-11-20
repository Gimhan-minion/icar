package com.uom.icar.ui.manageFuel;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uom.icar.R;
import com.uom.icar.SharedPreference;
import com.uom.icar.model.Fuel;
import com.uom.icar.model.Service;

import java.util.Calendar;

public class AddFuelFragment extends Fragment {

    private AddFuelViewModel mViewModel;
    EditText date,amount,price;
    CardView addRecord;
    String nic="";

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

                   DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Service");

                   int sAmount= Integer.parseInt(amount.getText().toString());
                   int sPrice= Integer.parseInt(price.getText().toString());
                   String sDate=date.getText().toString();



                   //add service
                   String vas="not set";
                   String total="1";
                   String key = reference.push().getKey();
                   Fuel fuel=new Fuel(key,nic,vas,sAmount,sPrice,sDate,total);
                   reference.child(key).setValue(fuel);
                   Toast.makeText(getActivity().getApplicationContext(), "Fuel information Added!", Toast.LENGTH_LONG).show();


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
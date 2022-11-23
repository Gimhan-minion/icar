package com.uom.icar.ui.manageVehicle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.uom.icar.model.Vehicle;
import com.uom.icar.ui.manageService.AddServiceFragment;

import java.util.ArrayList;
import java.util.Collections;

public class EditVehicleFragment extends Fragment {

    private EditVehicleViewModel mViewModel;
    CardView btnUpdate;
    String nic="";
    Spinner type;


    EditText vehicleNo, vehicleENo, vehicleCNo, fuelCapacity, currentFuelAmount, mileage,mileagePerLitre,serviceMileage;

    public static EditVehicleFragment newInstance() {
        return new EditVehicleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_edit_vehicle, container, false);

        SharedPreference preference=new SharedPreference();
        nic=preference.GetString(getContext(),SharedPreference.USER_NIC);

        String vN= Temp.getVehicleNo();

        vehicleNo= view.findViewById(R.id.addVehicleNo);
        vehicleCNo=view.findViewById(R.id.addChassisNo);
        vehicleENo=view.findViewById(R.id.addEngineNo);
        fuelCapacity=view.findViewById(R.id.addFuelTankCap);
        currentFuelAmount =view.findViewById(R.id.addFuelCurrentAmount);
        mileage=view.findViewById(R.id.addMileage);
        mileagePerLitre=view.findViewById(R.id.addMileagePerLitre);
        serviceMileage=view.findViewById(R.id.addServiceMileage);
        btnUpdate=view.findViewById(R.id.btnUpdate);


        //set spinners
        String[] vehicleType={"Select-","Bike","Car","Van"};
        ArrayList<String> spinnerType = new ArrayList<>();

        Collections.addAll(spinnerType, vehicleType);

//        spinnerType.add(vehicleType);

        type  = view.findViewById(R.id.spinner_vehicleType);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeAdapter);



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vehicle");
        Query checkUser = reference.orderByChild("vehicleNo").equalTo(vN);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                preloader.dismissDialog();
                if (snapshot.exists()) {

                    vehicleENo.setText(snapshot.child(vN).child("engineNo").getValue(String.class));
                    vehicleCNo.setText(snapshot.child(vN).child("chassisNo").getValue(String.class));
                    vehicleNo.setText(snapshot.child(vN).child("vehicleNo").getValue(String.class));
                    serviceMileage.setText(snapshot.child(vN).child("serviceMileage").getValue(String.class));
                    mileagePerLitre.setText(snapshot.child(vN).child("mileagePerLitre").getValue(String.class));
                    fuelCapacity.setText(snapshot.child(vN).child("fuelTankCapacity").getValue(String.class));
                    mileage.setText(snapshot.child(vN).child("mileage").getValue(String.class));
                    currentFuelAmount.setText(snapshot.child(vN).child("currentFuelAmount").getValue(String.class));

                    String t = snapshot.child(vN).child("vehicleType").getValue(String.class);
                    for (int i = 0; i < spinnerType.size(); i++) {
                        String temp =spinnerType.get(i);
//                        String[] splitRF = temp.split("[,]", 0);
//                        String roomFloor = splitRF[0];
                        if(t.equals(temp)){
                            type.setSelection(i);
                            break;
                        }
                    }

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = vehicleNo.getText().toString();
                String eno = vehicleENo.getText().toString();
                String cno = vehicleCNo.getText().toString();
                String fc = fuelCapacity.getText().toString();
                String cfa = currentFuelAmount.getText().toString();
                String m = mileage.getText().toString();
                String mpl = mileagePerLitre.getText().toString();
                String sm = serviceMileage.getText().toString();
                String st = String.valueOf(type.getSelectedItem());


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vehicle");
                Query checkUser = reference.orderByChild("vehicleNo").equalTo(vN);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            //updating db
                            try{
                                //creating object
                                Vehicle vehicle = new Vehicle(no, nic, st, cno, eno, sm, fc, cfa, m, mpl);
                                reference.child(vN).setValue(vehicle);
                                Toast.makeText(getActivity().getApplicationContext(),"Vehicle information Updated!",Toast.LENGTH_LONG).show();


                                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                                ViewVehicleFragment fragment = new ViewVehicleFragment();
                                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                                trans.addToBackStack(null);
                                trans.commit();
                            }catch(Exception ex)
                            {
                                throw ex;
                            }

//                            if( vehicleNo.getText().toString().equals(snapshot.child(vN).child("engineNo").getValue(String.class))
//                                    &&vehicleCNo.getText().toString().equals(snapshot.child(vN).child("chassisNo").getValue(String.class))
//                                    &&vehicleNo.getText().toString().equals(snapshot.child(vN).child("vehicleNo").getValue(String.class))
//                                    &&serviceMileage.getText().toString().equals(snapshot.child(vN).child("serviceMileage").getValue(String.class))
//                                    &&mileagePerLitre.getText().toString().equals(snapshot.child(vN).child("mileagePerLitre").getValue(String.class))
//                                    &&fuelCapacity.getText().toString().equals(snapshot.child(vN).child("fuelTankCapacity").getValue(String.class))
//                                    && mileage.getText().toString().equals(snapshot.child(vN).child("mileage").getValue(String.class))
//                                    && currentFuelAmount.getText().toString().equals(snapshot.child(vN).child("currentFuelAmount").getValue(String.class))
//                                    && type.getSelectedItem().equals(snapshot.child(vN).child("vehicleType").getValue(String.class))){
//
//                                Toast.makeText(getActivity().getApplicationContext(),"Nothing to update!",Toast.LENGTH_LONG).show();
//                            }
//                            else{
//                                Toast.makeText(getActivity().getApplicationContext(), "up", Toast.LENGTH_LONG).show();
////
////                                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
////                                    @Override
////                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////                                        if (snapshot.exists()) {
//                                            //updating db
//                                            try{
//                                                //creating object
//                                                Vehicle vehicle = new Vehicle(no, nic, st, cno, eno, sm, fc, cfa, m, mpl);
//                                                reference.child(vN).setValue(vehicle);
//                                                Toast.makeText(getActivity().getApplicationContext(),"Vehicle information Updated!",Toast.LENGTH_LONG).show();
//                                            }catch(Exception ex)
//                                            {
//                                                throw ex;
//                                            }

////                                        } else {
////                                            Toast.makeText(getActivity().getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
////                                        }
////                                    }
////
////                                    @Override
////                                    public void onCancelled(@NonNull DatabaseError error) {
////
////                                    }
////                                });
//                            }
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditVehicleViewModel.class);
        // TODO: Use the ViewModel
    }

}
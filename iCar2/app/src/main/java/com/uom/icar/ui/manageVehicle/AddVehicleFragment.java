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
import com.uom.icar.model.Vehicle;
import com.uom.icar.ui.login.LoginFragment;

import java.util.ArrayList;
import java.util.Collections;

public class AddVehicleFragment extends Fragment {

    private AddVehicleViewModel mViewModel;
    Spinner type;
    CardView btnCreate;

    EditText vehicleNo, vehicleENo, vehicleCNo, fuelCapacity, currentFuelAmount, mileage,mileagePerLitre,serviceMileage;

    DatabaseReference referance;
    FirebaseDatabase rootNode;

    String key;


//    String price ="";
//    String priceForRoomType ="";
//    String priceForBathroomType="";
//    String typeID;
//    int roomPrice =0;
//    int bathroomPrice=0;

    public static AddVehicleFragment newInstance() {
        return new AddVehicleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_vehicle, container, false);

        vehicleNo= view.findViewById(R.id.addVehicleNo);
        vehicleCNo=view.findViewById(R.id.addChassisNo);
        vehicleENo=view.findViewById(R.id.addEngineNo);
        fuelCapacity=view.findViewById(R.id.addFuelTankCap);
        currentFuelAmount =view.findViewById(R.id.addFuelCurrentAmount);
        mileage=view.findViewById(R.id.addMileage);
        mileagePerLitre=view.findViewById(R.id.addMileagePerLitre);
        serviceMileage=view.findViewById(R.id.addServiceMileage);


        //set spinners
        String[] vehicleType={"Select-","Bike","Car","Van"};
        ArrayList<String> spinnerType = new ArrayList<>();

        Collections.addAll(spinnerType, vehicleType);

//        spinnerType.add(vehicleType);

        type  = view.findViewById(R.id.spinner_vehicleType);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeAdapter);

        //create post
        btnCreate=view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                referance = rootNode.getReference("Vehicle");

                String no = vehicleNo.getText().toString();
                String eno = vehicleENo.getText().toString();
                String cno = vehicleCNo.getText().toString();
                int fc = Integer.parseInt(fuelCapacity.getText().toString());
                int cfa = Integer.parseInt(currentFuelAmount.getText().toString());
                int m = Integer.parseInt(mileage.getText().toString());
                int mpl = Integer.parseInt(mileagePerLitre.getText().toString());
                int sm = Integer.parseInt(serviceMileage.getText().toString());
                String st = String.valueOf(type.getSelectedItem());

                if (checkValid()){

                    //checking duplicates
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vehicle");
                    Query checkUser = reference.orderByChild("vehicleNo").equalTo(no);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
    //                                preloader.dismissDialog();
                            if (snapshot.exists()) {

                                Toast.makeText(getActivity().getApplicationContext(), "There is an exiting vehicle for this vehicleNo number", Toast.LENGTH_LONG).show();

                            } else {
                                //saving data to DB
                                try {
                                    //creating object
                                    String nic = "notset";
                                    Vehicle vehicle = new Vehicle(no, nic, st, cno, eno, sm, fc, cfa, m, mpl);

                                    referance.child(no).setValue(vehicle);
                                    Toast.makeText(getActivity().getApplicationContext(), "Vehicle added successfully!", Toast.LENGTH_LONG).show();

    //                                //Move to login frag
    //                                LoginFragment fragment = new LoginFragment();
    //                                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
    //                                trans.replace(R.id.nav_host_fragment_content_main, fragment);
    //                                trans.addToBackStack(null);
    //                                trans.commit();
                                } catch (Exception ex) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Failed to add vehicle", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddVehicleViewModel.class);
        // TODO: Use the ViewModel
    }

    private boolean checkValid() {
        if (vehicleNo.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Vehicle no cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (vehicleENo.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Engine no cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (vehicleCNo.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Chassis no cannot blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (fuelCapacity.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Fuel capacity  cannot blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (currentFuelAmount.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Current fuel amount cannot blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (mileage.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Mileage cannot blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (mileagePerLitre.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Mileage per litre cannot blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (serviceMileage.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Service mileage cannot blank",Toast.LENGTH_LONG).show();
            return false;
        }

        String strType = type.getSelectedItem().toString();
        if (strType.equals("Select-")) {
            Toast.makeText(getActivity().getApplicationContext(),"Select a vehicle Type",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

}
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uom.icar.R;
import com.uom.icar.Temp;
import com.uom.icar.ui.manageFuel.AddFuelFragment;
import com.uom.icar.ui.manageService.AddServiceFragment;
import com.uom.icar.ui.register.RegisterFragment;

public class ViewVehicleFragment extends Fragment {

    private ViewVehicleViewModel mViewModel;
    CardView fuel,service,editVehicle;
    TextView ENo,CNo,VNo,Type,MPL,TC,M;
    public static ViewVehicleFragment newInstance() {
        return new ViewVehicleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_vehicle, container, false);

        String vehicleNo= Temp.getVehicleNo();

        fuel=view.findViewById(R.id.btnFuel);
        service=view.findViewById(R.id.btnService);
        editVehicle=view.findViewById(R.id.btnEdit);
        ENo=view.findViewById(R.id.txtENo);
        CNo=view.findViewById(R.id.txtCNo);
        VNo=view.findViewById(R.id.txtvNo);
        Type=view.findViewById(R.id.txtType);
        MPL=view.findViewById(R.id.txtMPL);
        TC=view.findViewById(R.id.txtTC);
        M=view.findViewById(R.id.txtM);



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vehicle");
        Query checkUser = reference.orderByChild("vehicleNo").equalTo(vehicleNo);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                preloader.dismissDialog();
                if (snapshot.exists()) {


                    ENo.setText(snapshot.child(vehicleNo).child("engineNo").getValue(String.class));
                    CNo.setText(snapshot.child(vehicleNo).child("chassisNo").getValue(String.class));
                    VNo.setText(snapshot.child(vehicleNo).child("vehicleNo").getValue(String.class));
                    Type.setText(snapshot.child(vehicleNo).child("vehicleType").getValue(String.class));
                    MPL.setText(snapshot.child(vehicleNo).child("mileagePerLitre").getValue(String.class)+"KM");
                    TC.setText(snapshot.child(vehicleNo).child("fuelTankCapacity").getValue(String.class)+"L");
                    M.setText(snapshot.child(vehicleNo).child("mileage").getValue(String.class)+"KM");



                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        editVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                EditVehicleFragment fragment = new EditVehicleFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                AddFuelFragment fragment = new AddFuelFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                AddServiceFragment fragment = new AddServiceFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewVehicleViewModel.class);
        // TODO: Use the ViewModel
    }

}
package com.uom.icar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uom.icar.R;
import com.uom.icar.SharedPreference;
import com.uom.icar.databinding.FragmentHomeBinding;
import com.uom.icar.model.Vehicle;
import com.uom.icar.ui.manageVehicle.AddVehicleFragment;
import com.uom.icar.ui.register.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    String nic="";
    CardView add;
    TextView nodata;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SharedPreference preference=new SharedPreference();
        nic=preference.GetString(getContext(),SharedPreference.USER_NIC);

        add=root.findViewById(R.id.btnAddVehicle);

        nodata=root.findViewById(R.id.nodata);
        nodata.setVisibility(View.GONE);



        RecyclerView recyclerView = root.findViewById(R.id.rcvV);
        List<Vehicle> vehicleList = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Vehicle");
        Query getVehicles = rootRef.orderByChild("userNic").equalTo(nic);

        getVehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                preloader.dismissDialog();
                if (snapshot.exists()) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Vehicle vehicle=postSnapshot.getValue(Vehicle.class);
                        vehicleList.add(vehicle);
                    }

                    VehicleAdapter adapter= new VehicleAdapter(vehicleList,fdb);
                    recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
                    recyclerView.setAdapter(adapter);
                }else{
                nodata.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                AddVehicleFragment fragment = new AddVehicleFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
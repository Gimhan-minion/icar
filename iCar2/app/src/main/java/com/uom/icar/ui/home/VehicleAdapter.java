package com.uom.icar.ui.home;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
        import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
        import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uom.icar.MainActivity;
import com.uom.icar.R;
import com.uom.icar.Temp;
import com.uom.icar.model.Vehicle;
import com.uom.icar.ui.login.LoginFragment;
import com.uom.icar.ui.manageVehicle.ViewVehicleFragment;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    FirebaseDatabase fdb;
    List<Vehicle> vehicleList;
    DatabaseReference referance;
    FirebaseDatabase rootNode;
    private Context context;


    public VehicleAdapter(List<Vehicle> vehicles, FirebaseDatabase _db){

        vehicleList = vehicles;
        fdb = _db;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View Items = inflater.inflate(R.layout.item_vehicle,parent,false);
        ViewHolder holder = new ViewHolder(Items);
        context = parent.getContext();
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehicle vehicle=vehicleList.get(position);

        holder.vehicleNo.setText(vehicle.getVehicleNo());
        holder.fuel.setText(vehicle.getCurrentFuelAmount()+"L");




        holder.vehicleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Temp.setVehicleNo(vehicle.getVehicleNo());
                ViewVehicleFragment fragment =new ViewVehicleFragment();
                FragmentTransaction ft=((MainActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final ProgressDialog progressDialog = new ProgressDialog(holder.btnDel.getContext());
//                progressDialog.setTitle("Removing Vehicle...");
//                progressDialog.setCancelable(false);

                AlertDialog.Builder builder =new AlertDialog.Builder(holder.btnDel.getContext());
                builder.setMessage("Are you sure,You want to remove the vehicle").setTitle("Confirm Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        progressDialog.show();
                        rootNode = FirebaseDatabase.getInstance();
                        referance = rootNode.getReference("Vehicle");


                        referance.child(vehicle.getVehicleNo()).removeValue();



                        Toast.makeText((MainActivity)v.getContext(),"Vehicle Removed!",Toast.LENGTH_LONG).show();
                        FragmentTransaction trans =((MainActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                        HomeFragment fragment = new HomeFragment();
                        trans.replace(R.id.nav_host_fragment_content_main, fragment);
                        trans.addToBackStack(null);
                        trans.commit();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if no action
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vehicleNo,fuel;
        ImageView imgVehicle;
        CardView vehicleCard;
        ImageButton btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleCard =itemView.findViewById(R.id.vehicleCard);
            imgVehicle =itemView.findViewById(R.id.imgVehicle);
            vehicleNo =itemView.findViewById(R.id.vehicleNo);
            fuel =itemView.findViewById(R.id.vehicleFuel);
            btnDel=itemView.findViewById(R.id.imgBtnDel);
        }
    }
}
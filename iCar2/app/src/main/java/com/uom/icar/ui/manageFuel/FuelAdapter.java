package com.uom.icar.ui.manageFuel;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uom.icar.MainActivity;
import com.uom.icar.R;
import com.uom.icar.Temp;
import com.uom.icar.model.Fuel;
import com.uom.icar.model.Vehicle;
import com.uom.icar.ui.home.HomeFragment;
import com.uom.icar.ui.manageVehicle.ViewVehicleFragment;

import java.util.List;

public class FuelAdapter extends RecyclerView.Adapter<com.uom.icar.ui.manageFuel.FuelAdapter.ViewHolder> {
    FirebaseDatabase fdb;
    List<Fuel> fuelList;
    DatabaseReference referance;
    FirebaseDatabase rootNode;
    private Context context;


    public FuelAdapter(List<Fuel> fuelRecords, FirebaseDatabase _db){

        fuelList = fuelRecords;
        fdb = _db;
    }


    @NonNull
    @Override
    public com.uom.icar.ui.manageFuel.FuelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View Items = inflater.inflate(R.layout.item_fuel,parent,false);
        com.uom.icar.ui.manageFuel.FuelAdapter.ViewHolder holder = new com.uom.icar.ui.manageFuel.FuelAdapter.ViewHolder(Items);
        context = parent.getContext();
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull com.uom.icar.ui.manageFuel.FuelAdapter.ViewHolder holder, int position) {

        Fuel fuel=fuelList.get(position);

        holder.txtFL.setText(fuel.getFuelInLitres()+"L");
        holder.txtC.setText("RS. "+fuel.getTotal()+".00");
        holder.txtDate.setText(fuel.getRefuelDate());


        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final ProgressDialog progressDialog = new ProgressDialog(holder.btnDel.getContext());
//                progressDialog.setTitle("Removing Vehicle...");
//                progressDialog.setCancelable(false);

                AlertDialog.Builder builder =new AlertDialog.Builder(holder.btnDel.getContext());
                builder.setMessage("Are you sure,You want to remove the fuel record").setTitle("Confirm Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        progressDialog.show();
                        rootNode = FirebaseDatabase.getInstance();
                        referance = rootNode.getReference("Fuel");


                        referance.child(fuel.getId()).removeValue();



                        Toast.makeText((MainActivity)v.getContext(),"Fuel Record Removed!",Toast.LENGTH_LONG).show();
                        FragmentTransaction trans =((MainActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                        AddFuelFragment fragment = new AddFuelFragment();
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
        return fuelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtC,txtFL,txtDate;
        ImageButton btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtC =itemView.findViewById(R.id.txtC);
            txtFL =itemView.findViewById(R.id.txtFL);
            txtDate =itemView.findViewById(R.id.txtDate);
            btnDel=itemView.findViewById(R.id.imgBtnDelFuel);
        }
    }
}

package com.uom.icar.ui.manageService;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uom.icar.MainActivity;
import com.uom.icar.R;
import com.uom.icar.model.Service;
import com.uom.icar.ui.manageFuel.AddFuelFragment;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<com.uom.icar.ui.manageService.ServiceAdapter.ViewHolder> {
    FirebaseDatabase fdb;
    List<Service> serviceList;
    DatabaseReference referance;
    FirebaseDatabase rootNode;
    private Context context;


    public ServiceAdapter(List<Service> serviceRecords, FirebaseDatabase _db){

        serviceList = serviceRecords;
        fdb = _db;
    }


    @NonNull
    @Override
    public com.uom.icar.ui.manageService.ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View Items = inflater.inflate(R.layout.item_service,parent,false);
        com.uom.icar.ui.manageService.ServiceAdapter.ViewHolder holder = new com.uom.icar.ui.manageService.ServiceAdapter.ViewHolder(Items);
        context = parent.getContext();
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull com.uom.icar.ui.manageService.ServiceAdapter.ViewHolder holder, int position) {

        Service service=serviceList.get(position);

        holder.txtT.setText(service.getName());
        holder.txtCM.setText(service.getCurrentMileage()+"Km");
        holder.txtDes.setText(service.getDescription());
        holder.txtDate.setText(service.getServiceDate());


        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final ProgressDialog progressDialog = new ProgressDialog(holder.btnDel.getContext());
//                progressDialog.setTitle("Removing Vehicle...");
//                progressDialog.setCancelable(false);

                AlertDialog.Builder builder =new AlertDialog.Builder(holder.btnDel.getContext());
                builder.setMessage("Are you sure,You want to remove the service record").setTitle("Confirm Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        progressDialog.show();
                        rootNode = FirebaseDatabase.getInstance();
                        referance = rootNode.getReference("Service");


                        referance.child(service.getId()).removeValue();



                        Toast.makeText((MainActivity)v.getContext(),"Fuel Record Removed!",Toast.LENGTH_LONG).show();
                        FragmentTransaction trans =((MainActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                        AddServiceFragment fragment = new AddServiceFragment();
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
        return serviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCM, txtT,txtDate,txtDes;
        ImageButton btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCM =itemView.findViewById(R.id.txtCurrentM);
            txtT =itemView.findViewById(R.id.txtT);
            txtDes=itemView.findViewById(R.id.txtDes);
            txtDate =itemView.findViewById(R.id.txtDate);
            btnDel=itemView.findViewById(R.id.imgBtnDelService);
        }
    }
}
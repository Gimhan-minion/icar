package com.uom.icar.ui.home;


import android.annotation.SuppressLint;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
        import androidx.recyclerview.widget.RecyclerView;

        import com.google.firebase.database.FirebaseDatabase;
import com.uom.icar.MainActivity;
import com.uom.icar.R;
import com.uom.icar.Temp;
import com.uom.icar.model.Vehicle;
import com.uom.icar.ui.login.LoginFragment;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    FirebaseDatabase fdb;
    List<Vehicle> vehicleList;
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




        holder.vehicleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Temp.setVehicleNo(vehicle.getVehicleNo());
                LoginFragment fragment =new LoginFragment();
                FragmentTransaction ft=((MainActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vehicleNo;
        ImageView imgVehicle;
        CardView vehicleCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleCard =itemView.findViewById(R.id.vehicleCard);
            imgVehicle =itemView.findViewById(R.id.imgVehicle);
            vehicleNo =itemView.findViewById(R.id.vehicleNo);
        }
    }
}
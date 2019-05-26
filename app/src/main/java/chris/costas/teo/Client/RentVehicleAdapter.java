package chris.costas.teo.Client;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import chris.costas.teo.R;
import model.classes.Vehicle;

public class RentVehicleAdapter extends RecyclerView.Adapter<RentVehicleAdapter.CustomViewHolder>{
    Context mContext;
    List<Vehicle> mVehicles;
    FragmentManager fragmentManager;
    Vehicle vehicle;
    public RentVehicleAdapter(Context mContext,List<Vehicle> mVehicles,FragmentManager fragmentManager){
        this.mContext = mContext;
        this.mVehicles = mVehicles;
        this.fragmentManager= fragmentManager;
    }
    @NonNull
    @Override
    public RentVehicleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rent_list_item,parent,false);

        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RentVehicleAdapter.CustomViewHolder holder, int position) {
        vehicle = mVehicles.get(position);
        holder.id.setText(vehicle.getId());
        holder.data.setText(vehicle.getBrand()+" "+vehicle.getModel());
        AssetManager assetManager = mContext.getAssets();
        Drawable picDrawable = vehicle.getPic();
        if (picDrawable == null){
            InputStream pictureStream = null;
            try {
                pictureStream = assetManager.open("vehiclePics/"+vehicle.getBrand()+vehicle.getModel()+".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Drawable pictureDrawable = Drawable.createFromStream(pictureStream,"");
            holder.pic.setImageDrawable(pictureDrawable);
        }else{
            holder.pic.setImageDrawable(vehicle.getPic());
        }

    }

    @Override
    public int getItemCount() {
        return mVehicles.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id;
        TextView data;
        ImageView pic;
        ImageView confirm;
        ImageView info;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.rent_vehicle_id);
            data = itemView.findViewById(R.id.rent_vehicle_data);
            pic = itemView.findViewById(R.id.rent_vehicle_pic);
            confirm = itemView.findViewById(R.id.rent_vehicle);
            info = itemView.findViewById(R.id.info_vehicle);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    vehicle = mVehicles.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Info:\n" +
                            "Brand:"+vehicle.getBrand()+"\n"+
                            "Model:"+vehicle.getModel()+"\n"+
                            "Type:"+vehicle.getType()+"\n"+
                            "Seats:"+vehicle.getSeats()+"\n"+
                            "Fuel type:"+vehicle.getFuelType()+"\n"+
                            "PCE:"+vehicle.isPce()+"\n"+
                            "Rate:"+vehicle.getRate()+"\n"+
                            "Extra:"+vehicle.getExtra()+"\n"+
                            "Transmission type:"+vehicle.getTransmissionType()+"\n")
                            .setCancelable(true);
                    builder.create().show();
                }
            });
        }


        @Override
        public void onClick(View v) {

        }


    }
}

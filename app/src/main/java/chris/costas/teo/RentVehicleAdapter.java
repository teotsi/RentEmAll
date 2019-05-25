package chris.costas.teo;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import model.classes.Vehicle;
import model.services.AccountService;

public class RentVehicleAdapter extends RecyclerView.Adapter<RentVehicleAdapter.CustomViewHolder>{
    Context mContext;
    List<Vehicle> mVehicles;
    FragmentManager fragmentManager;
    public RentVehicleAdapter(Context mContext,List<Vehicle> mVehicles,FragmentManager fragmentManager){
        this.mContext = mContext;
        this.mVehicles = mVehicles;
        this.fragmentManager= fragmentManager;
    }
    @NonNull
    @Override
    public RentVehicleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RentVehicleAdapter.CustomViewHolder holder, int position) {
        Vehicle vehicle = mVehicles.get(position);
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
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id;
        TextView data;
        ImageView pic;
        ImageView delete_vehicle;
        ImageView edit_vehicle;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.rent_vehicle_id);
            data = itemView.findViewById(R.id.rent_vehicle_data);
            pic = itemView.findViewById(R.id.rent_vehicle_pic);
        }


        @Override
        public void onClick(View v) {

        }


    }
}

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

public class VehicleManagementAdapter extends RecyclerView.Adapter<VehicleManagementAdapter.CustomViewHolder> {

    Context mContext;
    List<Vehicle> mVehicles;
    FragmentManager fragmentManager;

    public VehicleManagementAdapter(Context mContext, List<Vehicle> mVehicles, FragmentManager fragmentManager) {
        this.mContext = mContext;
        this.mVehicles = mVehicles;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder myViewHolder, int i) {
        Vehicle vehicle = AccountService.getVehicles().get(i);
        myViewHolder.id.setText(vehicle.getId());
        myViewHolder.data.setText(vehicle.getBrand() + " " + vehicle.getModel());
        AssetManager assetManager = mContext.getAssets();
        Drawable picDrawable = vehicle.getPic();
        if (picDrawable == null) {
            InputStream pictureStream = null;
            try {
                pictureStream = assetManager.open("vehiclePics/" + vehicle.getBrand() + vehicle.getModel() + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Drawable pictureDrawable = Drawable.createFromStream(pictureStream, "");
            myViewHolder.pic.setImageDrawable(pictureDrawable);
        } else {
            myViewHolder.pic.setImageDrawable(vehicle.getPic());
        }
        //TODO image

    }

    public void refresh() {
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mVehicles.size();
    }

    public void removeItem(int index) {
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, mVehicles.size());
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id;
        TextView data;
        ImageView pic;
        ImageView delete_vehicle;
        ImageView edit_vehicle;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.vehicle_id);
            data = itemView.findViewById(R.id.vehicle_data);
            pic = itemView.findViewById(R.id.vehicle_pic);
            delete_vehicle =(ImageView) itemView.findViewById(R.id.delete_vehicle);
            edit_vehicle = (ImageView) itemView.findViewById(R.id.edit_vehicle);
            delete_vehicle.setOnClickListener(this);
            edit_vehicle.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == delete_vehicle.getId()) {
                int position = getAdapterPosition();
                Toast.makeText(v.getContext(), "ITEM PRESSED = " + position, Toast.LENGTH_SHORT).show();
                AccountService.removeVehicle(position);
                removeItem(position);
            } else if (v.getId() == edit_vehicle.getId()) {
                int position = getAdapterPosition();
                EditVehicleDialog dialog = EditVehicleDialog.display(fragmentManager, AccountService.getVehicles().get(position), position);
                System.out.println("After display");
                notifyDataSetChanged();
                System.out.println("hola muyyy");
            }
        }


    }
}

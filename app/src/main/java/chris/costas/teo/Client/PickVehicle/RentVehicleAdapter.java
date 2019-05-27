package chris.costas.teo.Client.PickVehicle;

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
import java.time.LocalDate;
import java.util.List;

import chris.costas.teo.R;
import model.classes.Vehicle;

public class RentVehicleAdapter extends RecyclerView.Adapter<RentVehicleAdapter.CustomViewHolder>{
    Context mContext;
    List<Vehicle> mVehicles;
    FragmentManager fragmentManager;
    Vehicle vehicle;
    LocalDate startDate;
    LocalDate endDate;
    PickVehicle activity;

    private final PickVehiclePresenter mPresenter;

    public RentVehicleAdapter(Context mContext, List<Vehicle> mVehicles, FragmentManager fragmentManager, LocalDate startDate, LocalDate endDate, PickVehicle pickVehicle, PickVehiclePresenter mPresenter){
        this.mContext = mContext;
        this.mVehicles = mVehicles;
        this.fragmentManager= fragmentManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activity = pickVehicle;
        this.mPresenter = mPresenter;
    }
    @NonNull
    @Override
    public RentVehicleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rent_list_item,parent,false);

        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RentVehicleAdapter.CustomViewHolder holder, int position) {
        mPresenter.onBindRowViewAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {

        return mPresenter.getRows();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PickVehicleContract.MvpView {

        TextView vehicleId;
        TextView vehicleData;
        ImageView pic;
        ImageView confirm;
        ImageView info;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleId = itemView.findViewById(R.id.rent_vehicle_id);
            vehicleData = itemView.findViewById(R.id.rent_vehicle_data);
            pic = itemView.findViewById(R.id.rent_vehicle_pic);
            confirm = itemView.findViewById(R.id.rent_vehicle);
            confirm.setOnClickListener(this);
            info = itemView.findViewById(R.id.info_vehicle);
            info.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.info_vehicle) {
                int position = getAdapterPosition();
               mPresenter.handleInfoClick(position,mContext);
            }else if(v.getId() == R.id.rent_vehicle){
                mPresenter.handleRentClick(fragmentManager, startDate, endDate,activity,getAdapterPosition());
            }
        }

        @Override
        public void setID(String id) {
            this.vehicleId.setText(id);
        }

        @Override
        public void setVehicleData(String vehicleData) {
            this.vehicleData.setText(vehicleData);
        }

        @Override
        public void setPicture(Drawable picture, AssetManager assetManager, String brand, String model) {
            if(picture == null){
                InputStream pictureStream = null;
                try {
                    pictureStream = assetManager.open("vehiclePics/"+brand+model+".png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Drawable pictureDrawable = Drawable.createFromStream(pictureStream,"");
                this.pic.setImageDrawable(pictureDrawable);
            }else{
                this.pic.setImageDrawable(vehicle.getPic());
            }
        }
    }
}

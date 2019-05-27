package chris.costas.teo.Business.VehicleManagement;

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

import chris.costas.teo.R;
import model.classes.Vehicle;

public class VehicleManagementAdapter extends RecyclerView.Adapter<VehicleManagementAdapter.CustomViewHolder> {

    Context mContext;
    List<Vehicle> mVehicles;
    FragmentManager fragmentManager;
    ManagementAdapterPresenter mPresenter;

    public VehicleManagementAdapter(Context mContext, List<Vehicle> mVehicles, FragmentManager fragmentManager, ManagementAdapterPresenter mPresenter) {
        this.mContext = mContext;
        this.mVehicles = mVehicles;
        this.fragmentManager = fragmentManager;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder myViewHolder, int i) {
        mPresenter.onBindRowViewAtPosition(myViewHolder, i);
    }

    public void refresh() {
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mPresenter.getRows();
    }

    public void removeItem(int index) {
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, mVehicles.size());
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, VehicleManagementContract.MvpView {

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
            delete_vehicle = (ImageView) itemView.findViewById(R.id.delete_vehicle);
            edit_vehicle = (ImageView) itemView.findViewById(R.id.edit_vehicle);
            delete_vehicle.setOnClickListener(this);
            edit_vehicle.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == delete_vehicle.getId()) {
                int position = getAdapterPosition();
                mPresenter.handleDelete(position, fragmentManager);
                removeItem(position);
            } else if (v.getId() == edit_vehicle.getId()) {
                int position = getAdapterPosition();
                mPresenter.handleDelete(position, fragmentManager);
                notifyDataSetChanged();
            }
        }


        @Override
        public void setID(String id) {
            this.id.setText(id);
        }

        @Override
        public void setVehicleData(String vehicleData) {
            this.data.setText(vehicleData);
        }

        @Override
        public void setPicture(Drawable picture, AssetManager assetManager, String brand, String model) {
            if (picture == null) {
                InputStream pictureStream = null;
                try {
                    pictureStream = assetManager.open("vehiclePics/" + brand + model + ".png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Drawable pictureDrawable = Drawable.createFromStream(pictureStream, "");
                this.pic.setImageDrawable(pictureDrawable);
            } else {
                this.pic.setImageDrawable(picture);
            }
        }
    }
}

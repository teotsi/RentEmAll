package chris.costas.teo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.classes.RentingApplication;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder> {

    Context mContext;
    List<RentingApplication> applications;

    public ApplicationAdapter(Context mContext, List<RentingApplication> applications) {
        this.mContext = mContext;
        this.applications = applications;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_application,parent, false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RentingApplication app= applications.get(position);
        holder.id.setText(app.getId());
        holder.start_date.setText(app.getStartDate().toString());
        holder.end_date.setText(app.getEndDate().toString());
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView id;
        private TextView start_date;
        private TextView end_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id= (TextView) itemView.findViewById(R.id.application_id);
            start_date= (TextView) itemView.findViewById(R.id.start_date_id);
            end_date= (TextView) itemView.findViewById(R.id.end_date_id);

        }
    }
}

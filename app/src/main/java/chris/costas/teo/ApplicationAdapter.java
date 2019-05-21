package chris.costas.teo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.classes.RentingApplication;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder> {

    Context mContext;
    List<RentingApplication> applications;
    private OnNoteListener mOnNoteListener;
    FragmentManager fragmentManager;

    public ApplicationAdapter(Context mContext, List<RentingApplication> applications, OnNoteListener onNoteListener, FragmentManager fragmentManager) {
        this.mContext = mContext;
        this.applications = applications;
        this.mOnNoteListener=onNoteListener;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_application,parent, false);
        MyViewHolder vHolder=new MyViewHolder(v, mOnNoteListener);
        return vHolder;
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

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView id;
        private TextView start_date;
        private TextView end_date;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            id= (TextView) itemView.findViewById(R.id.application_id);
            start_date= (TextView) itemView.findViewById(R.id.start_date_id);
            end_date= (TextView) itemView.findViewById(R.id.end_date_id);
            this.onNoteListener= onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
            System.out.println("Adapter Position issssssss: "+ pos);
            onNoteListener.onNoteClick(pos);
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

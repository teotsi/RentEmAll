package chris.costas.teo.Business.Applications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

import chris.costas.teo.R;
import model.classes.RentingApplication;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder> {

    private final ApplicationAdapterPresenter adPresenter;

    Context mContext;
    List<RentingApplication> applications;
    private OnNoteListener mOnNoteListener;
    FragmentManager fragmentManager;

    public ApplicationAdapter(Context mContext, List<RentingApplication> applications, OnNoteListener onNoteListener, FragmentManager fragmentManager, ApplicationAdapterPresenter adPresenter) {
        this.mContext = mContext;
        this.applications = applications;
        this.mOnNoteListener=onNoteListener;
        this.fragmentManager=fragmentManager;
        this.adPresenter=adPresenter;
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
        adPresenter.onBindRepositoryRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ApplicationContract.RepositoryRowView{

        private TextView id;
        private TextView start_date;
        private TextView end_date;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            id= itemView.findViewById(R.id.application_id);
            start_date= itemView.findViewById(R.id.start_date_id);
            end_date= itemView.findViewById(R.id.end_date_id);
            this.onNoteListener= onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
            onNoteListener.onNoteClick(pos);
        }

        @Override
        public void setId(String id) {
            this.id.setText(id);
        }

        @Override
        public void setStartDate(LocalDate startDate) {
            this.start_date.setText(startDate.toString());
        }

        @Override
        public void setEndDate(LocalDate endDate) {
            this.end_date.setText(endDate.toString());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

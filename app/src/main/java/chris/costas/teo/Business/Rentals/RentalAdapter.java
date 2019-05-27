package chris.costas.teo.Business.Rentals;

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
import model.classes.Rental;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.MyViewHolder> {

    Context mContext;
    List<Rental> rentals;
    private OnNoteListener mOnNoteListener;
    FragmentManager fragmentManager;

    public final RentalsContract.RentalsAdapterPresenter adPresenter;

    public RentalAdapter(Context mContext, List<Rental> rentals, OnNoteListener onNoteListener, FragmentManager fragmentManager, RentalAdapterPresenter adPresenter) {
        this.mContext = mContext;
        this.rentals=rentals;
        this.mOnNoteListener=onNoteListener;
        this.fragmentManager=fragmentManager;
        this.adPresenter=adPresenter;
    }

    @NonNull
    @Override
    public RentalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_rental,parent, false);
        MyViewHolder vHolder=new MyViewHolder(v, mOnNoteListener);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        adPresenter.onBindRepositoryRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return rentals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, RentalsContract.RentalsAdapterView{

        private TextView id;
        private TextView start_date;
        private TextView end_date;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            id= itemView.findViewById(R.id.rental_id);
            start_date= itemView.findViewById(R.id.receipt_date_id);
            end_date= itemView.findViewById(R.id.delivery_date_id);
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

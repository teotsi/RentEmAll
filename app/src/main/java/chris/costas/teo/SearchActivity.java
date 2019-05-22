package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;

public class SearchActivity extends AppCompatActivity {
    private LocalDate startDate;
    private LocalDate endDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button startButton = (Button) findViewById(R.id.start_date_pick);
        Button endButton = (Button) findViewById(R.id.end_date_pick);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker(){
        DialogFragment datepicker = new CalendarDatePickerDialog();
        datepicker.show(getSupportFragmentManager(), "date picker");

    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

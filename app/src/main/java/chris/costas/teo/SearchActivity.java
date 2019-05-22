package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;

public class SearchActivity extends AppCompatActivity {
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static TextView startText;
    private static TextView endText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button startButton = (Button) findViewById(R.id.start_date_pick);
        Button endButton = (Button) findViewById(R.id.end_date_pick);
        startText = (TextView) findViewById(R.id.start_date);
        endText = (TextView) findViewById(R.id.end_date);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker("start");
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker("end");
            }
        });
    }

    private void showDatePicker(String type){
        DialogFragment datepicker = new CalendarDatePickerDialog();
        datepicker.show(getSupportFragmentManager(), type);

    }

    public static void setStartDate(LocalDate startDate) {
        SearchActivity.startDate = startDate;
        System.out.println("hola");
        startText.setText(startDate.toString());
    }

    public static void setEndDate(LocalDate endDate) {
        SearchActivity.endDate = endDate;
        System.out.println("hola3");
        endText.setText(endDate.toString());
    }
}

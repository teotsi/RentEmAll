package chris.costas.teo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;

import model.services.AccountService;

public class StatisticsActivity extends AppCompatActivity {
    LocalDate date;
    EditText dateInput;
    Button export;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        dateInput= findViewById(R.id.StatsDate);
        export= findViewById(R.id.ExportStatsButton);
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=LocalDate.parse(dateInput.getText().toString());
                TextView Statistics = findViewById(R.id.StatsResults);
                String income = AccountService.incomeStats(date);
                Statistics.setText(income);
            }
        });

    }
}

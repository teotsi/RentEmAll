package chris.costas.teo.Client;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import chris.costas.teo.R;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import model.classes.Vehicle;
import model.services.SearchService;

/*Responsible for searching for appropriate vehicles
based on date, location and filters*/
public class SearchActivity extends AppCompatActivity {
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static TextView startText;
    private static TextView endText;
    private EditText location;
    private double longitude, latitude;
    private static String filters = "";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button startButton = (Button) findViewById(R.id.start_date_pick);
        Button endButton = (Button) findViewById(R.id.end_date_pick);
        Button filterButton = (Button) findViewById(R.id.filter_button);
        startText = (TextView) findViewById(R.id.start_date);
        endText = (TextView) findViewById(R.id.end_date);
        location = (EditText) findViewById(R.id.location_id);
        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.search_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.search_call) {
                    if(TextUtils.isEmpty(location.getText().toString())){
                        location.setError("This field is required");
                    }else{
//                        if()
                        try {
                            setCoordinates();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!datesValid(startDate, endDate)) {
                            Toast t = Toast.makeText(SearchActivity.this, "Invalid dates! Try again", Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            ArrayList<Vehicle> vehicles;
                            if (filters.equals("")) {
                                vehicles = (ArrayList<Vehicle>) SearchService.getUnfilteredVehicleList(LocalDate.parse(startText.getText().toString()), LocalDate.parse(endText.getText().toString()), latitude, longitude);
                            }else{
                                System.out.println("filtering");
                                vehicles = (ArrayList<Vehicle>) SearchService.getFilteredVehicleList(LocalDate.parse(startText.getText().toString()), LocalDate.parse(endText.getText().toString()), filters, latitude, longitude);
                            }
                            Intent intent=  new Intent(SearchActivity.this, PickVehicle.class);
                            intent.putExtra("vehicles", vehicles);
                            intent.putExtra("startDate",startDate);
                            intent.putExtra("endDate", endDate);
                            startActivity(intent);
                        }
                    }

                }else{
                    FilterDialog.display(getSupportFragmentManager());
                }
                return true;
            }
        });
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
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterDialog.display(getSupportFragmentManager());
            }
        });
    }

    private boolean datesValid(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return false;
        } else
            return true;
    }

    private void showDatePicker(String type) {
        DialogFragment datepicker = new CalendarDatePickerDialog();
        datepicker.show(getSupportFragmentManager(), type);

    }

    public static void addFilter(String filter, String value){
        System.out.println("holaaaaaaaaaaaaaaaaaaaaaaa");
        if(filters.equals("")){
            filters+=filter+","+value;
        }else{
            filters+=","+filter+","+value;
        }
    }

    public static String getFilters() {
        return filters;
    }

    public void setCoordinates() throws IOException {
        Geocoder gc = new Geocoder(this);

        List<Address> addressList = gc.getFromLocationName(location.getText().toString(), 1);

        if (addressList.size() != 0) {
            Address add = addressList.get(0);
            latitude = add.getLatitude();
            longitude = add.getLongitude();
        } else {
            Toast t = Toast.makeText(this, "We couldn't find the address. Try adding more info(City, Postal no., etc) and check your spelling", Toast.LENGTH_SHORT);
            t.show();
        }
    }
}

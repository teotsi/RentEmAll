package chris.costas.teo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarDatePickerDialog extends DialogFragment {
    private String tag;
    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    if (tag.equals("start")) {
                        int viewMonth = view.getMonth()+1;
                        System.out.println(viewMonth);
                        int viewDay = view.getDayOfMonth();

                        String monthString = String.valueOf(viewMonth);
                        if (viewMonth < 10) {
                            monthString = 0 + String.valueOf(viewMonth);
                        }
                        String dayString = String.valueOf(viewDay);
                        if (viewDay < 10) {
                            dayString = 0 + String.valueOf(viewDay);
                        }
                        SearchActivity.setStartDate(LocalDate.parse(view.getYear() + "-" +
                                monthString + "-" + dayString));
                    } else if (tag.equals("end")) {
                        int viewMonth = view.getMonth()+1;
                        int viewDay = view.getDayOfMonth();

                        String monthString = String.valueOf(viewMonth);
                        if (viewMonth < 10) {
                            monthString = 0 + String.valueOf(viewMonth);
                        }
                        String dayString = String.valueOf(viewDay);
                        if (viewDay < 10) {
                            dayString = 0 + String.valueOf(viewDay);
                        }
                        SearchActivity.setEndDate(LocalDate.parse(view.getYear() + "-" +
                                monthString + "-" + dayString));
                    }
                }
            };

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        tag = this.getTag();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }
}

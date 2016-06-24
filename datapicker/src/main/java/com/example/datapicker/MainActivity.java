package com.example.datapicker;

import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private DatePicker datePicker;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 24) {
            calendar = Calendar.getInstance();
            Log.i("jerey", "Today:" + calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月");
        } else {

        }


        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                String src = i + "时" + i1 + "分";
                Toast.makeText(MainActivity.this, src, Toast.LENGTH_LONG).show();

                setTitle(i + "时" + i1 + "分");
            }
        });

//        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                String src = i + "年" + (i1 + 1) + "月" + i2 +"天";
//                Toast.makeText(MainActivity.this, src, Toast.LENGTH_LONG).show();
//            }
//        },12,12,12).show();
//

        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String src = i + "时" + i1 + "分";
                Toast.makeText(MainActivity.this, src, Toast.LENGTH_LONG).show();
            }
        },11,11,true).show();
    }
}

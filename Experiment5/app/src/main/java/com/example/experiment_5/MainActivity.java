package com.example.experiment_5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView dueDate;
    private TextView todayDate;
    private TextView penaltyAmt;
    private Button findpenaltybtn;
    private Date date1, date2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dueDate = findViewById(R.id.duedatepicker);
        todayDate = findViewById(R.id.todaydate);
        penaltyAmt = findViewById(R.id.penalty);
        findpenaltybtn = findViewById(R.id.penaltybtn);

        Calendar myCalendar = Calendar.getInstance();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
        String formatedDate = df.format(c);

        try {
            date2 = df.parse(formatedDate);
        } catch(ParseException e) {
            e.printStackTrace();
        }
    }

    DatePickerDialog.onDateSetListner date = (view, year, monthlyyear,)
}
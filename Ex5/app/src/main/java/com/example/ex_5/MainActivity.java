package com.example.ex_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datepicker);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date and the due date from your data source
                Date currentDate = new Date();
//                Date dueDate = getDueDateFromDataSource();
                Date currentdate = Calendar.getInstance().getTime();



                // Get the selected return date from the date picker
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                Date returnDate = new Date(year - 1900, month, day); // Note: Date(year, month, day) is deprecated

                // Calculate the number of days late
//                long diffInMillis = returnDate.getTime() - duedate.getTime();
                long diffInMillis = currentdate.getTime() - returnDate.getTime();

                long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

                // If the book is returned after the due date, apply a penalty of $1 per day late
                if (diffInDays > 0) {
                    double penalty = diffInDays * 2.0; // Calculate the penalty amount
                    // Apply the penalty to the student's account
//                    applyPenaltyToStudentAccount(penalty);
                    // Notify the student of the penalty
                    Toast.makeText(MainActivity.this, "You returned the book " + diffInDays + " days late. A penalty " + penalty + " ruppes has been applied to your account.", Toast.LENGTH_LONG).show();
                } else {
                    // Notify the student that the book has been returned on time
                    Toast.makeText(MainActivity.this, "Thank you for returning the book on time!", Toast.LENGTH_LONG).show();
                }

                // Close the activity and return to the previous screen
//                finish();
            }
        });
    }
}
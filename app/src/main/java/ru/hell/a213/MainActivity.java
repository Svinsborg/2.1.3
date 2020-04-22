package ru.hell.a213;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button chooseStartDate;
    private Button chooseEndDate;
    private CalendarView startDateCalendar;
    private CalendarView endtDateCalendar;
    private Button btnOK;
    private long startDate;
    private String startDateTxt;
    private long endDate;
    private String endDateTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        chooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDateCalendar.setVisibility(View.VISIBLE);
                endtDateCalendar.setVisibility(View.GONE);
            }
        });

        chooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endtDateCalendar.setVisibility(View.VISIBLE);
                startDateCalendar.setVisibility(View.GONE);
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startDate == 0){
                    Toast.makeText(MainActivity.this, "Ошибка! Введите начальную дату!", Toast.LENGTH_LONG).show();
                } else {
                    if (endDate == 0){
                        Toast.makeText(MainActivity.this, "Ошибка! Введите конечную дату!", Toast.LENGTH_LONG).show();
                    } else {
                        if (startDate > endDate){
                            Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                            chooseStartDate.setText("Дата-время старта задачи:");
                            chooseEndDate.setText("Дата-время окончания задачи:");
                        } else {
                            Toast.makeText(MainActivity.this, "старт: " + startDateTxt + " окончаниe: " + endDateTxt, Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }
        });
    }

    private void initViews() {
        chooseStartDate = findViewById(R.id.chooseStartDate);
        chooseEndDate = findViewById(R.id.chooseEndDate);
        startDateCalendar = findViewById(R.id.startDateCalendar);
        endtDateCalendar = findViewById(R.id.endtDateCalendar);
        btnOK = findViewById(R.id.btnOK);


        startDateCalendar.setVisibility(View.GONE);
        endtDateCalendar.setVisibility(View.GONE);

        startDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                startDateTxt = i+"-"+i1+"-"+i2;
                chooseStartDate.setText("Дата-время старта задачи: " + startDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                startDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        endtDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                endDateTxt = i+"-"+i1+"-"+i2;
                chooseEndDate.setText("Дата-время окончания задачи: " + endDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                endDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });
    }
}
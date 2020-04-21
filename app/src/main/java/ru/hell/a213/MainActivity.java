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

    private Button ChooseStartDate;
    private Button ChooseEndDate;
    private CalendarView StartDateCalendar;
    private CalendarView EndtDateCalendar;
    private Button BtnOK;
    private long StartDate;
    private String StartDateTxt;
    private long EndDate;
    private String EndDateTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        ChooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartDateCalendar.setVisibility(View.VISIBLE);
                EndtDateCalendar.setVisibility(View.GONE);
            }
        });

        ChooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndtDateCalendar.setVisibility(View.VISIBLE);
                StartDateCalendar.setVisibility(View.GONE);
            }
        });

        BtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StartDate > EndDate){
                    Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                    ChooseStartDate.setText("Дата-время старта задачи:");
                    ChooseEndDate.setText("Дата-время окончания задачи:");
                } else {
                    Toast.makeText(MainActivity.this, "старт: " + StartDateTxt + " окончаниe: " + EndDateTxt, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initViews() {
        ChooseStartDate = findViewById(R.id.chooseStartDate);
        ChooseEndDate = findViewById(R.id.chooseEndDate);
        StartDateCalendar = findViewById(R.id.startDateCalendar);
        EndtDateCalendar = findViewById(R.id.endtDateCalendar);
        BtnOK = findViewById(R.id.btnOK);

        // Скроем календари при запуске приложения
        StartDateCalendar.setVisibility(View.GONE);
        EndtDateCalendar.setVisibility(View.GONE);

        StartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                StartDateTxt = i+"-"+i1+"-"+i2;
                ChooseStartDate.setText("Дата-время старта задачи: " + StartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                StartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        EndtDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                EndDateTxt = i+"-"+i1+"-"+i2;
                ChooseEndDate.setText("Дата-время окончания задачи: " + EndDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                EndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });
    }
}

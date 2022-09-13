package com.example.calendarsweet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerview;
    private LocalDate selectDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiwidgets();
        selectDate = LocalDate.now();
        setMonthView();
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearText(selectDate));
        ArrayList <String> daysInMonth = daysInMonthArray(selectDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),  7);
        calendarRecyclerview.setLayoutManager(layoutManager);
        calendarRecyclerview.setAdapter(calendarAdapter);
    }

    private int monthYearText(LocalDate date) {
        return 0;
    }

    private ArrayList<String> daysInMonthArray(LocalDate Date)
    {
        ArrayList <String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(Date);

        int daysInMonth =yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectDate.withDayOfMonth(1);
        int daysOfweek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <=42; i++)
        {
            if (i <= daysOfweek || i> daysInMonth + daysOfweek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - daysOfweek));
            }


        }
        return  daysInMonthArray;


    }

    private String monthYearFromDate (LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM AAAA");
        return date.format(formatter);

    }

    private void initiwidgets()
    {
        calendarRecyclerview = findViewById(R.id.VistaReciclaje);
        monthYearText = findViewById(R.id.MesAñoTv);
    }

    public void MesAnteriorAction(View view) {
        selectDate = selectDate.minusMonths(1);
        setMonthView();
    }


    public void SiguienteMesAction(View view)
    {
        selectDate = selectDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText)
    {
        if (dayText.equals(""))
        {
            String message = "Seleccione una fecha" + dayText + "" + monthYearFromDate(selectDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}
package com.example.dgfab.BusinessDashboard.Business_CRM;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import com.example.dgfab.R;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class CalenderActivity extends AppCompatActivity {
    RecyclerView mycrmrec;
    TextView tv_cal_date;
    HorizontalCalendarView horizontalCalendarView;
    private HorizontalCalendar horizontalCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        tv_cal_date = findViewById(R.id.tv_cal_date);
        mycrmrec = findViewById(R.id.mycrmrec);
        horizontalCalendarView = findViewById(R.id.calendarView);

        //********************************************************************
        /* start 100year months ago from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.YEAR, -100);

        /* end after 100year months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 100);

        // Default Date set to Today.
        final Calendar defaultSelectedDate = Calendar.getInstance();

        horizontalCalendar = new HorizontalCalendar.Builder(getWindow().getDecorView(), R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .showTopText(true)
                .showBottomText(false)
                .textColor(Color.LTGRAY, Color.WHITE)
                .colorTextMiddle(Color.LTGRAY, Color.parseColor("#ffd54f"))
                .end()
                .defaultSelectedDate(defaultSelectedDate)
                .build();
        tv_cal_date.setText(DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());
        Log.i("Default Date", DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                String selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();
                // Toast.makeText(getActivity(), selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
                Log.i("onDateSelected", selectedDateStr + " - Position = " + position);

                tv_cal_date.setText(selectedDateStr);
            }

        });
        //**************************************************************
    }
}

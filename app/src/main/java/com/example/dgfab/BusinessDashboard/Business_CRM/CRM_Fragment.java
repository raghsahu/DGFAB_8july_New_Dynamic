package com.example.dgfab.BusinessDashboard.Business_CRM;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.R;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class CRM_Fragment extends Fragment {

    TextView tv_cal_date;
    private HorizontalCalendar horizontalCalendar;
    HorizontalCalendarView horizontalCalendarView;

    public CRM_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_crm_dashboard, container, false);

        tv_cal_date=rootview.findViewById(R.id.tv_cal_date);
        horizontalCalendarView=rootview.findViewById(R.id.calendarView);

        //********************************************************************
        /* start 100year months ago from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.YEAR, -100);

        /* end after 100year months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 100);

        // Default Date set to Today.
        final Calendar defaultSelectedDate = Calendar.getInstance();

        horizontalCalendar = new HorizontalCalendar.Builder(rootview,R.id.calendarView)
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





        return rootview;
    }









}

package com.example.dgfab.BusinessDashboard.Business_CRM;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dgfab.Adapter.Search_All_Adapter;
import com.example.dgfab.AllParsings.Searching_Manufacturers_Data;
import com.example.dgfab.BusinessDashboard.Search_All_Users;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class CalenderActivity extends AppCompatActivity {
    RecyclerView mycrmrec;
    TextView tv_cal_date;
    HorizontalCalendarView horizontalCalendarView;
    private static final int DATE_DIALOG_ID = 1;
    TimePicker timewa;
    AlertDialog.Builder builder;
    String Friname, Friid;
    Button setrem;
    //  private HorizontalCalendar horizontalCalendar;
    EditText chdate, chtime, frnname, abtrem, metcause;
    EditText editTextDate;
    private int Year;
    private int Month;
    private String format = "";
    private int Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        tv_cal_date = findViewById(R.id.tv_cal_date);
        metcause = findViewById(R.id.metcause);
        chtime = findViewById(R.id.chtime);
        setrem = findViewById(R.id.setrem);
        abtrem = findViewById(R.id.abtrem);
        frnname = findViewById(R.id.frnname);
        mycrmrec = findViewById(R.id.mycrmrec);
        horizontalCalendarView = findViewById(R.id.calendarView);
        chdate = findViewById(R.id.chdate);


        //********************************************************************
        /* start 100year months ago from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.YEAR, -100);

        /* end after 100year months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 100);
//         hour = timewa.getCurrentHour();
//         min = timewa.getCurrentMinute();
//        if (hour == 0) {
//            hour += 12;
//            format = "AM";
//        } else if (hour == 12) {
//            format = "PM";
//        } else if (hour > 12) {
//            hour -= 12;
//            format = "PM";
//        } else {
//            format = "AM";
//        }
//        Toast.makeText(CalenderActivity.this, "Remainder time is"+new StringBuilder().append(hour).append(" : ").append(min)
//                .append(" ").append(format), Toast.LENGTH_SHORT).show();
//        chtime.setText(new StringBuilder().append(hour).append(" : ").append(min)
//                .append(" ").append(format));
        // Default Date set to Today.
        try {
            if (getIntent() != null) {
                Friname = getIntent().getStringExtra("Friname");
                Friid = getIntent().getStringExtra("Friid");
                frnname.setText(Friname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (abtrem.getText().toString().length() != 0) {
                    new PostRemainder(Friid, Friname, new SessionManager(v.getContext()).getUS(), abtrem.getText().toString(), chdate.getText().toString()).execute();
                } else {
                    Toast.makeText(CalenderActivity.this, "Remainder Cause Can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++set time
        chtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.remainderact);
                timewa = dialog.findViewById(R.id.timewa);
                Button okbtn = dialog.findViewById(R.id.okbtn);
                // if button is clicked, close the custom dialog
                okbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int hour = timewa.getCurrentHour();
                        int min = timewa.getCurrentMinute();
                        if (hour == 0) {
                            hour += 12;
                            format = "AM";
                        } else if (hour == 12) {
                            format = "PM";
                        } else if (hour > 12) {
                            hour -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        Toast.makeText(CalenderActivity.this, "Remainder time is" + new StringBuilder().append(hour).append(" : ").append(min)
                                .append(" ").append(format), Toast.LENGTH_SHORT).show();
                        chtime.setText(new StringBuilder().append(hour).append(" : ").append(min)
                                .append(" ").append(format));

                        dialog.dismiss();
                    }
                });
                timewa.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        int hour = timewa.getCurrentHour();
                        int min = timewa.getCurrentMinute();
                        if (hour == 0) {
                            hour += 12;
                            format = "AM";
                        } else if (hour == 12) {
                            format = "PM";
                        } else if (hour > 12) {
                            hour -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        Toast.makeText(CalenderActivity.this, "Remainder time is" + new StringBuilder().append(hour).append(" : ").append(min)
                                .append(" ").append(format), Toast.LENGTH_SHORT).show();
                        chtime.setText(new StringBuilder().append(hour).append(" : ").append(min)
                                .append(" ").append(format));

                    }
                });

                dialog.show();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++
        final Calendar defaultSelectedDate = Calendar.getInstance();


        chdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                Year = mcurrentDate.get(Calendar.YEAR);
                Month = mcurrentDate.get(Calendar.MONTH);
                Day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(CalenderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        chdate.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                    }
                }, Year, Month, Day);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
            //        }

        });
//        tv_cal_date.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                chdate.setText(s.toString());
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                chdate.setText(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                chdate.setText(s.toString());
//
//            }
//        });

        //**************************************************************
    }


    public class PostRemainder extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String namesearch;
        int us;
        String friid, friname, aboutremiander, selectedItem;


        public PostRemainder(String friid, String friname, int us, String aboutremiander, String selectedItem) {
            this.us = us;
            this.friid = friid;
            this.friname = friname;
            this.aboutremiander = aboutremiander;
            this.selectedItem = selectedItem;

        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(CalenderActivity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/addremainder");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", us);
                postDataParams.put("friend_id", friid);
                postDataParams.put("friend_name", friname);
                postDataParams.put("remainder_cause", aboutremiander);
                postDataParams.put("remainder_time", chtime.getText().toString());
                postDataParams.put("remainder_date", chdate.getText().toString());
                postDataParams.put("rem_type", selectedItem);


                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000  /*milliseconds*/);
                conn.setConnectTimeout(15000  /*milliseconds*/);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        StringBuffer Ss = sb.append(line);
                        Log.e("Ss", Ss.toString());
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                dialog.dismiss();

                JSONObject jsonObject = null;
                Log.e("PostRegistration", result.toString());
                try {

                    jsonObject = new JSONObject(result);

                    Boolean response = jsonObject.getBoolean("responce");
                    if (response) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }




}

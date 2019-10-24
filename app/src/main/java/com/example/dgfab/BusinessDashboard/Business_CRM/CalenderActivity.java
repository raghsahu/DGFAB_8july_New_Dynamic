package com.example.dgfab.BusinessDashboard.Business_CRM;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

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

import devs.mulham.horizontalcalendar.HorizontalCalendarView;

public class CalenderActivity extends AppCompatActivity {

    TextView nameofrem;
    TimePicker timepic;
    DatePicker datePicker;
    Button setrem;
    //  private HorizontalCalendar horizontalCalendar;
    EditText remark, abtrem, metcause;
    TextView seldata;
    Button remidme;
    Spinner remspin;
    EditText Day, Month, Year, Hour, Minutes, AmorPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        nameofrem = findViewById(R.id.nameofrem);
        remspin = findViewById(R.id.remspin);
        remark = findViewById(R.id.remark);
        datePicker = findViewById(R.id.datePicker);
        Day = findViewById(R.id.day);
        Month = findViewById(R.id.month);
        seldata = findViewById(R.id.seldata);
        Year = findViewById(R.id.year);
        remidme = findViewById(R.id.remidme);
        Hour = findViewById(R.id.hour);
        Minutes = findViewById(R.id.min);
        AmorPm = findViewById(R.id.amorpm);
        timepic = findViewById(R.id.timepic);
        nameofrem.setText(getIntent().getStringExtra("Name"));
//        metcause = findViewById(R.id.metcause);
//        chtime = findViewById(R.id.chtime);
//        setrem = findViewById(R.id.setrem);
//        abtrem = findViewById(R.id.abtrem);
//        frnname = findViewById(R.id.frnname);

//        chdate = findViewById(R.id.chdate);
        Day.setText(String.valueOf(datePicker.getDayOfMonth()));
        Month.setText(String.valueOf(datePicker.getMonth()));
        Year.setText(String.valueOf(datePicker.getYear()));
        timepic.setIs24HourView(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Hour.setText(String.valueOf(timepic.getHour()));
            Minutes.setText(String.valueOf(timepic.getMinute()));
            if (timepic.getHour() == 13) {
                Hour.setText(String.valueOf(1));
            }
            if (timepic.getHour() == 14) {
                Hour.setText(String.valueOf(2));
            }
            if (timepic.getHour() == 15) {
                Hour.setText(String.valueOf(3));
            }
            if (timepic.getHour() == 16) {
                Hour.setText(String.valueOf(4));
            }
            if (timepic.getHour() == 17) {
                Hour.setText(String.valueOf(5));
            }
            if (timepic.getHour() == 18) {
                Hour.setText(String.valueOf(6));
            }
            if (timepic.getHour() == 19) {
                Hour.setText(String.valueOf(7));
            }
            if (timepic.getHour() == 20) {
                Hour.setText(String.valueOf(8));
            }
            if (timepic.getHour() == 21) {
                Hour.setText(String.valueOf(9));
            }
            if (timepic.getHour() == 22) {
                Hour.setText(String.valueOf(10));
            }
            if (timepic.getHour() == 23) {
                Hour.setText(String.valueOf(11));
            }
            if (timepic.getHour() == 24) {
                Hour.setText(String.valueOf(12));
            }

            String AM_PM;
            if (timepic.getHour() < 12) {
                AM_PM = "AM";

                AmorPm.setText(String.valueOf(AM_PM));
            } else {
                AM_PM = "PM";
                AmorPm.setText(String.valueOf(AM_PM));
            }
        }

        seldata.setText("Date selected " + Day.getText().toString() + "/" + Month.getText().toString() + "/" + Year.getText().toString());
        //********************************************************************
        /* start 100year months ago from now */

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
//        try {
//            if (getIntent() != null) {
//                Friname = getIntent().getStringExtra("Friname");
//                Friid = getIntent().getStringExtra("Friid");
//                frnname.setText(Friname);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        setrem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (abtrem.getText().toString().length() != 0) {
//                    new PostRemainder(Friid, Friname, new SessionManager(v.getContext()).getUS(), abtrem.getText().toString(), chdate.getText().toString()).execute();
//                } else {
//                    Toast.makeText(CalenderActivity.this, "Remainder Cause Can not be empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++set time
//        chtime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // custom dialog
//                final Dialog dialog = new Dialog(v.getContext());
//                dialog.setContentView(R.layout.remainderact);
//                timewa = dialog.findViewById(R.id.timewa);
//                Button okbtn = dialog.findViewById(R.id.okbtn);
//                // if button is clicked, close the custom dialog
//                okbtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int hour = timewa.getCurrentHour();
//                        int min = timewa.getCurrentMinute();
//                        if (hour == 0) {
//                            hour += 12;
//                            format = "AM";
//                        } else if (hour == 12) {
//                            format = "PM";
//                        } else if (hour > 12) {
//                            hour -= 12;
//                            format = "PM";
//                        } else {
//                            format = "AM";
//                        }
//                        Toast.makeText(CalenderActivity.this, "Remainder time is" + new StringBuilder().append(hour).append(" : ").append(min)
//                                .append(" ").append(format), Toast.LENGTH_SHORT).show();
//                        chtime.setText(new StringBuilder().append(hour).append(" : ").append(min)
//                                .append(" ").append(format));
//
//                        dialog.dismiss();
//                    }
//                });
//                timewa.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//                    @Override
//                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                        int hour = timewa.getCurrentHour();
//                        int min = timewa.getCurrentMinute();
//                        if (hour == 0) {
//                            hour += 12;
//                            format = "AM";
//                        } else if (hour == 12) {
//                            format = "PM";
//                        } else if (hour > 12) {
//                            hour -= 12;
//                            format = "PM";
//                        } else {
//                            format = "AM";
//                        }
//                        Toast.makeText(CalenderActivity.this, "Remainder time is" + new StringBuilder().append(hour).append(" : ").append(min)
//                                .append(" ").append(format), Toast.LENGTH_SHORT).show();
//                        chtime.setText(new StringBuilder().append(hour).append(" : ").append(min)
//                                .append(" ").append(format));
//
//                    }
//                });
//
//                dialog.show();
//            }
//        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        final Calendar defaultSelectedDate = Calendar.getInstance();
//
//
//        chdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //To show current date in the datepicker
//                Calendar mcurrentDate = Calendar.getInstance();
//                Year = mcurrentDate.get(Calendar.YEAR);
//                Month = mcurrentDate.get(Calendar.MONTH);
//                Day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog mDatePicker = new DatePickerDialog(CalenderActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
//                        // TODO Auto-generated method stub
//                        /*      Your code   to get date and time    */
//                        chdate.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
//                    }
//                }, Year, Month, Day);
//                mDatePicker.setTitle("Select date");
//                mDatePicker.show();
//            }
//            //        }
//
//        });
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
        remidme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Day.getText().toString().isEmpty() && !Month.getText().toString().isEmpty() &&
                        !remark.getText().toString().isEmpty()) {
                    String Date = Day.getText().toString() + "/" + Month.getText().toString() + "/" + Year.getText().toString();
                    String Time = Hour.getText().toString() + ":" + Minutes.getText().toString() + "" + AmorPm.getText().toString();
                    new PostRemainder(Time, Date, new SessionManager(CalenderActivity.this).getUS(), remark.getText().toString(), remspin.getSelectedItem().toString()).execute();
                } else {
                    Toast.makeText(CalenderActivity.this, "Please Select all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        timepic.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Hour.setText(String.valueOf(hourOfDay));
                if (hourOfDay == 13) {
                    Hour.setText(String.valueOf(1));
                }
                if (hourOfDay == 14) {
                    Hour.setText(String.valueOf(2));
                }
                if (hourOfDay == 15) {
                    Hour.setText(String.valueOf(3));
                }
                if (hourOfDay == 16) {
                    Hour.setText(String.valueOf(4));
                }
                if (hourOfDay == 17) {
                    Hour.setText(String.valueOf(5));
                }
                if (hourOfDay == 18) {
                    Hour.setText(String.valueOf(6));
                }
                if (hourOfDay == 19) {
                    Hour.setText(String.valueOf(7));
                }
                if (hourOfDay == 20) {
                    Hour.setText(String.valueOf(8));
                }
                if (hourOfDay == 21) {
                    Hour.setText(String.valueOf(9));
                }
                if (hourOfDay == 22) {
                    Hour.setText(String.valueOf(10));
                }
                if (hourOfDay == 23) {
                    Hour.setText(String.valueOf(11));
                }
                if (hourOfDay == 24) {
                    Hour.setText(String.valueOf(12));
                }
                Minutes.setText(String.valueOf(minute));
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "AM";

                    AmorPm.setText(String.valueOf(AM_PM));
                } else {
                    AM_PM = "PM";
                    AmorPm.setText(String.valueOf(AM_PM));
                }

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Day.setText(String.valueOf(dayOfMonth));
                    Month.setText(String.valueOf(monthOfYear));
                    Year.setText(String.valueOf(year));

                }
            });
        } else {
            datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Day.setText(String.valueOf(dayOfMonth));
                    Month.setText(String.valueOf(monthOfYear));
                    Year.setText(String.valueOf(year));

                }
            });
        }
        //**************************************************************
    }


    public class PostRemainder extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String namesearch;
        int us;
        String Ttime, Ddate, aboutremiander, selectedItem;


        public PostRemainder(String time, String date, int us, String aboutremiander, String selectedItem) {
            this.us = us;

            this.aboutremiander = aboutremiander;
            this.Ttime = time;
            this.Ddate = date;
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
                postDataParams.put("friend_id", getIntent().getStringExtra("Id"));
                postDataParams.put("friend_name", getIntent().getStringExtra("Name"));
                postDataParams.put("remainder_cause", aboutremiander);
                postDataParams.put("remainder_time", Ttime);
                postDataParams.put("remainder_date", Ddate);
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
                        Toast.makeText(CalenderActivity.this, "Remainder Set Success", Toast.LENGTH_SHORT).show();
                        finish();
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

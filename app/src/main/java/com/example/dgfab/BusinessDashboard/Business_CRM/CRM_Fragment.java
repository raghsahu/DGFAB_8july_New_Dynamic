package com.example.dgfab.BusinessDashboard.Business_CRM;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.FriendsAdapter;
import com.example.dgfab.Adapter.MyCRMAdapter;
import com.example.dgfab.AllParsings.Friends;
import com.example.dgfab.AllParsings.Friendsdata;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CRM_Fragment extends Fragment {

    TextView tv_cal_date;
    private HorizontalCalendar horizontalCalendar;
    HorizontalCalendarView horizontalCalendarView;
    SessionManager sessionManager;
    MyCRMAdapter friendsAdapter;
    RecyclerView mycrmrec;
    private List<Friendsdata> FriendsDataList = new ArrayList<>();

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
        sessionManager = new SessionManager(getActivity());
        GetAllMyContacts();

        tv_cal_date=rootview.findViewById(R.id.tv_cal_date);
        mycrmrec = rootview.findViewById(R.id.mycrmrec);
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

    private void GetAllMyContacts() {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Getting Country");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Log.d("sortname is", "" + sessionManager.getUS());
        Call<Friends> Get_All_Country_New = AbloutApi.FRIENDS_CALL(String.valueOf(sessionManager.getUS()));
        Get_All_Country_New.enqueue(new Callback<Friends>() {
            @Override
            public void onResponse(Call<Friends> call, Response<Friends> response) {
                Log.e("getcity", "" + response.toString());
                if (response != null) {
                    Log.e("Get_City", "" + response.body().getResponce());
                    try {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if (response.body().getData().get(i).getName().length() != 0) {
                                Log.d("sortname is", response.body().getData().get(i).getName());
//                                if(!response.body().getData().get(i).getUstatus().equals("0")) {
                                FriendsDataList.add(new Friendsdata(response.body().getData().get(i).getUserId(), response.body().getData().get(i).getName(), response.body().getData().get(i).getUstatus(), response.body().getData().get(i).getSenderid(), response.body().getData().get(i).getReceiverid(), response.body().getData().get(i).getMobile()));
//                                }
                            }
                        }
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        friendsAdapter = new MyCRMAdapter(getActivity(), FriendsDataList);
                        mycrmrec.setLayoutManager(llm);
                        mycrmrec.setAdapter(friendsAdapter);
                        // countyed.showDropDown();
                        // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Friends> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("error_country", "" + t.getMessage());
                Toast.makeText(getActivity(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}

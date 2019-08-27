package com.example.dgfab.BusinessDashboard.Business_CRM;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
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
import com.example.dgfab.AllParsings.GetRemainder;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

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
    public Friends friendsdata;
    private Call<GetRemainder> Get_All_Country_New;

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
        GetAllMyContacts(1);


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
                GetAllMyContacts(0);
            }

        });
        //**************************************************************
        return rootview;
    }

    private void GetAllRemainder(int size, int callfrom) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Getting Remainder Informations");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        FriendsDataList.clear();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api AbloutApi = RetroLogin.create(Api.class);
        Log.d("sortnamedfhdgf is", "" + sessionManager.getUS());
        if (callfrom == 1) {
            Get_All_Country_New = AbloutApi.GET_REMAINDER_CALL(String.valueOf(sessionManager.getUS()), tv_cal_date.getText().toString());
        } else {
            Get_All_Country_New = AbloutApi.GET_REMAINDER_CALL(String.valueOf(sessionManager.getUS()), tv_cal_date.getText().toString());
        }
        Get_All_Country_New.enqueue(new Callback<GetRemainder>() {
            @Override
            public void onResponse(Call<GetRemainder> call, Response<GetRemainder> response) {
                Log.e("getcity", "" + response.toString());
                if (response != null) {
                    Log.e("Get_Rem", "" + response.body().getResponce());
                    for (int i = 0; i < size; i++) {
                        try {
                            String rem_type = response.body().getData().get(i).getRemType();
                            String rem_date = response.body().getData().get(i).getRemainderDate();
                            String rem_time = response.body().getData().get(i).getRemainderTime();
                            if (response.body().getData().get(i).getRemType().length() != 0 && response.body().getData().get(i).getRemainderTime().length() != 0 && response.body().getData().get(i).getRemainderDate().length() != 0) {
                                FriendsDataList.add(new Friendsdata(friendsdata.getData().get(i).getUserId(), friendsdata.getData().get(i).getName(), friendsdata.getData().get(i).getUstatus(), friendsdata.getData().get(i).getSenderid(), friendsdata.getData().get(i).getReceiverid(), friendsdata.getData().get(i).getMobile(), response.body().getData().get(i).getRemType(), response.body().getData().get(i).getRemainderDate(), response.body().getData().get(i).getRemainderTime(), response.body().getData().get(i).getRemainderCause()));
                            } else {
                                FriendsDataList.add(new Friendsdata(friendsdata.getData().get(i).getUserId(), friendsdata.getData().get(i).getName(), friendsdata.getData().get(i).getUstatus(), friendsdata.getData().get(i).getSenderid(), friendsdata.getData().get(i).getReceiverid(), friendsdata.getData().get(i).getMobile(), "", "", "", ""));
                            }
                        } catch (Exception e) {
                            FriendsDataList.add(new Friendsdata(friendsdata.getData().get(i).getUserId(), friendsdata.getData().get(i).getName(), friendsdata.getData().get(i).getUstatus(), friendsdata.getData().get(i).getSenderid(), friendsdata.getData().get(i).getReceiverid(), friendsdata.getData().get(i).getMobile(), "", "", "", ""));
                            e.printStackTrace();
                        }

                    }

                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    friendsAdapter = new MyCRMAdapter(getActivity(), FriendsDataList);
                    mycrmrec.setLayoutManager(llm);
                    mycrmrec.setAdapter(friendsAdapter);
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetRemainder> call, Throwable t) {
                progressDialog.dismiss();
                for (int i = 0; i < size; i++) {
                    FriendsDataList.add(new Friendsdata(friendsdata.getData().get(i).getUserId(), friendsdata.getData().get(i).getName(), friendsdata.getData().get(i).getUstatus(), friendsdata.getData().get(i).getSenderid(), friendsdata.getData().get(i).getReceiverid(), friendsdata.getData().get(i).getMobile(), "", "", "", ""));
                }
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                friendsAdapter = new MyCRMAdapter(getActivity(), FriendsDataList);
                mycrmrec.setLayoutManager(llm);
                mycrmrec.setAdapter(friendsAdapter);
                friendsAdapter.notifyDataSetChanged();
                Log.e("error_rremy", "" + t.getMessage());
                Log.e("call ca", "" + call.isCanceled());
                Log.e("call ca", "" + call.isExecuted());
                Log.e("error_rremy", "" + t.getMessage());
                Toast.makeText(getActivity(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void GetAllMyContacts(int callfrom) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Getting All Your Contacts");
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
                Log.e("getcon", "" + response.toString());
                if (response != null) {
                    Log.e("Get_Cons", "" + response.body().getResponce());
                    try {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if (response.body().getData().get(i).getName().length() != 0) {
                                Log.d("sortnamejhgh is", response.body().getData().get(i).getName());
//                                if(!response.body().getData().get(i).getUstatus().equals("0")) {
                                friendsdata = response.body();

//                                }
                            }
                        }
                        //  new GETALLREMAINDErs(response.body().getData().size()).execute();

                        GetAllRemainder(response.body().getData().size(), callfrom);
//
//                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                        llm.setOrientation(LinearLayoutManager.VERTICAL);
//                        friendsAdapter = new MyCRMAdapter(getActivity(), FriendsDataList);
//                        mycrmrec.setLayoutManager(llm);
//                        mycrmrec.setAdapter(friendsAdapter);
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


//    private class GETALLREMAINDErs extends AsyncTask<String, Void, String> {
//        int size;
//        int us;
//        String friid,  friname,  aboutremiander,  selectedItem;
//        private ProgressDialog dialog;
//
//
//        public GETALLREMAINDErs(int size) {
//            this.size = size;
//        }
//
//        protected void onPreExecute() {
//            dialog = new ProgressDialog(getActivity());
//            dialog.show();
//
//        }
//
//        protected String doInBackground(String... arg0) {
//
//            try {
//
//                URL url = new URL("https://neareststore.in/api/api/getremainder");
//
//                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("user_id", new SessionManager(getActivity()).getUS());
//                postDataParams.put("remainder_date", tv_cal_date.getText().toString());
//
//                Log.e("postDataParams", postDataParams.toString());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000  /*milliseconds*/);
//                conn.setConnectTimeout(15000  /*milliseconds*/);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode = conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in = new BufferedReader(new
//                            InputStreamReader(
//                            conn.getInputStream()));
//
//                    StringBuffer sb = new StringBuffer("");
//                    String line = "";
//
//                    while ((line = in.readLine()) != null) {
//
//                        StringBuffer Ss = sb.append(line);
//                        Log.e("Ss", Ss.toString());
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                } else {
//                    return new String("false : " + responseCode);
//                }
//            } catch (Exception e) {
//                return new String("Exception: " + e.getMessage());
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result != null) {
//                dialog.dismiss();
//
//                JSONObject jsonObject = null;
//                Log.e("PostRegistration", result.toString());
//                try {
//
//                    jsonObject = new JSONObject(result);
//                    Log.e("json is" , ""+jsonObject.toString());
////                    Log.e("Get_Rem", "" + response.body().getResponce());
////                    for(int i=0;i<size;i++) {
////                        try{
////                            if( response.body().getData().get(i).getRemType().length() !=0&& response.body().getData().get(i).getRemainderTime().length()!=0&& response.body().getData().get(i).getRemainderDate().length() !=0)
////                            {
////                                FriendsDataList.add(new Friendsdata(friendsdata.getData().get(i).getUserId(), friendsdata.getData().get(i).getName(), friendsdata.getData().get(i).getUstatus(), friendsdata.getData().get(i).getSenderid(), friendsdata.getData().get(i).getReceiverid(), friendsdata.getData().get(i).getMobile(), response.body().getData().get(i).getRemType(),  response.body().getData().get(i).getRemainderDate(),  response.body().getData().get(i).getRemainderTime()));
////                            }else {
////                                FriendsDataList.add(new Friendsdata(friendsdata.getData().get(i).getUserId(), friendsdata.getData().get(i).getName(), friendsdata.getData().get(i).getUstatus(), friendsdata.getData().get(i).getSenderid(), friendsdata.getData().get(i).getReceiverid(), friendsdata.getData().get(i).getMobile(), "None", "None",  "None"));
////                            }
////                        }catch (Exception e)
////                        {
////                            FriendsDataList.add(new Friendsdata(friendsdata.getData().get(i).getUserId(), friendsdata.getData().get(i).getName(), friendsdata.getData().get(i).getUstatus(), friendsdata.getData().get(i).getSenderid(), friendsdata.getData().get(i).getReceiverid(), friendsdata.getData().get(i).getMobile(), "None", "None",  "None"));
////                            e.printStackTrace();
////                        }
////
////                    }
//
//                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                    llm.setOrientation(LinearLayoutManager.VERTICAL);
//                    friendsAdapter = new MyCRMAdapter(getActivity(), FriendsDataList);
//                    mycrmrec.setLayoutManager(llm);
//                    mycrmrec.setAdapter(friendsAdapter);
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//        public String getPostDataString(JSONObject params) throws Exception {
//
//            StringBuilder result = new StringBuilder();
//            boolean first = true;
//
//            Iterator<String> itr = params.keys();
//
//            while (itr.hasNext()) {
//
//                String key = itr.next();
//                Object value = params.get(key);
//
//                if (first)
//                    first = false;
//                else
//                    result.append("&");
//
//                result.append(URLEncoder.encode(key, "UTF-8"));
//                result.append("=");
//                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//            }
//            return result.toString();
//        }
//    }
}

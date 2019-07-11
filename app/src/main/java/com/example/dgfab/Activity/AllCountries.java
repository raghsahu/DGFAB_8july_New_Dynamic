package com.example.dgfab.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.APIanURLs.Api;
import com.example.dgfab.APIanURLs.REtroURls;
import com.example.dgfab.Adapter.Country_Adapter;
import com.example.dgfab.AllParsings.Add_Services;
import com.example.dgfab.AllParsings.Example;
import com.example.dgfab.AllParsings.Get_Country;
import com.example.dgfab.Connectivity.HttpHandler;
import com.example.dgfab.Java_Adapter_Files.Country_files;
import com.example.dgfab.R;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllCountries extends AppCompatActivity {
    RecyclerView contrec;
    ArrayAdapter<Country_files> country_filesArrayAdapter ;
    ArrayList<Country_files> country_files = new ArrayList<>();
    private ProgressDialog progressDialog;

    public static String[] country = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",

            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",

            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",

            "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",

            "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria",

            "Burkina Faso", "Burma (Myanmar)", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",

            "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island",

            "Cocos (Keeling) Islands", "Colombia", "Comoros", "Cook Islands", "Costa Rica",

            "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo",

            "Denmark", "Djibouti", "Dominica", "Dominican Republic",

            "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",

            "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia",

            "Gabon", "Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",

            "Greenland", "Grenada", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",

            "Haiti", "Holy See (Vatican City)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",

            "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast", "Jamaica",

            "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait",

            "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",

            "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia",

            "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mayotte", "Mexico",

            "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",

            "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia",

            "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea",

            "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama",

            "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland",

            "Portugal", "Puerto Rico", "Qatar", "Republic of the Congo", "Romania", "Russia", "Rwanda",

            "Saint Barthelemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin",

            "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino",

            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",

            "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea",

            "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland",

            "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau",

            "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",

            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "US Virgin Islands", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",

            "Wallis and Futuna", "West Bank", "Yemen", "Zambia", "Zimbabwe"};
    private String server_url;

    //    public static String[]  code = new String[]{"+93", "+355", "+213", "+1 684", "+376", "+244", "+1 264", "+672", "+1 268", "+54", "+374",
//
//            "+297", "+61", "+43", "+994", "+1 242", "+973", "+880", "+1 246", "+375", "+32", "+501",
//
//            "+229", "+1 441", "+975", "+591", "+387", "+267", "+55", "+246", "+1 284", "+673", "+359",
//
//            "+226", "+95", "+257", "+855", "+237", "+1", "+238", "+1 345", "+236", "+235", "+56", "+86",
//
//            "+61", "+891", "+57", "+269", "+682", "+506", "+385", "+53", "+357", "+420", "+243", "+45",
//
//            "+253", "+1 767", "+1 849", "+1 829", "+1 809", "+593", "+20", "+503", "+240", "+291", "+372",
//
//            "+251", "+500", "+298", "+679", "+358", "+33", "+689", "+241", "+220", "+970", "+995", "+49",
//
//            "+233", "+350", "+30", "+299", "+1 473", "+1 671", "+502", "+224", "+245", "+592", "+509",
//
//            "+379", "+504", "+852", "+36", "+354", "+91", "+62", "+98", "+964", "+353", "+44", "+972",
//
//            "+39", "+225", "+1 876", "+81", "+44", "+962", "+7", "+254", "+686", "+381", "+965", "+996",
//
//            "+856", "+371", "+961", "+266", "+231", "+218", "+423", "+370", "+352", "+853", "+389",
//
//            "+261", "+265", "+60", "+960", "+223", "+356", "+692", "+222", "+230", "+262", "+52", "+691",
//
//            "+373", "+377", "+976", "+382", "+1 664", "+212", "+258", "+264", "+674", "+977", "+31",
//
//            "+599", "+687", "+64", "+505", "+227", "+234", "+683", "+672", "+850", "+1 670", "+47",
//
//            "+968", "+92", "+680", "+507", "+675", "+595", "+51", "+63", "+870", "+48", "+351", "+1",
//
//            "+974", "+242", "+40", "+7", "+250", "+590", "+290", "+1 869", "+1 758", "+1 599", "+508",
//
//            "+1 784", "+685", "+378", "+239", "+966", "+221", "+381", "+248", "+232", "+65", "+421",
//
//            "+386", "+677", "+252", "+27", "+82", "+34", "+94", "+249", "+597", "+268", "+46", "+41",
//
//            "+963", "+886", "+992", "+255", "+66", "+670", "+228", "+690", "+676", "+1 868", "+216",
//
//            "+90", "+993", "+1 649", "+688", "+256", "+380", "+971", "+44", "+1", "+598", "+1 340",
//
//            "+998", "+678", "+58", "+84", "+681", "+970", "+967", "+260", "+263"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_countries);

        contrec = findViewById(R.id.contrec);

//        for(int i=0;i<country.length;i++)
//        {
//            country_files.add(new Country_files(new RadioButton(AllCountries.this) ,country[i] ,new TextView(AllCountries.this)));
//        }
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        contrec.setLayoutManager(llm);
//        contrec.setAdapter( new Country_Adapter(AllCountries.this , country_files));

      new GetAllCountries().execute();

    }

    private class GetAllCountries extends AsyncTask<String, Void, String> {
        String output = "";
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(AllCountries.this);
            dialog.setMessage("Processing");
            dialog.show();
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                server_url = "https://restcountries.eu/rest/v2";


            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("sever_url>>>>>>>>>", server_url);
            output = HttpHandler.makeServiceCall(server_url);
            //   Log.e("getcomment_url", output);
            System.out.println("getcomment_url" + output);
            return output;
        }


        @Override
        protected void onPostExecute(String output) {
            if (output == null) {
                dialog.dismiss();
            } else {
                try {
                    dialog.dismiss();
                    Log.e("GetCountry",output.toString());

                         JSONArray jsonarray=new JSONArray(output);
                        for (int i = 0; i < jsonarray.length(); i++) {

                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            String name = jsonobject.getString("name");
                            String flag = jsonobject.getString("flag");


                            Log.e("GetCountry_list",name);
                            Log.e("GetCountry_flag",flag);


                    country_files.add(new Country_files(name,flag));
                        }

                      LinearLayoutManager llm = new LinearLayoutManager(AllCountries.this);
                       llm.setOrientation(LinearLayoutManager.VERTICAL);
                      contrec.setLayoutManager(llm);
                      contrec.setAdapter( new Country_Adapter(AllCountries.this , country_files));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onPostExecute(output);
            }

        }

    }

//    private void GetAllCountries() {
//
//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMax(1000);
//        progressDialog.setTitle("Getting Country");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .readTimeout(100,TimeUnit.SECONDS).build();
//        Retrofit RetroLogin = new Retrofit.Builder()
//                .baseUrl(REtroURls.GetCountry).client(client).addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api AbloutApi = RetroLogin.create(Api.class);
//
//        Call<Example> Get_all_country = AbloutApi.Get_Country_Call();
//        Get_all_country.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                 Log.e("getcountry" , ""+response.toString());
//                if (response!=null){
//                    Log.e("Get_country",""+response.body().getName());
//
//                    Toast.makeText(AllCountries.this, "true", Toast.LENGTH_SHORT).show();
//
//                }
//
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//                progressDialog.dismiss();
//                Log.e("error_country",t.getMessage());
//                //Toast.makeText(AllCountries.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//               // Toast.makeText(AllCountries.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    
    
    
}

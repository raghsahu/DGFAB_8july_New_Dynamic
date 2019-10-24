package com.example.dgfab.BusinessDashboard.Business_Directory;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dgfab.Adapter.Search_All_Adapter;
import com.example.dgfab.AllParsings.Searching_Manufacturers_Data;
import com.example.dgfab.R;
import com.example.dgfab.SessionManage.SessionManager;
import com.google.gson.JsonParser;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

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
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Directories_Fragment extends Fragment {
    RecyclerView recdir;
    androidx.appcompat.widget.SearchView searchView;
    Toolbar toolbar;
    SessionManager sessionManager;
    Search_All_Adapter search_all_adapter;
    private List<Searching_Manufacturers_Data> searching_manufacturers_data = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View dirvView = inflater.inflate(R.layout.directories_fragment, container, false);
        sessionManager = new SessionManager(getActivity());
        toolbar = (Toolbar) dirvView.findViewById(R.id.toolbar);
        recdir = (RecyclerView) dirvView.findViewById(R.id.recdir);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        searchViewCode(dirvView);
        new GetAllUsers("", sessionManager.getUS()).execute();
        //     new GetAllUsers(namesearch,sessionManager.getUS()).execute();
        return dirvView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void searchViewCode(View dirvView) {
        searchView = (androidx.appcompat.widget.SearchView) dirvView.findViewById(R.id.search_view_dir);
//        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new GetAllUsers(query, sessionManager.getUS()).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                new GetAllUsers(newText, sessionManager.getUS()).execute();
                return false;
            }
        });
//        searchView.setEllipsize(true);
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//            }
//        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class GetAllUsers extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        String namesearch;
        int us;

        public GetAllUsers(String namesearch, int us) {
            this.namesearch = namesearch;
            this.us = us;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://neareststore.in/api/api/searchbyusers");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", us);
                postDataParams.put("name", namesearch);


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
                        for (int ip = 0; ip < jsonObject.getInt("usercount"); ip++) {
                            JsonParser parser = new JsonParser();
                            // String retVal = parser.parse(String.valueOf("user"+ip+1)).getAsString();
                            JSONObject object = jsonObject.getJSONObject("user" + ip);
                            //      1== accept , 2== reject,0=pending ,null= not sent
                            try {
                                String px = object.getString("status");
                                if (object.getString("status").equals("2")) {
                                    searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                            object.getString("email"), object.getString("image"), object.getString("mobile"), "Accepted"));
                                } else if (object.getString("status").equals("1")) {
                                    searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                            object.getString("email"), object.getString("image"), object.getString("mobile"), "Pending"));
                                } else if (object.getString("status").equals("0")) {
                                    searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                            object.getString("email"), object.getString("image"), object.getString("mobile"), "Send Request"));
                                } else if (object.getString("status").equals("")) {
                                    searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                            object.getString("email"), object.getString("image"), object.getString("mobile"), "Send Request"));
                                }
                            } catch (Exception e) {
                                searching_manufacturers_data.add(new Searching_Manufacturers_Data(object.getString("user_id"), object.getString("name"),
                                        object.getString("email"), object.getString("image"), object.getString("mobile"), "Send Request"));
                                e.printStackTrace();
                            }

//                           object.get()
                        }
                        search_all_adapter = new Search_All_Adapter(getActivity(), searching_manufacturers_data);
                        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                        recdir.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                        recdir.setLayoutManager(gridLayoutManager);
                        recdir.setAdapter(search_all_adapter);
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Network Problem", Toast.LENGTH_SHORT).show();
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

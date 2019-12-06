package com.example.telstraproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.telstraproject.adapter.ExploreVisitCardAdapter;
import com.example.telstraproject.R;
import com.example.telstraproject.utils.ExploreVisit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExploreVisitCardAdapter mExploreVisitCardAdapter;
    private ArrayList<ExploreVisit> mExploreVisitList;
    private RequestQueue mRequestQueue;
    public String title = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExploreVisitList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();
    }

    private void parseJson() {
        mExploreVisitList.clear();
        mExploreVisitCardAdapter = new ExploreVisitCardAdapter(MainActivity.this,mExploreVisitList);
        mRecyclerView.setAdapter(mExploreVisitCardAdapter);
        String url = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String jsonObject = response.getString("title");

                    setTitleInActionBar(jsonObject);
                    JSONArray jsonArray = response.getJSONArray("rows");
                    Log.i("Bindu", "onResponse: length "+jsonArray.length());
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject rowsJsonObject = jsonArray.getJSONObject(i);

                        String imageUrl = rowsJsonObject.getString("imageHref");
                        String title = rowsJsonObject.getString("title");
                        String description = rowsJsonObject.getString("description");

                        if (!imageUrl.equals("null") && !title.equals("null") && !description.equals("null")){
                            Log.i("BINDu", "onResponse: All are not null ");

                            mExploreVisitList.add(new  ExploreVisit(imageUrl,title,description));
                        } else if (imageUrl.equals("null") && !title.equals("null") && !description.equals("null")){
                            Log.i("BINDu", "onResponse: image is  null ");

                            imageUrl = "";
                            mExploreVisitList.add(new  ExploreVisit(imageUrl,title,description));
                        } else if (description.equals("null") && !imageUrl.equals("null") && !title.equals("null")){
                            Log.i("BINDu", "onResponse: description null ");

                            mExploreVisitList.add(new  ExploreVisit(imageUrl,title,""));
                        } else if (title.equals("null") && !imageUrl.equals("null") && !description.equals("null")) {
                            Log.i("BINDu", "onResponse: title null ");

                            mExploreVisitList.add(new ExploreVisit(imageUrl, "", description));
                        }
                     //   Log.i("BINDU", "mExploreVisitList "+mExploreVisitList.get(i).toString());
                    }
                    mExploreVisitCardAdapter = new ExploreVisitCardAdapter(MainActivity.this,mExploreVisitList);
                    mRecyclerView.setAdapter(mExploreVisitCardAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(request);
    }

    public void setTitleInActionBar(String jsonObject) {
        setTitle(jsonObject);
        title = jsonObject.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.refresh_btn).setVisible(true);
        menu.findItem(R.id.refresh_btn).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                parseJson();
                return true;
            }
        });
//        menu.findItem(R.id.title).setVisible(true);
        return true;
    }
}

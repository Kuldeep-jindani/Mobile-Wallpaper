package project.sau.pro.com.wallpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.sau.pro.com.wallpaper.paginator_search.Grid_model_search;
import project.sau.pro.com.wallpaper.paginator_search.RecyclerAdapterSearch;

public class SearchActivity extends AppCompatActivity {
//    PullToLoadView pullToLoadView;
    RecyclerView pullToLoadView;
    RecyclerAdapterSearch adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // new paginator_search(getContext(),pullToLoadView).initializePagination();

        pullToLoadView = findViewById(R.id.wallpaper_grid_search);

        pullToLoadView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        String URL="http://www.charmhdwallpapers.com/wallpaper/category";;
        Log.e("Grid service url", URL);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.e("IN ASYNC TASK search", response);
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("data");



                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = (JSONObject) array.get(i);
                        Grid_model_search grid_model = new Grid_model_search();
                        grid_model.setId(o.getInt("id"));
                        grid_model.setimg_url(o.getString("category_name"));
                        grid_model.setimg_url(o.getString("image_url"));

                        adapter.add(grid_model);

                    }

//                    adapter = new RecyclerAdapterSearchCategory(getApplicationContext(), new ArrayList<Grid_model_search>());
                    adapter = new RecyclerAdapterSearch(getApplicationContext(), array,getSupportFragmentManager());
                    pullToLoadView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue.add(stringRequest);


//        new paginator_search(SearchActivity.this,pullToLoadView).initializePagination();
    }
}

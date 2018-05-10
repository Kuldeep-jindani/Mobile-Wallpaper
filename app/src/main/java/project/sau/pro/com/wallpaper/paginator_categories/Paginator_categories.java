package project.sau.pro.com.wallpaper.paginator_categories;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Paginator_categories {


    Context c;
    PullToLoadView pullToLoadView;
    RecyclerView rv;
    AdapterCategories adapter;
    boolean isLoading = false;
    boolean hasLoadAll = false;
    int nextPage;


    public Paginator_categories(Context c, PullToLoadView pullToLoadView) {

        this.c = c;
        this.pullToLoadView = pullToLoadView;
        rv = pullToLoadView.getRecyclerView();
        rv.setLayoutManager(new GridLayoutManager(c, 3));
        adapter = new AdapterCategories(c, new ArrayList<Grid_model_categories>());
        rv.setAdapter(adapter);
        initializePagination();
    }


    public void initializePagination() {

        pullToLoadView.isLoadMoreEnabled(true);
        pullToLoadView.setPullCallback(new PullCallback() {
            @Override
            public void onLoadMore() {
                LoadData(nextPage);
            }

            @Override
            public void onRefresh() {
                adapter.clear();
                hasLoadAll=false;
                LoadData(1);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return hasLoadAll;
            }
        });

        pullToLoadView.initLoad();
    }


    public void LoadData(final int page) {
        isLoading=true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(c);

                String URL="http://www.charmhdwallpapers.com/wallpaper/category";/*?page_size=20&last_item_id=0&device_uid=c699fde86c24b1c&category_id=&category_type=Hot&color_code=&count=0%22";*/
                Log.e("Grid service url", URL);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("page IN ASYNC TASK ", response);
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray array = jsonObject.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = (JSONObject) array.get(i);
                                Grid_model_categories grid_model = new Grid_model_categories();
                                grid_model.setId(o.getInt("id"));
                                grid_model.setimg_url(o.getString("category_name"));
                                grid_model.setimg_url(o.getString("image_url"));
                                adapter.add(grid_model);
                            }
                            pullToLoadView.setComplete();
                            isLoading=false;
                            nextPage=page+1;

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
            }
        },10);
    }

}


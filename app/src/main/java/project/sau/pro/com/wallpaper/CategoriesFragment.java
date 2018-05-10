package project.sau.pro.com.wallpaper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.srx.widget.PullToLoadView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.sau.pro.com.wallpaper.paginator_categories.Paginator_categories;
import project.sau.pro.com.wallpaper.paginator_search.RecyclerAdapterSearch;
import project.sau.pro.com.wallpaper.paginator_search.paginator_search;
/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {
    RecyclerView pullToLoadView;
    RecyclerAdapterSearch adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        pullToLoadView = view.findViewById(R.id.wallpaper_grid_search);

        pullToLoadView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        AdView adView = view.findViewById(R.id.adView);
        MobileAds.initialize(getContext(),"ca-app-pub-7796828333997958/4152584076");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-7796828333997958/4152584076").build();
        adView.loadAd(adRequest);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String URL="http://www.charmhdwallpapers.com/wallpaper/category";/*?page_size=20&last_item_id=0&device_uid=c699fde86c24b1c&category_id=&category_type=Hot&color_code=&count=0%22";*/
        Log.e("Grid service url", URL);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.e("IN ASYNC TASK search", response);
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("data");

                   /* for (int i = 0; i < array.length(); i++) {
                        JSONObject o = (JSONObject) array.get(i);
                        Grid_model_search grid_model = new Grid_model_search();
                        grid_model.setId(o.getInt("id"));
                        grid_model.setimg_url(o.getString("category_name"));
                        grid_model.setimg_url(o.getString("image_url"));

                        adapter.add(grid_model);

                    }*/

//                    adapter = new RecyclerAdapterSearch(getContext(), new ArrayList<Grid_model_search>());
                    adapter = new RecyclerAdapterSearch(getContext(), array,
                            getFragmentManager());
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

      // new paginator_search(getContext(),pullToLoadView).initializePagination();
    
        return view;
    }
}



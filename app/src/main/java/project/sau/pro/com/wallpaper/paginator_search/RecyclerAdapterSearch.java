package project.sau.pro.com.wallpaper.paginator_search;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import project.sau.pro.com.wallpaper.FeatureImageGrid;
import project.sau.pro.com.wallpaper.R;
import project.sau.pro.com.wallpaper.SearchActivity;

public class RecyclerAdapterSearch extends RecyclerView.Adapter<RecyclerAdapterSearch.ViewHolder>{

    private Context c;
    ArrayList<Grid_model_search> grid_models;
    JSONArray array;
    android.support.v4.app.FragmentManager fragmentManager;

    public RecyclerAdapterSearch(Context c, ArrayList<Grid_model_search> grid_models) {

        this.c = c;
        this.grid_models=grid_models;

    }
    public RecyclerAdapterSearch(Context c, JSONArray array,android.support.v4.app.FragmentManager fragmentManager) {

        this.c = c;
        this.array=array;
        this.fragmentManager=fragmentManager;

    }



    public  void  clear()
    {
        grid_models.clear();
    }
    public  void add(Grid_model_search grid_model)
    {
        grid_models.add(grid_model);
    }

    @Override
    public RecyclerAdapterSearch.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.searchlist, parent, false);
        return new RecyclerAdapterSearch.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterSearch.ViewHolder holder, int position) {
//        final Grid_model_search grid_model = grid_models.get(position);
        try {
            final JSONObject jsonObjec=array.getJSONObject(position);
            holder.textView.setText(jsonObjec.getString("category_name"));
            Picasso.get().load(jsonObjec.getString("image_url"))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                       /* Intent intent = new Intent(view.getContext(), CategoryImages.class);
                        intent.putExtra("name",jsonObjec.getString("id"));
                        c.startActivity(intent);*/

                        FragmentTransaction ft=fragmentManager.beginTransaction();
                        Bundle b=new Bundle();
                        b.putString("name",jsonObjec.getString("id"));
                        FeatureImageGrid featureImageGrid=new FeatureImageGrid();
                        featureImageGrid.setArguments(b);
                        ft.replace(R.id.fragment_container,featureImageGrid).commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

/*
        holder.textView.setText(grid_model.getCategory_name());
        Picasso.get().load(grid_model.getImg_url())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);*/
    }




    @Override
    public int getItemCount() {
        return array.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        private ViewHolder(View v){

            super(v);
            imageView = v.findViewById(R.id.img_gride_search);
            textView = v.findViewById(R.id.category_name);
        }

    }

}

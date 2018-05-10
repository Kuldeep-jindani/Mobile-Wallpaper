package project.sau.pro.com.wallpaper.paginator_featured;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

import project.sau.pro.com.wallpaper.Preview_daily;
import project.sau.pro.com.wallpaper.R;

public class AdapterFeatured extends RecyclerView.Adapter<AdapterFeatured.ViewHolder>{

    private Context c;
    JSONArray array;
    FragmentManager fragmentManager;
    ArrayList<Grid_model_featured> grid_models;


    public AdapterFeatured(Context c, ArrayList<Grid_model_featured> grid_models) {
        this.c = c;
        this.grid_models=grid_models;
    }


    public  void  clear()
    {
        grid_models.clear();
    }
    public  void add(Grid_model_featured grid_model)
    {
        grid_models.add(grid_model);
    }

    @Override
    public AdapterFeatured.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.featuredlist, parent, false);
        return new AdapterFeatured.ViewHolder(view);


    }


    @Override
    public void onBindViewHolder(final AdapterFeatured.ViewHolder holder, int position) {
        final Grid_model_featured grid_model = grid_models.get(position);
        Picasso.get().load(grid_model.getImg_url())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
        holder.textView.setText(grid_model.getFavourite_no());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Preview_daily.class);
                intent.putExtra("img_url", grid_model.getImg_url());
                //intent.putExtra("count",grid_model.getFavourite_no());
                c.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return grid_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        private ViewHolder(View v){
            super(v);
            imageView = v.findViewById(R.id.img_gride);
            textView = v.findViewById(R.id.count);

        }

    }

}

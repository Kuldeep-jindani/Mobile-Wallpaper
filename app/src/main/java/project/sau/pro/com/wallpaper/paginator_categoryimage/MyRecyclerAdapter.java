package project.sau.pro.com.wallpaper.paginator_categoryimage;

import android.annotation.SuppressLint;
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

/**
 * Created by Payal on 4/9/2018.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>  {

    private Context c;
    JSONArray array;
    FragmentManager fragmentManager;
    ArrayList<project.sau.pro.com.wallpaper.paginator.Grid_model> grid_models;


    public MyRecyclerAdapter(Context c, ArrayList<project.sau.pro.com.wallpaper.paginator.Grid_model> grid_models) {
        this.c = c;
        this.grid_models=grid_models;
    }

    public  void  clear()
    {
        grid_models.clear();
    }
    public  void add(project.sau.pro.com.wallpaper.paginator.Grid_model grid_model)
    {
        grid_models.add(grid_model);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.dailylist, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final project.sau.pro.com.wallpaper.paginator.Grid_model grid_model = grid_models.get(position);
        Picasso.get().load(grid_model.getImg_url())
                .placeholder(R.drawable.ic_launcher_background)
                .resize(300,500)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);


        holder.textView.setText(grid_model.getFavourite_no());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Preview_daily.class);
//                intent.putExtra("img_url", grid_model.getImg_url());

                intent.putExtra("grid",grid_models);
                intent.putExtra("pos",position);
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return grid_models.size();
    }

/*
    @Override
    public int getCount() {
        return grid_models.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);

        }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(c);
        int padding = c.getResources().getDimensionPixelSize(R.dimen.padding_medium);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
       // imageView.setImageResource(grid_models[position]);
        imageView.setImageResource(position);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
*/




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

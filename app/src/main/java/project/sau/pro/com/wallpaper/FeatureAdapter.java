package project.sau.pro.com.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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


class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder>  {
    Context c;
    JSONArray array;
    FragmentManager fragmentManager;
    public FeatureAdapter(Context c, JSONArray array,FragmentManager fragmentManager) {

        this.c = c;
        this.array=array;
        this.fragmentManager=fragmentManager;

    }



    @Override
    public FeatureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(c).inflate(R.layout.featuredlist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeatureAdapter.ViewHolder holder, int position) {


        try {
            final JSONObject o=array.getJSONObject(position);

            Picasso.get().load(o.getString("image_url")).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Intent intent = new Intent(view.getContext(),CategoryImages.class);

                        FragmentTransaction ft=fragmentManager.beginTransaction();
                        Bundle b=new Bundle();
                        b.putString("name",o.getString("id"));
                        FeatureImageGrid featureImageGrid=new FeatureImageGrid();
                        featureImageGrid.setArguments(b);
                        ft.replace(R.id.fragment_container,featureImageGrid).commit();



                       /* intent.putExtra("name",o.getString("id"));
                        c.startActivity(intent);*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return array.length();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        private ViewHolder(View v){
            super(v);
            imageView = v.findViewById(R.id.img_featured);
        }

    }


}

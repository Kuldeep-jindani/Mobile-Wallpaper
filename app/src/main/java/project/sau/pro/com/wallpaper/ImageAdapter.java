package project.sau.pro.com.wallpaper;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import project.sau.pro.com.wallpaper.paginator.Grid_model;
import project.sau.pro.com.wallpaper.paginator_hot.Grid_model_hot;

public class ImageAdapter extends PagerAdapter {

    Context context;
    /*private int[] GalImages = new int[] {
            R.drawable.settingsvector,
            R.drawable.heart,
            R.drawable.searchicon
    };*/
    View view;
    ArrayList<Grid_model>  grid_models;
    int pos;

    public ImageAdapter(Context context, ArrayList<Grid_model> grid_models){
        this.context=context;
        this.grid_models=grid_models;
    }


    @Override
    public int getCount() {
        return grid_models.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Grid_model grid_model=grid_models.get(position);
        view= (ViewGroup) LayoutInflater.from(context).inflate(R.layout.preview,null);
        ImageView imageView = view.findViewById(R.id.img_preview);
//        final Button button = view.findViewById(R.id.set_as_bcgrnd);
       /* button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {

                final KProgressHUD hud = KProgressHUD.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                new AsyncTask<Void, Void, Button>() {

                    protected void onPreExecute() {
                    }

                    protected Button doInBackground(Void... unused) {
                        WallpaperManager myWallpaperManager
                                = WallpaperManager.getInstance(context);
                        try {

                            Log.e("data",grid_model.getImg_url());
                            Bitmap result;
                            result = Picasso.get()
                                    .load(grid_model.getImg_url())
                                    .get();

                            myWallpaperManager.setBitmap(result);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        hud.dismiss();

                        return button;
                    }
                    protected void onPostExecute(Button unused) {
                        Toast.makeText(context, "Image set as wallpaper.", Toast.LENGTH_SHORT).show();
                    }
                }.execute();
            }
        });*/




//        imageView.setImageResource(this.grid_models.get(position).getId());
  /*       imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
*///        imageView.setImageResource(grid_model.getImg_url());


        Log.e("dhgsfsdg",grid_model.getImg_url());
        Picasso.get().load(grid_model.getImg_url())

                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into( imageView);
//        ((ViewPager) container).addView(imageView, 0);
        (container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==((View)object);
    }

}

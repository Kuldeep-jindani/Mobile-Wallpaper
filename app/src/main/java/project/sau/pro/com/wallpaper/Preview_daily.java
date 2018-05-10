package project.sau.pro.com.wallpaper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import project.sau.pro.com.wallpaper.paginator.Grid_model;
import project.sau.pro.com.wallpaper.paginator.Paginator;

public class Preview_daily extends AppCompatActivity {
    Button btn_set;
    ImageView imageView;
    ViewPager viewPager;
    String App_ID = "ca-app-pub-7796828333997958/4152584076";
    //AdView adView;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_daily);
        viewPager = findViewById(R.id.view_pager);
        /*adView = findViewById(R.id.adView);
        MobileAds.initialize(this,App_ID);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/

        //viewPager = findViewById(R.id.img_preview);

        Intent i = getIntent();
        ArrayList<Grid_model> grid_models = (ArrayList<Grid_model>) i.getSerializableExtra("grid");
        ImageAdapter adapter = new ImageAdapter(this, grid_models);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(i.getIntExtra("pos", 0));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



       /* Picasso.get().load(getIntent().getStringExtra("img_url"))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into((Target) imageView);
        getIntent().getStringExtra("count");


        btn_set = findViewById(R.id.set_as_bcgrnd);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final KProgressHUD hud = KProgressHUD.create(Preview_daily.this)
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
                                = WallpaperManager.getInstance(getApplicationContext());
                        try {
                            Bitmap result;
                            result = Picasso.get()
                                    .load(getIntent().getStringExtra("img_url"))
                                    .get();

                            myWallpaperManager.setBitmap(result);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        hud.dismiss();

                        return btn_set;
                    }
                    protected void onPostExecute(Button unused) {
                        Toast.makeText(getApplicationContext(), "Image set as wallpaper.", Toast.LENGTH_SHORT).show();
                    }
                }.execute();
            }
        });*/
    }
}


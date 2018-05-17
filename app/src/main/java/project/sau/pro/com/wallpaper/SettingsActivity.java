package project.sau.pro.com.wallpaper;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.kobakei.ratethisapp.RateThisApp;

public class SettingsActivity extends AppCompatActivity {
TextView txt_share,remove_ads,txt_rateus,txt_more_apps;
    ImageView img1,img2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        AdView adView = findViewById(R.id.adView);
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-7796828333997958/4152584076");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-7796828333997958/4152584076").build();
       adView.loadAd(adRequest);
        RateThisApp.onStart(this);

        img1=findViewById(R.id.img1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.project.netflix.netflix123"));
                startActivity(browserIntent);
            }
        });
        img2=findViewById(R.id.img2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.pro.st.mynutritiondiet"));
                startActivity(browserIntent);
            }
        });

        txt_rateus = findViewById(R.id.rate_us);
        txt_rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=project.sau.pro.com.wallpaper"));
                startActivity(browserIntent);
            }
        });
        txt_more_apps  = findViewById(R.id.more_apps);
        txt_share = findViewById(R.id.share_app);
        txt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=project.sau.pro.com.wallpaper");
                startActivity(Intent.createChooser(intent, "Share link!"));
            }
        });
        remove_ads = findViewById(R.id.remove_ads);
        remove_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,InAppBillingActivity.class);
                startActivity(intent);
            }
        });

    }
}

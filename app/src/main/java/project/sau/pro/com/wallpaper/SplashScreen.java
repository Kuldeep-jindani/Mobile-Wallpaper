package project.sau.pro.com.wallpaper;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private  Runnable mRunnable;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        ((AnimationDrawable) getWindow().getDecorView().getBackground()).start();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callActivity();

            }

        }, SPLASH_TIME_OUT);

    }

    private void callActivity() {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        if (mHandler != null && mRunnable != null){
            mHandler.removeCallbacks(mRunnable);
        }

        super.onDestroy();
    }
}

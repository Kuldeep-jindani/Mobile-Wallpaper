package project.sau.pro.com.wallpaper;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashScreen extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private  Runnable mRunnable;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
//        ((AnimationDrawable) getWindow().getDecorView().getBackground()).start();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callActivity();

                if (!checkPermission()) {
                    requestPermission();
                } else {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // ((AnimationDrawable) getWindow().getDecorView().getBackground()).start();
                            callActivity();

                        }

                    }, SPLASH_TIME_OUT);
                }
            }


            private void callActivity() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }




            @Override
            protected void onDestroy() {
                if (mHandler != null && mRunnable != null) {
                    mHandler.removeCallbacks(mRunnable);
                }

                super.onDestroy();
            }

            private static final int PERMISSION_REQUEST_CODE = 200;

            private boolean checkPermission() {
                int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
                int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);

                return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
            }

            private void requestPermission() {

                ActivityCompat.requestPermissions(SplashScreen.this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

            }

            @Override
            public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
                switch (requestCode) {
                    case PERMISSION_REQUEST_CODE:
                        if (grantResults.length > 0) {

                            boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                            boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                            if (locationAccepted && cameraAccepted) {
                                Toast.makeText(SplashScreen.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {

                                Toast.makeText(SplashScreen.this, "Permission Denied", Toast.LENGTH_SHORT).show();

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                        showMessageOKCancel("You need to allow access to both the permissions",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                                                                    PERMISSION_REQUEST_CODE);
                                                        }
                                                    }
                                                });
                                        return;
                                    }
                                }

                            }
                        }


                        break;
                }
            }


            private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
                new AlertDialog.Builder(SplashScreen.this)
                        .setMessage(message)
                        .setPositiveButton("OK", okListener)
                        .setNegativeButton("Cancel", null)
                        .create()
                        .show();
            }

        }
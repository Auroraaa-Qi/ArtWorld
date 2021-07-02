package auroraaa.yr.androidart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 5000; // 5s后进入系统
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);


        // Enables Always-on
        // setAmbientEnabled();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainIntent = new Intent(LaunchActivity.this, MainActivity.class);
                LaunchActivity.this.startActivity(mainIntent);
                LaunchActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
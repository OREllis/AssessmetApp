package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        setTitle("");

        final int splashTimeout = 3000; //1000 = 1 second

        //create handler to exec method after delay
        //delay = splashTimeout
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash_Activity.this, LoginActivity.class);
                Splash_Activity.this.startActivity(mainIntent);
                Splash_Activity.this.finish();
            }
        }, splashTimeout);


    }
}

package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Venues_Activity extends AppCompatActivity {

    //stores username passed from MainActivity
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues_);

        //declare intent to get username
        final Intent getUName = getIntent();
        //set username String to passed username
        username = getUName.getStringExtra("username");
    }

    //method that opens web page with SSE Hydro on a map
    public void sseHydro_btn_Click(View v) {
        //make new intent to open url, pass ACTION_VIEW to it
        Intent openUrl = new Intent(Intent.ACTION_VIEW);

        //set the url to open by parsing string url to uri
        openUrl.setData(Uri.parse("https://goo.gl/Ddqs9y"));

        //open url
        startActivity(openUrl);
    }

    //starts HydroComments_Activity.class, ends Venues_Activity
    public void hydroComments_btn_Click(View v) {
        //declare intent to open CommentsActivity
        Intent hydroComments = new Intent(this, CommentsActivity.class);

        //pass username to CommentsActivity
        hydroComments.putExtra("username", username);
        //pass Activity code to CommentsActivity
        hydroComments.putExtra("activityCode", "SSE Hydro");

        //start CommentsActivity
        startActivity(hydroComments);

    }

    //starts MainActivity.class, ends Venues_Activity.class
    public void hydroBack_btn_Click(View v) {
        //declare intent to start MainActivity
        Intent venuesMain = new Intent(this, MainActivity.class);

        //pass username to MainActivity
        venuesMain.putExtra("username", username);
        //start MainActivity
        startActivity(venuesMain);

        //end VenuesActivity
        finish();
    }
}

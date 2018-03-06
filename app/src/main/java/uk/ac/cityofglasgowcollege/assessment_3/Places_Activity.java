package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Places_Activity extends AppCompatActivity {

    //stores username passed from MainActivity
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_);

        //gets username from MainActivity, store it in username
        final Intent getUName = getIntent();
        username = getUName.getStringExtra("username");
    }

    //opens url to map with buchanan st on it.
    public void buchanan_btn_Click(View v) {
        //make new intent to open url, pass ACTION_VIEW to it
        Intent openUrl = new Intent(Intent.ACTION_VIEW);

        //set the url to open by parsing string url to uri
        openUrl.setData(Uri.parse("https://goo.gl/LTTeFa"));

        //open url
        startActivity(openUrl);
    }

    //opens web page about buchanan street
    public void buchananComments_btn_Click(View v) {

        //declare intent to start CommentsActivity
        Intent placesComments = new Intent(this, CommentsActivity.class);

        //pass username to CommentsActivity
        placesComments.putExtra("username", username);
        //pass Activity code for PlacesActivity
        placesComments.putExtra("activityCode", "Buchanan Street");

        //start CommentsActivity
        startActivity(placesComments);

    }

    //starts MainActivity.class, ends Places_Activity.class
    public void back_btn_Click(View v) {
        //declare intent to start MainActivity
        Intent placesMain = new Intent(this, MainActivity.class);

        //pass username to MainActivity
        placesMain.putExtra("username", username);
        //start MainActivity
        startActivity(placesMain);

        //end Places_Activity
        finish();
    }
}

package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ArtAndMuseums_Activity extends AppCompatActivity {

    //stores username
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_and_museums_);

        //declare intent to get username from MainActivity, store in username
        final Intent in = getIntent();
        username = in.getStringExtra("username");
    }

    public void kelvingrove_btn_Click(View v) {
        //make new intent to open url, pass ACTION_VIEW to it
        Intent openUrl = new Intent(Intent.ACTION_VIEW);

        //set the url to open by parsing string url to uri
        openUrl.setData(Uri.parse("https://goo.gl/Ddqs9y"));

        //open url
        startActivity(openUrl);
    }

    public void artComments_btn_Click(View v) {
        //declare intent to start CommentsActivity
        Intent artComments = new Intent(this, CommentsActivity.class);

        //pass username to CommentsActivity
        artComments.putExtra("username", username);
        //pass Activity code for ArtAndMuseums_Activity to CommentsActivity
        artComments.putExtra("activityCode", "Kelvingrove Art Gallery and Museum");

        //start CommentsActivity
        startActivity(artComments);

    }

    //starts MainActivity.class, ends ArtAndMuseums.class
    public void artBack_btnClick(View v) {
        //declare intent to start MainActivity
        Intent artMain = new Intent(this, MainActivity.class);

        //pass username to MainActivity
        artMain.putExtra("username", username);
        //start MainActivity
        startActivity(artMain);

        //end ArtAndMuseums_Activity
        finish();


    }
}

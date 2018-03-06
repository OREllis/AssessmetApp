package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TransportationActivity extends AppCompatActivity {

    //// TODO: 27/02/2018 Finish comments

    //string to store username
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);

        //declare intent to get username from MainActivity, store in username
        Intent getUname = getIntent();
        username = getUname.getStringExtra("username");
    }

    private void openUrl(final String url) {
        //make new intent to open url, pass ACTION_VIEW to it
        Intent openUrl = new Intent(Intent.ACTION_VIEW);

        //set the url to open by parsing passed string to uri
        openUrl.setData(Uri.parse(url));

        //open url
        startActivity(openUrl);
    }

    public void subwaySite_btn_Click(View v) {
        //pass subway website url to openUrl method
        openUrl("http://www.spt.co.uk/subway/maps-stations/");
    }

    public void busSite_btn_Click(View v) {
        //pass Bus website url to openUrl method
        openUrl("https://goo.gl/d2HZeF");
    }

    public void taxisSite_btn_Click(View v) {
        //pass taxi website url to openUrl method
        openUrl("http://www.glasgowtaxis.co.uk/");
    }

    public void bikesSite_btn_Click(View v) {
        //pass bike website url to openUrl method
        openUrl("https://www.nextbike.co.uk/en/glasgow/");
    }

    public void transportBack_btn_Click(View v) {
        Intent main = new Intent(this, MainActivity.class);
        main.putExtra("username", username);

        startActivity(main);
        finish();
    }
}

package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //stores username from LoginActivity
    public String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get passed username from LoginActivity
        final Intent getUName = getIntent();

        //sets TextView to "Welcome" with the username concatenated
        final TextView title_txt = (TextView) findViewById(R.id.title_txt);

        //set String username to passed string "username"
        username = getUName.getStringExtra("username");

        //display the Username
        title_txt.setText(title_txt.getText().toString() + username);
    }


    //starts Places_Activity.class
    public void places_btn_Click(View v) {
        //declare intent for PlacesActivity
        Intent places = new Intent(this, Places_Activity.class);

        //put username in intent
        places.putExtra("username", username);

        //start PlacesActivity
        startActivity(places);
    }

    //starts Venues_Activity.class
    public void venues_btn_Click(View v) {
        //declares intent for VenuesActivity
        Intent venues = new Intent(this, Venues_Activity.class);

        //put username in intent
        venues.putExtra("username", username);

        //start VenuesActivity
        startActivity(venues);
    }

    public void artAndMuseums_btn_Click(View v) {
        //declare intent for ArtAndMuseumsActivity
        Intent art = new Intent(this, ArtAndMuseums_Activity.class);

        //put username in intent
        art.putExtra("username", username);

        //start ArtAndMuseumsActivity
        startActivity(art);
    }

    public void transport_btn_Click(View v) {
        //declare intent for TransportationActivity
        Intent transport = new Intent(this, TransportationActivity.class);

        //put username in intent
        transport.putExtra("username", username);

        //start TransportationActivity
        startActivity(transport);
    }

    //starts LoginActivity.class, ends MainActivity.class
    public void logout_btn_Click(View v) {
        //declare intent for LoginActivity
        Intent login = new Intent(this, LoginActivity.class);

        //empty username
        username = null;

        //start LoginActivity
        startActivity(login);

        //end MainActivity
        finish();
    }
}

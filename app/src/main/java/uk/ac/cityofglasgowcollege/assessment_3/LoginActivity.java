package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //activity launches when login button is clicked
    public void login_btn_Click(View v) {
        //store username and password as EditText
        final EditText username_txt = (EditText) findViewById(R.id.username_txt),
                password_txt = (EditText) findViewById(R.id.password_txt);

        //convert EditTexts to String
        final String username = username_txt.getText().toString(),
                password = password_txt.getText().toString();

        //declare class containing methods to display errors
        DisplayMessages DM = new DisplayMessages();

        //if username or password is empty
        if (username.length() == 0 || password.length() == 0) {

            //call displayAlert, pass title, message, and this to it, then return
            DM.displayAlert("Empty Field", "You must enter a username and password", this);
            return;
        }

        //declare class that manages & queries SQL database
        DBHandler DB = new DBHandler(this);

        //pass username and password to check if details are accurate
        if (DB.checkLogin(username, password)) {

            //declare intent to start MainActivity
            Intent main = new Intent(this, MainActivity.class);

            //pass username to main
            main.putExtra("username", username);

            //start MainActivity
            startActivity(main);

            //end current activity
            finish();

        } else
            //display error if invalid details
            DM.displayAlert("Invalid Details", "Enter a valid Username and Password", this);


    }

    public void register_btn_Click(View v) {
        //declare intent to run RegisterActivity, start RegisterActivity
        final Intent reg = new Intent(this, RegisterActivity.class);
        startActivity(reg);
    }
}

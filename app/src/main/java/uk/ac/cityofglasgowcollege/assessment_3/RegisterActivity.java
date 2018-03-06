package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void enterDetails_btn_Click(View v) {
        //store details as EditText
        final EditText regUname_txt = (EditText) findViewById(R.id.regUname_txt),
                regPass_txt = (EditText) findViewById(R.id.regPass_txt),
                email_txt = (EditText) findViewById(R.id.email_txt),
                age_txt = (EditText) findViewById(R.id.age_txt);

        //convert details to string (age specifically to int)
        final String username = regUname_txt.getText().toString(),
                password = regPass_txt.getText().toString(),
                email = email_txt.getText().toString(),
                age = age_txt.getText().toString();


        //declare class files used in activity
        DisplayMessages DM = new DisplayMessages();
        DBHandler DB = new DBHandler(this);
        User U = new User();

        //checks if strings are empty, shows error and returns if they are
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()) {
            DM.displayAlert("Error", "One or more fields are empty", this);
            return;
        }

        //call Users class, pass details to it
        U.setUsername(username);
        U.setPassword(password);
        U.setEmail(email);
        U.setAge(Integer.parseInt(age));

        //pass username to DB.usernameTaken to check if it already exists
        if (DB.usernameTaken(username))
            //display error msg
            DM.displayAlert("Username Taken", "Use a different Username", this);
        else {
            try {
                //try to add user details to database
                DB.addUser(U);
            } catch (Exception ex) {
                //display error message if exception happens, then return
                DM.displayAlert("An Exception has Occurred", "Error: " + ex, this);
                return;
            }

            //display toast showing confirmation message
            DM.toasty(this, "Details added, Registration Successful");
        }

    }

    public void loginReg_btn_Click(View v) {
        //declare intent to return to LoginActivity, start LoginActivity, finish this Activity
        final Intent back = new Intent(this, LoginActivity.class);
        startActivity(back);
        finish();
    }
}

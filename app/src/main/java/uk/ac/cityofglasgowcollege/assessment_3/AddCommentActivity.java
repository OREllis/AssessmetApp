package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Comment;

public class AddCommentActivity extends AppCompatActivity {

    //string to store username and activityCode
    String usernameIn = "",
            activityCode = "";

    //int to store rating, initialised to invalid number
    int rating = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        //declare intent to get info passed to AddCommentActivity
        Intent getInfo = getIntent();
        //get passed username, store in usernameIn
        usernameIn = getInfo.getStringExtra("username");
        //get activityCode, store in activityCode
        activityCode = getInfo.getStringExtra("activityCode");

        //find username_txt & activityCode_txt, store as TextView
        TextView username_txt = (TextView) findViewById(R.id.username_txt),
                activityCode_txt = (TextView) findViewById(R.id.activityCode_txt);

        //set username_txt text to usernameIn, set activityCode_txt text to activityCode
        username_txt.setText(usernameIn);
        activityCode_txt.setText(activityCode);
    }

    public void addComment_btn_Click(View v) {
        //find commentText_txt, store as EditText
        EditText commentText_txt = (EditText) findViewById(R.id.commentText_txt);

        //store text from commentText_txt as String
        String commentText = commentText_txt.getText().toString();

        //create instance of DisplayMessages to display error if necessary
        DisplayMessages DM = new DisplayMessages();

        //declare new Comment
        Comments comment = new Comments();

        //if check radio returns false
        if (!checkRadio()) {
            //display error, then return
            DM.displayAlert("Err", "Enter a rating", this);
            return;
        }

        //add details to comment
        comment.setUsername(usernameIn);
        comment.setProductId(activityCode);
        comment.setCommentRating(rating);
        comment.setComments(commentText);

        try {
            //declare DBHandler
            DBHandler DB = new DBHandler(this);

            //try to add Comment to DBHandler
            DB.addComment(comment);

            //if exception happens
        } catch (Exception ex) {
            //display error message, then return
            DM.displayAlert("An Exception has occurred: ", ex.toString(), this);
            return;
        }

        //display confirmation message
        DM.toasty(this, "Comment has Successfully been added.");
    }

    public void commentBack_btn_Click(View v) {
        //declare intent to start CommentsActivity
        Intent comment = new Intent(this, CommentsActivity.class);

        //pass usernameIn, and activityCode to CommentsActivity
        comment.putExtra("username", usernameIn);
        comment.putExtra("activityCode", activityCode);

        //start Comments Activity, and finish AddCommentActivity
        startActivity(comment);
        finish();
    }

    //function checks each radio button to see if its checked, and makes rating = to
    //the checked radio button's number
    public boolean checkRadio() {

        //find radio buttons and store them as RadioButtons
        RadioButton rating0_rad = (RadioButton) findViewById(R.id.rating0_rad),
                rating1_rad = (RadioButton) findViewById(R.id.rating1_rad),
                rating2_rad = (RadioButton) findViewById(R.id.rating2_rad),
                rating3_rad = (RadioButton) findViewById(R.id.rating3_rad),
                rating4_rad = (RadioButton) findViewById(R.id.rating4_rad),
                rating5_rad = (RadioButton) findViewById(R.id.rating5_rad);


        //checks each radio button individually if they're checked,
        // makes rating = checked radio button's number
        if (rating0_rad.isChecked()) {
            rating = 0;
            return true;
        } else if (rating1_rad.isChecked()) {
            rating = 1;
            return true;
        } else if (rating2_rad.isChecked()) {
            rating = 2;
            return true;
        } else if (rating3_rad.isChecked()) {
            rating = 3;
            return true;
        } else if (rating4_rad.isChecked()) {
            rating = 4;
            return true;
        } else if (rating5_rad.isChecked()) {
            rating = 5;
            return true;
        }

        //if none of the radio buttons are checked
        return false;
    }
}

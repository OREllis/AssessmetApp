package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    //declare strings to store username, and activityCode passed to CommentsActivity
    private String username = "",
            activityCode = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        //declare intent to get username & activityCode
        Intent getInfo = getIntent();

        //store passed username in string username
        username = getInfo.getStringExtra("username");
        //store passed Activity code in activityCode
        activityCode = getInfo.getStringExtra("activityCode");

        //find commentTitle_txt, store as TextView
        TextView commentTitle_txt = (TextView) findViewById(R.id.commentTitle_txt);
        //set commentTitle_txt text to itself with activityCode concatenated to it
        commentTitle_txt.setText(commentTitle_txt.getText() + activityCode);

        //run displayComments method
        displayComments();
    }


    //method that displays the comments for the activityCode passed to CommentsActivity
    private void displayComments() {
        //find the reviews ListView
        ListView comments_lst = (ListView) findViewById(R.id.comments_lst);

        //create instance of DBHandler class (pass "this" as parameter)
        DBHandler DB = new DBHandler(this);


        //create instance of ArrayList<Review>, call getComments to populate ArrayList
        ArrayList<Comments> comments = DB.getComments(activityCode);

        //declare ArrayList of strings called commentStrings
        ArrayList<String> commentStrings = new ArrayList<>();

        //loop through all Comments in ArrayList
        for (Comments c : comments) {

            //string to store formatted comment
            String formattedComment = "Commenter:\t\t" + c.getUsername() +
                    "\nRating:\t\t\t\t" + c.getCommentRating() + " out of 5 " +
                    "\nComments:\t\t\t\t" + c.getComments() + "\n";

            //add formatted string to commentStrings
            commentStrings.add(formattedComment);
        }

        //declare ArrayAdapter that will display all strings in commentStrings in comments_lst
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, commentStrings);


        //connect adapter to comments_lst
        comments_lst.setAdapter(listAdapter);

    }

    //starts AddCommentActivity
    public void writeComment_btn_Click(View v) {
        //declare intent to start AddCommentActivity
        Intent add = new Intent(this, AddCommentActivity.class);

        //pass username, and activityCode to AddCommentActivity
        add.putExtra("username", username);
        add.putExtra("activityCode", activityCode);

        //start AddCommentActivity
        startActivity(add);
    }

    public void backToMenu_btn_Click(View v) {
        //declare intent to start MainActivity
        Intent back = new Intent(this, MainActivity.class);

        //passes username to MainActivity, starts MainActivity, then closes CommentsActivity
        back.putExtra("username", username);
        startActivity(back);
        finish();
    }

}

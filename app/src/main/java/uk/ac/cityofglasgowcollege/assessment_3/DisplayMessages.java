package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by 30055525 on 23/02/2018.
 */

public class DisplayMessages {

    //takes in alert title, alert message, and context of activity running the method
    public void displayAlert(String title, String msgIn, Context currentActivity) {
        //display alert dialogue with correct title & msg
        new AlertDialog.Builder(currentActivity)
                .setTitle(title).setMessage(msgIn)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no extra functionality required when ok button is clicked
                    }
                })
                .show();
    }

    //takes in context of activity running toasty, and the message to be displayed
    public void toasty(Context currentActivity, String msgIn) {
        Toast t = Toast.makeText(currentActivity, msgIn, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }
}

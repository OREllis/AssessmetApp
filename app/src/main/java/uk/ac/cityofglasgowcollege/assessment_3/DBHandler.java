package uk.ac.cityofglasgowcollege.assessment_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by 30055525 on 19/02/2018.
 */

public class DBHandler extends SQLiteOpenHelper {
    //FINAL variables to hold the names of the two TABLES
    public static final String TABLE_USERS = "users",
            TABLE_CommentS = "Comments";

    //FINAL variables to hold the COLUMNS for the USERS table
    public static final String COLUMN_ID = "id",
            COLUMN_USERNAME = "username",
            COLUMN_PASSWORD = "password",
            COLUMN_EMAIL = "email",
            COLUMN_AGE = "age";

    //FINAL variables to hold the COLUMNS for the CommentS table (reuse "username" from above)
    public static final String COLUMN_PRODUCT_ID = "productId",
            COLUMN_COMMENTS = "CommentComments",
            COLUMN_COMMENT_RATING = "CommentRating";


    //constructor for the DBHandler class - takes in a parameter defining the context
    public DBHandler(Context context) {
        //UsersDB is the name of the database which will be created
        //null is to say use the standard SQL Cursor behaviours   //1 is the version number of the DB
        super(context, "UsersDB", null, 1);
    }


    //Create tables for database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //build a string which contains the necessary SQL to create the USERS table
        final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT," +
                COLUMN_EMAIL + " TEXT," + COLUMN_AGE + " INT" +
                ")";

        //execute the sql by calling the execSQL method
        db.execSQL(CREATE_USERS_TABLE);


        //build a string which contains the necessary SQL to create the CommentS table
        final String CREATE_COMMENTS_TABLE = "CREATE TABLE " + TABLE_CommentS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCT_ID + " TEXT," + COLUMN_USERNAME + " TEXT," +
                COLUMN_COMMENTS + " TEXT," + COLUMN_COMMENT_RATING + " INT" +
                ")";

        //execute the sql by calling the execSQL method
        db.execSQL(CREATE_COMMENTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //we need to have this method as we inherit from SQLiteOpenHelper
        //but don't need to code it as we won't use it
    }


    //add user to users table - takes in a parameter of User
    public void addUser(final User user) {
        //content values class allows us to store all the data we wish to insert for the new user
        ContentValues values = new ContentValues();

        //call the put method to add the data we wish for a certain column
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_AGE, user.getAge());

        //get a connection to the db we setup
        SQLiteDatabase db = getWritableDatabase();

        //call the insert method to add all the data in the ContentValues object to a new row in the db
        db.insert(TABLE_USERS, null, values);

        //close the db
        db.close();
    }

    //Check if new username is already in table - takes in a parameter of username
    public boolean usernameTaken(String username) {
        //build a string which contains the necessary SQL to check if the username exists
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = '" + username + "'";

        //get a connection to the db we setup
        SQLiteDatabase db = this.getReadableDatabase();

        //declare a cursor which will be used to read the data from the database table
        Cursor cursor = db.rawQuery(query, null);

        //Carry out SQL query on username from users Table
        //Use 'Cursor' to hold any results - should be 1 result if found and 0 if not
        if (cursor.getCount() > 0) {
            //username already exists
            cursor.close();
            db.close();

            return true;
        }

        //close db & cursor
        cursor.close();
        db.close();

        return false;
    }

    //CheckLogin method confirms if username is in database and if so checks if stored password matches input
    //both checks must pass to return true - the user logged in correctly
    public boolean checkLogin(String username, String password) {
        //build a string which contains the necessary SQL to check if the username and password combo ecist
        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USERNAME + " = '" + username + "'" +
                " AND " + COLUMN_PASSWORD + " = '" + password + "'";

        //get a connection to the db we setup
        SQLiteDatabase db = this.getReadableDatabase();

        //db.execSQL("DELETE FROM "+ TABLE_USERS);
        //db.execSQL("DELETE FROM "+ TABLE_CommentS);

        //declare a cursor which will be used to read the data from the database table
        Cursor cursor = db.rawQuery(query, null);

        //declare boolean to hold result
        boolean valid;

        //Carry out SQL query on username and password from users Table
        //Use 'Cursor' to hold any results - should be 1 result if found and 0 if not
        //if login and password are in database
        valid = cursor.getCount() > 0;

        //close database & cursor
        cursor.close();
        db.close();

        //return result
        return valid;
    }

    //add Comment to Comments table - takes in a parameter of Comment
    public void addComment(Comments Comment) {
        //content values class allows us to store all the data we wish to insert for the new Comment
        ContentValues values = new ContentValues();

        //call the put method to add the data we wish for a certain column
        values.put(COLUMN_PRODUCT_ID, Comment.getProductId());
        values.put(COLUMN_USERNAME, Comment.getUsername());
        values.put(COLUMN_COMMENT_RATING, Comment.getCommentRating());
        values.put(COLUMN_COMMENTS, Comment.getComments());

        //get a connection to the db we setup
        SQLiteDatabase db = getWritableDatabase();

        //call the insert method to add all the data in the ContentValues object to a new row in the db
        db.insert(TABLE_CommentS, null, values);

        //close the db
        db.close();
    }

    //Fetch Comments left for a product - takes in the parameter ProductId
    public ArrayList<Comments> getComments(String productId) {
        //declare an array list of Comment class objects
        ArrayList<Comments> Comments = new ArrayList<>();

        //build a string which contains the necessary SQL to check if the username and password combo exist
        String query = "SELECT * FROM " + TABLE_CommentS + " WHERE " + COLUMN_PRODUCT_ID + " = '" + productId + "'";

        //get a connection to the db we setup
        SQLiteDatabase db = this.getReadableDatabase();

        //declare a cursor which will be used to read the data from the database table
        Cursor cursor = db.rawQuery(query, null);

        //Carry out SQL query on username and password from users Table
        //Use 'Cursor' to hold any results - should be 1 result if found and 0 if not
        if (cursor.getCount() > 0) {
            // for each record returned - add to Comments ArrayList

            //move to first record
            cursor.moveToFirst();

            //create new instance of class
            Comments r = new Comments();

            r.setProductId(cursor.getString(1));
            r.setUsername(cursor.getString(2));
            r.setComments(cursor.getString(3));
            r.setCommentRating(cursor.getInt(4));

            //add Comments to ArrayList
            Comments.add(r);

            //repeat task while there's records to add
            while (cursor.moveToNext()) {
                r = new Comments();

                r.setProductId(cursor.getString(1));
                r.setUsername(cursor.getString(2));
                r.setComments(cursor.getString(3));
                r.setCommentRating(cursor.getInt(4));

                //add Comments to ArrayList
                Comments.add(r);
            }

            //no more records
            cursor.close();
            db.close();

        }
        //return populated ArrayList
        return Comments;
    }
}

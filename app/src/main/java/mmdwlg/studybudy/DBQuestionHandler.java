package mmdwlg.studybudy;

import android.content.Context;
//import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This section creates the question db and will be called when the home screen fires.
 */

public class DBQuestionHandler extends SQLiteOpenHelper {

    //create and initialize some variables for the database creation
    private static final int DB_Version = 2;
    private static final String DB_Name = "UserDB.db";
    private static final String TBL_Name = "questions";
    private static final String COL_ID = "ID";
    private static final String COL_Set = "questionSet";
    private static final String COL_Question = "question";
    private static final String COL_a = "a";
    private static final String COL_b = "b";
    private static final String COL_c = "c";
    private static final String COL_d = "d";
    private static final String COL_Answer = "answer";
    private static final String COL_Category = "questionCat";

    private SQLiteDatabase db;
    //SQLiteOpenHelper db;


    //this is the constructor
    DBQuestionHandler(Context context) {

        super(context, DB_Name, null, DB_Version);

        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this is initializing the SQL string to create the table
        final String seakwull = "CREATE TABLE IF NOT EXISTS " + TBL_Name + "("
                + COL_ID + " integer primary key, "
                + COL_Set + " TEXT, "
                + COL_Question + " TEXT, "
                + COL_a + " TEXT, "
                + COL_b + " TEXT, "
                + COL_c + " TEXT, "
                + COL_d + " TEXT, "
                + COL_Answer + " TEXT, "
                + COL_Category + " TEXT);";

        //this will create the questions table!
        db.execSQL(seakwull);

        try {
            db.execSQL(seakwull);
            } catch(SQLException e) {
            Log.d("ohNo", "oh no its broken");
        }

        Log.d("msg", "table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String drop = "DROP TABLE " + TBL_Name;
        //db.execSQL(drop);
        Log.d("msg", "table dropped section");
        onCreate(db);
    }

    void addUser() {

        /*
        //test data
        Log.d("msg", "just some test data");

        String update = "DELETE FROM users";
        try {
            db.execSQL(update);
            Log.d("ok", "successfully deleted");
        } catch(SQLException e) {
            Log.d("ohNo", "delete all didnt work");
        }

        String add = "INSERT INTO users (userName, password) VALUES ('test', 'pw'), ('dw', 'pw')";
        try {
            db.execSQL(add);
            Log.d("ok", "successfully added users");
        } catch(SQLException e) {
            Log.d("ohNo", "did not add users");
        }
        */


        //
        //SQLiteDatabase db = this.getWritableDatabase();

        //String seakwull = "INSERT INTO " + TBL_Name + "(" + COL_User + ", " + COL_Pass + ") VALUES('test', 'pw');";

        //try to run it, catch the error and make a note in log
        //try {
        //    db.execSQL(seakwull);
        //} catch(SQLException e) {
        //    Log.d("ohNo", "oh no its broken");
        //}

        //craft an SQL query to select from the users table
        //String whoa = "SELECT * FROM users";
        //define a cursor to hold the results
        //Cursor c;
        //run the query and pass the results to the cursor
        //c = db.rawQuery(whoa, null);

        //move to the first spot in the cursor. This will just log the first userName and pass for now
        //c.moveToFirst();
        //String index = c.getString(0);
        //String theUser = c.getString(1);
        //String thePass = c.getString(2);

        //here it is logging it to reference for login
        //Log.d("testData", "index: " + index + ", user name: " + theUser + ", password: " + thePass);

        //close that cursor
        //c.close();

    }

}

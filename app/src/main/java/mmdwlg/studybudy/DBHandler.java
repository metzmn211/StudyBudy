package mmdwlg.studybudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Danny on 2/17/2018.
 * This section creates the db and will be called when the app starts up as well as handling various login functions.
 */

public class DBHandler extends SQLiteOpenHelper {

    //create and initialize some variables for the database creation
    private static final int DB_Version = 1;
    private static final String DB_Name = "UserDB.db";
    private static final String TBL_Name = "users";
    private static final String COL_ID = "ID";
    private static final String COL_User = "userName";
    private static final String COL_Pass = "password";

    //this is the constructor
    public DBHandler(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    // <('o'<) <('o')> (>'o')> <('o')> <('o'<) <('o')> (>'o')> <('o')> <('o'<) <('o')> (>'o')>
    // This is the onCreate function. This is only called if the database has never been created.
    // This can be problematic with editing databases on the project so we can work around it.
    // <('o'<) <('o')> (>'o')> <('o')> <('o'<) <('o')> (>'o')> <('o')> <('o'<) <('o')> (>'o')>
    @Override
    public void onCreate(SQLiteDatabase db) {
        //this is initializing the SQL string to create the table
        final String seakwull = "CREATE TABLE IF NOT EXISTS " + TBL_Name + "("
                + COL_ID + " integer primary key, " + COL_User + " TEXT, " + COL_Pass + " TEXT);";

        //this will create the users table!
        try {
            db.execSQL(seakwull);
            Log.d("msg", "table created");
        } catch(SQLException e) {
            Log.d("ohNo", "table not created");
        }

        //this will add in our test username and password.
        String addTestUser = "INSERT INTO " + TBL_Name + "(" + COL_User + ", " + COL_Pass + ") VALUES('test', 'pw');";

        //try to run it to add in the user, catch the error and make a note in log
        try {
            db.execSQL(addTestUser);
            Log.d("msg", "users added into table");
        } catch(SQLException e) {
            Log.d("ohNo", "oh no its broken");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String drop = "DROP TABLE " + TBL_Name;
        //db.execSQL(drop);
        Log.d("msg", "table dropped section");
        onCreate(db);
    }

    public void addUser() {
        //this will add a dummy userName and pass
        SQLiteDatabase db = this.getWritableDatabase();

        String seakwull = "INSERT INTO " + TBL_Name + "(" + COL_User + ", " + COL_Pass + ") VALUES('test', 'pw');";

        //try to run it, catch the error and make a note in log
        try {
            db.execSQL(seakwull);
            Log.d("msg", "users added into table");
        } catch(SQLException e) {
            Log.d("ohNo", "oh no its broken");
        }

        //craft an SQL query to select from the users table
        String whoa = "SELECT * FROM users";
        //define a cursor to hold the results
        Cursor c;
        //run the query and pass the results to the cursor
        c = db.rawQuery(whoa, null);

        //move to the first spot in the cursor. This will just log the first userName and pass for now
        c.moveToFirst();
        String index = c.getString(0);
        String theUser = c.getString(1);
        String thePass = c.getString(2);

        //here it is logging it to reference for login
        Log.d("testData", "index: " + index + ", user name: " + theUser + ", password: " + thePass);

        //close that cursor
        c.close();

    }

    //this is for the authentication, values that are typed in will be passed here and referenced against the db of users.
    public boolean authUser(String uN, String pW) {
        //get a db to read from.
        SQLiteDatabase db = this.getReadableDatabase();
        boolean auth = false;

        //craft an SQL query to select from the users table
        String whoa = "SELECT * FROM users";
        //define a cursor to hold the results
        Cursor c;
        //run the query and pass the results to the cursor
        c = db.rawQuery(whoa, null);

        //move to the first spot in the cursor. This will just log the first userName and pass for now
        c.moveToFirst();

        Integer i = 0;

        while (i < c.getCount()) {
            String theUser = c.getString(1);
            String thePass = c.getString(2);

            //this will check to see if they match and if so, return true to validate the login. false if not
            if ((uN.equals(theUser)) && (pW.equals(thePass))) {
                auth = true;
                break;
            } else {
                auth = false;
                i++;
                c.moveToNext();
            }
        }

        //close it up...
        c.close();

        //return our value
        return auth;

    }

}

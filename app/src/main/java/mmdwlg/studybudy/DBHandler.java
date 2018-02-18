package mmdwlg.studybudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Danny on 2/17/2018.
 * This section creates the db and will be called when the app starts up
 */

public class DBHandler extends SQLiteOpenHelper {

    //create and initialize some variables for the database creation
    private static final int DB_Version = 1;
    private static final String DB_Name = "UserDB.db";
    private static final String TBL_Name = "users";
    private static final String COL_ID = "_ID";
    private static final String COL_User = "userName";
    private static final String COL_Pass = "password";

    //this is the constructor
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_Name, factory, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this is initializing the SQL string to create the table
        final String seakwull = "CREATE TABLE " + TBL_Name + "("
                + COL_ID + " integer primary key, " + COL_User + " TEXT, " + COL_Pass + " TEXT);";

        //this will create the users table!
        db.execSQL(seakwull);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

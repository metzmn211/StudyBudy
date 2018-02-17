package sql;

/**
 * Created by louis on 2/16/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.User;

import java.util.ArrayList;
import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USER + "("
            + COLUMN_USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + "TEXT,"
            + COLUMN_USER_EMAIL + "TEXT," + COLUMN_USER_PASSWORD + "TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS" + TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, )
    }
}

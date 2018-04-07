package mmdwlg.studybudy;

import android.content.Context;
//import android.database.Cursor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This section creates the question db and will be called when the home screen fires.
 */

public class DBQuestionHandler extends SQLiteOpenHelper {

    //create and initialize some variables for the database creation
    private static final int DB_Version = 1;
    private static final String DB_Name = "QuestionDB.db";
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
    private static final String COL_theUser = "userName";

    private SQLiteDatabase db;
    //SQLiteOpenHelper db;


    //this is the constructor
    DBQuestionHandler(Context context) {

        super(context, DB_Name, null, DB_Version);

        //db = getWritableDatabase();

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
                + COL_Category + " TEXT, "
                + COL_theUser + " TEXT);";

        //this will create the questions table!
        db.execSQL(seakwull);

        try {
            db.execSQL(seakwull);
            Log.d("msg", "table created");
        } catch(SQLException e) {
            Log.d("ohNo", "oh no its broken");
        }

        //this will populate the table with some dummy data for testing
        String add = "INSERT INTO questions (questionSet, question, a, b, c, d, answer, questionCat, userName) " +
                "VALUES " +
                "('testData', 'What is 1 + 1?', '2', '42', '0', '6', 'a', 'math', 'test'), " +
                "('testData', 'What is the meaning of life, the universe, and everything?', 'ignorance = bliss', '42', 'programming', 'ale', 'b', 'philosophy', 'test'), " +
                "('testData', 'Are there still more questions?', 'yes', 'no', 'unsure', 'huh?', 'c', 'general', 'test'), " +
                "('testData', 'Last question', 'Great', '42', '2', 'This is not a question?', 'd', 'trick', 'test')";

        try {
            db.execSQL(add);
            Log.d("ok", "successfully added questions");
        } catch(SQLException e) {
            Log.d("ohNo", "oh it didnt work");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE " + TBL_Name;
        db.execSQL(drop);
        Log.d("msg", "table dropped section");
        onCreate(db);
    }

    public void getQuestionSets() {


        //this calls the database
        SQLiteDatabase db = this.getReadableDatabase();

        //define a cursor to query and hold the results
        Cursor setCurse = db.query(true, "questions", new String[] { "questionSet" }, null, null, null, null, null, null);

        //this loop will go through the cursor and pull all the question set names
        //this logic can be modified and built into takeQuiz.java - we can return the cursor and pull the
        //sets, or build them into an array here and return the array - that might be easier
        if (setCurse.moveToFirst()) {
            do {
                String set = setCurse.getString(setCurse.getColumnIndex("questionSet"));
                //this just logs the results so you can see what we are working with.
                //there is only one set in the test data now
                Log.d("theSet", set);
            } while (setCurse.moveToNext());
        }
        //close the cursor and free the resources.
        setCurse.close();

    }

    //this section will take the clicked on value as displayed in getQuestionSets() - the quiz set the users wishes to take
    //it will then run a query and return all the questions, answers, etc.
    public void takeTheQuiz(String theSet) {

        SQLiteDatabase db = this.getReadableDatabase();

        //NOTE - this variable is hard coded in for now - we need to get it from the view
        //it is hardcoded with nonsense in takequiz.java function call
        theSet = "testData";

        Cursor getQuestions = db.query(
                "questions", //pull from table questions
                null, //this will return all columns
                "questionSet = '" + theSet + "'", //conditional statement
                null,
                null,
                null,
                null,
                null);

        //similar to the above function, this will look through the cursor.
        //this is a counter to organize the questions
        Integer i = 1;
        if (getQuestions.moveToFirst()) {
            do {
                //this grabs the question and logs it for you to see
                String question = getQuestions.getString(getQuestions.getColumnIndex("question"));
                Log.d("question" + i, question);

                //these next few sections grab the potential answers
                String a = getQuestions.getString(getQuestions.getColumnIndex("a"));
                Log.d("a" + i, a);

                String b = getQuestions.getString(getQuestions.getColumnIndex("b"));
                Log.d("b" + i, b);

                String c = getQuestions.getString(getQuestions.getColumnIndex("c"));
                Log.d("c" + i, c);

                String d = getQuestions.getString(getQuestions.getColumnIndex("d"));
                Log.d("d" + i, d);

                //this is the right answer
                String answer = getQuestions.getString(getQuestions.getColumnIndex("answer"));
                Log.d("answer" + i, answer);

                //future functionality - this is the question category for sorting
                String cat = getQuestions.getString(getQuestions.getColumnIndex("questionCat"));
                Log.d("cat" + i, cat);

                //future functionality - this is the user column so we can sort questions by users
                String user = getQuestions.getString(getQuestions.getColumnIndex("userName"));
                Log.d("user" + i, user);

                i++;

            } while (getQuestions.moveToNext());
        }



        getQuestions.close();
    }

    //this is a dummy section for now that is called on load for the menu. It has queries to add in questions and holds some others
    //for later reference.
    //DONT CALL THIS - just a holder for queries in the future.
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

        String add = "INSERT INTO questions (questionSet, question, a, b, c, d, answer, questionCat) " +
                "VALUES " +
                "('testData', 'What is 1 + 1?', '2', '42', '0', '6', 'a', 'math'), " +
                "('testData', 'What is the meaning of life, the universe, and everything?', 'ignorance = bliss', '42', 'programming', 'ale', 'b', 'philosophy'), " +
                "('testData', 'Are there still more questions?', 'yes', 'no', 'unsure', 'huh?', 'c', 'general'), " +
                "('testData', 'Last question', 'Great', '42', '2', 'This is not a question?', 'd', 'trick')";

        try {
            db.execSQL(add);
            Log.d("ok", "successfully added questions");
        } catch(SQLException e) {
            Log.d("ohNo", "oh it didnt work");
        }


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

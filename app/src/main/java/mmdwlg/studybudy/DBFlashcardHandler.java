package mmdwlg.studybudy;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Danny on 4/7/2018.
 */

public class DBFlashcardHandler extends SQLiteOpenHelper{

    private static final int DB_Version = 1;
    private static final String DB_Name = "FlashcardDB.db";
    private static final String TBL_Name = "flashcards";
    private static final String COL_ID = "ID";
    private static final String COL_flashSet = "flashcardSet";
    private static final String COL_ques = "flashQ";
    private static final String COL_ans = "flashA";
    private static final String COL_Category = "flashCat";
    private static final String COL_theUser = "userName";

    private SQLiteDatabase db;

    //this is the constructor
    DBFlashcardHandler(Context context) {

        //database creation in this land
        super(context, DB_Name, null, DB_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //this is initializing the SQL string to create the table
        final String seakwull = "CREATE TABLE IF NOT EXISTS " + TBL_Name + "("
                + COL_ID + " integer primary key, "
                + COL_flashSet + " TEXT, "
                + COL_ques + " TEXT, "
                + COL_ans + " TEXT, "
                + COL_Category + " TEXT, "
                + COL_theUser + " TEXT);";

        //this will create the flashcard table! fire!
        db.execSQL(seakwull);

        try {
            db.execSQL(seakwull);
            Log.d("msg", "flashcard table created");
        } catch(SQLException e) {
            Log.d("ohNo", "oh no flashcard table is broken");
        }

        //this will populate the table with some dummy data for testing
        String addFlash = "INSERT INTO flashcards (flashcardSet, flashQ, flashA, flashCat, userName) " +
                "VALUES " +
                "('testData', 'What is 1 + 1?', '2', 'math', 'test'), " +
                "('testData', 'What is the meaning of life, the universe, and everything?', '42', 'philosophy', 'test'), " +
                "('testData', 'Are there still more questions?', 'unsure', 'general', 'test'), " +
                "('testData', 'Last question', 'This is not a question?', 'trick', 'test');";

        try {
            db.execSQL(addFlash);
            Log.d("ok", "successfully added flashcards");
        } catch(SQLException e) {
            Log.d("ohNo", "oh no adding flashcards didn't work...");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String drop = "DROP TABLE " + TBL_Name;
        //db.execSQL(drop);
        Log.d("msg", "flashcard on upgrade section");
        //onCreate(db);
    }

    public void getFlashcardSets() {


        //this calls the database
        SQLiteDatabase db = this.getReadableDatabase();

        //define a cursor to query and hold the results
        Cursor setCurse = db.query(true, TBL_Name, new String[] { "flashcardSet" }, null, null, null, null, null, null);

        //this loop will go through the cursor and pull all the question set names
        //this logic can be modified and built into takeQuiz.java - we can return the cursor and pull the
        //sets, or build them into an array here and return the array - that might be easier
        if (setCurse.moveToFirst()) {
            do {
                String set = setCurse.getString(setCurse.getColumnIndex("flashcardSet"));
                //this just logs the results so you can see what we are working with.
                //there is only one set in the test data now
                Log.d("flashcard Set", set);
            } while (setCurse.moveToNext());
        }
        //close the cursor and free the resources.
        setCurse.close();

    }

    //this section will take the clicked on value as displayed in getFlashCardSets() - the flashcard
    //set the users wishes to take
    //it will then run a query and return all the questions and answers for the flashcards
    public void viewTheFlashcards(String theSet) {

        SQLiteDatabase db = this.getReadableDatabase();

        //NOTE - this variable is hard coded in for now - we need to get it from the view
        //it is hardcoded with nonsense in ViewFlashcards.java function call
        theSet = "testData";

        Cursor getFlashcards = db.query(
                "flashcards", //pull from table questions
                null, //this will return all columns
                "flashcardSet = '" + theSet + "'", //conditional statement
                null,
                null,
                null,
                null,
                null);

        //similar to the above function, this will look through the cursor.
        //this is a counter to organize the questions
        Integer i = 1;
        if (getFlashcards.moveToFirst()) {
            do {
                //this grabs the question and logs it for you to see
                String question = getFlashcards.getString(getFlashcards.getColumnIndex("flashQ"));
                Log.d("flashcard question" + i, question);

                //these next few sections grab the potential answers
                String answer = getFlashcards.getString(getFlashcards.getColumnIndex("flashA"));
                Log.d("flashcard answer" + i, answer);

                //future functionality - this is the question category for sorting
                String cat = getFlashcards.getString(getFlashcards.getColumnIndex("flashCat"));
                Log.d("flashcard category" + i, cat);

                //future functionality - this is the user column so we can sort questions by users
                String user = getFlashcards.getString(getFlashcards.getColumnIndex("userName"));
                Log.d("user" + i, user);

                i++;

            } while (getFlashcards.moveToNext());
        }

        getFlashcards.close();
    }

}

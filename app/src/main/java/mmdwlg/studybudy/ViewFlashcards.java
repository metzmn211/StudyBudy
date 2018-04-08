package mmdwlg.studybudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewFlashcards extends AppCompatActivity {

    //create the flashcard handler
    DBFlashcardHandler dbf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flashcards);

        //call and create the class
        dbf = new DBFlashcardHandler(this);

        //this section will use the function to pull the information on all the quiz "sets"
        //I am thinking a set is a related set of questions, so we would want to display each of them
        //e.g., "Test 1", "Midterm", etc.
        dbf.getFlashcardSets();

        //dbf.takeTheQuiz("testDeleteMeLater");

        // Button to return to home screen
        Button goToHomeScreen = (Button) findViewById(R.id.home);
        goToHomeScreen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(ViewFlashcards.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }
}

package mmdwlg.studybudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TakeQuiz extends AppCompatActivity {

    //declare the class
    DBQuestionHandler dbq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        //call and create the class
        dbq = new DBQuestionHandler(this);

        //this section will use the function to pull the information on all the quiz "sets"
        //I am thinking a set is a related set of questions, so we would want to display each of them
        //e.g., "Test 1", "Midterm", etc.
        dbq.getQuestionSets();

        dbq.takeTheQuiz("testDeleteMeLater");

        // Button to return to home screen
        Button goToHomeScreen = (Button) findViewById(R.id.home);
        goToHomeScreen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(TakeQuiz.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }
}

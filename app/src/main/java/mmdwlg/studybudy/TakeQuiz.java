package mmdwlg.studybudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TakeQuiz extends AppCompatActivity {

    //create the question handler
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
        //button to return to home screen
        Button buttonHome = findViewById(R.id.home);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        // button to take quiz
        Button buttonTakeQuiz = findViewById(R.id.takeQuiz);
        buttonTakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        Spinner dropdown = findViewById(R.id.spinner_category);
        String[] categories = new String[]{"Math", "English", "Science"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        dropdown.setAdapter(adapter);
    }



    private void goHome() {
        Intent intent = new Intent(TakeQuiz.this, HomeScreen.class);
        startActivity(intent);
    }

    private void startQuiz() {
        Intent intent = new Intent(TakeQuiz.this, QuizActivity.class);
        startActivity(intent);

    }
}


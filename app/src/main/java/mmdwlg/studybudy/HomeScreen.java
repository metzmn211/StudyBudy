package mmdwlg.studybudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    DBQuestionHandler dbq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //this creates a new instance of the class
        dbq = new DBQuestionHandler(this);
        //this calls a random function to load data into the db. i am commenting it out for now.
        //dbq.addUser();

      // Button to go to Take Quiz Screen
      Button goToTakeQuiz = (Button) findViewById(R.id.takeQuiz);
      goToTakeQuiz.setOnClickListener(new View.OnClickListener(){

          @Override
          public void onClick(View view){
              Intent intent = new Intent(HomeScreen.this, TakeQuiz.class);
              startActivity(intent);
          }
      });
        // Button to go to Edit Quiz Screen
        Button goToEditQuiz = (Button) findViewById(R.id.editQuiz);
        goToEditQuiz.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, EditQuiz.class);
                startActivity(intent);
            }
        });
        // Button to go to View Flashcards Screen
        Button goToViewFlashcards = (Button) findViewById(R.id.viewCards);
        goToViewFlashcards.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, ViewFlashcards.class);
                startActivity(intent);
            }
        });
        // Button to go to Edit Flashcards Screen
        Button goToEditFlashcards = (Button) findViewById(R.id.editCards);
        goToEditFlashcards.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, EditFlashcards.class);
                startActivity(intent);
            }
        });
        // Return to Login Screen
        Button goToMainActivity = (Button) findViewById(R.id.logout);
        goToMainActivity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

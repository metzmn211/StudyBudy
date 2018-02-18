package mmdwlg.studybudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz);
        // Button to return to home screen
        Button goToHomeScreen = (Button) findViewById(R.id.home);
        goToHomeScreen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(EditQuiz.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }
}

package mmdwlg.studybudy;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class FlashCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);
        TextView answer = FlashCardsActivity.this.findViewById(R.id.text_view_answer);
        answer.setVisibility(View.INVISIBLE);

        //when button is pressed it makes the answer text visible
        Button button = (Button) findViewById(R.id.button_show_answer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView answer = FlashCardsActivity.this.findViewById(R.id.text_view_answer);
                answer.setVisibility(View.VISIBLE);
            }
        });
    }

}

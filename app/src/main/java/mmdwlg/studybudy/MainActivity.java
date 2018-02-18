package mmdwlg.studybudy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button loginButton;
    EditText un, pw;
    TextView tx1;
    int counter = 3;
    //this creates an instance of DBHandler for later use
    DBHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is a temporary button bypassing authentication
        Button goToHomeScreen = (Button) findViewById(R.id.byPass);
        goToHomeScreen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(intent);
            }
        });

        //instantiate the db
        dbh = new DBHandler(this);
        //this is a temporary function to add in a dummy username and pass
        //this can be built onto later to create new users.
        dbh.addUser();

        //this defines the login button.
        loginButton = findViewById(R.id.button);
        un = findViewById(R.id.editText);
        pw = findViewById(R.id.editText2);

        tx1 = findViewById(R.id.textView4);
        tx1.setVisibility(View.GONE);

        //this is the click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call the function to validate the credentials
                boolean auth = dbh.authUser(un.getText().toString(), pw.getText().toString());

                //if it is true we shall travel to the home screen
                if (auth == true) {
                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(intent);
                //if not an error is displayed...
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Snap username or password is incorrect!", Toast.LENGTH_LONG).show();
                }
                //if (ed1.getText().toString().equals("admin") &&
                //        ed2.getText().toString().equals("admin")) {
                    //Toast.makeText(getApplicationContext(),
                      //      "Redirecting...", Toast.LENGTH_SHORT).show();
                /*} else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                            tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }

}*/
            }
        });
/*
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }*/

} }


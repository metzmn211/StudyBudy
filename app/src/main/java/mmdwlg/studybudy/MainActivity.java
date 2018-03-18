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
    //this creates an instance of DBHandler for later use
    DBHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate the db
        dbh = new DBHandler(this);
        //this is a temporary function to add in a dummy username and pass
        //this can be built onto later to create new users.
        dbh.addUser();

        //this defines the login button.
        loginButton = findViewById(R.id.button);
        un = findViewById(R.id.editText);
        pw = findViewById(R.id.editText2);


        //this is the click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call the function to validate the credentials
                boolean auth = dbh.authUser(un.getText().toString(), pw.getText().toString());

                //if it is true we shall travel to the home screen
                if (auth) {
                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(intent);
                //if not an error is displayed...
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Snap username or password is incorrect!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}


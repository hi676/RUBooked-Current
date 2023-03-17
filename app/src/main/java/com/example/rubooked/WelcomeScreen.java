package com.example.rubooked;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

public class WelcomeScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        Button signUp = (Button)findViewById(R.id.SignUp);
        signUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (signUp.isPressed()) {
                    Intent intent = new Intent(WelcomeScreen.this, SignUp.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        Button logIn = (Button)findViewById(R.id.Login);
        logIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (logIn.isPressed()) {
                    Intent intent = new Intent(WelcomeScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
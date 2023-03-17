package com.example.rubooked;

import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import com.google.firebase.FirebaseApp;

public class Login extends AppCompatActivity {

    private ImageView backButton;
    private EditText email, password;
    private Button continueButton, forgotpasswordButton;
    private String emailValue, passwordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
//        mAuth = FirebaseAuth.getInstance();
        backButton = findViewById(R.id.backButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        continueButton = findViewById(R.id.continueButton);
        backButton = findViewById(R.id.backButton);
        forgotpasswordButton = findViewById(R.id.forgotpassword);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });

        forgotpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });
        nextScreen();
    }

    private void nextScreen(){
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValue = email.getText().toString();
                passwordValue = password.getText().toString();
                if (emailValue.matches("") || passwordValue.matches("")){
                    Toast toast = Toast.makeText(Login.this, "Please enter all fields", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                else if (!emailValue.contains("@scarletmail.rutgers.edu")){
                    Toast toast = Toast.makeText(Login.this, "Please enter scarletmail email", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                else {
                    Intent intent = new Intent(Login.this, WelcomeScreen.class);
                    startActivity(intent);
                }
            }
        });
    }
}
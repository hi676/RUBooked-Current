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
public class SignUp extends AppCompatActivity {

    private ImageView backButton;
    private EditText email;
    private EditText password;
    private EditText confirmpassword;
    private Button continueButton;
    private String emailValue, passwordValue, confirmPasswordValue;
//    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
//        mAuth = FirebaseAuth.getInstance();
        backButton = findViewById(R.id.backButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        continueButton = findViewById(R.id.continueButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, WelcomeScreen.class);
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
                confirmPasswordValue = confirmpassword.getText().toString();
                String echo = passwordValue + confirmPasswordValue;
                if (emailValue.matches("") || passwordValue.matches("") || confirmPasswordValue.matches("")){
                    Toast toast = Toast.makeText(SignUp.this, "Please enter all fields", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                else if (!passwordValue.equals(confirmPasswordValue)){
                    Toast toast = Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                else if (!emailValue.contains("@scarletmail.rutgers.edu")){
                    Toast toast = Toast.makeText(SignUp.this, "Please enter scarletmail email", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                else{
                    Intent intent = new Intent(SignUp.this, WelcomeScreen.class);
                    startActivity(intent);
                }
            }
        });
    }
}
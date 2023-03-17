package com.example.rubooked;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ForgotPassword extends AppCompatActivity {

    private ImageView backButton;
    private Button submitButton;
    private EditText typeEmail;
    private String emailValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.submit);
        typeEmail = findViewById(R.id.typeEmail);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, Login.class);
                startActivity(intent);
            }
        });
        nextScreen();
    }

    private void nextScreen(){
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValue = typeEmail.getText().toString();
                if (emailValue.matches("")){
                    Toast toast = Toast.makeText(ForgotPassword.this, "Please enter email", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                else if (!emailValue.contains("@scarletmail.rutgers.edu")){
                    Toast toast = Toast.makeText(ForgotPassword.this, "Please enter scarletmail email", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                else {
                    Intent intent = new Intent(ForgotPassword.this, WelcomeScreen.class);
                    startActivity(intent);
                }
            }
        });
    }
}
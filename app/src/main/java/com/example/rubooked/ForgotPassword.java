package com.example.rubooked;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class ForgotPassword extends AppCompatActivity {

    private ImageView backButton;
    private Button submitButton;
    private EditText typeEmail;
    private TextView email;
    private String emailValue, passwordValue, confirmPasswordValue;
    private boolean bool = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.submit);
        typeEmail = findViewById(R.id.typeEmail);
        email = findViewById(R.id.email);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, Login.class);
                startActivity(intent);
            }
        });
        nextScreen();
//        enterPassword();
//        confirmPassword(passwordValue);
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
                else{
                    enterPassword();
                }
            }
        });
    }

    private void enterPassword(){
        typeEmail.setText("");
        typeEmail.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        email.setText("Enter New Password");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordValue = typeEmail.getText().toString();
                if (passwordValue.matches("")){
                    Toast toast = Toast.makeText(ForgotPassword.this, "Please enter password", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    enterPassword();
                }
                else{
                    confirmPassword(passwordValue);
                }
            }
        });
    }

    private void confirmPassword(String password){
        typeEmail.setText("");
        email.setText("Confirm Password");
        typeEmail.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPasswordValue = typeEmail.getText().toString();
                if (confirmPasswordValue.matches("")){
                    Toast toast = Toast.makeText(ForgotPassword.this, "Please enter password", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                if (!password.matches(confirmPasswordValue)){
                    Toast toast = Toast.makeText(ForgotPassword.this, "Please match password", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                }
                else{
                    Intent intent = new Intent(ForgotPassword.this, WelcomeScreen.class);
                    startActivity(intent);
                }
            }
        });
    }
}
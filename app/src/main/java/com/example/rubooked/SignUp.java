package com.example.rubooked;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class SignUp extends AppCompatActivity {

    private ImageView backButton;
    private EditText email;
    private EditText password;
    private EditText confirmpassword;
    private Button continueButton;
    private String emailValue, passwordValue, confirmPasswordValue;
    //    private User user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
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

    private void nextScreen() {
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValue = email.getText().toString();
                passwordValue = password.getText().toString();
                confirmPasswordValue = confirmpassword.getText().toString();
                String echo = passwordValue + confirmPasswordValue;
                if (emailValue.matches("") || passwordValue.matches("") || confirmPasswordValue.matches("")) {
                    Toast toast = Toast.makeText(SignUp.this, "Please enter all fields", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                } else if (!passwordValue.equals(confirmPasswordValue)) {
                    Toast toast = Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                } else if (!emailValue.contains("@scarletmail.rutgers.edu")) {
                    Toast toast = Toast.makeText(SignUp.this, "Please enter scarletmail email", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    nextScreen();
                } else {
                    mAuth
                            .createUserWithEmailAndPassword(emailValue, passwordValue)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),
                                                        "Registration successful!",
                                                        Toast.LENGTH_LONG)
                                                .show();

                                        // if the user created intent to login activity
                                        Intent intent
                                                = new Intent(SignUp.this,
                                                WelcomeScreen.class);
                                        startActivity(intent);
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        user.sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(getApplicationContext(),
                                                                            "Verification email sent",
                                                                            Toast.LENGTH_LONG)
                                                                    .show();
                                                        }
                                                    }
                                                });
                                    } else {

                                        // Registration failed
                                        FirebaseException e = (FirebaseException) task.getException();
                                        Toast.makeText(
                                                        getApplicationContext(),
                                                        "Registration failed!!"
                                                                + " Please try again later" + e.getMessage(),
                                                        Toast.LENGTH_LONG)
                                                .show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
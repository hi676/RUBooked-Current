package com.example.rubooked;

import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button continueButton;
    private String emailValue, passwordValue;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        ImageView backButton = findViewById(R.id.backButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        continueButton = findViewById(R.id.continueButton);
        backButton = findViewById(R.id.backButton);
        Button forgotpasswordButton = findViewById(R.id.forgotpassword);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, WelcomeScreen.class);
            startActivity(intent);
        });

        forgotpasswordButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, WelcomeScreen.class);
            startActivity(intent);
        });
        nextScreen();
    }

    private void nextScreen() {
        continueButton.setOnClickListener(v -> {
            emailValue = email.getText().toString();
            passwordValue = password.getText().toString();
            if (emailValue.matches("") || passwordValue.matches("")) {
                Toast toast = Toast.makeText(Login.this, "Please enter all fields", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                nextScreen();
            } else if (!emailValue.contains("@scarletmail.rutgers.edu")) {
                Toast toast = Toast.makeText(Login.this, "Please enter scarletmail email", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                nextScreen();
            } else {
                FirebaseUser user = mAuth.getCurrentUser();
                user.reload();
                // signin existing user
                mAuth.signInWithEmailAndPassword(emailValue, passwordValue)
                        .addOnCompleteListener(
                                task -> {
                                    boolean emailVerified = user.isEmailVerified();
                                    if (emailVerified) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(),
                                                            "Login successful!!",
                                                            Toast.LENGTH_LONG)
                                                    .show();

                                            Intent intent = new Intent(Login.this, WelcomeScreen.class);
                                            startActivity(intent);
                                        } else {
                                            FirebaseException e = (FirebaseException) task.getException();
                                            // sign-in failed
                                            Toast.makeText(getApplicationContext(),
                                                            "Login failed: " + e.getMessage(),
                                                            Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),
                                                        "Please verify email before proceeding",
                                                        Toast.LENGTH_LONG)
                                                .show();
                                    }
                                    });
            }
        });
    }
}
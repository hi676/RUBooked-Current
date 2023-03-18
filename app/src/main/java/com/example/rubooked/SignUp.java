package com.example.rubooked;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

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
        ImageView backButton = findViewById(R.id.backButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        continueButton = findViewById(R.id.continueButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, WelcomeScreen.class);
            startActivity(intent);
        });
        nextScreen();
    }

    private void nextScreen() {
        continueButton.setOnClickListener(v -> {
            emailValue = email.getText().toString();
            passwordValue = password.getText().toString();
            confirmPasswordValue = confirmpassword.getText().toString();
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
                        .addOnCompleteListener(task -> {
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
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(),
                                                                "Verification email sent",
                                                                Toast.LENGTH_LONG)
                                                        .show();
                                            }
                                        });
                            } else {

                                // Registration failed
                                FirebaseException e = (FirebaseException) task.getException();
                                Toast.makeText(
                                                getApplicationContext(),
                                                "Registration failed: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
            }
        });
    }
}
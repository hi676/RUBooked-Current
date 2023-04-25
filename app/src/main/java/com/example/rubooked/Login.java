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

public class Login extends AppCompatActivity {

    private EditText email, password;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.backButton);
        ImageView backButton;
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Button continueButton = findViewById(R.id.continueButton);
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
        continueButton.setOnClickListener(v -> nextScreen());
    }

    private void nextScreen() {
        user = mAuth.getCurrentUser();
        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();
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
                mAuth.signInWithEmailAndPassword(emailValue, passwordValue)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                boolean emailVerified = user.isEmailVerified();
                                if (emailVerified) {
                                    if (task.isSuccessful()) {
//                                        Toast t = Toast.makeText(getApplicationContext(),
//                                                "Login successful!!",
//                                                Toast.LENGTH_LONG);
//                                        t.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
//                                        t.show();

                                        Intent intent = new Intent(Login.this, Register.class);
                                        startActivity(intent);
                                    } else {
                                        FirebaseException e = (FirebaseException) task.getException();
                                        // sign-in failed
                                        Toast.makeText(getApplicationContext(),
                                                        "Login failed: " + e.getMessage(),
                                                        Toast.LENGTH_LONG)
                                                .show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                                    "Please verify email before proceeding",
                                                    Toast.LENGTH_LONG)
                                            .show();
                                }
                            } else {
                                FirebaseException e = (FirebaseException) task.getException();
                                Toast.makeText(getApplicationContext(), "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                //                mAuth.getCurrentUser().linkWithCredential(credential)
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
////                                if (task.isSuccessful()) {
////                                    Log.d(TAG, "linkWithCredential:success");
////                                    FirebaseUser user = task.getResult().getUser();
////                                } else {
////                                    Log.w(TAG, "linkWithCredential:failure", task.getException());
////                                    Toast.makeText(Login.this, "Authentication failed.",
////                                            Toast.LENGTH_SHORT).show();
////                                }
//                            }
//                        });
//                Toast.makeText(getApplicationContext(),
//                                                            user.toString(),
//                                                            Toast.LENGTH_LONG)
//                                                    .show();
//                user.reload();
                // signin existing user
            }
    }

}
////                }
////            }
//        });
//    }
//}
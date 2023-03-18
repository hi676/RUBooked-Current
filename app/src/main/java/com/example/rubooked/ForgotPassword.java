package com.example.rubooked;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private Button submitButton;
    private EditText typeEmail;
    private String emailValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ImageView backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.submit);
        typeEmail = findViewById(R.id.typeEmail);
        findViewById(R.id.email);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPassword.this, Login.class);
            startActivity(intent);
        });
        nextScreen();
    }

    private void nextScreen(){
        submitButton.setOnClickListener(v -> {
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
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.fetchSignInMethodsForEmail(emailValue)
                        .addOnCompleteListener(task -> {

                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                            if (isNewUser) {
                                Toast toast = Toast.makeText(ForgotPassword.this, "Email not registered", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                                toast.show();
                            } else {
                                auth.sendPasswordResetEmail(emailValue)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Toast toast = Toast.makeText(ForgotPassword.this, "Password reset email sent", Toast.LENGTH_SHORT);
                                                toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
                                                toast.show();
                                                Intent intent = new Intent(ForgotPassword.this,
                                                        WelcomeScreen.class);
                                                startActivity(intent);
                                            }
                                        });
                            }

                        });
            }
        });
    }

}
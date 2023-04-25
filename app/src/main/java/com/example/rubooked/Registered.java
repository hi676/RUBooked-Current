package com.example.rubooked;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Registered extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);
        Button bookAnotherRoom = findViewById(R.id.RegisteredButton);
        bookAnotherRoom.setOnClickListener(v -> {
            Intent intent = new Intent(Registered.this, Register.class);
            startActivity(intent);
        });
        Button logOut = findViewById(R.id.logOutButton);
        logOut.setOnClickListener(v -> {
            Intent intent = new Intent(Registered.this, WelcomeScreen.class);
            startActivity(intent);
        });
    }
}

package com.example.rubooked;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_process);
        Button confirm = findViewById(R.id.Confirm);
        confirm.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, Registered.class);
            startActivity(intent);
        });
    }
}

package com.example.aacevedo6716.elzo_pos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        setTitle(name+"'s POS");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
    }
}

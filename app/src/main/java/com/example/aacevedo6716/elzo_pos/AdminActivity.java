package com.example.aacevedo6716.elzo_pos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Recieve Information for Login
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age",-1);


        setTitle(name+"'s Manager Console");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

//        final TextView nameField = (TextView) findViewById(R.id.txtName);
//        final TextView usernameField = (TextView) findViewById(R.id.txtUserName);
//        final TextView ageField = (TextView) findViewById(R.id.txtAge);


        //Welcome Message
        String message = name + ", Welcome to Elzo POS";

//        nameField.setText(name.toString());
//        usernameField.setText(username.toString());
//        ageField.setText(age+"");


    }
}

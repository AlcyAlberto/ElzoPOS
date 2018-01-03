package com.example.aacevedo6716.elzo_pos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public String[][] Items = new String[37][5];
    private final static String Tag = "Validate Array";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Login");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etUsername = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
        final ImageButton btnInfo = (ImageButton) findViewById(R.id.btnInfo);


        for (int i=1; i < 36; i++){
            final String btnName = "ITEM "+i;


            final int finalI = i;
            //Log.d(Tag,""+finalI);

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success =  jsonResponse.getBoolean("success");

                        if (success){
                            String btnText = jsonResponse.getString("btnName");
                            int id = jsonResponse.getInt("id");
                            String name = jsonResponse.getString("name");
                            int price = jsonResponse.getInt("price");
                            String description = jsonResponse.getString("description");
                            Log.d(Tag,"id: "+id);
                            Log.d(Tag,name);
                            Log.d(Tag,"Price: "+price);
                            Log.d(Tag,description);
                            Items[id][0] = ""+id;
                            Items[id][1] = btnText;
                            Items[id][2] = name;
                            Items[id][3] = ""+price;
                            Items[id][4] = description;
                        }
                        else{
                            Items[finalI][0] = "Empty";
                            Items[finalI][1] = "Empty";
                            Items[finalI][2] = "Empty";
                            Items[finalI][3] = "Empty";
                            Items[finalI][4] = "Empty";
                        }

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };

              POSrequest posRequest = new POSrequest(btnName, responseListener);
              RequestQueue queue = Volley.newRequestQueue(this);
              queue.add(posRequest);
        }
        LoadContentClass.items = Items;


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InfoIntent = new Intent(LoginActivity.this, InfoActivity.class);
                LoginActivity.this.startActivity(InfoIntent);
            }
        });

        //click on Register text
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

//        LoadContent("Section1");
//        LoadContent("Section2");
//        LoadContent("Section3");







        // Click on login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success =  jsonResponse.getBoolean("success");
                            if (success){
                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");
                                String username = jsonResponse.getString("username");
                                String password = jsonResponse.getString("password");
                                String management = jsonResponse.getString("management");

                                if(management.equals("M")){
                                    Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("username", username);
                                    intent.putExtra("age", age);
                                    intent.putExtra("password", password);
                                    LoginActivity.this.startActivity(intent);
                                }else if(management.equals("N")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Account has not been granted permission, Please talk to Manager ")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }else if(management.equals("W")){
                                    //LoadContentClass.Items = Items;
                                    Intent intent = new Intent(LoginActivity.this,POSActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("username", username);
                                    intent.putExtra("age", age);
                                    intent.putExtra("password", password);
                                   //intent.putExtra("Items", Items);
                                    LoginActivity.this.startActivity(intent);
                                }

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed : Incorrect Credentials ")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(username,password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

    }

//    public void LoadContent(String Table){
//        final String[][] Items = new String[25][5];
//        final String Section = Table;
//        for (int i=1; i < 12; i++){
//            final String btnName = "ITEM "+i;
//
//
//            final int finalI = i;
//            //Log.d(Tag,""+finalI);
//
//            Response.Listener<String> responseListener = new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        JSONObject jsonResponse = new JSONObject(response);
//                        boolean success =  jsonResponse.getBoolean("success");
//
//                        if (success){
//                            String btnText = jsonResponse.getString("btnName");
//                            int id = jsonResponse.getInt("id");
//                            String name = jsonResponse.getString("name");
//                            int price = jsonResponse.getInt("price");
//                            String description = jsonResponse.getString("description");
//                            Log.d(Tag,"id: "+id);
//                            Log.d(Tag,name);
//                            Log.d(Tag,"Price: "+price);
//                            Log.d(Tag,description);
//                            Items[id][0] = ""+id;
//                            Items[id][1] = btnText;
//                            Items[id][2] = name;
//                            Items[id][3] = ""+price;
//                            Items[id][4] = description;
//                        }
//                        else{
//                            Items[finalI][0] = "Empty";
//                            Items[finalI][1] = "Empty";
//                            Items[finalI][2] = "Empty";
//                            Items[finalI][3] = "Empty";
//                            Items[finalI][4] = "Empty";
//                        }
//
//                    }catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            };
//
//            POSrequest posRequest = new POSrequest(btnName,Section, responseListener);
//            RequestQueue queue = Volley.newRequestQueue(this);
//            queue.add(posRequest);
//        }
//
//        if (Table == "Section1")
//        {
//            LoadContentClass.Section1 = Items;
//        }
//        else if(Table == "Section2")
//        {
//            LoadContentClass.Section2 = Items;
//        }
//        else
//        {
//            LoadContentClass.Section3 = Items;
//        }
//
//    }

}



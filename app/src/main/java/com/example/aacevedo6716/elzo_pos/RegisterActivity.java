package com.example.aacevedo6716.elzo_pos;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {

    int progress = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Register");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //seekBar for Age
        final SeekBar seekbar = (SeekBar) findViewById(R.id.sbAge);
        seekbar.setMax(60);
        seekbar.setProgress(progress);
        final TextView tvAge = (TextView) findViewById(R.id.tvAge);
        tvAge.setTextColor(Color.GREEN);

        //Text field for name username and password
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView etwarning = (TextView) findViewById(R.id.tvWarning);
        etwarning.setTextColor(Color.RED);


        //button for register confirm
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final int age = Integer.parseInt(tvAge.getText().toString());
                final String management = "N";

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonOResponse = new JSONObject(response);
                            boolean success = jsonOResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Successful, Waiting on Review")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                RegisterActivity.this.startActivity(intent);
                                            }
                                        })
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed, Please fill out all fields")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, management, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });


        tvAge.setText("" + progress);
        //tvAge.setTextSize(progress);

        //Age bar : turns red and disables button when under 18, green and enables when 18 and over.
        // If under 18 warning text appears.
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //progress = 1;
                progress = seekBar.getProgress();
                tvAge.setText("" + progress);

                if (progress < 18) {
                    tvAge.setTextColor(Color.RED);
                    btnRegister.setClickable(false);
                    btnRegister.setTextColor(Color.GRAY);
                    etwarning.setText("MUST BE OVER 18");
                    etwarning.setTextSize(25);
                }
                if (progress >= 18) {
                    tvAge.setTextColor(Color.GREEN);
                    btnRegister.setClickable(true);
                    btnRegister.setTextColor(Color.BLACK);
                    etwarning.setText("");
                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

}

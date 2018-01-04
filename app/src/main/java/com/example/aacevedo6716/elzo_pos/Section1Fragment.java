package com.example.aacevedo6716.elzo_pos;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alcy on 2017-10-10.
 */

public class Section1Fragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "Section1Fragment";

    private static final int[] idArray = {R.id.btnItem1,R.id.btnItem2,R.id.btnItem3,R.id.btnItem4,R.id.btnItem5,R.id.btnItem6,R.id.btnItem7,
            R.id.btnItem8,R.id.btnItem9,R.id.btnItem10,R.id.btnItem11,R.id.btnItem12};

    public Button[] buttons = new Button[idArray.length];





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.section1,container,false);


        for (int i=0; i < idArray.length; i++){
            //int y = i+1;
            buttons[i] = view.findViewById(idArray[i]);
            buttons[i].setText(LoadContentClass.items[i][2]);
            buttons[i].setOnClickListener(this);
            if (buttons[i].getText() == "Empty" || buttons[i].getText() == ""){
                buttons[i].setVisibility(view.GONE);
            }
        }
//        btnItem1.setOnClickListener(this);
//        btnItem2.setOnClickListener(this);
        return view;
    }



    @Override
    public void onClick(View view) {

        final Button x = (Button)view;
        final String name = (String) x.getText();
        String price;

        for (int i = 0; i < 36; i++) {
           if (LoadContentClass.items[i][2] == name){
                price = LoadContentClass.items[i][3];

                String Display = name+" :     "+price;
                LoadContentClass.Ordered.add(Display);
                return;
            }


        }



//        Response.Listener<String> responseListener = new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonResponse = new JSONObject(response);
//                    boolean success =  jsonResponse.getBoolean("success");
//
//                    if (success){
//                        String name = jsonResponse.getString("name");
//                        int price = jsonResponse.getInt("price");
//                        String description = jsonResponse.getString("description");
//                        x.setText(name);
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                        builder.setMessage(description + " Price: "+price+"")
//                                .setNegativeButton("OK", null)
//                                .create()
//                                .show();
//                    }
//                    else{
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                        builder.setMessage("Its working")
//                                .setNegativeButton("OK", null)
//                                .create()
//                                .show();
//                    }
//
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//
//        OrderRequest orderRequest = new OrderRequest(name, responseListener);
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        queue.add(orderRequest);
//
    }



}

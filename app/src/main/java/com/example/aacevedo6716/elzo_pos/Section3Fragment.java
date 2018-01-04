package com.example.aacevedo6716.elzo_pos;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alcy on 2017-10-10.
 */

public class Section3Fragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "Section3Fragment";

    private static final int[] idArray3 = {R.id.btnItem25,R.id.btnItem26,R.id.btnItem27,R.id.btnItem28,R.id.btnItem29,R.id.btnItem30,R.id.btnItem31,
            R.id.btnItem32,R.id.btnItem33,R.id.btnItem34,R.id.btnItem35,R.id.btnItem36};

    private Button[] buttons3 = new Button[idArray3.length];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.section3,container,false);

        for (int i=0; i < idArray3.length; i++){
            int y = i+25;
            buttons3[i] = view.findViewById(idArray3[i]);
            buttons3[i].setText(LoadContentClass.items[y][2]);
            buttons3[i].setOnClickListener(this);
            buttons3[i].setVisibility(view.VISIBLE);
            if (buttons3[i].getText() == "Empty" || buttons3[i].getText() == "")
            {
                buttons3[i].setVisibility(view.GONE);
            }
        }


        return view;
    }

    @Override
    public void onClick(View view) {

        final Button x = (Button)view;
        final String name = (String) x.getText();

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success =  jsonResponse.getBoolean("success");

                    if (success){
                        String name = jsonResponse.getString("name");
                        int price = jsonResponse.getInt("price");
                        String description = jsonResponse.getString("description");
                        x.setText(name);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage(description + " Price: "+price+"")
                                .setNegativeButton("OK", null)
                                .create()
                                .show();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Its working")
                                .setNegativeButton("OK", null)
                                .create()
                                .show();
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        OrderRequest orderRequest = new OrderRequest(name, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(orderRequest);
    }
}

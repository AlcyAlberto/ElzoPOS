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

public class Section2Fragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "Section2Fragment";


    private static final int[] idArray2 = {R.id.btnItem13,R.id.btnItem14,R.id.btnItem15,R.id.btnItem16,R.id.btnItem17,R.id.btnItem18,R.id.btnItem19,
            R.id.btnItem20,R.id.btnItem21,R.id.btnItem22,R.id.btnItem23,R.id.btnItem24};

    private Button[] buttons2 = new Button[idArray2.length];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.section2,container,false);


        for (int i=0; i < idArray2.length; i++){
            int y = i+13;
            buttons2[i] = view.findViewById(idArray2[i]);
            buttons2[i].setText(LoadContentClass.items[y][2]);
            buttons2[i].setOnClickListener(this);
            buttons2[i].setVisibility(view.VISIBLE);
            if (buttons2[i].getText() == "Empty" || buttons2[i].getText() == "")
            {
                buttons2[i].setVisibility(view.GONE);
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

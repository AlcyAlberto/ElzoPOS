package com.example.aacevedo6716.elzo_pos;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alcy on 2017-10-17.
 */

public class POSrequest extends StringRequest{
    private static final String Pos_Request_URL = "https://elzopos.000webhostapp.com/ItemLookup.php";
    private Map<String, String> params;
    public POSrequest(String btnName, Response.Listener<String>listener){
        super(Request.Method.POST, Pos_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("btnName",btnName);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

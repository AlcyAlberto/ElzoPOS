package com.example.aacevedo6716.elzo_pos;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alcy on 2017-10-17.
 */

public class OrderRequest extends StringRequest{
    private static final String Pos_Request_URL = "https://elzopos.000webhostapp.com/OrderLookup.php";
    private Map<String, String> params;

    public OrderRequest(String Name, Response.Listener<String>listener){
        super(Request.Method.POST, Pos_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("Name",Name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

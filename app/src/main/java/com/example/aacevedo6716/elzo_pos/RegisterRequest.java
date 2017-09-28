package com.example.aacevedo6716.elzo_pos;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alcy on 2017-09-23.
 */

public class RegisterRequest extends StringRequest{

    private static final String Register_Request_URL = "https://elzopos.000webhostapp.com/Register.php";
    private Map<String, String>params;

    public RegisterRequest(String name, String username, int age, String password, String management, Response.Listener<String>listener){
        super(Method.POST, Register_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("password",password);
        params.put("age",age+"");
        params.put("management",management);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

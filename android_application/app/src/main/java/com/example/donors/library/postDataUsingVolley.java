package com.example.donors.library;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import java.util.HashMap;
import java.util.Map;

public class postDataUsingVolley {

    public void postDataUsingVolley(Context context,String uid ,String email ,String number ,String locale,String blood) {
        // url to post our data
        String url = "https://127.0.0.1:5000/donate";


        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, "Data added to API", Toast.LENGTH_SHORT).show();

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("idDonor",uid);
                params.put("disease",email);
                //params.put("number",number);
                params.put("bloodGroup",blood);
                params.put("address",locale);

                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}



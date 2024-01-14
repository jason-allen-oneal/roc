package com.jasononeal.roc;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jasononeal.RoC;

import org.json.JSONObject;

import java.util.HashMap;

public class API {
    RequestQueue queue = Queue.getInstance(RoC.getAppContext()).getRequestQueue();
    String apiUrl = "http://89.116.44.100:3000/api";
    HashMap<String, String> params = new HashMap<>();

    interface CallbackInterface {
        void onRequestComplete(JSONObject response);
    }

    public void addParams(String k, String v) {
        params.put(k, v);
    }

    public void post(String target, CallbackInterface cb){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl+target, null, cb::onRequestComplete, error -> {
            // TODO: Handle error
            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                Toast.makeText(RoC.getAppContext(),
                        RoC.getAppContext().getString(R.string.no_network),
                        Toast.LENGTH_LONG).show();
            } else if (error instanceof AuthFailureError) {
                Toast.makeText(RoC.getAppContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            } else if (error instanceof ServerError) {
                Toast.makeText(RoC.getAppContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            } else if (error instanceof NetworkError) {
                Toast.makeText(RoC.getAppContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            } else if (error instanceof ParseError) {
                Toast.makeText(RoC.getAppContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }
}

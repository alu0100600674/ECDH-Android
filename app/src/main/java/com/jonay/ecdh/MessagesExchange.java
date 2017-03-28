package com.jonay.ecdh;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

/**
 * Created by jonay on 22/03/17.
 */

public class MessagesExchange {

    public static void sendTestMessage(RequestQueue queue, String url, String msg, Context ctx, String appid){
        final Context ctx2 = ctx;

        HashMap<String, String> params = new HashMap<>();
        params.put("appid", appid);
        params.put("msg", msg);

        // Request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GlobalClass globalData = (GlobalClass) ctx2;


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        queue.add(jsonObjectRequest);
    }

}

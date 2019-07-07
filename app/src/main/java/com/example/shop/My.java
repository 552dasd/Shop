package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class My extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Context ctx = this;
        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
        int id = sp.getInt("STRING_KEY", 1);
        String url ="http://nanhai655.cn:8080/shop/shopping";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                "{ \"commodityid\": "+id+" }  ",
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {


                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley",error.toString());
                    }
                }
        );
        MyApplication.addRequest(jsonObjectRequest,"MainActivity");

        finish();

    }
    public void dingdan(int userid){
        String url ="http://nanhai655.cn:8080/shop/Add_the_order?User="+userid;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                "",
                new Response.Listener< JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley",error.toString());
                    }
                }
        );
        MyApplication.addRequest(jsonObjectRequest,"MainActivity");

    }
    public void code(String co){
        String url ="http://nanhai655.cn:8080/shop/QRcode?code="+co;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                "",
                new Response.Listener< JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley",error.toString());
                    }
                }
        );
        MyApplication.addRequest(jsonObjectRequest,"MainActivity");
    }
}

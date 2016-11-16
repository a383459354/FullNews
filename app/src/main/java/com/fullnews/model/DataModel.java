package com.fullnews.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fullnews.presenter.GetDataListener;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class DataModel {

    Context context;

    public DataModel(Context context){
        this.context=context;
    }

    /**
     * 获取数据
     */
    public void getNewslist(String url, final GetDataListener getDataListener){
        RequestQueue requestQueue =Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response----",response.toString());
                        getDataListener.success(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getDataListener.failure(error.toString());
                    }
                }
        ) ;
        requestQueue.add(jsonObjectRequest);
    }

    public void getVideolist(String url, final GetDataListener getDataListener){
        RequestQueue requestQueue =Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response",response.toString());
                        getDataListener.success(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getDataListener.failure(error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * POST请求
     */
    public void getBookData(String url, final GetDataListener getDataListener){
        RequestQueue rq=Volley.newRequestQueue(context);
        StringRequest sr=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getDataListener.success(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getDataListener.failure(error.toString());
                    }
                });
    }
}

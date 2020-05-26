package fr.cooktail.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import fr.cooktail.interfaces.IResult;

public class Requests {

    IResult mResultCallback = null;
    Context mContext;

    public Requests(IResult resultCallback, Context context){
        mResultCallback = resultCallback;
        mContext = context;
    }


    public Object getCategories() {
        final String baseURL = " https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list";

        RequestQueue queue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObj = new JsonObjectRequest(
                Request.Method.GET,
                baseURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(mResultCallback != null)
                            mResultCallback.notifySuccess("GET", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (mResultCallback != null)
                            mResultCallback.notifyError("GET", error);
                    }

        });

        queue.add(jsonObj);

        return jsonObj;
    }

    public Object getIngredients() {
        final String baseURL = "https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list";

        RequestQueue queue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObj = new JsonObjectRequest(
                Request.Method.GET,
                baseURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(mResultCallback != null)
                            mResultCallback.notifySuccess("GET", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mResultCallback != null)
                    mResultCallback.notifyError("GET", error);
            }
        });

        queue.add(jsonObj);
        return jsonObj;
    }

}

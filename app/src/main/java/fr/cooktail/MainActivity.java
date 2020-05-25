package fr.cooktail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String baseURL = " https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list";

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest categoriesRequest = new JsonObjectRequest(
                Request.Method.GET,
                baseURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Rest response", response.toString());

                        try {
                            JSONArray categories = response.getJSONArray("drinks");

                            if (categories.length() > 0) {
                                for(int countItem = 0; countItem<categories.length(); countItem++) {
                                    JSONObject categoriesObject = categories.getJSONObject(countItem);

                                    String name = categoriesObject.isNull("strCategory") ? "" : categoriesObject.optString("strCategory");
                                    Log.d("categories", name);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Rest response", error.toString());
                    }
                }
        );

        requestQueue.add(categoriesRequest);
    }
}

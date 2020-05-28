package fr.cooktail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.cooktail.interfaces.IResult;
import fr.cooktail.services.Requests;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static fr.cooktail.util.Menu.cooktailOnOptionsItemSelected;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    IResult mResultCallback = null;
    Requests requests;

    ListView categoriesList;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVolleyCallback();
        requests = new Requests(mResultCallback,this);
        requests.getCategories();


        categoriesList = (ListView) findViewById(R.id.categories_list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        categoriesList.setAdapter(adapter);

    }

    void initVolleyCallback(){
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType,JSONObject response) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON get" + response);

                ArrayList<String> categoriesList = new ArrayList<>();

                try {
                    JSONArray jsonArray = response.getJSONArray("drinks");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        String name = jo.getString("strCategory");
                        categoriesList.add(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.clear();
                adapter.addAll(categoriesList);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void notifyError(String requestType,VolleyError error) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON post" + "That didn't work!");
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return cooktailOnOptionsItemSelected(item , this) ;
    }
}

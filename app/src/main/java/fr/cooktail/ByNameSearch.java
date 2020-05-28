package fr.cooktail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.cooktail.interfaces.IResult;
import fr.cooktail.services.Requests;

import static fr.cooktail.util.Menu.cooktailOnOptionsItemSelected;

public class ByNameSearch extends AppCompatActivity {

    SearchView searchView;

    private String TAG = "ByNameSearch Activity";

    IResult mResultCallback = null;
    Requests requests;

    ArrayAdapter adapter;
    ListView cocktailList;

    String cocktail_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.by_name_search_layout);

        this.searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                Log.d("SEARCH VIEW", name);

                initVolleyCallback();
                requests = new Requests(mResultCallback,ByNameSearch.this);
                requests.getByName(name);

                cocktailList = findViewById(R.id.cocktail_list);
                adapter = new ArrayAdapter(ByNameSearch.this, android.R.layout.simple_list_item_1);
                cocktailList.setAdapter(adapter);

                cocktailList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Intent intent = new Intent(ByNameSearch.this,ByNameResult.class);
                        //intent.putExtra("COCKTAIL_ID", cocktail_id);
                        //startActivity(intent);
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                return false;
            }
        });


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

    void initVolleyCallback(){
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON get" + response);

                ArrayList<String> cocktailList = new ArrayList<>();

                try {
                    JSONArray jsonArray = response.getJSONArray("drinks");
                    if(jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            String cocktail_name = jo.getString("strDrink");
                            cocktail_id = jo.getString("idDrink");
                            cocktailList.add(cocktail_name);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.clear();
                adapter.addAll(cocktailList);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON post" + "That didn't work!");
            }
        };
    }

}

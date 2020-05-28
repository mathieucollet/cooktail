package fr.cooktail;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.cooktail.adapters.IngredientsAdapter;
import fr.cooktail.interfaces.IResult;
import fr.cooktail.services.Requests;

import static fr.cooktail.util.Menu.cooktailOnOptionsItemSelected;

public class ByIngredientSearch extends AppCompatActivity {
    private RecyclerView recyclerView;
    private IngredientsAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    IResult mResultCallback = null;
    Requests requests;

    SearchView ET_typeIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.by_ingredient_search_layout);

        this.recyclerView = (RecyclerView) findViewById(R.id.RV_ingredientsList);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new IngredientsAdapter();
        recyclerView.setAdapter(mAdapter);


        initVolleyCallback();
        requests = new Requests(mResultCallback,this);
        requests.getIngredients();


    this.ET_typeIngredient = findViewById(R.id.ET_typeIngredient);
    this.ET_typeIngredient.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            mAdapter.sort_byName(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            mAdapter.sort_byName(newText);
            synchronized (mAdapter) {
            mAdapter.notifyDataSetChanged();
            }
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
        Log.d("persoLOG", "_ByIngredientSearch_ : "+ " (start volley) "+ recyclerView.getAdapter().getItemCount() );
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                ArrayList<String> ingredientsList = new ArrayList<>();

                try {
                    JSONArray jsonArray = response.getJSONArray("drinks");
                    for (int i=0  ;  i < jsonArray.length()  ;  i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        String nameIngredient = jo.getString("strIngredient1");
                        ingredientsList.add(nameIngredient);
                        mAdapter.addIngredient(nameIngredient);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mAdapter.sort_AZ();
                synchronized(mAdapter){
                    mAdapter.notify();
                }
            }
            @Override
            public void notifyError(String requestType, VolleyError error) {
                Toast.makeText(ByIngredientSearch.this, "error ! "+ requestType , Toast.LENGTH_LONG).show();
            }
        };
    }
}

package fr.cooktail;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import fr.cooktail.adapters.CocktailsAdapter;
import fr.cooktail.adapters.IngredientsAdapter;
import fr.cooktail.interfaces.IResult;
import fr.cooktail.models.Drink;
import fr.cooktail.services.Requests;

import static fr.cooktail.util.Menu.cooktailOnOptionsItemSelected;

public class ByIngredientResult extends AppCompatActivity {
    String theIngredient ;

    private RecyclerView recyclerView;
    private CocktailsAdapter mAdapter;

    IResult mResultCallback = null;
    Requests requests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.by_ingredient_result_layout);

        // RV_coktailsList

        this.theIngredient = this.getExtraString(savedInstanceState , "theIngredient");

        this.recyclerView = findViewById(R.id.RV_coktailsList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.mAdapter = new CocktailsAdapter();
        recyclerView.setAdapter(mAdapter);

        initVolleyCallback();
        requests = new Requests(mResultCallback,this);
        requests.getCocktailsByIngredient(this.theIngredient);
    }

    private String getExtraString(Bundle aSavedInstanceState, String anExtra) {
        String ret;

        if (aSavedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) ret = null;
            else ret = extras.getString(anExtra);
        } else
            ret = (String) aSavedInstanceState.getSerializable(anExtra);

        return ret ;
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
                ArrayList<Drink> retList = new ArrayList<>();

                try {
                    JSONArray jsonArray = response.getJSONArray("drinks");
                    for (int i=0  ;  i < jsonArray.length()  ;  i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        String nameDrink = jo.getString("strDrink");
                        String thumbDrink = jo.getString("strDrinkThumb");
                        int idDrink = jo.getInt("idDrink");
                        Drink newDrink = new Drink(idDrink, nameDrink , thumbDrink) ;
                        retList.add(newDrink);
                        mAdapter.addDrink(newDrink);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("persoLOG", "ByIngredientResult : " + retList.toString() );

//                mAdapter.a
            }
            @Override
            public void notifyError(String requestType, VolleyError error) {
                Toast.makeText(ByIngredientResult.this, "error ! "+ requestType , Toast.LENGTH_LONG).show();
            }
        };
    }
}

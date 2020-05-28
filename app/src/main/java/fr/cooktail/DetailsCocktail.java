package fr.cooktail;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.cooktail.interfaces.IResult;
import fr.cooktail.models.Drink;
import fr.cooktail.models.DrinkDetailed;
import fr.cooktail.services.Requests;

import static fr.cooktail.util.Menu.cooktailOnOptionsItemSelected;

public class DetailsCocktail extends AppCompatActivity  {
    int theIdCocktail ;
    boolean isRandom ;
    DrinkDetailed theDrink ;

    private IResult mResultCallback = null;
    private Requests requests;

    TextView TV_contentName ;
    TextView TV_contentCategory ;
    TextView TV_contentAlchoholic ;
    TextView TV_contentGlassType ;
    TextView TV_contentTags ;
    Button BTN_like;

    ListView LV_ingredientsList ;
    ArrayAdapter<String> theArrayAdapterIngredients;
    ListView LV_measuresList ;
    ArrayAdapter<String> theArrayAdapterMeasures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        this.TV_contentName = findViewById(R.id.TV_contentName);
        this.TV_contentCategory = findViewById(R.id.TV_contentCategory);
        this.TV_contentAlchoholic = findViewById(R.id.TV_contentAlchoholic);
        this.TV_contentGlassType = findViewById(R.id.TV_contentGlassType);
        this.TV_contentTags = findViewById(R.id.TV_contentTags);
        this.BTN_like = findViewById(R.id.BTN_like);


        this.theDrink = new DrinkDetailed();
        this.theDrink.ingredients = new ArrayList<String>();
        this.theDrink.measures = new ArrayList<String>();
        //
        this.LV_ingredientsList = findViewById(R.id.LV_ingredientsList);
        this.theArrayAdapterIngredients = new ArrayAdapter<>(this, R.layout.simple_list_item, this.theDrink.ingredients);
        this.LV_ingredientsList.setAdapter(theArrayAdapterIngredients);
        //
        this.LV_measuresList = findViewById(R.id.LV_measuresList);
        this.theArrayAdapterMeasures = new ArrayAdapter<>(this, R.layout.simple_list_item, this.theDrink.measures);
        this.LV_measuresList.setAdapter(theArrayAdapterMeasures);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                this.theIdCocktail = 0 ; // { "drinks":null }
                this.isRandom = false ;
            }
            else {
                this.theIdCocktail = extras.getInt("idCocktail");
                this.isRandom = extras.getBoolean("isRandom");
            }

        } else {
            this.theIdCocktail = (int) savedInstanceState.getSerializable("idCocktail");
            this.isRandom = (boolean) savedInstanceState.getSerializable("isRandom");
        }


    //TODO: fetch the cocktail details with a fonction from J
        //TODO: fill up the textViewS details

        BTN_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(DetailsCocktail.this);
//                db.addDrink();
            }
        });
        initVolleyCallback();
        requests = new Requests(mResultCallback,this);
        requests.getDrinkById(this.theIdCocktail);
        if (this.isRandom)
            requests.getDrinkByRandom();
         else
            requests.getDrinkById(this.theIdCocktail);

    }


    void initVolleyCallback(){
        mResultCallback = new IResult() {

            @Override
            public void notifySuccess(String requestType, JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("drinks");
                    for (int i=0  ;  i < jsonArray.length()  ;  i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);

                        ArrayList<String> ingredients = new ArrayList<>();
                        for(int j=1  ;  j<=15  ;  j++){
                            if ( jo.getString("strIngredient"+j) != "null" )
                            ingredients.add(jo.getString("strIngredient"+j));
                        }

                        ArrayList<String> measures = new ArrayList<>();
                        for(int j=1  ;  j<=15  ;  j++)
                            if ( jo.getString("strMeasure"+j) != "null" )
                            measures.add(jo.getString("strMeasure"+j));

                        theDrink.setIdDrink(jo.getInt("idDrink"));
                        theDrink.setStrDrink(jo.getString("strDrink"));
                        theDrink.setStrTags(jo.getString("strTags"));
                        theDrink.setStrCategory(jo.getString("strCategory"));
                        theDrink.setStrAlcoholic(jo.getString("strAlcoholic"));
                        theDrink.setStrGlass(jo.getString("strGlass"));
                        theDrink.setStrInstructions(jo.getString("strInstructions"));
                        theDrink.setStrDrinkThumb(jo.getString("strDrinkThumb"));
                        theDrink.setIngredients(ingredients);
                        theDrink.setMeasures(measures);
                    }
                    TV_contentName.setText(String.valueOf(theDrink.strDrink));
                    TV_contentCategory.setText(theDrink.strCategory);
                    TV_contentAlchoholic.setText(theDrink.strAlcoholic);
                    TV_contentGlassType.setText(theDrink.strGlass);
                    TV_contentTags.setText(theDrink.strTags);


                    synchronized(theArrayAdapterIngredients){
                    theArrayAdapterIngredients.clear();
                        for (String ing : theDrink.ingredients) {
                            theArrayAdapterIngredients.add(ing);
                        }
                        theArrayAdapterIngredients.notifyDataSetChanged();
                    }
                    synchronized(theArrayAdapterMeasures){
                        theArrayAdapterMeasures.clear();
                        for (String ing : theDrink.measures) {
                            theArrayAdapterMeasures.add(ing);
                        }
                        theArrayAdapterMeasures.notifyDataSetChanged();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void notifyError(String requestType, VolleyError error) {
                Toast.makeText(DetailsCocktail.this, "error ! "+ requestType , Toast.LENGTH_LONG).show();
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

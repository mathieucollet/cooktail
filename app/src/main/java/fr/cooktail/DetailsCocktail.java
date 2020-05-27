package fr.cooktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static fr.cooktail.util.Menu.cooktailOnOptionsItemSelected;

public class DetailsCocktail extends AppCompatActivity  {
    int theIdCocktail ;

    TextView TV_contentName ;
    TextView TV_contentCategory ;
    TextView TV_contentAlchoholic ;
    TextView TV_contentGlassType ;
    TextView TV_contentTags ;
    TextView TV_contentInstructions ;
    TextView TV_contentIngredients ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        this.TV_contentName = findViewById(R.id.TV_contentName);
        this.TV_contentCategory = findViewById(R.id.TV_contentCategory);
        this.TV_contentAlchoholic = findViewById(R.id.TV_contentAlchoholic);
        this.TV_contentGlassType = findViewById(R.id.TV_contentGlassType);
        this.TV_contentTags = findViewById(R.id.TV_contentTags);
        this.TV_contentInstructions = findViewById(R.id.TV_contentInstructions);
        this.TV_contentIngredients = findViewById(R.id.TV_contentIngredients);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null)
                this.theIdCocktail = 0 ; // { "drinks":null }
            else
                this.theIdCocktail = extras.getInt("idCocktail");

        } else {
            this.theIdCocktail = (int) savedInstanceState.getSerializable("idCocktail");
        }

        this.TV_contentName.setText(String.valueOf( this.theIdCocktail) );

        //TODO: fetch the cocktail details with a fonction from J
        //TODO: fill up the textViewS details

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

package fr.cooktail.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.cooktail.ByIngredientResult;
import fr.cooktail.DetailsCocktail;
import fr.cooktail.R;

import java.util.Collections;
import java.util.Comparator;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {
    private ArrayList<String> ingredientsList ;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public Button TV_itemIngredient;
        public MyViewHolder(Button v) {
            super(v);
            TV_itemIngredient = v;
        }
    }


    public IngredientsAdapter(ArrayList<String> data) {
        this.ingredientsList = data ;
    }
    public IngredientsAdapter() {
        this.ingredientsList = new ArrayList<String>();
    }

    @NonNull
    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final Button aButt = (Button) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_button_layout, parent, false);

        aButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(parent.getContext(), ByIngredientResult.class);
                newIntent.putExtra("theIngredient", aButt.getText().toString());

                Log.d("persoLOG", "_IngredientAdapter_ : "+newIntent.getExtras().toString()) ;

                parent.getContext().startActivity( newIntent  );
            }
        });

        MyViewHolder vh = new MyViewHolder(aButt);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.TV_itemIngredient.setText(ingredientsList.get(position));
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (ingredientsList != null) ret = ingredientsList.size();
        return ret;
    }

    public void addIngredient(String newIngredient) {
        this.ingredientsList.add(newIngredient);
    }

    public void sort_AZ() {
        Collections.sort(this.ingredientsList);
    }

    /**
     * sort the ArrayList putting in first the ingredients matching the research
     * @param searchString
     */
    public void sort_byName(String searchString) {
       for( int i=0  ;  i <= this.ingredientsList.size() -1  ;  i++) {
           String ing = this.ingredientsList.get(i);

           if( ing.toLowerCase().contains(searchString.toLowerCase()) ) {
               this.ingredientsList.remove(i) ;
               this.ingredientsList.add(0 , ing);
           }
       }
    }

}

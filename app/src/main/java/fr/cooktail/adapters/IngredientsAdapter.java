package fr.cooktail.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.cooktail.R;

import java.util.Collections;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {
    private ArrayList<String> ingredientsList ;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView TV_itemIngredient;
        public MyViewHolder(TextView v) {
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
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
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
     * TODO: create sort_byName()
     * TODO: use it in the field of the Activity, each time the value changes
     * @param searchString
     */
    public void sort_byName(String searchString) {}

}

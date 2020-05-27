package fr.cooktail.adapters;

import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

import fr.cooktail.ByIngredientResult;
import fr.cooktail.DetailsCocktail;
import fr.cooktail.R;
import fr.cooktail.models.Drink;

public class CocktailsAdapter extends RecyclerView.Adapter<CocktailsAdapter.MyViewHolder> {
    private ArrayList<Drink> theDrinkList ;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //        public Button TV_itemIngredient;
        public ConstraintLayout CL_drinkCard_Layout;
        public TextView TV_drinkName;
        public ImageView IV_drinkThumb;
        public int idDrink;
        public MyViewHolder(ConstraintLayout aCL, TextView aTV, ImageView anIV) {
            super(aCL);
            this.CL_drinkCard_Layout = aCL;
            this.TV_drinkName = aTV;
            this.IV_drinkThumb = anIV;
        }
    }

    public CocktailsAdapter(ArrayList<Drink> data) {
        this.theDrinkList = data ;
    }
    public CocktailsAdapter() {
        this.theDrinkList = new ArrayList<Drink>();
    }

    @NonNull
    @Override
    public CocktailsAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        final ConstraintLayout newCL = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_card_layout, parent, false);
        final TextView newTV = (TextView) newCL.getViewById(R.id.TV_nameDrink);
        ImageView newIV = (ImageView) newCL.getViewById(R.id.IV_drinkThumb);

        newCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();

                Intent newIntent = new Intent(parent.getContext(), DetailsCocktail.class);
                newIntent.putExtra("idCocktail", theDrinkList.get(position).idDrink);

                Log.d("persoLOG", "CocktailAdapter-setOnClickListener : "+ newIntent.getExtras() ) ;

                parent.getContext().startActivity( newIntent  );
            }
        });

        MyViewHolder myVH = new MyViewHolder(newCL, newTV, newIV);
        return myVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Drink currentDrink = theDrinkList.get(position);
        holder.TV_drinkName.setText(currentDrink.strDrink) ;
//        holder.IV_drinkThumb.setImageURI(Uri.parse(currentDrink.strDrinkThumb));
        holder.idDrink = currentDrink.idDrink;

        holder.CL_drinkCard_Layout.setTag(position);
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (theDrinkList != null) ret = theDrinkList.size();
        return ret;
    }

    public void addDrink(Drink newDrink) {
        this.theDrinkList.add(newDrink);
    }

//    public void sort_AZ() {
//        Collections.sort(this.theDrinkList);
//    }
//
//    /**
//     * sort the ArrayList putting in first the ingredients matching the research
//     * TO//DO: create sort_byName()
//     * TO//DO: use it in the field of the Activity, each time the value changes
//     * @param searchString
//     */
//    public void sort_byName(String searchString) {}

}

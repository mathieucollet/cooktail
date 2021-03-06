package fr.cooktail.util;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import fr.cooktail.About;
import fr.cooktail.ByNameSearch;
import fr.cooktail.ByIngredientSearch;
import fr.cooktail.DetailsCocktail;
import fr.cooktail.Favorites;
import fr.cooktail.R;


public abstract class Menu {

    /**
     * This method aims to factorise the onOptionsItemSelected() method in View classes because they use the same menu.
     * @param item the same param as onOptionsItemSelected
     * @param currentPackageContext should be the current class that invokes the function .this (for example :  TheCurrentClass.this )
     */
    public static boolean cooktailOnOptionsItemSelected(@NonNull MenuItem item , Context currentPackageContext) {
//        boolean ret ;
        boolean ret = false ;
        Intent newIntent;

        switch (item.getItemId()) {
            case R.id.It_FARandom:
                newIntent = new Intent(currentPackageContext, DetailsCocktail.class);
                newIntent.putExtra("isRandom", true);
                currentPackageContext.startActivity(newIntent);
                ret = true;
                break;
            case R.id.It_FAFavorites:
                newIntent = new Intent(currentPackageContext, Favorites.class);
                currentPackageContext.startActivity(newIntent);
                ret = true;
                break;
            case R.id.It_About:
                newIntent = new Intent(currentPackageContext, About.class);
                currentPackageContext.startActivity(newIntent);
                ret = true;
                break;
            case R.id.It_SBName:
                newIntent = new Intent(currentPackageContext, ByNameSearch.class);
                currentPackageContext.startActivity(newIntent);
                ret = true;
                break;
            case R.id.It_SBIngredient:
                newIntent = new Intent(currentPackageContext, ByIngredientSearch.class);
                currentPackageContext.startActivity(newIntent);
                ret = true;
                break;
//            default:
//                ret = currentPackageContext.super.onOptionsItemSelected(item);
//                break;
        }
        return ret;
    }

}

package fr.cooktail.models;

import java.util.ArrayList;

public class DrinkDetailed {
    public int idDrink ;  // idDrink: "13940",

    public String strDrink ; // strDrink: "69 Special",

    // strDrinkAlternate: null,

    // strDrinkES: null,

    // strDrinkDE: null,

    // strDrinkFR: null,

    // strDrinkZH-HANS: null,

    // strDrinkZH-HANT: null,

    public String strTags ; // strTags: null,

    // strVideo: null,

    public String strCategory ; // strCategory: "Ordinary Drink",

    // strIBA: null,

    public String strAlcoholic ; //

    public String strGlass ; // strGlass: "Collins Glass",

    public String strInstructions ; // strInstructions: "Pour 2 oz. gin. Add 4 oz. 7-up. Add Lemon Juice for flavor. If you are weak, top up glass with more 7-Up.",

    // strInstructionsES: null,

    // strInstructionsDE: "Gießen Sie 6 cl. Gin. Füge 12 cl. 7-up hinzu. Füge Zitronensaft für den Geschmack hinzu. Wenn Sie zu schwach sind, füllen Sie das Glas mit mehr 7-Up auf.",

    // strInstructionsFR: null,

    // strInstructionsZH-HANS: null,

    // strInstructionsZH-HANT: null,

    public String strDrinkThumb ; // strDrinkThumb: "https://www.thecocktaildb.com/images/media/drink/vqyxqx1472669095.jpg",

    public ArrayList<String> ingredients ;
    // strIngredient1: "Gin",
    // strIngredient2: "7-Up",
    // strIngredient3: "Lemon juice",
    // strIngredient4: null,
    // strIngredient5: null,
    // strIngredient6: null,
    // strIngredient7: null,
    // strIngredient8: null,
    // strIngredient9: null,
    // strIngredient10: null,
    // strIngredient11: null,
    // strIngredient12: null,
    // strIngredient13: null,
    // strIngredient14: null,
    // strIngredient15: null,

    public ArrayList<String> measures ;
    // strMeasure1: "2 oz dry ",
    // strMeasure2: "4 oz ",
    // strMeasure3: "0.75 oz ",
    // strMeasure4: null,
    // strMeasure5: null,
    // strMeasure6: null,
    // strMeasure7: null,
    // strMeasure8: null,
    // strMeasure9: null,
    // strMeasure10: null,
    // strMeasure11: null,
    // strMeasure12: null,
    // strMeasure13: null,
    // strMeasure14: null,
    // strMeasure15: null,

    // strCreativeCommonsConfirmed: "No",
    // dateModified: "2016-08-31 19:44:55"


    public DrinkDetailed(int idDrink, String strDrink, String strTags, String strCategory, String strAlcoholic, String strGlass, String strInstructions, String strDrinkThumb, ArrayList<String> ingredients, ArrayList<String> measures) {
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strTags = strTags;
        this.strCategory = strCategory;
        this.strAlcoholic = strAlcoholic;
        this.strGlass = strGlass;
        this.strInstructions = strInstructions;
        this.strDrinkThumb = strDrinkThumb;
        this.ingredients = ingredients;
        this.measures = measures;
    }

    public DrinkDetailed() {}

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void setStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setMeasures(ArrayList<String> measures) {
        this.measures = measures;
    }

    @Override
    public String toString() {
        return "DrinkDetailed{" +
                "  idDrink=" + idDrink +
                ",   strDrink='" + strDrink + '\'' +
                ",   strTags='" + strTags + '\'' +
                ",   strCategory='" + strCategory + '\'' +
                ",   strAlcoholic='" + strAlcoholic + '\'' +
                ",   strGlass='" + strGlass + '\'' +
                ",   strInstructions='" + strInstructions + '\'' +
                ",   strDrinkThumb='" + strDrinkThumb + '\'' +
                ",   ingredients=" + ingredients +
                ",   measures=" + measures +
                '}';
    }
}

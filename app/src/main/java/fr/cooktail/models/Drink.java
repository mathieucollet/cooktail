package fr.cooktail.models;

public class Drink {
    public int idDrink ;
    public String strDrink , strDrinkThumb ;

    public Drink(int idDrink, String strDrink, String strDrinkThumb) {
        this.strDrink = strDrink;
        this.strDrinkThumb = strDrinkThumb;
        this.idDrink = idDrink;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "strDrink='" + strDrink + '\'' +
                ", strDrinkThumb='" + strDrinkThumb + '\'' +
                ", idDrink='" + idDrink + '\'' +
                '}';
    }
}

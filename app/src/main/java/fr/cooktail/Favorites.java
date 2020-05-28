package fr.cooktail;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.cooktail.adapters.FavoritesAdapter;

public class Favorites extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseHelper db;
    ArrayList<String> drinkIDs, drinkApiIDs, drinkNames;
    FavoritesAdapter favoritesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        this.recyclerView = findViewById(R.id.RV_favoritesList);

        db = new DatabaseHelper(getApplicationContext());
        drinkIDs = new ArrayList<>();
        drinkApiIDs = new ArrayList<>();
        drinkNames = new ArrayList<>();

        storeDataInArrays();

        favoritesAdapter = new FavoritesAdapter(getApplicationContext(), drinkIDs, drinkApiIDs, drinkNames);
        recyclerView.setAdapter(favoritesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        favoritesAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                Log.d("API_ID", drinkApiIDs.get(position) + "");
                Intent newIntent = new Intent(Favorites.this, DetailsCocktail.class);
                newIntent.putExtra("idCocktail", Integer.parseInt(drinkApiIDs.get(position)));

                Favorites.this.startActivity(newIntent);
            }
        });
    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No favorites yet.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                drinkIDs.add(cursor.getString(0));
                drinkApiIDs.add(cursor.getString(1));
                drinkNames.add(cursor.getString(2));
            }
        }
    }
}

package fr.cooktail.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.cooktail.ByIngredientResult;
import fr.cooktail.DetailsCocktail;
import fr.cooktail.R;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList favorite_id, favorite_api_id, favorite_name;
    private View.OnClickListener onClickListener;

    public FavoritesAdapter(Context context, ArrayList favorite_id, ArrayList favorite_api_id, ArrayList favorite_name) {
        this.context = context;
        this.favorite_id = favorite_id;
        this.favorite_api_id = favorite_api_id;
        this.favorite_name = favorite_name;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favorite_row, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.favorite_name_txt.setText(String.valueOf(favorite_name.get(position)));
        holder.api_id = Integer.parseInt(favorite_api_id.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return favorite_id.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView favorite_name_txt;
        int api_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            favorite_name_txt = itemView.findViewById(R.id.TV_favorite_name);
        }
    }
}

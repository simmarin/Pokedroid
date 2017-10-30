package com.mvp.demo.pokedroid.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvp.demo.pokedroid.R;
import com.mvp.demo.pokedroid.model.Pokemon;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jatempa on 10/23/17.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private ArrayList<Pokemon> dataset;
    private Context context;

    public PokemonAdapter() {
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.name.setText(p.getName());

        Glide.with(context)
             .load("https://pokeapi.co/media/sprites/pokemon/" + p.getNumber() + ".png")
             .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addToPokemonList(ArrayList<Pokemon> pokemons) {
        dataset.addAll(pokemons);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
        @OnClick(R.id.image)
        public void showDetails() {
            Intent intent= new Intent(context, Details2Activity.class);

            image.buildDrawingCache();
            Bitmap imagen= image.getDrawingCache();

            Bundle extras = new Bundle();
            extras.putParcelable("imagebitmap", (Parcelable) imagen);
            intent.putExtras(extras);
            intent.putExtra("name",name.getText());
            context.startActivity(intent);
        }
    }


}

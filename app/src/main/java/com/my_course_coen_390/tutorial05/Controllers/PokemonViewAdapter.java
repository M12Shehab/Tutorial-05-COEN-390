package com.my_course_coen_390.tutorial05.Controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.my_course_coen_390.tutorial05.Models.Pokemon;
import com.my_course_coen_390.tutorial05.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonViewAdapter extends RecyclerView.Adapter <PokemonViewAdapter.DataViewHolder>{

    ArrayList<Pokemon>items = new ArrayList<>();
    Context context;

    public PokemonViewAdapter(ArrayList<Pokemon> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.pokemon_card, parent, false);
        DataViewHolder holder = new DataViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        if(items.get(position).getImage_url().length() > 0)
        {
            Picasso.with(context)
                    .load(items.get(position).getImage_url())
                    .fit()
                    .centerCrop()
                    .error(android.R.drawable.stat_notify_error)
                    .into(holder.image_url, new Callback() {

                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                            Log.w("Image_Url", "Load image Error !!");
                        }
                    });
        }

        holder.name.setText(items.get(position).getName());
        holder.HP.setText("HP : "+items.get(position).getHP());
        holder.attack.setText("Attack : "+items.get(position).getAttack());
        holder.defence.setText("Defence : "+items.get(position).getDefence());
    }

    @Override
    public int getItemCount() {
        if(items.size() > 0)
            return items.size();
        return 0;
    }

    public void setFilter(ArrayList<Pokemon> newArrayList) {
        items = new ArrayList<>();
        items.addAll(newArrayList);
        notifyDataSetChanged();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image_url;
        TextView HP;
        TextView attack;
        TextView defence;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            image_url = itemView.findViewById(R.id.pokemon_image);
            HP = itemView.findViewById(R.id.txt_hp);
            attack = itemView.findViewById(R.id.txt_attack);
            defence = itemView.findViewById(R.id.txt_defence);

        }
    }
}

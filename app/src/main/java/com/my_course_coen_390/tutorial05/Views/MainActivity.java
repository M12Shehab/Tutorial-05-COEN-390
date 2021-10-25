package com.my_course_coen_390.tutorial05.Views;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.my_course_coen_390.tutorial05.Controllers.PokemonViewAdapter;
import com.my_course_coen_390.tutorial05.Models.Pokemon;
import com.my_course_coen_390.tutorial05.R;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AbsRuntimePermission
        implements SearchView.OnQueryTextListener
{

    RecyclerView rc;
    ArrayList<Pokemon> Pokemons;
    PokemonViewAdapter adapter;
    SearchView txt_Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Search = findViewById(R.id.txt_search);
        txt_Search.setOnQueryTextListener(this);

        Pokemons = generate_dummy();
        rc= findViewById(R.id.rc_pokemon);
        adapter = new PokemonViewAdapter(Pokemons, this);
        rc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rc.setAdapter(adapter);
        new ItemTouchHelper(card_touch_helper).attachToRecyclerView(rc);
    }

    private ArrayList<Pokemon> generate_dummy() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String[] names = {
                "Bulbasaur",
                "Charmander",
                "Squirtle",
                "Snorlax",
                "Butterfree",
                "Pidgeotto",
                "Pikachu",
                "Jigglypuff",
                "Ninetales",
                "Gengar",
                "Staryu"
        };
        String[] urls = {
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/143.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/012.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/017.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/039.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/038.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/094.png",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/120.png"

        };
        Random random = new Random(4);

        for (int index =0; index < names.length; ++index)
        {
            Pokemon pokemon = new Pokemon();
            pokemon.setName(names[index]);
            pokemon.setImage_url(urls[index]);
            pokemon.setHP(random.nextInt(100));
            pokemon.setAttack(random.nextInt(1000));
            pokemon.setDefence(random.nextInt(1000));
            pokemons.add(pokemon);
        }
        return pokemons;
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Pokemon> newArrayList = new ArrayList<>();
        for(Pokemon d: Pokemons)
        {
            String name = d.getName().toLowerCase();
            if(name.contains(newText))
            {
                newArrayList.add(d);
            }
        }
        adapter.setFilter(newArrayList);
        return true;
    }

    // This function is to swap and delete
    ItemTouchHelper.SimpleCallback card_touch_helper = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if(direction == ItemTouchHelper.RIGHT) {
                Pokemons.remove(viewHolder.getAdapterPosition());
//                adapter.notifyDataSetChanged();
            }
            else
            {
                Pokemon p = Pokemons.get(viewHolder.getAdapterPosition());
                Toast.makeText(getBaseContext(),
                        "You Left swap pokemon " +p.getName(),
                        Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();
        }
    };

}
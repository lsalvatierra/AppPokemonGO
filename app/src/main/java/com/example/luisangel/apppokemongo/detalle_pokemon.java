package com.example.luisangel.apppokemongo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.luisangel.apppokemongo.model.Pokemon;

public class detalle_pokemon extends AppCompatActivity {


    private TextView tvPokemon;
    private ImageView ivPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pokemon);
        tvPokemon = (TextView)findViewById(R.id.tvNombrePokemon);
        ivPokemon = (ImageView)findViewById(R.id.ivImagenPokemon);
        if(getIntent().hasExtra("pokemon")){
            Pokemon objPokemon = getIntent()
                    .getParcelableExtra("pokemon");
            tvPokemon.setText(objPokemon.getName());
            Glide.with(getApplicationContext())
                    .load("https://pokeapi.co/media/sprites/pokemon/"+objPokemon.getNumero()+".png")
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPokemon);

        }
    }
}

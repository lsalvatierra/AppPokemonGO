package com.example.luisangel.apppokemongo.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//importa R del proyecto.
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.luisangel.apppokemongo.R;
import com.example.luisangel.apppokemongo.detalle_pokemon;
import com.example.luisangel.apppokemongo.model.Pokemon;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {


    private ArrayList<Pokemon> datapokemon;
    private Context context;

    //Creamos el constructor.


    public ListaPokemonAdapter(Context context) {
        this.context = context;
        datapokemon = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Establecemos la referencia del layout.
        View vi = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(vi);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Empezamos a establecer los valores en el textview
        //Pintamos la imagen del pokemon.
        final Pokemon pok = datapokemon.get(position);
        holder.tvnompokemon.setText(pok.getName());
        Glide.with(context)
                .load("https://pokeapi.co/media/sprites/pokemon/"+pok.getNumero()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgpokemon);
        holder.contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intAndroidDetalle =
                        new Intent(context, detalle_pokemon.class);
                intAndroidDetalle.putExtra("pokemon", pok);


                context.startActivity(intAndroidDetalle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datapokemon.size();
    }

    //Método que agrega información a la lista.
    public void agregarLista(ArrayList<Pokemon> listaPokemon){
        datapokemon.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    //Clase que nos permite recuperar los controles del layout.
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgpokemon;
        private TextView tvnompokemon;
        CardView contenedor;

        public ViewHolder(View itemView) {
            super(itemView);
            imgpokemon = (ImageView)itemView.findViewById(R.id.imgpokemon);
            tvnompokemon = (TextView) itemView.findViewById(R.id.tvnompokemon);
            contenedor = (CardView)itemView.findViewById(R.id.contenedor);
        }
    }
}

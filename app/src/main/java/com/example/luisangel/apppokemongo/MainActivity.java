package com.example.luisangel.apppokemongo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.luisangel.apppokemongo.adapters.ListaPokemonAdapter;
import com.example.luisangel.apppokemongo.model.Pokemon;
import com.example.luisangel.apppokemongo.model.PokemonRespuesta;
import com.example.luisangel.apppokemongo.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static  final  String TAG ="pokemonGO";
    private Retrofit retrofit;
    private RecyclerView rvDatos;
    private ListaPokemonAdapter lstAdapter;
    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvDatos = (RecyclerView)findViewById(R.id.rvDatos);
        lstAdapter = new ListaPokemonAdapter(this);
        rvDatos.setAdapter(lstAdapter);
        rvDatos.setHasFixedSize(true);

        final GridLayoutManager layoutManager =
                new GridLayoutManager(this, 3);
        rvDatos.setLayoutManager(layoutManager);

        //Evento que nos permite detectar el scroll del RECYCLERVIEW
        rvDatos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if(aptoParaCargar){
                        if((visibleItemCount + pastVisibleItems) >= totalItemCount){
                            aptoParaCargar = false;
                            offset +=20;
                            ObtenerDatos(offset);
                        }
                    }
                }
            }

        });
        //Obtenemos la información del Servicio Rest
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        offset = 0;
        ObtenerDatos(offset);
    }


    //Método que realiza la obtención de datos con RETROFIT.
    private void ObtenerDatos(int offset) {
        PokeapiService service =
                retrofit.create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall =
                service.obtenerListaPokemon(20,offset);

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listapokemon = pokemonRespuesta.getResults();
                    lstAdapter.agregarLista(listapokemon);
                }

            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {

                aptoParaCargar = true;
                Log.e(TAG, "onfailure: "+t.getMessage());

            }
        });

    }


}

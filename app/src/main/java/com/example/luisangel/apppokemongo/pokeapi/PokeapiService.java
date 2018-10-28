package com.example.luisangel.apppokemongo.pokeapi;

import com.example.luisangel.apppokemongo.model.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {

    //Metodo que se va implementar en el adapter para el uso de RETROFIT
    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit")int limit,
                                               @Query("offset") int offset);
}

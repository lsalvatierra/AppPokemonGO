package com.example.luisangel.apppokemongo.model;

import java.util.ArrayList;

public class PokemonRespuesta {

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }

    private ArrayList<Pokemon> results;

}

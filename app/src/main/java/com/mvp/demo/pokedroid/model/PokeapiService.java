package com.mvp.demo.pokedroid.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jatempa on 10/23/17.
 */

public interface PokeapiService {
    @GET("pokemon")
    Observable<PokemonList> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);
}
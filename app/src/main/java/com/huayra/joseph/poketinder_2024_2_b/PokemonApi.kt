package com.huayra.joseph.poketinder_2024_2_b

import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(): Response<PokemonListResponse>

}
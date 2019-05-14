package com.gpixel.Retrofit;



import com.gpixel.javabeans.Juego;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by PilarMarGom on 04/01/2018.
 */

public interface APIRestService {
    public static final String BASE_URL ="https://www.giantbomb.com/api/games/?api_key=bd2514fa50bc7f31b80992f4dd257af11aa48f96&format=json&field_list=name";

    @GET("games")
    Call<ArrayList<Juego>> obtenerCds();

    @GET("game")
    Call<Juego>obtenerJuego();



}
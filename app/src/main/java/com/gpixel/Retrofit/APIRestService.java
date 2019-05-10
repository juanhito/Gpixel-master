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
    public static final String BASE_URL ="https://www.giantbomb.com/api/games/?api_key=[YOUR API KEY]";

    @GET("name")
    Call<ArrayList<Juego>> obtenerCds();


}
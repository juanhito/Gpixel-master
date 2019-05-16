package com.gpixel.Retrofit;



        import com.gpixel.javabeans.Juego;
        import com.gpixel.javabeans.Prueba;

        import java.security.Key;
        import java.util.ArrayList;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Path;
        import retrofit2.http.Query;

/**
 * Created by PilarMarGom on 04/01/2018.
 */

public interface APIRestService {
    public static final String BASE_URL ="https://www.giantbomb.com/api/";

    public static final String Key="bd2514fa50bc7f31b80992f4dd257af11aa48f96";
    public static final String format="json";
    public static final String field_list="name,deck,original_release_date";
   @GET("games/")
   Call<ArrayList<Juego>> obtenerCds(@Query("api_key")String Key,@Query("format")String format,@Query("field_list")String name);

    @GET("games/")
    Call<Prueba>obtenerPrueba2();

    @GET("game")
    Call<Juego> obtenerJuego(@Query("api_key")String Key,@Query("format")String format,@Query("field_list")String nombre);

    @GET("games/")
    Call<Prueba> obtenerPrueba(@Query("api_key")String Key, @Query("format")String format, @Query("field_list")String nombre);





}
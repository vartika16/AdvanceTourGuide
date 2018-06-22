package com.example.vartikajain.advancetourguide.Api;

import com.example.vartikajain.advancetourguide.models.FourSquareEvent;
import com.example.vartikajain.advancetourguide.models.FourSquareJSON;
import com.example.vartikajain.advancetourguide.models.FourSquareResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by VARTIKA JAIN on 18-06-2018.
 */

public interface API {
    @GET("venues/search?v=20180618")
    Call<FourSquareJSON>getSearchVenues(@Query("client_id") String client_id,
                                        @Query("client_secret") String client_secret,
                                        @Query("ll") String ll);
    @GET("venues/search?v=20180618")
    Call<FourSquareJSON>getSearchVenuesByArea(@Query("client_id") String client_id,
                                              @Query("client_secret") String client_secret,
                                              @Query("near") String near);
    @GET("venues/trending?v=20180618")
    Call<FourSquareJSON>getTrendingVenues(@Query("client_id") String client_id,
                                              @Query("client_secret") String client_secret,
                                              @Query("near") String near);
    @GET("venues/explore?v=20180618")
    Call<FourSquareJSON>getRecommendedVenues(@Query("client_id") String client_id,
                                            @Query("client_secret") String client_secret,
                                            @Query("ll") String ll);
//    @GET("venues/{VENUE_ID}/photos?v=20180620")
//    Call<FourSquareJSON>getVenuePhotos(@Path("VENUE_ID") String VENUE_ID,
//                                       @Query("client_id") String client_id,
//                                       @Query("client_secret") String client_secret);
    @GET("venues/{VENUE_ID}?v=20180620")
    Call<FourSquareJSON>getVenueDetails(@Path("VENUE_ID") String VENUE_ID,
                                        @Query("client_id") String client_id,
                                        @Query("client_secret") String client_secret);
    @GET("venues/{VENUE_ID}/events?v=20180620")
    Call<ArrayList<FourSquareEvent>>getVenueEvents(@Path("VENUE_ID") String VENUE_ID,
                                                   @Query("client_id") String client_id,
                                                   @Query("client_secret") String client_secret);


}

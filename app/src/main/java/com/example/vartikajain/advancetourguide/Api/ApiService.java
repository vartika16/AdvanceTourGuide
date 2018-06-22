package com.example.vartikajain.advancetourguide.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VARTIKA JAIN on 18-06-2018.
 */

public class ApiService {
    private ApiService(){}
    private static API FourSquareApi=null;

    public static API getApi(){
        if(FourSquareApi==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://api.foursquare.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            FourSquareApi=retrofit.create(API.class);
        }
        return FourSquareApi;
    }
}

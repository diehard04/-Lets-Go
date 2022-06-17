package com.example.letsgo.internet;

import com.example.letsgo.internet.model.BusStopsResult;
import com.example.letsgo.internet.model.SourceDestinationResult;
import com.example.letsgo.internet.model.SourceDestPostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dipak Kumar Mehta on 6/13/2022.
 */
public interface Api {
    String BASE_URL = "http://2bincdev.com/letsgo/api/";

    @POST("busstop.php")
    Call<SourceDestinationResult> getSourceDestination();

    @POST("search.php")
    Call<BusStopsResult> getBusList(@Body SourceDestPostModel model);

//    String BASE_URL = "https://run.mocky.io/v3/";
//    @GET("dad8803e-3ac3-4354-ba0b-d4114f800d0d")
//    Call<Results> getSourceDestination();
}

package com.udacity.uelordi.bakingapp.network;

/**
 * Created by uelordi on 17/04/2017.
 */
import android.database.Observable;

import com.udacity.uelordi.bakingapp.content.Ingredient;
import com.udacity.uelordi.bakingapp.content.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DBMovieServiceEndPoint {
    @GET("/baking.json")
    Call<List<Recipe>> getRecipeList();
//    @GET("movie/{id}/reviews")
//    Call<ReviewResponse> getMovieReviews(@Path("id") long movieId, @Query("api_key") String api_key);
//
//    @GET("movie/{sort_by}")
//    Call<MovieListResponse> getMovieList(@Path("sort_by") String sortBy, @Query("api_key") String api_key);
////    Observable<MovieListResponse>

}

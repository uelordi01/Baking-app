package com.udacity.uelordi.bakingapp.network;

import android.content.Context;
import android.database.Observable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.udacity.uelordi.bakingapp.content.Recipe;
import com.udacity.uelordi.bakingapp.interfaces.OnHandleDataCallback;
import com.udacity.uelordi.bakingapp.service.RecipeTask;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uelordi on 18/04/2017.
 */

public class NetworkModule {
    private static final String STATIC_MOVIE_DB_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking";
    private final static String IMAGE_URL_PATH = "http://image.tmdb.org/t/p/w185";
    private final static String YOUTUBE_BASE_URL="http://www.youtube.com/watch?v=";
    private static final String TAG = "NetworkModule";

    private static NetworkModule mInstance;
    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private DBMovieServiceEndPoint client;
    OnHandleDataCallback mRecipeCallback;

    public NetworkModule() {
        configureNetworkModule();
    }

    private void configureNetworkModule() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        builder =
                new Retrofit.Builder()
                        .baseUrl(STATIC_MOVIE_DB_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );
        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        client =  retrofit.create(DBMovieServiceEndPoint.class);
    }
    public void getRecipeList() {
        Call<List<Recipe>> call = client.getRecipeList();
        call.enqueue(
                new Callback<List<Recipe>>() {
                    @Override
                    public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                        if(response.isSuccessful()) {
                            List<Recipe> mRecipe = response.body();
                            mRecipeCallback.onDataRecipesSuceed(mRecipe);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Recipe>> call, Throwable t) {

                    }
                }
        );
    }
    public void setRecipeHandleCallback(OnHandleDataCallback callback) {
        mRecipeCallback = callback;
    }

    public static  NetworkModule getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new NetworkModule();
        }
        return mInstance;
    }
    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public static String getImageUrlPah() {
        return IMAGE_URL_PATH;
    }
    public static String getYoutubePath() {
        return YOUTUBE_BASE_URL;
    }

}

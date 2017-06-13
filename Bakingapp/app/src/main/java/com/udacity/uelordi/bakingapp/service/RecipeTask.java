package com.udacity.uelordi.bakingapp.service;

import android.content.Context;

import com.udacity.uelordi.bakingapp.content.Recipe;
import com.udacity.uelordi.bakingapp.database.BakingContentProvider;
import com.udacity.uelordi.bakingapp.interfaces.OnHandleDataCallback;
import com.udacity.uelordi.bakingapp.network.NetworkModule;

import java.util.List;

/**
 * Created by uelordi on 13/06/2017.
 */

public class RecipeTask implements OnHandleDataCallback
{
    private static RecipeTask mInstance = null;
    private Context mContext;
    public static  RecipeTask getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new RecipeTask();
        }
        return mInstance;
    }
    public  void SyncInmediatelly(Context context) {
        mContext = context;
        NetworkModule.getInstance().setRecipeHandleCallback(this);
        NetworkModule.getInstance().getRecipeList();
    }

    @Override
    public void onDataRecipesSuceed(List<Recipe> data) {
//        mContext.getContentResolver().query(BakingContentProvider.);
        //TODO generate the database here:

    }
}

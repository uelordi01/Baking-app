package com.udacity.uelordi.bakingapp.service;

import android.content.ContentValues;
import android.content.Context;

import com.udacity.uelordi.bakingapp.content.Ingredient;
import com.udacity.uelordi.bakingapp.content.Recipe;
import com.udacity.uelordi.bakingapp.database.BakingContentProvider;
import com.udacity.uelordi.bakingapp.database.RecipeColumns;
import com.udacity.uelordi.bakingapp.interfaces.OnHandleDataCallback;
import com.udacity.uelordi.bakingapp.network.NetworkModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uelordi on 13/06/2017.
 */

public class RecipeTask implements OnHandleDataCallback
{
    private static RecipeTask mInstance = null;
    private Context mContext;
    public static final String DATA_CHANGE_ACTION ="com.udacity.uelordi.bakingapp.DATA_CHANGED";
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
    public void setmContext(Context context) {
        mContext = context;
    }
    @Override
    public void onDataRecipesSuceed(List<Recipe> data) {
//        mContext.getContentResolver().query(BakingContentProvider.);
        //TODO generate the database here:
        ContentValues [] values = convertRecipeListToContentValues(data);

        mContext.getContentResolver().bulkInsert(BakingContentProvider.BakingRecipe.RECIPE_CONTENT_URI,values);

    }
    private ContentValues[] convertRecipeListToContentValues(List<Recipe> data) {
        ContentValues[] cValues =  new ContentValues[data.size()];
        int index =0;
        for(Recipe rep: data) {
            ContentValues cv = new ContentValues();
            cv.put(RecipeColumns._ID, rep.getId());
            cv.put(RecipeColumns.NAME, rep.getName());
            cv.put(RecipeColumns.IMAGE, rep.getImage());
            cValues[index] =cv;
            index++;
        }
        return cValues;
    }
    public void insertRecipeData(ContentValues cv) {
        mContext.getContentResolver().insert(BakingContentProvider.BakingRecipe.RECIPE_CONTENT_URI, cv);
    }
}

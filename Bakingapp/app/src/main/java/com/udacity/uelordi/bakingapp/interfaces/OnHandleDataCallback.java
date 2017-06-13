package com.udacity.uelordi.bakingapp.interfaces;

import com.udacity.uelordi.bakingapp.content.Recipe;

import java.util.List;

/**
 * Created by uelordi on 13/06/2017.
 */

public interface OnHandleDataCallback {
    public void onDataRecipesSuceed(List<Recipe> data);
}

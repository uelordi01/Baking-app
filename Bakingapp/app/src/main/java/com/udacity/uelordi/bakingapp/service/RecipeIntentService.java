package com.udacity.uelordi.bakingapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by uelordi on 13/06/2017.
 */

public class RecipeIntentService extends IntentService {
    public RecipeIntentService() {
        super("RecipeIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        RecipeTask.getInstance().SyncInmediatelly(getApplicationContext());
    }
}

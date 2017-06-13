package com.udacity.uelordi.bakingapp.service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by uelordi on 13/06/2017.
 */

public class RecipeUtils {
    public static void syncInmediatelly(@NonNull Context context) {
        Intent startSyncIntent = new Intent(context, RecipeIntentService.class);
        context.startService(startSyncIntent);
    }
}

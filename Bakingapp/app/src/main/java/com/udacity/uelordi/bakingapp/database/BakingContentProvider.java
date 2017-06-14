package com.udacity.uelordi.bakingapp.database;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

import static com.udacity.uelordi.bakingapp.database.BakingDatabase.RECIPE_TABLE;

/**
 * Created by uelordi on 14/06/2017.
 */


@ContentProvider(authority = BakingContentProvider.AUTHORITY,
        database = BakingDatabase.class)
public final class BakingContentProvider {

    public static final String AUTHORITY =
            "com.udacity.uelordi.bakingapp.BakingContentProvider";

    @TableEndpoint(table = BakingDatabase.RECIPE_TABLE)
    public static class BakingRecipe {

        @ContentUri(
                path = RECIPE_TABLE,
                type = " com.udacity.uelordi.bakingapp.database/list",
                defaultSort = RecipeColumns.NAME + " ASC")
        public static final Uri RECIPE_CONTENT_URI
                = Uri.parse("content://" + AUTHORITY + "/" + RECIPE_TABLE);
    }
}

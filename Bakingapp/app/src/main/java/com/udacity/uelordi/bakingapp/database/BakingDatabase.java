package com.udacity.uelordi.bakingapp.database;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by uelordi on 14/06/2017.
 */
@Database(version = BakingDatabase.VERSION)
public final class BakingDatabase {
    public static final int VERSION = 1;

    @Table(RecipeColumns.class)
    public static final String RECIPE_TABLE = "Recipes";
}

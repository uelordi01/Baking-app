package com.udacity.uelordi.bakingapp.database;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;


import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;


/**
 * Created by uelordi on 14/06/2017.
 */
public interface RecipeColumns {
    @DataType(INTEGER) @PrimaryKey
    String _ID = "_id";

    @DataType(TEXT) @NotNull
    String NAME = "name";

    @DataType(TEXT)
    String IMAGE = "image";
}

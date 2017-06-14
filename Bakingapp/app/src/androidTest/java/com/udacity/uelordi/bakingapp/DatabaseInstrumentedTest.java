package com.udacity.uelordi.bakingapp;

import android.content.ContentValues;
import android.provider.SyncStateContract;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.uelordi.bakingapp.content.Recipe;
import com.udacity.uelordi.bakingapp.database.RecipeColumns;
import com.udacity.uelordi.bakingapp.service.RecipeTask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by user on 14/06/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseInstrumentedTest {
    private RecipeTask mDataSource;

    @Before
    public void setUp(){
        mDataSource = new RecipeTask();
        mDataSource.setmContext(InstrumentationRegistry.getTargetContext());
    }
    @Test
    public void testPreConditions() {
        assertNotNull(mDataSource);
    }
    @Test
    public void testInsert() {
        ContentValues cv = new ContentValues();
        cv.put(RecipeColumns._ID, 1);
        cv.put(RecipeColumns.NAME, "carrot cake");
        cv.put(RecipeColumns.IMAGE, "myImage");
        mDataSource.insertRecipeData(cv);
    }

}

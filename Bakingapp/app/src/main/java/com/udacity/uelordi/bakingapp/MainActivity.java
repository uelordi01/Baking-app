package com.udacity.uelordi.bakingapp;

import android.support.v4.content.CursorLoader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udacity.uelordi.bakingapp.content.Recipe;
import com.udacity.uelordi.bakingapp.database.BakingContentProvider;
import com.udacity.uelordi.bakingapp.database.RecipeColumns;
import com.udacity.uelordi.bakingapp.service.RecipeUtils;

import java.security.Permission;
import java.util.ArrayList;

import android.Manifest;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
implements RecipeAdapter.RecipeItemClickListener,
        LoaderManager.LoaderCallbacks<Cursor>{

    private static final String[] permissions_needed = {Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE
    };
    private static final String LOG_TAG ="MainActivity";
    @BindView(R.id.rv_recipe_list)
    RecyclerView mrvRecipeList;
    private LinearLayoutManager mRecipeLayoutManager;
    private RecipeAdapter mRecipeAdapter;

    boolean isMobile = true;
    private static String[] RECIPE_PROJECTION = {
        RecipeColumns.NAME,
        RecipeColumns.IMAGE
    };
    private static final int RECIPE_VIDEO_LIST_TASK = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        RecipeUtils.syncInmediatelly(getApplicationContext());
        mRecipeLayoutManager = new LinearLayoutManager(MainActivity.this);
        ButterKnife.bind(this);
        mRecipeAdapter = new RecipeAdapter(getApplicationContext(), MainActivity.this);
        mrvRecipeList.setLayoutManager(mRecipeLayoutManager);
        mrvRecipeList.setAdapter(mRecipeAdapter);
        getRecipeList();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RecipeUtils.syncInmediatelly(getApplicationContext());
    }

        public void requestPermissions() {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                ArrayList array_aux = new ArrayList<String>();

                String[] permissions_requested;
                for (String aPermissions_needed : permissions_needed) {
                    int hasPermission = ContextCompat.checkSelfPermission(this, aPermissions_needed);
                    if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                        array_aux.add(aPermissions_needed);
                        Log.d("Activity", "request permission %s" + aPermissions_needed);
                    }
                }
                if (array_aux.size() != 0) {
                    //Log.d("Activity", "allow permission");
                    permissions_requested = (String[]) array_aux.toArray(new String[array_aux.size()]);


                    ActivityCompat.requestPermissions(this, permissions_requested,
                            REQUEST_CODE_ASK_PERMISSIONS);
                }
            } else {
                Log.v(LOG_TAG, "No need to ask permissions programatically");
            }

        }

    private void getRecipeList() {

            LoaderManager loaderManager = getSupportLoaderManager();
            Loader<String> recipeListLoader = loaderManager.getLoader(RECIPE_VIDEO_LIST_TASK);
            if ( recipeListLoader == null ) {
                getSupportLoaderManager().initLoader(RECIPE_VIDEO_LIST_TASK, null, this);
            } else {
                loaderManager.restartLoader(RECIPE_VIDEO_LIST_TASK, null, this);
            }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                BakingContentProvider.BakingRecipe.RECIPE_CONTENT_URI,
                RECIPE_PROJECTION,
                null, null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data.getCount() > 0) {
            mRecipeAdapter.setCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mRecipeAdapter.setCursor(null);
    }

    @Override
    public void onRecipeItemClick(long id) {
        Log.v(LOG_TAG, "item clicked Recipe id "+id);
    }
}

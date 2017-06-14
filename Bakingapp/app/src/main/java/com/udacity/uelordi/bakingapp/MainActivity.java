package com.udacity.uelordi.bakingapp;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udacity.uelordi.bakingapp.content.Recipe;
import com.udacity.uelordi.bakingapp.service.RecipeUtils;

import java.security.Permission;
import java.util.ArrayList;

import android.Manifest;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String[] permissions_needed = {Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE
    };
    private static final String LOG_TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        RecipeUtils.syncInmediatelly(getApplicationContext());
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

}

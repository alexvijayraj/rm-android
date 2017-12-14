package com.nilasoftwares.rm.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.nilasoftwares.rm.R;
import com.nilasoftwares.rm.model.HomeModel;
import com.nilasoftwares.rm.presenter.HomePresenter;
import com.nilasoftwares.rm.view.HomeView;

/**
 * Created by alexvijayrajamalaraj on 12/12/17.
 */

/**
 * Home Activity that displays Google Maps
 *
 *  - The Main Activity of the app
 *  - Enables users to book or schedule rides
 *
 * @author alexvijayrajamalaraj
 */
public class HomeActivity extends FragmentActivity implements HomeView, View.OnClickListener, OnMapReadyCallback,
        GoogleMap.OnMyLocationChangeListener{

    //The map object
    private GoogleMap mMap;

    //The context variable
    private Context context;

    //latlng object to store latitiude and longitude
    private LatLng latLng;

    private HomePresenter homePresenter;
    private RecyclerView mRecyclerView;

    private ImageButton ibHeaderMenu, ibGps;
    private DrawerLayout mDrawerLayout;

    private static final String TAG = "HOME_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ibHeaderMenu = (ImageButton) findViewById(R.id.ibHeaderMenu);
        ibGps = (ImageButton) findViewById(R.id.ibGps);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ibHeaderMenu.setOnClickListener(this);
        ibGps.setOnClickListener(this);

        homePresenter = new HomeModel(HomeActivity.this, this);

        //initialize Left Hand Navigation Drawer
        initDrawer();

        //initialize Google Maps
        initMap();

    }

    @Override
    public void onBackPressed() {
        //do nothing for now
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ibHeaderMenu:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.ibGps:
                getLocation();
                break;
            default:
                break;
        }

    }

    @Override
    public void toggleNavDrawer(boolean toggle) {

        if(toggle){
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }else{
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }

    }

    /**
     * method to initialize the Left hand navigation drawer
     *
     */
    private void initDrawer(){

        //Letting the system know that the list objects are of fixed size
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(homePresenter.getNavDrawerAdapter());

        // setting a layout Manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    /**
     * method to initialize Google Maps
     *
     */
    private void initMap(){

        //The Map fragment is initialized
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Call back is called when the google maps is ready for further setup
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //check if location permissions are available
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == getPackageManager().PERMISSION_GRANTED) {

            //turn on location services
            mMap.setMyLocationEnabled(true);

            //setup the location change listener
            mMap.setOnMyLocationChangeListener(this);

            //get the last known location
            Location location = mMap.getMyLocation();
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                latLng = new LatLng(latitude, longitude);

                //move the camera to the last known location
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(9));
            }
        } else if (permissionCheck == getPackageManager().PERMISSION_DENIED) {

            //request location permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},
                        1);
            }

        }

        //Turns off map rotation and removes Google's GPS button
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

    }

    @Override
    public void onMyLocationChange(Location location) {

    }

    /**
     * Call back for getting location result
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //check for GPS  and get location - send request to server for AQI
                    getLocation();

                    Log.d(TAG, "permission granted");

                } else {
                    // permission denied,
                    Log.d(TAG, "permission denied");
                }
                return;
            }
        }
    }

    /**
     * Method to check if the user has enabled GPS permission or not
     */
    private void getLocation() {

        //check if location permission is available
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        //if location permission available
        if (permissionCheck == getPackageManager().PERMISSION_GRANTED) {

            //turn on location services
            mMap.setMyLocationEnabled(true);

            //setup the location change listener
            mMap.setOnMyLocationChangeListener(this);

            //get the last known location
            Location location = mMap.getMyLocation();
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                latLng = new LatLng(latitude, longitude);

                //move the camera to the last known location
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(9));
            }
        }else{
            //request location permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},
                        1);
            }
        }
    }
}

package com.nilasoftwares.rm.activity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.nilasoftwares.rm.R;
import com.nilasoftwares.rm.model.HomeModel;
import com.nilasoftwares.rm.presenter.HomePresenter;
import com.nilasoftwares.rm.view.HomeView;

/**
 * Created by alexvijayrajamalaraj on 12/12/17.
 */

public class HomeActivity extends FragmentActivity implements HomeView, View.OnClickListener, OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener{

    private HomePresenter homePresenter;
    private RecyclerView mRecyclerView;

    private ImageButton ibHeaderMenu;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ibHeaderMenu = (ImageButton) findViewById(R.id.ibHeaderMenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ibHeaderMenu.setOnClickListener(this);

        homePresenter = new HomeModel(HomeActivity.this, this);

        //init Left Hand Navigation Drawer
        initDrawer();

        //The Map fragment is initialized
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
            default:
                break;
        }

    }

    @Override
    public void toggleNavDrawer(boolean toggle) {

    }

    private void initDrawer(){

        //Letting the system know that the list objects are of fixed size
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(homePresenter.getNavDrawerAdapter());

        // setting a layout Manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onMyLocationChange(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}

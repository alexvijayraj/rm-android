package com.nilasoftwares.rm.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.nilasoftwares.rm.Adapters.LeftHandDrawerAdapter;
import com.nilasoftwares.rm.presenter.HomePresenter;
import com.nilasoftwares.rm.view.HomeView;
import android.support.v7.widget.RecyclerView;

/**
 * Created by alexvijayrajamalaraj on 12/12/17.
 */

/**
 * Model class of the Home activity
 *
 *  - sets up Left Hand Navigation drawer
 *
 * @author alexvijayrajamalaraj
 */
public class HomeModel implements HomePresenter {

    private HomeView homeView;
    private Context context;

    private RecyclerView.Adapter navDrawerAdapter;

    public HomeModel(HomeView homeView, Context context){

        this.homeView = homeView;
        this.context = context;

        //setup Left hand Navigation Drawer
        initNavDrawer();

    }

    @Override
    public void showNavDrawer() {

    }

    @Override
    public RecyclerView.Adapter getNavDrawerAdapter() {
        return navDrawerAdapter;
    }

    private void initNavDrawer(){

        navDrawerAdapter = new LeftHandDrawerAdapter(context);
    }
}

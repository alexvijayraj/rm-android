package com.nilasoftwares.rm.presenter;


import android.support.v7.widget.RecyclerView;

/**
 * Created by alexvijayrajamalaraj on 12/12/17.
 */

public interface HomePresenter {

    void showNavDrawer();
    RecyclerView.Adapter getNavDrawerAdapter();

}

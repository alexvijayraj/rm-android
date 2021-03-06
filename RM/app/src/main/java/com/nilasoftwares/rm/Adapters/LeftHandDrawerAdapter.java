package com.nilasoftwares.rm.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nilasoftwares.rm.R;

/**
 * Created by alexvijayrajamalaraj on 12/12/17.
 */

/**
 * Adapter to the left hand navigation drawer displayed on the Home screen
 *
 * @author alexvijayrajamalaraj
 */
public class LeftHandDrawerAdapter extends RecyclerView.Adapter<LeftHandDrawerAdapter.ViewHolder>{

    // indicates the header row of the nav drawer
    private static final int TYPE_HEADER = 0;

    // indicates all other rows of the nav drawer
    private static final int TYPE_ITEM = 1;

    private Context context;

    private String mNavTitles[];

    /**
     * Constructor that takes in context as a parameter
     *
     */
    public LeftHandDrawerAdapter(Context context) {
        this.context = context;

        //init hav drawer row elements
        initRowElements();

    }

    /**
     * View holder that represents each row of the drawer
     *
     */
    @Override
    public LeftHandDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

            ViewHolder vhItem = new ViewHolder(v, viewType);

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);

            ViewHolder vhHeader = new ViewHolder(v, viewType);

            return vhHeader;


        }

        return null;

    }

    /**
     * method gets called when view is setup. TextViews are set with corresponding texts
     *
     */
    @Override
    public void onBindViewHolder(LeftHandDrawerAdapter.ViewHolder holder, int position) {

        if(holder.Holderid != TYPE_HEADER){

            holder.textView.setText(mNavTitles[position - 1]);

        }

    }

    /**
     * method to return total number of rows of the drawer
     *
     */
    @Override
    public int getItemCount() {

        //add 1 for the header and rest are the elements
        return mNavTitles.length + 1;
    }

    /**
     * method to differentiate between header and item row depending on position
     *
     */
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    /**
     * method to return the position of the header
     *
     */
    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    /**
     * method to initialize the row elements except header of the drawer
     *
     */
    private void initRowElements(){

        mNavTitles = new String[]{getRowElement(1),getRowElement(2), getRowElement(3),
                getRowElement(4), getRowElement(5), getRowElement(6),
                getRowElement(7),};

    }

    /**
     * method to get the text to display on each row based on index
     *
     */
    private String getRowElement(int index){
        String retString = "";

        switch (index){
            case 1:
                retString = context.getResources().getString(R.string.nav_drawer_row_1);
                break;
            case 2:
                retString = context.getResources().getString(R.string.nav_drawer_row_2);
                break;
            case 3:
                retString = context.getResources().getString(R.string.nav_drawer_row_3);
                break;
            case 4:
                retString = context.getResources().getString(R.string.nav_drawer_row_4);
                break;
            case 5:
                retString = context.getResources().getString(R.string.nav_drawer_row_5);
                break;
            case 6:
                retString = context.getResources().getString(R.string.nav_drawer_row_6);
                break;
            default:
                retString = context.getResources().getString(R.string.nav_drawer_row_7);
                break;
        }

        return retString;
    }

    /**
     * class to setup the ViewHolder
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        int Holderid;

        TextView textView;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                Holderid = TYPE_ITEM;
            }
        }
    }
}



package edu.nyuad.androidgames;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.nyuad.boardgames.Chip;

/**
 * Created by seo on 3/19/18.
 */

public class GridAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<marker> mData;
    private String TAG;
    private int rowSize, colSize;
    private enum marker {
        EMPTY (R.drawable.rounded_button_grey),
        RED (R.drawable.rounded_button_red),
        BLUE (R.drawable.rounded_button_blue);
        private int value;
        marker(int value) { this.value = value; }
        int getValue() { return this.value; }
    }

    public GridAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        TAG = this.getClass().getName();
        mContext = context;
        mData = new ArrayList<marker>();

        for (int i = 0; i < resource; i++) {
            mData.add(marker.EMPTY); // Populate with empty chips
        }
        addAll(mData);
        notifyDataSetChanged();
    }

    public void setViewDimensions(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
    }

    public void set(Object object, int index) {
        assert (object instanceof marker);
        Object o = this.getItem(index);
        this.remove(o);
        this.insert(object, index);
    }

    public void place(Chip currentPlayer, int index) {
        assert (marker.valueOf(currentPlayer.toString()) instanceof marker);
        mData.set(index, marker.valueOf(currentPlayer.toString()));
        clear();
        addAll(mData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = new ImageView(mContext);
            ((ImageView)view).setScaleType(ImageView.ScaleType.FIT_CENTER);
            view.setLayoutParams(new GridView.LayoutParams(colSize, rowSize)); // set width and height of view
        } else {
            view = (ImageView)convertView;
        }
        ((ImageView)view).setImageResource(((marker)getItem(position)).getValue());  // the marker at given position
        return view;
    }
}

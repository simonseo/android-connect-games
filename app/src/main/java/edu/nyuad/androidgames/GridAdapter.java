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
import static edu.nyuad.androidgames.BoardGameActivity.marker;

/**
 * Created by seo on 3/19/18.
 */

public class GridAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<marker> mData;
    private String TAG;
    private int rowSize, colSize;


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

    public void setAll(ArrayList<Chip> data) {
        mData.clear();
        for (Chip chip : data) {
            mData.add(marker.valueOf(chip.toString()));
        }
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

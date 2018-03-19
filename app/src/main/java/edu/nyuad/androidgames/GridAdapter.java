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

import edu.nyuad.boardgames.Chip;

/**
 * Created by seo on 3/19/18.
 */

public class GridAdapter extends ArrayAdapter {
    private Context mContext;
    private String TAG;
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
        mContext = context;
        TAG = this.getClass().getName();

        for (int i = 0; i < resource; i++) {
            this.add(marker.EMPTY); // Populate with empty chips
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView view;
        if (convertView == null) {
            view = new ImageView(mContext);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new GridView.LayoutParams(100, 100));
        } else {
            view = (ImageView)convertView;
        }
        view.setImageResource(((marker)getItem(position)).getValue());  // the marker at given position
        return view;
    }
}

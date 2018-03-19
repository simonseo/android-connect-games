package edu.nyuad.androidgames;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import edu.nyuad.boardgames.Chip;

/**
 * Created by seo on 3/19/18.
 */

public class gridAdapter extends ArrayAdapter {
    private Context mContext;
    private enum marker {
        EMPTY (R.drawable.rounded_button_grey),
        RED (R.drawable.rounded_button_red),
        BLUE (R.drawable.rounded_button_blue);
        private int value;
        marker(int value) { this.value = value; }
    }

    public gridAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mContext = context;
        for (int i = 0; i < resource; i++) {
            this.add(marker.EMPTY); // Populate with empty chips
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}

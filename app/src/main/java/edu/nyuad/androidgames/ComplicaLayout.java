package edu.nyuad.androidgames;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.nyuad.androidgames.R;

/**
 * Created by seo on 3/7/18.
 */

public class ComplicaLayout extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.complica_layout, container, false);
        return v;
        //        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

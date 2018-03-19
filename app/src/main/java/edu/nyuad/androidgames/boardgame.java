package edu.nyuad.androidgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class boardgame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardgame);
    }

    /*
    // Parent layout
    LinearLayout parentLayout = (LinearLayout)findViewById(R.id.layout);

    // Layout inflater
    LayoutInflater layoutInflater = getLayoutInflater();
    View view;

    for (int i = 1; i < 101; i++){
        // Add the text layout to the parent layout
        view = layoutInflater.inflate(R.layout.text_layout, parentLayout, false);

        // In order to get the view we have to use the new view with text_layout in it
        TextView textView = (TextView)view.findViewById(R.id.text);
        textView.setText("Row " + i);

        // Add the text view to the parent layout
        parentLayout.addView(textView);
    */

}

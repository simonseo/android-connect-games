package edu.nyuad.androidgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import edu.nyuad.boardgames.Game;

public class boardGameActivity extends AppCompatActivity {
    private String TAG;
    private Game game;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardgame);
        TAG = getApplicationContext().getPackageName() +  "." + getLocalClassName();

        Bundle extras = getIntent().getExtras();
        String gameName = extras.getString("gameName", "ConnectFour");
        String className = "edu.nyuad.boardgames" + "." + gameName;
        Log.i(TAG, "className is: " + className);
        try {
            Class classRef = Class.forName(className);
            game = (Game)classRef.newInstance();
            Log.i(TAG,"Game of type " + gameName + " instantiated");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        gridView = (GridView)findViewById(R.id.boardGridView);
        gridView.setNumColumns(game.getColumns());
        gridAdapter = new gridAdapter(this, game.getColumns() * game.getRows());
        gridView.setAdapter(gridAdapter);


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

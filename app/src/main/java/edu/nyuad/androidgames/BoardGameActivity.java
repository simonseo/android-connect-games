package edu.nyuad.androidgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.nyuad.boardgames.Chip;
import edu.nyuad.boardgames.Game;
import edu.nyuad.boardgames.GameStateException;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BoardGameActivity extends AppCompatActivity {
    private String TAG;
    private Game game;
    private GridView gridView;
    private Button exitButton;
    private ImageView currentPlayerView, winnerView;
    private AndroidToGameTranslator translator;
    public enum marker {
        EMPTY (R.drawable.rounded_button_grey),
        RED (R.drawable.rounded_button_red),
        BLUE (R.drawable.rounded_button_blue);
        private int value;
        marker(int value) { this.value = value; }
        int getValue() { return this.value; }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardgame);
        TAG = getApplicationContext().getPackageName() + "." + getLocalClassName();

        Bundle extras = getIntent().getExtras();
        //should never go to default, but just to be safe
        String gameName = extras.getString("gameName", "ConnectFour");
        String className = "edu.nyuad.boardgames" + "." + gameName;
        Log.i(TAG, "className is: " + className);
        try {
            Class classRef = Class.forName(className);
            game = (Game) classRef.newInstance();
            translator = new AndroidToGameTranslator(game.getRows(), game.getColumns());
            Log.i("BoardGameActivity.java", "my message");
            Log.i(TAG, "Game of type " + gameName + " instantiated");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        currentPlayerView = (ImageView) findViewById(R.id.currentPlayerImageView);
        winnerView = (ImageView) findViewById(R.id.winnerPlayerImageView);
        exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gridView = (GridView) findViewById(R.id.boardGridView);
        gridView.setNumColumns(game.getColumns());


        final GridAdapter adapter = new GridAdapter(this, game.getRows() * game.getColumns());
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = (displayMetrics.widthPixels * 9) / (10 * game.getColumns());   //width for imageview should take up 90% of screen
        int h = (displayMetrics.heightPixels * 3) / (4 * game.getRows());   //height for imageview should take up 75% of screen
        int viewSize = min(h, w);
        adapter.setViewDimensions(viewSize, viewSize);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "" + i + " " + l, Toast.LENGTH_SHORT).show();
                Chip currentPlayer = null;
                try {
                    int[] positions = translator.androidToBoard(i);
                    int row = positions[0], col = positions[1];
                    currentPlayer = game.getCurrentPlayer();
                    game.placeChip(row, col);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.setAll(flattenBoard());
                if (game.isGameOver()) {
                    // Change current player colour to empty
                    Chip winner = Chip.EMPTY;
                    try {
                        winner = game.getWinningPlayer();
                    } catch (GameStateException e) {
                        // pass
                    }
                    if (winner.isEmpty()) {
                        Toast.makeText(getApplicationContext(), getString(R.string.tieMessage), Toast.LENGTH_LONG).show();
                    } else {
                        // Change winning player colour to winner
                        Toast.makeText(getApplicationContext(), String.format(getString(R.string.winMessage), winner), Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }

    private ArrayList<Chip> flattenBoard() {
        ArrayList<Chip> data = new ArrayList<Chip>();
        for (int i = 0; i < game.getRows(); i++) {
            for (int j = 0; j < game.getColumns(); j++) {
                data.add(game.getChip(i, j));
            }
        }
        return data;
    }
}

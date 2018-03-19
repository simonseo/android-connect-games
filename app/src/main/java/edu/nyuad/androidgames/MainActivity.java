package edu.nyuad.androidgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = getApplicationContext().getPackageName() +  "." + getLocalClassName();

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("TicTacToe");
        spinnerArray.add("ConnectFour");
        spinnerArray.add("Complica");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.gameSelectSpinner);
        sItems.setAdapter(adapter);

        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String selected = sItems.getSelectedItem().toString();
            Log.i(TAG, "selected option: " + selected);
            Intent i = new Intent(MainActivity.this, boardGameActivity.class);
            i.putExtra("gameName", selected );
            startActivity(i);
            }
        });
    }
}

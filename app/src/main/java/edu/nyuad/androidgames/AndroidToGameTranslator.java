package edu.nyuad.androidgames;

import android.util.Log;

/**
 * Created by seo on 3/20/18.
 */

public class AndroidToGameTranslator {
    private int numRow, numCol, androidBoardSize;
    private String TAG;

    public AndroidToGameTranslator(int numRow, int numCol) {
        this.numRow = numRow;
        this.numCol = numCol;
        this.androidBoardSize = numRow * numCol;
        TAG = this.getClass().getName();
    }
    public int[] androidToBoard(int androidPosition) {
        return new int[] {androidPosition/numCol, androidPosition%numCol};
    }
    public int boardToAndroid(int row, int col) {
        return row * numRow + col;
    }
}

package edu.nyuad.androidgames;

/**
 * Created by seo on 3/20/18.
 */

public class AndroidToGameTranslator {
    private int numRow, numCol, androidBoardSize;
    public AndroidToGameTranslator(int numRow, int numCol) {
        this.numRow = numRow;
        this.numCol = numCol;
        this.androidBoardSize = numRow * numCol;
    }
    public int[] androidToBoard(int androidPosition) {
        return new int[] {androidPosition/numRow, androidPosition%numRow};
    }
    public int boardToAndroid(int row, int col) {
        return row * numRow + col;
    }
}

import java.util.ArrayList;
import java.util.List;

class Board {
    private static final int DIMENSION_OF_ARRAY = 3;
    private static final int MAX_NUMBER_OF_SQUARES = 9;
    private static final int WINNING_ROWS = 8;
    private static final int WINNING_COLUMNS = 3;

    private int remaining = MAX_NUMBER_OF_SQUARES;
    private final String[][] arr = new String[DIMENSION_OF_ARRAY][DIMENSION_OF_ARRAY];



    public String[][] getArr(){
        return arr;
    }
    final List<Integer> freeSpots = new ArrayList<>();

    List<Integer> getFreeSpots(){
        return freeSpots;
    }

    void clearFreeSpotsList(){
        freeSpots.clear();
    }

    int getFreeSpotsSize(){
        return freeSpots.size();
    }
    private final int[][] winningCombination = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {3, 5, 7}
    };
    BoardFreeSpots boardFreeSpots;

    public Board() {
        boardFreeSpots = new BoardFreeSpots();
    }

    public void updateBoard(int move, String symbol) {
        for (String[] row : arr) {
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                if (row[colIdx].equals(String.valueOf(move))) {
                    row[colIdx] = symbol;
                    remaining--;
                }
            }
        }
    }

    public boolean checkEndState() {
        if (remaining <= 4) {
            for (int i = 0; i < WINNING_ROWS; i++) {
                int counterO = 0;
                int counterX = 0;
                for (int j = 0; j < WINNING_COLUMNS; j++) {
                    int elemFromWinningCombo = winningCombination[i][j];
                    elemFromWinningCombo -= 1;
                    String found = arr[elemFromWinningCombo / 3][elemFromWinningCombo % 3];
                    if (found.equals("O")) {
                        counterO++;
                    }
                    if (found.equals("X")) {
                        counterX++;
                    }
                }
                if (counterX == 3 || counterO == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[][] getBoardCopy() {
        String[][] copy = new String[DIMENSION_OF_ARRAY][DIMENSION_OF_ARRAY];
        for (int i = 0; i < DIMENSION_OF_ARRAY; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, DIMENSION_OF_ARRAY);
        }
        return copy;
    }

    public boolean isBoardFull() {
        return boardFreeSpots.getNumberOfFreeSpots(this) == 0;
    }
}



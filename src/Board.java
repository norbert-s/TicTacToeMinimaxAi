import java.util.ArrayList;
import java.util.List;

class Board extends BoardBase{
    private static final int DIMENSION_OF_ARRAY = 3;
    private static final int MAX_NUMBER_OF_SQUARES = 9;
    private static final int WINNING_ROWS = 8;
    private static final int WINNING_COLUMNS = 3;

    private int remaining = MAX_NUMBER_OF_SQUARES;
    private final char[][] arr = new char[DIMENSION_OF_ARRAY][DIMENSION_OF_ARRAY];

    public char[][] getArr() {
        return arr;
    }

    final List<Integer> freeSpots = new ArrayList<>();

    List<Integer> getFreeSpots() {
        return freeSpots;
    }

    void clearFreeSpotsList() {
        freeSpots.clear();
    }

    public Board() {
        boardFreeSpots = new BoardFreeSpots();
    }

    public void updateBoard(int move, char symbol) {
        for (char[] row : arr) {
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                if (row[colIdx] == Character.forDigit(move, 10)) {
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
                    int elemFromWinningCombo = winningCombinations[i][j];
                    elemFromWinningCombo -= 1;
                    char found = arr[elemFromWinningCombo / 3][elemFromWinningCombo % 3];
                    if (found == 'O') {
                        counterO++;
                    }
                    if (found == 'X') {
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

    public char[][] getBoardCopy() {
        char[][] copy = new char[DIMENSION_OF_ARRAY][DIMENSION_OF_ARRAY];
        for (int i = 0; i < DIMENSION_OF_ARRAY; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, DIMENSION_OF_ARRAY);
        }
        return copy;
    }

    public boolean isBoardFull() {
        return boardFreeSpots.getNumberOfFreeSpots(this.getArr()) == 0;
    }
}



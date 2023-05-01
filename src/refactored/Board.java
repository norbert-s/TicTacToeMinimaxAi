package refactored;

import java.util.ArrayList;
import java.util.List;

class Board {
    private static final int DIMENSION_OF_ARRAY = 3;
    private static final int MAX_NUMBER_OF_SQUARES = 9;
    private static final int WINNING_ROWS = 8;
    private static final int WINNING_COLUMNS = 3;

    private int remaining = MAX_NUMBER_OF_SQUARES;
    private String[][] arr = new String[DIMENSION_OF_ARRAY][DIMENSION_OF_ARRAY];
    private List<Integer> freeSpots = new ArrayList<>();
    private int[][] winningCombination = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {3, 5, 7}
    };

    public Board() {
        fillStringArrWithNumbers();
    }

    public void fillStringArrWithNumbers() {
        int count = 1;
        for (String[] row : arr) {
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                row[colIdx] = String.valueOf(count++);
            }
        }
    }

    public void drawBoard() {
        for (String[] row : arr) {
            System.out.print("|");
            for (String col : row) {
                System.out.print(col + "|");
            }
            System.out.println();
        }
    }

    public boolean isSpotFree(int spot) {
        createListOfCurrentFreeSpots();
        for (int i : freeSpots) {
            if (i == spot) {
                return true;
            }
        }
        return false;
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

    private void createListOfCurrentFreeSpots() {
        freeSpots.clear();
        for (String[] row : arr) {
            for (String col : row) {
                if (!col.equals("X") && !col.equals("O")) {
                    freeSpots.add(Integer.parseInt(col));
                }
            }
        }
    }
    public String[][] getBoardCopy() {
        String[][] copy = new String[DIMENSION_OF_ARRAY][DIMENSION_OF_ARRAY];
        for (int i = 0; i < DIMENSION_OF_ARRAY; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, DIMENSION_OF_ARRAY);
        }
        return copy;
    }

    public int getNumberOfFreeSpots() {
        createListOfCurrentFreeSpots();
        return freeSpots.size();
    }
    public boolean isBoardFull() {
        return getNumberOfFreeSpots() == 0;
    }
}



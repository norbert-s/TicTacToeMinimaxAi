package board;

import board.Board;

public class BoardFreeSpots {
    private Board createListOfCurrentFreeSpots(Board board) {
        board.clearFreeSpotsList();
        for (char[] row : board.getArr()) {
            for (char col : row) {
                if (col != 'X' && col != 'O') {
                    board.getFreeSpots().add(Character.getNumericValue(col));
                }
            }
        }
        return board;
    }

    public boolean isSpotFree(Board board, int spot) {
        for (int i : createListOfCurrentFreeSpots(board).getFreeSpots()) {
            if (i == spot) {
                return true;
            }
        }
        return false;
    }

    //ai uses it
    public int getNumberOfFreeSpots(char[][] board) {
        int countFreeSpots = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    countFreeSpots++;
                }
            }
        }
        return countFreeSpots;
    }
}
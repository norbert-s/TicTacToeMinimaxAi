public class BoardFreeSpots {

    public int getNumberOfFreeSpots(Board board) {
        return createListOfCurrentFreeSpots(board).getFreeSpotsSize();
    }

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
}
public class BoardFreeSpots {

    public int getNumberOfFreeSpots(Board board) {
        return createListOfCurrentFreeSpots(board).getFreeSpotsSize();
    }
    private Board createListOfCurrentFreeSpots(Board board) {
        board.clearFreeSpotsList();
        for (String[] row : board.getArr()) {
            for (String col : row) {
                if (!col.equals("X") && !col.equals("O")) {
                    board.getFreeSpots().add(Integer.parseInt(col));
                }
            }
        }
        return board;
    }
    public boolean isSpotFree(Board board,int spot) {
        for (int i : createListOfCurrentFreeSpots(board).getFreeSpots()) {
            if (i == spot) {
                return true;
            }
        }
        return false;
    }
}

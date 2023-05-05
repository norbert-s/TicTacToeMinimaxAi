package board;

public class SetupBoard {
    public static Board fillTheBoard(Board board) {
        int count = 1;
        for (char[] row : board.getArr()) {
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                row[colIdx] = Character.forDigit(count++, 10);
            }
        }
        return board;
    }
}

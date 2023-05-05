import java.util.HashMap;
import java.util.Map;


public class AIBoard {

    private final char computerSymbol;
    private final char humanSymbol;

    private int NUMBER_OF_ROWS = 3;

    private int NUMBER_OF_COLUMNS = 3;

    private final int[][] winningCombinations = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {3, 5, 7}
    };

    private final Map<String, Integer> SCORING_PLAYER_O_MOVES = new HashMap<>() {{
        put("X", -10);
        put("O", 10);
        put("tie", 0);
    }};

    private final Map<String, Integer> SCORING_PLAYER_X_MOVES = new HashMap<>() {{
        put("X", 10);
        put("O", -10);
        put("tie", 0);
    }};

    public AIBoard(char computerSymbol, char humanSymbol) {
        this.computerSymbol = computerSymbol;
        this.humanSymbol = humanSymbol;
    }

    public int calculateComputerMove(char[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                if (board[i][j] != 'O' && board[i][j] != 'X') {
                    char temp = board[i][j];
                    board[i][j] = computerSymbol;
                    int score = minimax(board, 9, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    board[i][j] = temp;
                    if (score > bestScore) {
                        bestScore = score;
                        move = Character.getNumericValue(temp);
                    }
                }
            }
        }
        return move;
    }

    private int minimax(char[][] board, int depth, int alpha, int beta, boolean isMaximizing) {
        String result = checkIfEndState(board);

        if (depth == 0 || !result.equals("false")) {
            return computerSymbol == 'O' ? SCORING_PLAYER_O_MOVES.get(result) : SCORING_PLAYER_X_MOVES.get(result);
        }

        if (isMaximizing) {
            int maxEvaluation = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'O' && board[i][j] != 'X') {
                        char temp = board[i][j];
                        board[i][j] = computerSymbol;
                        int evaluation = minimax(board, depth - 1, alpha, beta, false);
                        board[i][j] = temp;
                        maxEvaluation = Math.max(maxEvaluation, evaluation);
                        alpha = Math.max(alpha, evaluation);
                        if (beta <= alpha) {
                            return maxEvaluation;
                        }
                    }
                }
            }
            return maxEvaluation;
        } else {
            int minEvaluation = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'O' && board[i][j] != 'X') {
                        char temp = board[i][j];
                        board[i][j] = humanSymbol;
                        int evaluation = minimax(board, depth - 1, alpha, beta, true);
                        board[i][j] = temp;
                        minEvaluation = Math.min(minEvaluation, evaluation);
                        beta = Math.min(beta, evaluation);
                        if (beta <= alpha) {
                            return minEvaluation;
                        }
                    }
                }
            }
            return minEvaluation;
        }
    }

    private String checkIfEndState(char[][] board) {
        int remaining = countFreeSpots(board);

        if (remaining >= 0 && remaining <= 5) {
            for (int[] winningCombination : winningCombinations) {
                int counterO = 0;
                int counterX = 0;
                for (int j = 0; j < winningCombination.length; j++) {
                    int elemFromWinningCombination = winningCombination[j] - 1;
                    char found = board[elemFromWinningCombination / 3][elemFromWinningCombination % 3];
                    if (found == 'O') {
                        counterO++;
                    }
                    if (found == 'X') {
                        counterX++;
                    }
                }
                if (counterX == 3) {
                    return "X";
                } else if (counterO == 3) {
                    return "O";
                }
            }
        }
        if (remaining > 0) {
            return "false";
        } else {
            return "tie";
        }
    }

    private int countFreeSpots(char[][] board) {
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


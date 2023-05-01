package refactored;

import java.util.HashMap;
import java.util.Map;


public class AI {

    private String computerHere;
    private String humanHere;

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

    private Map<String, Integer> scores = new HashMap<>() {{
        put("X", -10);
        put("O", 10);
        put("tie", 0);
    }};

    private Map<String, Integer> scoresX = new HashMap<>() {{
        put("X", 10);
        put("O", -10);
        put("tie", 0);
    }};

    public AI(String computerSign, String humanSign) {
        computerHere = computerSign;
        humanHere = humanSign;
    }

    String getOpponentSymbol(){
        return humanHere;
    }

    public String bestMove(String[][] arrCopy) {
        int bestScore = -100;
        String move = "";
        int alpha = -1000;
        int beta = 1000;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!arrCopy[i][j].equals("O") && !arrCopy[i][j].equals("X")) {
                    String temp = arrCopy[i][j];
                    arrCopy[i][j] = computerHere;
                    int score = minimax(arrCopy, 9, alpha, beta, false);
                    arrCopy[i][j] = temp;
                    if (score > bestScore) {
                        bestScore = score;
                        move = temp;
                    }
                }
            }
        }
        return move;
    }

    public int calculateComputerMove(String[][] arr, String computerSign, String humanSign) {
        System.out.println("Computer sign: " + computerSign + " Human sign: " + humanSign);
        String thisIsTheMove = bestMove(arr);
        System.out.println("This is the move: " + thisIsTheMove);
        int thisIsTheChosenMove = Integer.parseInt(thisIsTheMove);
        return thisIsTheChosenMove;
    }

    public int minimax(String[][] arrCopy, int depth, int alpha, int beta, boolean isMaximizing) {
        String result = checkIfEndState(arrCopy);

        if (depth == 0 || !result.equals("false")) {
            if (computerHere.equals("O")) {
                return scores.get(result);
            } else {
                return scoresX.get(result);
            }
        }

        if (isMaximizing) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!arrCopy[i][j].equals("O") && !arrCopy[i][j].equals("X")) {
                        String temp = arrCopy[i][j];
                        arrCopy[i][j] = computerHere;
                        alpha = Math.max(alpha, minimax(arrCopy, depth - 1, alpha, beta, false));
                        arrCopy[i][j] = temp;

                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return alpha;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!arrCopy[i][j].equals("O") && !arrCopy[i][j].equals("X")) {
                        String temp = arrCopy[i][j];
                        arrCopy[i][j] = humanHere;
                        beta = Math.min(beta, minimax(arrCopy, depth - 1, alpha, beta, true));
                        arrCopy[i][j] = temp;

                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return beta;
        }
    }

    public String checkIfEndState(String[][] arrCopy) {
        int remaining = countFreeSpots(arrCopy);

        if (remaining >= 0 && remaining <= 5) {
            for (int[] winningCombination : winningCombinations) {
                int counterO = 0;
                int counterX = 0;
                for (int j = 0; j < winningCombination.length; j++) {
                    int elemFromWinningCombo = winningCombination[j];
                    elemFromWinningCombo -= 1;
                    String found = arrCopy[elemFromWinningCombo / 3][elemFromWinningCombo % 3];
                    if (found.equals("O")) {
                        counterO++;
                    }
                    if (found.equals("X")) {
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

    public int countFreeSpots(String[][] arrCopy) {
        int countFreeSpots = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!arrCopy[i][j].equals("X") && !arrCopy[i][j].equals("O")) {
                    countFreeSpots++;
                }
            }
        }
        return countFreeSpots;
    }
}


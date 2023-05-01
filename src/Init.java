import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Init {
    private AI ai;
    private static final int DIMENSION_OF_ARRAY = 3;
    private static final int MAX_NUMBER_OF_SQUARES = 9;
    private static final int WINNING_ROWS = 8;
    private static final int WINNING_COLUMNS = 3;

    private int remaining = MAX_NUMBER_OF_SQUARES;
    private String beginner = "";
    private String whoIsNext = "";

    private int chosenMove = -1;
    private List<Integer> freeSpots = new ArrayList<>();
    private int counterX = 0;
    private int counterO = 0;

    public String player = "";
    public String computer = "";
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
    private String[][] arr = new String[DIMENSION_OF_ARRAY][DIMENSION_OF_ARRAY];
    private Scanner scanner = new Scanner(System.in);

    Init() {
        fillStringArrWithNumbers();
        drawBoardOnScreen();
        chooseSide();
        gameDriver();
    }

    public static void main(String[] args) {
       new Init();
    }

    public void gameDriver() {
        while (remaining != 0) {
            takeMoveFromPlayerOrCalculateComputerMove();
            drawBoardOnScreen();
            boolean end = false;
            end = checkIfEndState();
            if (end) {
                gameOver();
                break;
            } else {
                whoComesNext();
            }
        }
    }

    public void fillStringArrWithNumbers() {
        int count = 1;
        for (String[] row : arr) {
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                row[colIdx] = String.valueOf(count++);
            }
        }
    }

    public void chooseSide() {
        boolean chosenOk = false;
        while (!chosenOk) {
            System.out.println("Which sign do you choose, 'X' or 'O'?");
            player = scanner.next();
            if (player.equals("X") || player.equals("O")) {
                chosenOk = true;
                computer = player.equals("X") ? "O" : "X";
            }
        }
        System.out.println("Player is " + player);
        beginner = player.equals("X") ? "player" : "Computer";
        System.out.println("The first move is of " + beginner);
        whoIsNext = beginner;
    }

    public void drawBoardOnScreen() {
        for (String[] row : arr) {
            System.out.print("|");
            for (String col : row) {
                System.out.print(col + "|");
            }
            System.out.println();
        }
    }


    public void whoComesNext() {
        if (remaining == 0) {
            gameOver();
        }
        if (whoIsNext.equals("player")) {
            whoIsNext = "Computer";
        } else {
            whoIsNext = "player";
        }
    }

    public void takeMoveFromPlayerOrCalculateComputerMove() {
        if (remaining <= MAX_NUMBER_OF_SQUARES && remaining > 0) {
            System.out.println("Next move is of " + whoIsNext);
        }
        if (whoIsNext.equals("player")) {
            System.out.println("What's your next move?");
            pushChosenSpotToArray();
        } else {
            System.out.println("Computer is thinking");

            int moveToPush = calculateComputerMove(arr, computer, player);
            System.out.println("Chosen move in takeFromPlayer " + moveToPush);
            pushComputerMoveToArray(moveToPush);
        }
    }

    public void pushComputerMoveToArray(int moveToPush) {
        while (true) {
            System.out.println("Is the spot free? " + checkIfSpotIsFree(moveToPush));
            if (moveToPush >= 1 && moveToPush <= MAX_NUMBER_OF_SQUARES && checkIfSpotIsFree(moveToPush)) {
                for (String[] row : arr) {
                    for (int colIdx = 0; colIdx < row.length; colIdx++) {
                        if (row[colIdx].equals(String.valueOf(moveToPush))) {
                            row[colIdx] = whoIsNext.equals("player") ? player : computer;
                        }
                    }
                }
                remaining--;
                break;
            } else {
                System.out.println("Choose another number");
                calculateComputerMove(arr, computer, player);
            }
        }
    }

    public int calculateComputerMove(String[][] arr, String computerSign, String humanSign) {
        if (ai == null) ai = new AI(computerSign, humanSign);
        return ai.calculateComputerMove(arr, computerSign, humanSign);
    }

    public void pushChosenSpotToArray() {
        while (true) {
            chosenMove = scanner.nextInt();
            createListOfCurrentFreeSpots();
            if (chosenMove >= 1 && chosenMove <= 9 && checkIfSpotIsFree(chosenMove)) {
                for (String[] row : arr) {
                    for (int colIdx = 0; colIdx < row.length; colIdx++) {
                        if (row[colIdx].equals(String.valueOf(chosenMove))) {
                            row[colIdx] = whoIsNext.equals("player") ? player : computer;
                        }
                    }
                }
                System.out.println(checkIfSpotIsFree(chosenMove));
                System.out.println(freeSpots.size());
                remaining--;
                break;
            } else {
                System.out.println("Choose another spot!");
            }
        }
    }

    public void createListOfCurrentFreeSpots() {
        freeSpots.clear();
        for (String[] row : arr) {
            for (String col : row) {
                if (!col.equals("X") && !col.equals("O")) {
                    freeSpots.add(Integer.parseInt(col));
                }
            }
        }
    }

    public boolean checkIfSpotIsFree(int spot) {
        createListOfCurrentFreeSpots();
        for (int i : freeSpots)
            if (i == spot)
                return true;
        return false;
    }

    public void gameOver() {
        try {
            if (counterX < 3 && counterO < 3) {
                System.out.println("It is a draw");
                TimeUnit.SECONDS.sleep(5);
            } else if ((counterX == 3 && player.equals("X")) || (counterO == 3 && player.equals("O"))) {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Player has won");
            } else if ((counterX == 3 && !player.equals("X")) || (counterO == 3 && !player.equals("O"))) {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Computer has won");
            }
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Game over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfEndState() {
        if (remaining <= 4) {
            for (int i = 0; i < WINNING_ROWS; i++) {
                counterO = 0;
                counterX = 0;
                for (int j = 0; j < WINNING_COLUMNS; j++) {
                    int elemFromWinningCombo = winningCombination[i][j];
                    elemFromWinningCombo -= 1;
                    String found = arr[elemFromWinningCombo / 3][elemFromWinningCombo % 3];
                    if (found.equals("O"))
                        counterO++;
                    if (found.equals("X"))
                        counterX++;
                }
                if (counterX == 3 || counterO == 3)
                    return true;
            }
        }
        return false;
    }
}
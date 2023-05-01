package refactored;

import java.util.Scanner;

public class Game {
    private Board board;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private String currentPlayer;
    private String beginner;
    private Scanner scanner = new Scanner(System.in);

    public Game() {
        board = new Board();
        chooseSide();
        board.draw();
        play();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void chooseSide() {
        boolean chosenOk = false;
        while (!chosenOk) {
            System.out.println("Which sign do you choose, 'X' or 'O' (Kindly type the alphabet 'o' or 'O', zero is not accepted!)?");
            String playerSymbol = scanner.next();
            if (playerSymbol.equalsIgnoreCase("X") || playerSymbol.equalsIgnoreCase("O")) {
                chosenOk = true;
                playerSymbol = playerSymbol.toUpperCase();
                String computerSymbol = playerSymbol.equals("X") ? "O" : "X";
                humanPlayer = new HumanPlayer(playerSymbol);
                computerPlayer = new ComputerPlayer(computerSymbol, playerSymbol);
            }
        }
        System.out.println("Player is " + humanPlayer.getSymbol());
        beginner = humanPlayer.getSymbol().equals("X") ? "player" : "Computer";
        System.out.println(beginner + " starts");
        currentPlayer = beginner;
    }

    private void play() {
        while (!board.checkEndState() && !isBoardFull()) {
            if (currentPlayer.equals("player")) {
                int move = humanPlayer.makeMove();
                if (board.isSpotFree(move)) {
                    board.updateBoard(move, humanPlayer.getSymbol());
                    board.draw();
                    currentPlayer = "Computer";
                } else {
                    System.out.println("Choose another spot!");
                }
            } else {
                int move = computerPlayer.makeMove(board.getBoardCopy());
                if (board.isSpotFree(move)) {
                    System.out.println("Computer's move is " + move);
                    board.updateBoard(move, computerPlayer.getSymbol());
                    board.draw();
                    currentPlayer = "player";
                } else {
                    System.out.println("Computer chose an invalid move, please try again!");
                }
            }
        }
        if (board.checkEndState()) {
            if (currentPlayer.equals("player")) {
                System.out.println("Computer wins!");
            } else {
                System.out.println("Player wins!");
            }
        } else {
            System.out.println("It's a draw!");
        }
    }

    private boolean isBoardFull() {
        return board.getNumberOfFreeSpots() == 0;
    }
}
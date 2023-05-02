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
        board.drawBoard();
        play();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void chooseSide() {
        while (true) {
            System.out.println("Which sign do you choose, 'X' or 'O' ?");
            String playerSymbol = scanner.next();
            if (playerSymbol.equalsIgnoreCase("X") || playerSymbol.equalsIgnoreCase("O")) {

                playerSymbol = playerSymbol.toUpperCase();
                String computerSymbol = playerSymbol.equals("X") ? "O" : "X";
                humanPlayer = new HumanPlayer(playerSymbol);
                computerPlayer = new ComputerPlayer(computerSymbol, playerSymbol);
                break;
            }
        }
        System.out.println("Player is " + humanPlayer.getSymbol());
        beginner = humanPlayer.getSymbol().equals("X") ? "player" : "Computer";
        System.out.println(beginner + " starts");
        currentPlayer = beginner;
    }

    private void play() {
        while (!board.checkEndState() && !board.isBoardFull()) {
            if (currentPlayer.equals("player")) {
                int move = humanPlayer.makeMove();
                if (board.isSpotFree(move)) {
                    board.updateBoard(move, humanPlayer.getSymbol());
                    board.drawBoard();
                    currentPlayer = "Computer";
                } else {
                    System.out.println("Choose another spot!");
                }
            } else {
                int move = computerPlayer.makeMove(board.getBoardCopy());
                if (board.isSpotFree(move)) {
                    System.out.println("Computer's move is " + move);
                    board.updateBoard(move, computerPlayer.getSymbol());
                    board.drawBoard();
                    currentPlayer = "player";
                } else {
                    //for debugging purpose
                    throw new RuntimeException("Illegal computer move");
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


}
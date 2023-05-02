import java.util.Scanner;

public class Game {
    private Board board;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private String currentPlayer;
    private String beginner;
    private Scanner scanner = new Scanner(System.in);

    private SetupGame setupGame;

    public HumanPlayer getHumanPlayer() {
        return humanPlayer;
    }

    public void setHumanPlayer(HumanPlayer humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    public void setComputerPlayer(ComputerPlayer computerPlayer) {
        this.computerPlayer = computerPlayer;
    }


    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getBeginner() {
        return beginner;
    }

    public void setBeginner(String beginner) {
        this.beginner = beginner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Game() {
        setupGame = new SetupGame();
        setupGame.chooseSide(this);
        board = new Board();
        board.drawBoard();
        play();
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
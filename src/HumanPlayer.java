import java.util.Scanner;


public class HumanPlayer {
    private final char symbol;
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(char symbol) {
        this.symbol = symbol;
    }


    public int makeMove() {
        System.out.println("What's your next move?");
        return scanner.nextInt();
    }

    public char getSymbol() {
        return symbol;
    }
}
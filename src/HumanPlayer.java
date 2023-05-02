import java.util.Scanner;


public class HumanPlayer {
    private final String symbol;
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String symbol) {
        this.symbol = symbol;
    }


    public int makeMove() {
        System.out.println("What's your next move?");
        return scanner.nextInt();
    }

    public String getSymbol() {
        return symbol;
    }
}
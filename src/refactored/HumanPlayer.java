package refactored;

import java.util.Scanner;


public class HumanPlayer {
    private String symbol;
    private Scanner scanner = new Scanner(System.in);

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
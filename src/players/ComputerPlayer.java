package players;

import ai.AIBoard;

public class ComputerPlayer {
    private final char symbol;
    private final AIBoard ai;

    public ComputerPlayer(char symbol, char opponentSymbol) {
        this.symbol = symbol;
        this.ai = new AIBoard(symbol, opponentSymbol);
    }

    public int makeMove(char[][] arr) {
        return ai.calculateComputerMove(arr);
    }

    public char getSymbol() {
        return symbol;
    }
}
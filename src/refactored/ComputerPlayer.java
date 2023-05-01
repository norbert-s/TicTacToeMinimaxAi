package refactored;

public class ComputerPlayer {
    private String symbol;
    private AI ai;

    public ComputerPlayer(String symbol, String opponentSymbol) {
        this.symbol = symbol;
        this.ai = new AI(symbol, opponentSymbol);
    }

    public int makeMove(String[][] arr) {
        return ai.calculateComputerMove(arr, symbol, ai.getOpponentSymbol());
    }

    public String getSymbol() {
        return symbol;
    }
}
public class ComputerPlayer {
    private final String symbol;
    private final AIBoard ai;

    public ComputerPlayer(String symbol, String opponentSymbol) {
        this.symbol = symbol;
        this.ai = new AIBoard(symbol, opponentSymbol);
    }

    public int makeMove(String[][] arr) {
        return ai.calculateComputerMove(arr);
    }

    public String getSymbol() {
        return symbol;
    }
}
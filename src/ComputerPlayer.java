public class ComputerPlayer {
    private String symbol;
    private AI ai;

    public ComputerPlayer(String symbol, String opponentSymbol) {
        this.symbol = symbol;
        this.ai = new AI(symbol, opponentSymbol);
    }

    public int makeMove(String[][] arr) {
        return ai.calculateComputerMove(arr);
    }

    public String getSymbol() {
        return symbol;
    }
}
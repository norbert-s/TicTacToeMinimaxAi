public class InitGame {
    public static Game chooseSide(Game gameInstance) {
        while (true) {
            System.out.println("Which sign do you choose, 'X' or 'O' ?");
            String playerSymbol = gameInstance.getScanner().next();
            if (playerSymbol.equalsIgnoreCase("X") || playerSymbol.equalsIgnoreCase("O")) {

                playerSymbol = playerSymbol.toUpperCase();
                String computerSymbol = playerSymbol.equals("X") ? "O" : "X";
                gameInstance.setHumanPlayer(new HumanPlayer(playerSymbol));
                gameInstance.setComputerPlayer(new ComputerPlayer(computerSymbol, playerSymbol));
                break;
            }
        }
        System.out.println("Player is " + gameInstance.getHumanPlayer().getSymbol());
        gameInstance.setBeginner(gameInstance.getHumanPlayer().getSymbol().equals("X") ? "player" : "Computer");
        System.out.println(gameInstance.getBeginner() + " starts");
        gameInstance.setCurrentPlayer(gameInstance.getBeginner());
        return gameInstance;
    }
    public static Board fillTheBoard(Board board) {
        int count = 1;
        for (String[] row : board.getArr()) {
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                row[colIdx] = String.valueOf(count++);
            }
        }
        return board;
    }
}

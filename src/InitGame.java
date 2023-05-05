public class InitGame {
    public static Game chooseSide(Game gameInstance) {
        while (true) {
            System.out.println("Which sign do you choose, 'X' or 'O' ?");
            String playerSymbol = gameInstance.getScanner().next();
            if (playerSymbol.equalsIgnoreCase("X") || playerSymbol.equalsIgnoreCase("O")) {

                playerSymbol = playerSymbol.toUpperCase();
                String computerSymbol = playerSymbol.equals("X") ? "O" : "X";
                gameInstance.setHumanPlayer(new HumanPlayer(playerSymbol.charAt(0)));
                gameInstance.setComputerPlayer(new ComputerPlayer(computerSymbol.charAt(0), playerSymbol.charAt(0)));
                break;
            }
        }
        System.out.println("Player is " + gameInstance.getHumanPlayer().getSymbol());
        gameInstance.setBeginner(gameInstance.getHumanPlayer().getSymbol() == 'X' ? "player" : "Computer");
        System.out.println(gameInstance.getBeginner() + " starts");
        gameInstance.setCurrentPlayer(gameInstance.getBeginner());
        return gameInstance;
    }

    public static Board fillTheBoard(Board board) {
        int count = 1;
        for (char[] row : board.getArr()) {
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                row[colIdx] = Character.forDigit(count++, 10);
            }
        }
        return board;
    }
}
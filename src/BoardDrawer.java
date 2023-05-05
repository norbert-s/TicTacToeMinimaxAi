public class BoardDrawer {
    public static void draw(char[][] arr) {
        for (char[] row : arr) {
            System.out.print("|");
            for (char col : row) {
                System.out.print(col + "|");
            }
            System.out.println();
        }
    }
}
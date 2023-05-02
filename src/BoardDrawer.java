public class BoardDrawer {
    public static void draw(String [][]arr) {
        for (String[] row : arr) {
            System.out.print("|");
            for (String col : row) {
                System.out.print(col + "|");
            }
            System.out.println();
        }
    }
}

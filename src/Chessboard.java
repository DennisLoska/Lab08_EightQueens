public class Chessboard {

    public int[][] chessBoard = new int[8][8];

    /*
        Konstruktor
     */
    public Chessboard(){
        createBoard(chessBoard);
    }

    /*
        Main-Methode zum Erstellen des Bretts.
     */
    public static void main(String[] args){
        Chessboard board = new Chessboard();
        board.selectField(3,6);
        System.out.println(board.selectField(2,2));
    }

    /*
        Erstellt ein Schachbrett aus ints, wo die 0 für "weiß" und 1 für "schwarz steht. Dieses wird dann in der Konsole angezeigt.
     */
    private void createBoard(int[][] chessBoard){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i%2==0) {
                    if (j % 2 == 0) chessBoard[i][j] = 0;
                    else chessBoard[i][j] = 1;
                } else {
                    if (j % 2 != 0) chessBoard[i][j] = 0;
                    else chessBoard[i][j] = 1;
                }
                System.out.print(chessBoard[i][j]+" ");
                if (j==7) System.out.print("\n");
            }
        }
    }

    private int selectField(int column, int row){
        return chessBoard[row-1][column-1];
    }
}

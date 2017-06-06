import java.util.ArrayList;

public class Chessboard {

    private ArrayList<ArrayList<ChessField>> chessBoard = new ArrayList<ArrayList<ChessField>>();
    private static Chessboard board;
    private boolean flag;


    /*
        Konstruktor
     */
    public Chessboard() {
        createBoard(chessBoard);
    }

    /*
        Main-Methode zum Erstellen des Bretts.
     */
    public static void main(String[] args) {
        board = new Chessboard();
        board.putQueen(1, 1);
        board.putQueen(5, 5);
        board.displayBoard();
        board.checkThreat();
    }

    /*
        Erstellt ein Schachbrett aus ints, wo "display 0 für "weiß" und 1 für "schwarz steht. Dieses wird dann in der Konsole angezeigt.
     */
    private void createBoard(ArrayList<ArrayList<ChessField>> chessBoard) {
        for (int row = 0; row < 8; row++) {
            ArrayList<ChessField> rowList;
            chessBoard.add(row, rowList = new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        ChessField field = new ChessField(col, row);
                        chessBoard.get(row).add(col, field);
                        field.setDisplay(0);
                    } else {
                        ChessField field = new ChessField(col, row);
                        chessBoard.get(row).add(col, field);
                        field.setDisplay(1);
                    }
                } else {
                    if (col % 2 != 0) {
                        ChessField field = new ChessField(col, row);
                        chessBoard.get(row).add(col, field);
                        field.setDisplay(0);
                    } else {
                        ChessField field = new ChessField(col, row);
                        chessBoard.get(row).add(col, field);
                        field.setDisplay(1);
                    }
                }
            }
        }
    }

    private void displayBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print(chessBoard.get(row).get(col).getDisplay() + " ");
                if (col == 7) System.out.print("\n");
            }
        }
    }

    /*
        Gibt das entsprechende Schachfeld zurück (-1 da ArrayList bei 0 anfängt)
     */
    private ChessField getField(int row, int column) {
        return chessBoard.get(row - 1).get(column - 1);
    }

    /*
        Das Feld wird anhand column und row gewählt und der boolean-wert für die Dame des Feldes auf "true" gesetzt
     */
    private void putQueen(int row, int column) {
        chessBoard.get(row - 1).get(column - 1).setHasQueen(true);
        chessBoard.get(row - 1).get(column - 1).setDisplay(2);
    }

    private void checkThreat() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board.getField(r + 1, c + 1).hasQueen()) {

                    // horizontal check
                    for (int i = c + 1; i < 8; i++) {
                        if (board.getField(r + 1, i + 1).hasQueen()) {
                            flag = true;
                            System.out.println("found horizontal!");
                        }
                    }

                    // vertical check
                    for (int i = r + 1; i < 8; i++) {
                        if (board.getField(i + 1, c + 1).hasQueen()) {
                            flag = true;
                            System.out.println("found vertical!");
                        }
                    }

                    // descending diagonal check
                    for (int i = r++; i < 8; i++) {
                        if (board.getField(i + 1, i + 1).hasQueen()) {
                            flag = true;
                            System.out.println("found diagonally!");
                        }
                    }

                    // ascending diagonal check

                }
            }
        }
    }

}

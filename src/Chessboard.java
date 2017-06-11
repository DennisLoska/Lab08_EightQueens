import java.util.ArrayList;

public class Chessboard {

    private ArrayList<ArrayList<ChessField>> chessBoard = new ArrayList<ArrayList<ChessField>>();
    private static Chessboard board;

    /*
        Konstruktor:
     */
    public Chessboard() {
        createBoard(chessBoard);
    }

    /*
        Main-Methode zum Erstellen des Bretts.
     */
    public static void main(String[] args) {
        board = new Chessboard();
        //fixing problem by using recursion in backTrackQueens()
        board.displayBoard();
        board.backTrackQueens();
    }

    /*
        Erstellt ein Schachbrett aus ints, wo "display 0 für "weiß" und 1 für "schwarz"
        steht. Dieses wird dann in der Konsole angezeigt. **AUFGABE 1**
     */
    private void createBoard(ArrayList<ArrayList<ChessField>> chessBoard) {
        for (int row = 0; row < 8; row++) {
            chessBoard.add(row, new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        createChessField(col, row, 0);
                    } else {
                        createChessField(col, row, 1);
                    }
                } else {
                    if (col % 2 != 0) {
                        createChessField(col, row, 0);
                    } else {
                        createChessField(col, row, 1);
                    }
                }
            }
        }
    }

    /*
        displays the chessboard: 0: white 1: black 2: queen
     */
    private void displayBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print(chessBoard.get(row).get(col).getDisplay() + " ");
                if (col == 7) System.out.print("\n");
            }
        }
        System.out.println();
    }

    /*
        creates a single ChessField object and adds it to the chessBoard
     */
    private void createChessField(int col, int row, int n) {
        ChessField field = new ChessField(col, row);
        chessBoard.get(row).add(col, field);
        field.setDisplay(n);
    }

    /*
        Gibt das entsprechende Schachfeld zurück
     */
    private ChessField getField(int row, int column) {
        return chessBoard.get(row).get(column);
    }

    /*
        Das Feld wird anhand column und row gewählt und der boolean-wert für die Dame des Feldes auf "true" gesetzt
     */
    private void putQueen(int row, int column) {
        chessBoard.get(row).get(column).setHasQueen(true);
        chessBoard.get(row).get(column).setDisplay(2);
    }

    /*
        for later usage in recursive method
    */
    private void removeQueen(int row, int column) {
        chessBoard.get(row).get(column).setHasQueen(false);
        if (column % 2 != 0 && row % 2 != 0) chessBoard.get(row).get(column).setDisplay(0);
        else if (column % 2 != 0 && row % 2 != 0) chessBoard.get(row).get(column).setDisplay(0);
        else if (column % 2 != 0 && row % 2 == 0) chessBoard.get(row).get(column).setDisplay(1);
        else if (column % 2 == 0 && row % 2 == 0) chessBoard.get(row).get(column).setDisplay(0);
        else if (column % 2 == 0 && row % 2 != 0) chessBoard.get(row).get(column).setDisplay(1);
    }

    /*
        AUFGABE 3 & 4
     */
    public boolean placeQueens(int queenCounter) {
        if (queenCounter == 8) {
            return true;
        }
        for (int row = 0; row < 8; row++) {
            if (checkThreat(row, queenCounter)) {
                putQueen(row, queenCounter);
                if (placeQueens(queenCounter + 1)) {
                    return true;
                }
                //BACKTRACK
                removeQueen(row, queenCounter);
            }
        }
        return false;

    }

    /*
        Checked, ob Damen sich gefährden **AUFGABE 2**
     */
    private boolean checkThreat(int row, int queenCounter) {
        //vertical
        for (int i = 0; i < queenCounter; i++) {
            if (board.getField(row, i).hasQueen()) {
                return false;
            }
        }
        //top left
        for (int i = row, j = queenCounter; i >= 0 && j >= 0; i--, j--) {
            if (board.getField(i, j).hasQueen()) {
                return false;
            }
        }
        //bottom left
        for (int i = row, j = queenCounter; i < 8 && j >= 0; i++, j--) {
            if (board.getField(i, j).hasQueen()) {
                return false;
            }
        }
        return true;
    }

    private void backTrackQueens() {
        if (placeQueens(0)) {
            System.out.println("\nSoulution: ");
            displayBoard();
        } else {
            System.out.println("\n No Soulution! ");
        }
    }
}

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

        //adding some queens
        board.putQueen(4, 1);
        board.putQueen(5, 2);
        board.putQueen(8, 1);
        board.putQueen(8, 5);
        board.putQueen(4, 5);
        board.putQueen(2, 3);
        board.putQueen(6, 7);
        board.putQueen(3, 3);
        board.putQueen(4, 4);

        board.displayBoard();
        board.checkThreat();
    }

    /*
        Erstellt ein Schachbrett aus ints, wo "display 0 für "weiß" und 1 für "schwarz"
        steht. Dieses wird dann in der Konsole angezeigt.
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

    /*
        Checks weither Queens are threatening each other
     */
    private void checkThreat() {
        int queenCounter = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board.getField(r + 1, c + 1).hasQueen()) {
                    // horizontal check
                    horizontalCheck(c, r, queenCounter);
                    // vertical check
                    verticalCheck(c, r, queenCounter);
                    // asscending diagonal check bottom right
                    diagonalCheckBottomRight(c, r, queenCounter);
                    // descending diagonal check top left
                    diagonalCheckTopLeft(c, r, queenCounter);
                    // asdescending diagonal check bottom left
                    diagonalCheckBottomLeft(c, r, queenCounter);
                    // desascending diagonal check top right
                    diagonalCheckTopRight(c, r, queenCounter);
                }
            }
            queenCounter++;
        }
    }

    private void horizontalCheck(int c, int r, int queenCounter) {
        for (int i = c + 1; i < 8; i++) {
            if (board.getField(r + 1, i + 1).hasQueen()) {
                flag = true;
                ChessField foundField = board.getField(i + 1, c + 1);
                System.out.println("found horizontal! " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
        }
    }

    private void verticalCheck(int c, int r, int queenCounter) {
        for (int i = r + 1; i < 8; i++) {
            if (board.getField(i + 1, c + 1).hasQueen()) {
                flag = true;
                ChessField foundField = board.getField(i + 1, c + 1);
                System.out.println("found vertical! " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
        }
    }

    private void diagonalCheckBottomRight(int c, int r, int queenCounter) {
        int counter1 = c + 1;
        for (int i = r + 1; i < 8; i++) {
            if (i < 0) i = 0;
            if (counter1 < 0) counter1 = 0;
            if (board.getField(i + 1, counter1 + 1).hasQueen()) {
                flag = true;
                ChessField foundField = board.getField(i + 1, counter1 + 1);
                System.out.println("found diagonally! ++ " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
            if (counter1 < 7) counter1++;
        }
    }

    private void diagonalCheckTopLeft(int c, int r, int queenCounter) {
        int counter2 = c - 1;
        for (int i = r - 1; i > 0; i--) {
            if (i < 0) i = 0;
            if (counter2 < 0) counter2 = 0;
            if (board.getField(i + 1, counter2 + 1).hasQueen()) {
                flag = true;
                ChessField foundField = board.getField(i + 1, counter2 + 1);
                System.out.println("found diagonally! -- " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
            if (counter2 > 0) counter2--;
        }
    }

    private void diagonalCheckBottomLeft(int c, int r, int queenCounter) {
        int counter3 = c - 1;
        for (int i = r + 1; i < 8; i++) {
            if (i < 0) i = 0;
            if (counter3 < 0) counter3 = 0;
            if (board.getField(i + 1, counter3 + 1).hasQueen()) {
                flag = true;
                ChessField foundField = board.getField(i + 1, counter3 + 1);
                System.out.println("found diagonally! +- " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
            if (counter3 > 0) counter3--;
        }
    }

    private void diagonalCheckTopRight(int c, int r, int queenCounter) {
        int counter4 = c + 1;
        for (int i = r - 1; i > 0; i--) {
            if (i < 0) i = 0;
            if (counter4 < 0) counter4 = 0;
            if (board.getField(i + 1, counter4 + 1).hasQueen()) {
                flag = true;
                ChessField foundField = board.getField(i + 1, counter4 + 1);
                System.out.println("found diagonally! -+ " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
            if (counter4 < 7) counter4++;
        }
    }
}

import java.util.ArrayList;

public class Chessboard {

    private ArrayList<ArrayList<ChessField>> chessBoard = new ArrayList<ArrayList<ChessField>>();
    private static Chessboard board;
    private boolean isThreat = false;
    private ChessField firstFound;
    private ChessField last;
    private boolean horizontalThreat;
    private boolean verticalThreat;
    private boolean bottomRightThreat;
    private boolean topLeftThreat;
    private boolean bottomLeftThreat;
    private boolean topRightThreat;

    /*
        Getter & Setter:
     */
    public boolean isThreat() {
        return isThreat;
    }

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

        //adding 8 queens
        board.putQueen(4, 1);
        board.putQueen(5, 2);
        board.putQueen(8, 1);
        board.putQueen(8, 5);
        board.putQueen(4, 5);
        board.putQueen(2, 3);
        board.putQueen(6, 7);
        board.putQueen(3, 3);

        //initializing problem/threats
        board.displayBoard();
        board.checkThreat();
        System.out.println(board.isThreat() + "\n");

        //fixing problem by using recursion in backTrackQueens()
        board.backTrackQueens();
        board.displayBoard();
        System.out.println(board.isThreat());
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
        for later usage in recursive method
     */
    private void removeQueen(int row, int column) {
        chessBoard.get(row - 1).get(column - 1).setHasQueen(false);
        if (column % 2 == 0) chessBoard.get(row - 1).get(column - 1).setDisplay(0);
        else chessBoard.get(row - 1).get(column - 1).setDisplay(1);
    }

    /*
        Checked, ob Damen sich gefährden **AUFGABE 2**
     */
    private boolean checkThreat() {
        int queenCounter = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                firstFound = board.getField(r + 1, c + 1);
                if (firstFound.hasQueen()) {
                    last = firstFound;
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
        return isThreat;
    }

    private void horizontalCheck(int c, int r, int queenCounter) {
        for (int i = c + 1; i < 8; i++) {
            if (board.getField(r + 1, i + 1).hasQueen()) {
                isThreat = true;
                horizontalThreat = true;
                ChessField foundField = board.getField(i + 1, c + 1);
                System.out.println("found horizontal! " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
        }
    }

    private void verticalCheck(int c, int r, int queenCounter) {
        for (int i = r + 1; i < 8; i++) {
            if (board.getField(i + 1, c + 1).hasQueen()) {
                isThreat = true;
                verticalThreat = true;
                ChessField foundField = board.getField(i + 1, c + 1);
                System.out.println("found vertical! " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
        }
    }

    private void diagonalCheckBottomRight(int c, int r, int queenCounter) {
        int colCounter = c + 1;
        for (int i = r + 1; i < 8; i++) {
            if (i < 0) i = 0;
            if (colCounter < 0) colCounter = 0;
            if (board.getField(i + 1, colCounter + 1).hasQueen()) {
                isThreat = true;
                bottomRightThreat = true;
                ChessField foundField = board.getField(i + 1, colCounter + 1);
                System.out.println("found diagonally! ++ " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
            if (colCounter < 7) colCounter++;
        }
    }

    private void diagonalCheckTopLeft(int c, int r, int queenCounter) {
        int colCounter = c - 1;
        for (int i = r - 1; i > 0; i--) {
            if (i < 0) i = 0;
            if (colCounter < 0) colCounter = 0;
            if (board.getField(i + 1, colCounter + 1).hasQueen()) {
                isThreat = true;
                topLeftThreat = true;
                ChessField foundField = board.getField(i + 1, colCounter + 1);
                System.out.println("found diagonally! -- " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
            if (colCounter > 0) colCounter--;
        }
    }

    private void diagonalCheckBottomLeft(int c, int r, int queenCounter) {
        int colCounter = c - 1;
        for (int i = r + 1; i < 8; i++) {
            if (i < 0) i = 0;
            if (colCounter < 0) colCounter = 0;
            if (board.getField(i + 1, colCounter + 1).hasQueen()) {
                isThreat = true;
                bottomLeftThreat = true;
                ChessField foundField = board.getField(i + 1, colCounter + 1);
                System.out.println("found diagonally! +- " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
            if (colCounter > 0) colCounter--;
        }
    }

    private void diagonalCheckTopRight(int c, int r, int queenCounter) {
        int colCounter = c + 1;
        for (int i = r - 1; i > 0; i--) {
            if (i < 0) i = 0;
            if (colCounter < 0) colCounter = 0;
            if (board.getField(i + 1, colCounter + 1).hasQueen()) {
                isThreat = true;
                topRightThreat = true;
                ChessField foundField = board.getField(i + 1, colCounter + 1);
                System.out.println("found diagonally! -+ " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
            }
            if (colCounter < 7) colCounter++;
        }
    }


    //TODO implement recursive method and maybe adding a class Queens
    private void backTrackQueens() {
        if (checkThreat()) {
            int row = last.getRow();
            int col = last.getColumn();

            //right
            if (col < 8) {
                if (!board.getField(row, col + 1).hasQueen() && horizontalThreat) {
                    removeQueen(row, col);
                    putQueen(row, col + 1);
                    displayBoard();
                    checkThreat();
                }
            }
            //left
            else if (col > 1) {
                if (!board.getField(row, col - 1).hasQueen() && horizontalThreat) {
                    removeQueen(row, col);
                    putQueen(row, col - 1);
                    displayBoard();
                    checkThreat();
                }
            }
            //up
            else if (row > 0) {
                if (!board.getField(row - 1, col).hasQueen() && verticalThreat) {
                    removeQueen(row, col);
                    putQueen(row - 1, col);
                    displayBoard();
                    checkThreat();
                }
            }
            //down
            else if (row < 8) {
                if (!board.getField(row + 1, col).hasQueen() && verticalThreat) {
                    removeQueen(row, col);
                    putQueen(row + 1, col);
                    displayBoard();
                    checkThreat();
                }
            }
            //bottom right
            else if (row < 8 && col < 8) {
                if (!board.getField(row + 1, col + 1).hasQueen() && bottomRightThreat) {
                    removeQueen(row, col);
                    putQueen(row + 1, col + 1);
                    displayBoard();
                    checkThreat();
                }
            }
            //top right
            else if (row > 0 && col < 8) {
                if (!board.getField(row - 1, col + 1).hasQueen() && topRightThreat) {
                    removeQueen(row, col);
                    putQueen(row - 1, col + 1);
                    displayBoard();
                    checkThreat();
                }
            }
            //bottom left
            else if (row < 8 && col > 0) {
                if (!board.getField(row + 1, col - 1).hasQueen() && bottomLeftThreat) {
                    removeQueen(row, col);
                    putQueen(row + 1, col - 1);
                    displayBoard();
                    checkThreat();
                }
            }
            //top left
            else if (row > 0 && col > 0) {
                if (!board.getField(row - 1, col - 1).hasQueen() && topLeftThreat) {
                    removeQueen(row, col);
                    putQueen(row - 1, col - 1);
                    displayBoard();
                    checkThreat();
                }
            } else backTrackQueens();
        } else {
            System.out.println("\nSoulution: ");
            displayBoard();
        }

    }
}

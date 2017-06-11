import java.util.ArrayList;

public class Chessboard {

    private ArrayList<ArrayList<ChessField>> chessBoard = new ArrayList<ArrayList<ChessField>>();
    private ArrayList<ChessField> queens = new ArrayList<>();
    private static Chessboard board;
    private boolean isThreat = false;
    private int queenCounter = 0;
    private int listcounter = 0;

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
        board.putQueen_1(0, 1 - 1);
        board.putQueen_1(0, 2 - 1);
        board.putQueen_1(0, 3 - 1);
        board.putQueen_1(0, 4 - 1);
        board.putQueen_1(0, 5 - 1);
        board.putQueen_1(0, 6 - 1);
        board.putQueen_1(0, 7 - 1);
        board.putQueen_1(0, 8 - 1);

        //making the list
        board.createQueensList(0);
        //fixing problem by using recursion in backTrackQueens()
        board.displayBoard();
        board.backTrackQueens(0);
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

    private void createQueensList(int listcounter) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                ChessField firstFound = board.getField(r, c);
                if (firstFound.hasQueen()) {
                    if (queens.size() < 8) {
                        queens.add(listcounter, firstFound);
                        listcounter++;
                    }
                }
            }
        }
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
    private void putQueen_1(int row, int column) {
        chessBoard.get(row).get(column).setHasQueen(true);
        chessBoard.get(row).get(column).setDisplay(2);
    }

    /*
        Das Feld wird anhand column und row gewählt und der boolean-wert für die Dame des Feldes auf "true" gesetzt
     */
    private void putQueen(int row, int column) {
        chessBoard.get(row).get(column).setHasQueen(true);
        chessBoard.get(row).get(column).setDisplay(2);
        queens.set(queenCounter, board.getField(row, column));
    }

    /*
        for later usage in recursive method
     */
    private void removeQueen(int row, int column) {
        chessBoard.get(row).get(column).setHasQueen(false);
        chessBoard.get(row).get(column).setBottomRightThreat(false);
        chessBoard.get(row).get(column).setTopRightThreat(false);
        chessBoard.get(row).get(column).setBottomLeftThreat(false);
        chessBoard.get(row).get(column).setTopLeftThreat(false);
        chessBoard.get(row).get(column).setHorizontalThreat(false);
        chessBoard.get(row).get(column).setVerticalThreat(false);
        if (column % 2 != 0 && row % 2 != 0) chessBoard.get(row).get(column).setDisplay(0);
        else if (column % 2 != 0 && row % 2 != 0) chessBoard.get(row).get(column).setDisplay(0);
        else if (column % 2 != 0 && row % 2 == 0) chessBoard.get(row).get(column).setDisplay(1);
        else if (column % 2 == 0 && row % 2 == 0) chessBoard.get(row).get(column).setDisplay(0);
        else if (column % 2 == 0 && row % 2 != 0) chessBoard.get(row).get(column).setDisplay(1);
    }


    /*
        Checked, ob Damen sich gefährden **AUFGABE 2**
     */
    private boolean checkThreat() {
        int row = queens.get(queenCounter).getRow();
        int col = queens.get(queenCounter).getColumn();
        // horizontal check
        horizontalCheck(col, row, queenCounter);
        // vertical check
        verticalCheck(col, row, queenCounter);
        // asscending diagonal check bottom right
        diagonalCheckBottomRight(col, row, queenCounter);
        // descending diagonal check top left
        diagonalCheckTopLeft(col, row, queenCounter);
        // asdescending diagonal check bottom left
        diagonalCheckBottomLeft(col, row, queenCounter);
        // desascending diagonal check top right
        diagonalCheckTopRight(col, row, queenCounter);

        queens.stream().filter(q -> q.isTopLeftThreat() || q.isTopRightThreat() ||
                q.isBottomLeftThreat() || q.isBottomRightThreat() || q.isHorizontalThreat()
                || q.isVerticalThreat()).forEach(q -> isThreat = true);

        return isThreat;
    }

    /*
    * @param queenCounter
    * Only there for souts
    * */
    private void horizontalCheck(int c, int r, int queenCounter) {
        for (int i = c + 1; i < 8; i++) {
            if (board.getField(r, i).hasQueen()) {
                isThreat = true;
                board.getField(r, c).setHorizontalThreat(true);
                queens.get(queenCounter).setHorizontalThreat(true);
                break;
                /*
                ChessField foundField = board.getField(i, c);
                System.out.println("found horizontal! " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
                        */
            }
        }
    }

    private void verticalCheck(int c, int r, int queenCounter) {
        for (int i = r + 1; i < 8; i++) {
            if (board.getField(i, c).hasQueen()) {
                isThreat = true;
                board.getField(r, c).setVerticalThreat(true);
                queens.get(queenCounter).setVerticalThreat(true);
                break;
                /*
                ChessField foundField = board.getField(i, c);
                System.out.println("found vertical! " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
                        */
            }
        }
    }

    private void diagonalCheckBottomRight(int c, int r, int queenCounter) {
        int colCounter = c + 1;
        for (int i = r + 1; i < 8; i++) {
            if (i < 0) i = 0;
            if (colCounter < 0) colCounter = 0;
            if (i > 7) i = 7;
            if (colCounter > 7) colCounter = 7;
            if (board.getField(i, colCounter).hasQueen()) {
                board.getField(r, c).setBottomRightThreat(true);
                queens.get(queenCounter).setBottomRightThreat(true);
                /*
                ChessField foundField = board.getField(i, colCounter);
                System.out.println("found diagonally! ++ " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
                        */
            }
            if (colCounter < 7) colCounter++;
        }
    }

    private void diagonalCheckTopLeft(int c, int r, int queenCounter) {
        int colCounter = c - 1;
        for (int i = r; i > 0; i--) {
            if (i < 0) i = 0;
            if (colCounter < 0) colCounter = 0;
            if (i > 7) i = 7;
            if (colCounter > 7) colCounter = 7;
            if (board.getField(i, colCounter).hasQueen()) {
                board.getField(r - 1, c).setTopLeftThreat(true);
                queens.get(queenCounter).setTopLeftThreat(true);
                break;
                /*
                ChessField foundField = board.getField(i, colCounter);
                System.out.println("found diagonally! -- " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
                        */
            }
            if (colCounter > 0) colCounter--;
        }
    }

    private void diagonalCheckBottomLeft(int c, int r, int queenCounter) {
        int colCounter = c - 1;
        for (int i = r + 1; i < 8; i++) {
            if (i < 0) i = 0;
            if (colCounter < 0) colCounter = 0;
            if (i > 7) i = 7;
            if (colCounter > 7) colCounter = 7;
            if (board.getField(i, colCounter).hasQueen()) {
                board.getField(r, c).setBottomLeftThreat(true);
                queens.get(queenCounter).setBottomLeftThreat(true);
                break;
                /*
                ChessField foundField = board.getField(i, colCounter);
                System.out.println("found diagonally! +- " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
                        */
            }
            if (colCounter > 0) colCounter--;
        }
    }

    private void diagonalCheckTopRight(int c, int r, int queenCounter) {
        int colCounter = c + 1;
        for (int i = r; i > 0; i--) {
            if (i < 0) i = 0;
            if (colCounter < 0) colCounter = 0;
            if (i > 7) i = 7;
            if (colCounter > 7) colCounter = 7;
            if (board.getField(i - 1, colCounter).hasQueen()) {
                board.getField(r, c).setTopRightThreat(true);
                queens.get(queenCounter).setTopRightThreat(true);
                break;
                /*
                ChessField foundField = board.getField(i, colCounter);
                System.out.println("found diagonally! -+ " + "For Queen: " + queenCounter + " Row: "
                        + foundField.getRow() + " Column: " + foundField.getColumn());
                        */
            }
            if (colCounter < 7) colCounter++;
        }
    }

    //TODO implement recursive method and maybe adding a class Queens
    private void backTrackQueens(int queenCounter) {
        //@Tony @Bernhard Ihr könnt diese Methode löschen wie ihr wollt, war nur ne erste Idee...
        if (checkThreat()) {

            int row = queens.get(queenCounter).getRow();
            int col = queens.get(queenCounter).getColumn();
            //trying to move queen vertically
            if (queens.get(queenCounter).isHorizontalThreat()) {
                if (row < 7) {
                    removeQueen(row, col);
                    int c = 6;
                    for (int i = 1; i < 8; i++) {
                        if (row < c && !chessBoard.get(row + i).get(col).hasQueen()) {
                            putQueen(row + i, col);
                            break;
                        }
                        c--;
                    }
                } else if (row == 7) {
                    removeQueen(row, col);
                    for (int i = 7; i >= 0; i--) {
                        if (!chessBoard.get(row - i).get(col).hasQueen()) {
                            putQueen(row - i, col);
                            break;
                        }
                    }
                }
                queens.get(queenCounter).setHorizontalThreat(false);
                backTrackQueens(queenCounter);
            }
            //trying to move queen horizontally
            else if (queens.get(queenCounter).isVerticalThreat()) {
                if (col < 7) {
                    removeQueen(row, col);
                    int c = 6;
                    for (int i = 1; i < 8; i++) {
                        if (col < c && !chessBoard.get(row).get(col + 1).hasQueen()) {
                            putQueen(row, col + 1);
                            break;
                        }
                        c--;
                    }
                } else if (col == 7) {
                    removeQueen(row, col);
                    for (int i = 7; i >= 0; i--) {
                        if (!chessBoard.get(row).get(col - i).hasQueen()) {
                            putQueen(row, col - i);
                            break;
                        }
                    }
                }
                queens.get(queenCounter).setVerticalThreat(false);
                backTrackQueens(queenCounter);

            } else if (queens.get(queenCounter).isBottomRightThreat()) {
                removeQueen(row, col);
                if (!queens.get(queenCounter).isBottomLeftThreat() && row < 7 && col > 0)
                    putQueen(row + 1, col - 1);
                else if (!queens.get(queenCounter).isTopRightThreat() && row > 0 && col < 7)
                    putQueen(row - 1, col + 1);
                else if (!queens.get(queenCounter).isHorizontalThreat() && col < 7)
                    putQueen(row, col + 1);
                else if (!queens.get(queenCounter).isVerticalThreat() && row < 7)
                    putQueen(row + 1, col);
                queens.get(queenCounter).setBottomRightThreat(false);
                backTrackQueens(queenCounter);

            } else if (queens.get(queenCounter).isTopRightThreat()) {
                removeQueen(row, col);
                if (!queens.get(queenCounter).isTopLeftThreat() && col > 0 && row > 0)
                    putQueen(row - 1, col - 1);
                else if (!queens.get(queenCounter).isBottomRightThreat() && row < 7 && col < 7)
                    putQueen(row + 1, col + 1);
                else if (!queens.get(queenCounter).isHorizontalThreat() && col < 7)
                    putQueen(row, col + 1);
                else if (!queens.get(queenCounter).isVerticalThreat() && row < 7)
                    putQueen(row + 1, col);
                queens.get(queenCounter).setTopRightThreat(false);
                backTrackQueens(queenCounter);

            } else if (queens.get(queenCounter).isBottomLeftThreat()) {
                removeQueen(row, col);
                if (!queens.get(queenCounter).isBottomRightThreat() && row < 7 && col < 7)
                    putQueen(row + 1, col + 1);
                else if (!queens.get(queenCounter).isTopLeftThreat() && col > 0 && row > 0)
                    putQueen(row - 1, col - 1);
                else if (!queens.get(queenCounter).isHorizontalThreat() && col < 7)
                    putQueen(row, col + 1);
                else if (!queens.get(queenCounter).isVerticalThreat() && row < 7)
                    putQueen(row + 1, col);
                queens.get(queenCounter).setBottomLeftThreat(false);
                backTrackQueens(queenCounter);

            } else if (queens.get(queenCounter).isTopLeftThreat()) {
                removeQueen(row, col);
                if (!queens.get(queenCounter).isTopRightThreat() && row > 0 && col < 7)
                    putQueen(row - 1, col + 1);
                else if (!queens.get(queenCounter).isBottomLeftThreat() && row < 7 && col > 0)
                    putQueen(row + 1, col - 1);
                else if (!queens.get(queenCounter).isHorizontalThreat() && col < 7)
                    putQueen(row, col + 1);
                else if (!queens.get(queenCounter).isVerticalThreat() && row < 7)
                    putQueen(row + 1, col);
                queens.get(queenCounter).setTopLeftThreat(false);
                backTrackQueens(queenCounter);

            } else if (queenCounter < 7) {
                queenCounter++;
                backTrackQueens(queenCounter);
            } else if (isThreat && queenCounter == 7) {
                queenCounter = 0;
                displayBoard();
                backTrackQueens(queenCounter);
            }
        } else {
            System.out.println("\nSoulution: ");
            displayBoard();
        }
    }
}

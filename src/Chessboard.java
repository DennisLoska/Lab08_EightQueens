import java.util.ArrayList;

public class Chessboard {

    private ArrayList<ArrayList<ChessField>> chessBoard = new ArrayList<ArrayList<ChessField>>();

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
        Chessboard board = new Chessboard();
        board.getField(3, 6);
        board.putQueen(3, 6);
        System.out.println(board.getField(3, 6).hasQueen());
    }

    /*
        Erstellt ein Schachbrett aus ints, wo "display 0 für "weiß" und 1 für "schwarz steht. Dieses wird dann in der Konsole angezeigt.
     */
    private void createBoard(ArrayList<ArrayList<ChessField>> chessBoard) {
        for (int row = 0; row < 8; row++) {
            chessBoard.add(row, new ArrayList<>());
            for (int j = 0; j < 8; j++) {
                if (row % 2 == 0) {
                    if (j % 2 == 0) {
                        ChessField field = new ChessField(j, row);
                        chessBoard.get(row).add(j, field);
                        field.setDisplay(0);
                    } else {
                        ChessField field = new ChessField(j, row);
                        chessBoard.get(row).add(j, field);
                        field.setDisplay(1);
                    }
                } else {
                    if (j % 2 != 0) {
                        ChessField field = new ChessField(j, row);
                        chessBoard.get(row).add(j, field);
                        field.setDisplay(0);
                    } else {
                        ChessField field = new ChessField(j, row);
                        chessBoard.get(row).add(j, field);
                        field.setDisplay(1);
                    }
                }
                System.out.print(chessBoard.get(row).get(j).getDisplay() + " ");
                if (j == 7) System.out.print("\n");
            }
        }
    }

    /*
        Gibt das entsprechende Schachfeld zurück (-1 da ArrayList bei 0 anfängt)
     */
    private ChessField getField(int column, int row) {
        return chessBoard.get(row - 1).get(column - 1);
    }

    /*
        Das Feld wird anhand column und row gewählt und der boolean-wert für die Dame des Feldes auf "true" gesetzt
     */
    private void putQueen(int column, int row) {
        chessBoard.get(row - 1).get(column - 1).setHasQueen(true);
    }

}

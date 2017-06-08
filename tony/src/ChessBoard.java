/**
 * Created by tweak on 07.06.17.
 */
public class ChessBoard {

    private ChessField[][] playboard;


    public ChessBoard() {

    }

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.createPlayboard(8,8);
        chessBoard.putQueen(2,2);
        chessBoard.redrawPlayboard();
    }

    private void redrawPlayboard() {
    }

    private void putQueen(int row, int col) {
        playboard[row][col].setHasQueen(true);
    }

    private void createPlayboard(int rows, int cols) {
        playboard = new ChessField[rows][cols];
        int length = playboard.length;

        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        ChessField field = new ChessField();
                        playboard[row][col] = field;
                        field.setState(0);
                    } else {
                        ChessField field = new ChessField();
                        playboard[row][col] = field;
                        field.setState(1);
                    }
                } else {
                    if (col % 2 == 0) {
                        ChessField field = new ChessField();
                        playboard[row][col] = field;
                        field.setState(1);
                    } else {
                        ChessField field = new ChessField();
                        playboard[row][col] = field;
                        field.setState(0);
                    }
                }
                System.out.print(playboard[row][col].getState() + " | ");
            }
            System.out.println("\n");
//            for (int i = 0; i < 30; i++){
//                System.out.print("-");
//            }
        }
    }

}

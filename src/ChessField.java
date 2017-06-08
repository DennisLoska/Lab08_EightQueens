/**
 * Created by Dennis on 06.06.2017.
 */
public class ChessField {

    private int display;
    private int column;
    private int row;
    private boolean hasQueen = false;

    public ChessField(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getColumn() {
        return column+1;
    }

    public int getRow() {
        return row+1;
    }

    public boolean hasQueen() {

        return hasQueen;
    }

    public void setHasQueen(boolean hasQueen) {

        this.hasQueen = hasQueen;
    }
}

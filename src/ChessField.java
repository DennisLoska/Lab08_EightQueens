/**
 * Created by Dennis on 06.06.2017.
 */
public class ChessField {

    private int display;
    private int column;
    private int row;
    private boolean hasQueen = false;
    private boolean horizontalThreat = false;
    private boolean verticalThreat = false;
    private boolean bottomRightThreat = false;
    private boolean topLeftThreat = false;
    private boolean bottomLeftThreat = false;
    private boolean topRightThreat = false;

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
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean hasQueen() {

        return hasQueen;
    }

    public void setHasQueen(boolean hasQueen) {

        this.hasQueen = hasQueen;
    }

    public boolean isHorizontalThreat() {
        return horizontalThreat;
    }

    public void setHorizontalThreat(boolean horizontalThreat) {
        this.horizontalThreat = horizontalThreat;
    }

    public boolean isVerticalThreat() {
        return verticalThreat;
    }

    public void setVerticalThreat(boolean verticalThreat) {
        this.verticalThreat = verticalThreat;
    }

    public boolean isBottomRightThreat() {
        return bottomRightThreat;
    }

    public void setBottomRightThreat(boolean bottomRightThreat) {
        this.bottomRightThreat = bottomRightThreat;
    }

    public boolean isTopLeftThreat() {
        return topLeftThreat;
    }

    public void setTopLeftThreat(boolean topLeftThreat) {
        this.topLeftThreat = topLeftThreat;
    }

    public boolean isBottomLeftThreat() {
        return bottomLeftThreat;
    }

    public void setBottomLeftThreat(boolean bottomLeftThreat) {
        this.bottomLeftThreat = bottomLeftThreat;
    }

    public boolean isTopRightThreat() {
        return topRightThreat;
    }

    public void setTopRightThreat(boolean topRightThreat) {
        this.topRightThreat = topRightThreat;
    }
}

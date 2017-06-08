/**
 * Created by tweak on 07.06.17.
 */
public class ChessField {

    private int state;
    private boolean hasQueen;


    public ChessField() {

    }

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return this.state;
    }

    public void setHasQueen(boolean hasQueen) {
        this.hasQueen = hasQueen;
        this.state = 8;
    }
}

import java.util.Random;
import java.util.Stack;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * Created by Bernardo on 09.06.2017.
 * Das Chessboard wird mit einem zweidimensionalen Array repräsentiert, wobei eine 0 keine Dame und eine 1 eine Dame im Arraysind. 
 * Das überprüfen der Perfekten Damenaufstellung erfollgt durch rekursives Aufrufen der fillRowRecursive Methode, welche sich die bereits benutzten
 * aber nicht perfekten Felder in dem badFields array speichert. Jedesmal wenn Backtracking in der Methode aufgerufen wird 
 * - das heißt wenn die fill Methode die zuletzt gesetzte Queen löscht und in dem letzten Row nochmal nach einem passenden platz sucht, 
 * welcher nicht der vorherige platz dieser Dame sein Darf - werden alle drunterliegenden bereits als "badFields" 
 * abgestempelten Felder wieder auf 0 gesetzt - da sonst alle Felder badFields währen, befor die perfekte lösung steht
 */
public class ChessBoard {

    //speichert die Felder 0 ist der default, 1 falls eine Dame draufsteht
    int[][]chessBoard;
    int[][]badFields;
    int size;
    /*
    erstellt und FÃ¼llt ein Chessboard beliebiger GrÃ¶ÃŸe mit nullen(keine Dame drauf) und es gibt keine bereits benutzten Felder
     */
    public ChessBoard(int size){
        this.size = size;
        chessBoard = new int[size][size];
        badFields = new int[size][size];
        for(int r=0;r<size;r++){
            for(int c=0;c<size;c++){
                chessBoard[r][c]=0;
            	badFields[r][c]=0;
            	}
        }
    }

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard(15);
        chessBoard.start();
    }

    void start() {
        fillRowRecursive(0);
        displayBoard();
    }

    /*
     *	Exercise 1 zeigt uns wie das Schachfeld aussieht
     */
    public void displayBoard(){
        String board="";
        for(int r=0;r<size;r++){
            for(int c=0;c<size;c++){
            	if(hasQueen(r,c)) board+= "|1|";
            	else board+= "|0|";
            	if(c==size-1)board+= "\n";
            }
        }
        System.out.println(board);
    }
    
    public void putQueen(int row, int col){
    	chessBoard[row][col]=1;
    }
    public void removeQueen(int row, int col){
    	chessBoard[row][col]=0;
    }
    public boolean hasQueen(int row, int col){
    	if(chessBoard[row][col]==1) return true;
    	else return false;
    }
    
    /*
     * Exercise 2 Method which checks if a queen is threatening another
     */
    public boolean wholeBoardThreatCheck(){
    	for(int r=0;r<size;r++){
            for(int c=0;c<size;c++){
            	if(chessBoard[r][c]==1){
            		if(singleFieldThreatCheck(r,c)) return true;
            	}
    	
            }
    	}
    	return false;
    }
    
    public boolean singleFieldThreatCheck(int row, int col){
		
    	//horizontalCheck
    	for(int r=0;r<size;r++){
    		if(chessBoard[r][col]==1&&r!=row)return true;
    	}
    	//verticalCheck
    	for(int c=0;c<size;c++){
    		if(chessBoard[row][c]==1&&c!=col)return true;
    	}
    	
    	//risingDiagonal Check 
    	int c=col;
    	int r=row;
    	while(c<size-1&&r>0){
    		c++;
    		r--;
    		if(chessBoard[r][c]==1)return true;
    	}
    	c=col;
    	r=row;
    	while(c>0&&r<size-1){
    		c--;
    		r++;
    		if(chessBoard[r][c]==1)return true;
    	}
    	
    	//descending diagonal Check 
    	c=col;
    	r=row;
    	while(r<size-1&&c<size-1){
    		r++;
    		c++;
    		if(chessBoard[r][c]==1)return true;
    		//chessBoard[r][c]=1;
    	}
    	c=col-1;
    	r=row-1;
    	while(r>=0&&r<row&&c>=0&&c<col){
    		if(chessBoard[r][c]==1)return true;
    		//chessBoard[r][c]=1;
    		r--;
    		c--;
    	}
    	
		return false;
		
}

    /*
     * Exercise 3 Die rekursive Füllmethode mit Backtracking
     */  
   public boolean badFieldsCheck(int row, int col){    
           	if(badFields[row][col]==1)return true;
           	else return false;
   }
   public void clearUpperbadFieldCheck(int row){    
	   for(int r=row;r<size;r++){
           for(int c=0;c<size;c++){
        	   badFields[r][c]=0;
           }
   	}
}
    
 public void fillRowRecursive(int row){
    	
    	boolean finished = false;
    	
    	for(int i=0;i<size;i++){
    		if(!singleFieldThreatCheck(row,i)&&!badFieldsCheck(row,i)){
    			putQueen(row,i);
    			badFields[row][i]=1;
    			if(row<size-1)fillRowRecursive(row+1);
    			finished = true;
    			break;
    		}
    	}
    	//when no place for the queen was found -> backtrack
    	if(!finished){
    		// den ganzen row oben säubern
    		for(int i =0;i<size;i++){
    			removeQueen((row-1),i);
    		} 
    		//die bereits als falsch bewerteten Felder wieder leeren
    		clearUpperbadFieldCheck(row);
    		//rekursiver Aufruf - einen row drüber
    		fillRowRecursive(row-1);
    	}
    	}
    	
    }   
    



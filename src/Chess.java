import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//TODO: check
//TODO: check mate

/**
 * 
 * @author Gal Cohen
 *
 */
public class Chess {
	
	public static Board gameboard;
	public static String currentLoc = null;
	public static String newLoc = null;
	public static String thirdArgument = null;
	public static boolean askForDraw  = false;
	public static boolean whiteTurn = true;
	
	public static boolean enPassant = false;
	public static int enPassantX;
	public static int enPassantY;
	public static int enPassantToEliminateX;
	public static int enPassantToEliminateY;
	public static int enPassantCapturer1X;
	public static int enPassantCapturer1Y;
	public static int enPassantCapturer2X;
	public static int enPassantCapturer2Y;
	
	

	public static void main(String[] args) {
		//initialize game
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		 
		initializeGame();
		drawBoard();
		
		//game loop
		while (true){
			//read the player's move
		     input = null;
		    
		     try {
		    	 
		    	 if (whiteTurn){		
		    			System.out.println("White player's turn:");	
		    	 }else{
		    		 System.out.println("Black player's turn:");
		    	 }
		    	 
		    	 boolean check = gameboard.detectCheck(whiteTurn);
		    	 if (check == true) {
		    		 if (whiteTurn) {
		    			System.out.println("white king in check");
		    		 }else {
		    			System.out.println("black king in check");
		    		 }
		    	 }
		    
		    	 input = br.readLine();
		    	 
		    	 parseInput(input);
		  
		    	 drawBoard();
		    	 
		    	 check = gameboard.detectCheck(!whiteTurn);
		    	 if (check == true) {
		    		 if (!whiteTurn) {
		    			System.out.println("Checkmate");
		    			System.out.println("Black wins");
		    		 }else {
		    			System.out.println("Checkmate");
		    			System.out.println("White wins");
		    		 }
		    		 System.exit(1);
		    	 }
		    	 
		    	if ( gameboard.checkStalemate( whiteTurn)){
		    		System.out.println("Stalemate");
		    		System.exit(1);
		    	}
		    
		    	 
		     } catch (IOException e) {
		    	 System.out.println("Invalid input. Try again.");
		     }       
		}
		
		//exit
	}
	
	public static void initializeGame(){
		gameboard = new Board();
	}
	
	
	public static void resignGame(){
		
	}
	
	
	public static void gameDraw(){
		
	}
	
	public static void drawBoard(){
		String[][] result = new String[8][8];
		
		//make the tiles white or black
		boolean white = true;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (white){
					result[i][j] = "  |";
					white = false;
				}else{
					result[i][j] = "##|";
					white = true;
				}
			}
			white = !(white);
		}	
		
		// draw the pieces in the right locations
		for (int y = 0; y < 8; y++){
			for (int x = 0; x < 8; x++){
				
				if ( gameboard.board[x][y] != null){
					result[x][y] = gameboard.board[x][y].drawPiece() + "|";
				}
			}
		}
		
		//print out the whole board  
		System.out.println("_________________________");
		for (int y = 0; y < 8; y++){
			System.out.print("|");
			for (int x = 0; x < 8; x++){
				System.out.print(result[x][y]);
			}
			System.out.print("  " + (8-y));
			System.out.println();
		}
		System.out.println("\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'"); 
		System.out.println(" a  b  c  d  e  f  g  h"); 
	}
	
	
	
	public static int fileToCoordinate(String file){
		int result = (int) file.toLowerCase().charAt(0) - (int)('a');
		return result;
	}
	
	
	
	public static int rankToCoordinate(String file){
		int result = 7 - ((int) file.toLowerCase().charAt(1) - (int)('1'));
		return result;
	}

	
/*	public static boolean parseMove(String input){
		if (input.contains(" ") == false){
			return false;
		}
		currentLoc = input.substring(0, 2);
		newLoc = input.substring(3, 5);
		return true;
	}
*/	
	
	public static boolean legalInput(int x1, int x2, int x3, int x4){
		if ((x1 >= 0 && x1 <= 7) && (x2 >= 0 && x2 <= 7) && (x3 >= 0 && x3 <= 7) && (x4 >= 0 && x4 <= 7)){
			return true;
		}
		
		return false;
		
	}
	
	
	public static void parseInput(String input){
		StringTokenizer st = null;
		int count = 0;
		input = input.toLowerCase();
		input = input.trim();
		
		String[] array = new String[100]; 
		 
		st = new StringTokenizer(input);
		 
		while (st.hasMoreTokens()) {
	        array[count] = st.nextToken();
	        count++;
	    }
		
		if (count > 0 && count < 4){
			if (count == 1){
				if (array[0].equals("resign")){
					if (whiteTurn){
						System.out.println("Black wins");
						System.exit(1);
					}else{
						System.out.println("White wins");
						System.exit(1);
					}
					
				}else if (array[0].equals("draw")){
					if (askForDraw == true){
						System.out.println("Draw");
						System.exit(1);
					}else{
						System.out.println("You must ask the opponent if they are willing to call the game a draw first.");
					}
				}else{
					System.out.println("Invalid input. Please try again. ");
				}
			}else if (count == 2) {
				currentLoc = array[0];
				newLoc = array[1];
				if (currentLoc.length() == 2 && newLoc.length() == 2 ){
					
					executeMove();
				}else{
					System.out.println("Invalid input. Please try again.");
				}
				
			}else if (count == 3){
				currentLoc = array[0];
				newLoc = array[1];
				if (currentLoc.length() == 2 && newLoc.length() == 2 ){
					
					if (array[2].equals("draw?")){
						executeMove();
						askForDraw = true;
					}else if (array[2].equals("q") || array[2].equals("r") || array[2].equals("b") || array[2].equals("n")) {
						thirdArgument = array[2];
						executeMove();
					}else{
						System.out.println("Invalid input. Please try again.");
					}
				}else{
					System.out.println("Invalid input. Please try again.");
				}
			}
		}else{
			System.out.println("Invalid input.Please try again.");
		}
		
		currentLoc = null;
		newLoc = null;
		thirdArgument = null;
	}
	
	
	
	public static void executeMoveOld(){
		 
		int oldx = fileToCoordinate(currentLoc);
		int oldy =rankToCoordinate(currentLoc);
		 
		int newx = fileToCoordinate(newLoc);
		int newy =rankToCoordinate(newLoc);
		
		System.out.println(currentLoc +" "+ newLoc);
		 
		if (legalInput(oldx, oldy, newx, newy) == true){ //are the coordinates entered legal?
			
			if (gameboard.board[oldx][oldy] != null){ // is there a piece in the chosen spot?
				
				//if (gameboard.board[oldx][oldy].canMove(oldx, oldy, newx, newy, false)){
						
					if ((whiteTurn && gameboard.board[oldx][oldy].isWhite()) || (!whiteTurn && !gameboard.board[oldx][oldy].isWhite())){ //does the piece being moved belong to the player whos turn it is?
						if (gameboard.board[newx][newy] == null){ // is the new location empty?
							
							if (gameboard.testCastling(oldx, oldy, newx, newy)) {
								return;
							}
							
							if (gameboard.board[oldx][oldy].canMove(oldx, oldy, newx, newy, true)  && gameboard.isPathClear(oldx, oldy, newx, newx) ){ 
								
								gameboard.board[newx][newy] = gameboard.board[oldx][oldy];
								
								if ( gameboard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || gameboard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
									pawnPromotion(newx, newy);
								}
								
								gameboard.board[oldx][oldy] = null;
								
								whiteTurn = !whiteTurn;
							}else{
								System.out.println("Illegal move: This piece cannot move this way.");
							}
							
						}else if ((whiteTurn && !gameboard.board[newx][newy].isWhite()) || 
								(!whiteTurn && gameboard.board[newx][newy].isWhite())){ // is it the correct colored piece in the old spot, and the opposite colored in the new spot?
							if (gameboard.board[oldx][oldy].canMove(oldx, oldy, newx, newy, false)  && gameboard.isPathClear(oldx, oldy, newx, newx)){ 
								
								gameboard.board[newx][newy] = gameboard.board[oldx][oldy];
								
								if ( gameboard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || gameboard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
									pawnPromotion(newx, newy);
								}
								
								gameboard.board[oldx][oldy] = null;
								
								whiteTurn = !whiteTurn;
							}else{
									System.out.println("Illegal move: This piece cannot move this way.");
							}
							
						}else{
							 System.out.println("Illegal move. Please try again..??");
						}
					//}else{
					//	System.out.println("Illegal move: This piece does not belong to the player whos turn it is.");
					//}
				}else{
					 System.out.println("Illegal move: This piece cannot be moved this way. Please try again.");
				}
			}else{
				System.out.println("Invalid move: There are no pieces at the chosen location. Please try again.");
			}
		}else{
			System.out.println("Invalid move: The chosen location is not on the board. Please try again.");
		}
		 
	}
	
	
	public static void pawnPromotion(int newx, int newy) {
		if (gameboard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") && newy == 0) {
			if (thirdArgument == null) {
				gameboard.board[newx][newy] = new Queen(true);
				
			}else if (thirdArgument.equals("q")) {
				gameboard.board[newx][newy] = new Queen(true);
				
			}else if (thirdArgument.equals("r")) {
				gameboard.board[newx][newy] = new Rook(true);
				
			}else if (thirdArgument.equals("b")) {
				gameboard.board[newx][newy] = new Bishop(true);
				
			}else if (thirdArgument.equals("n")) {
				gameboard.board[newx][newy] = new Knight(true);
				
			}
		}else if (gameboard.board[newx][newy].drawPiece().equalsIgnoreCase("bp") && newy == 7) {
			if (thirdArgument == null) {
				gameboard.board[newx][newy] = new Queen(false);
				
			}else if (thirdArgument.equals("q")) {
				gameboard.board[newx][newy] = new Queen(false);
				
			}else if (thirdArgument.equals("r")) {
				gameboard.board[newx][newy] = new Rook(false);
				
			}else if (thirdArgument.equals("b")) {
				gameboard.board[newx][newy] = new Bishop(false);
				
			}else if (thirdArgument.equals("n")) {
				gameboard.board[newx][newy] = new Knight(false);
				
			}
		}
	}
	
	
	public static void executeMove(){
		 
		int oldx = fileToCoordinate(currentLoc);
		int oldy =rankToCoordinate(currentLoc);
		 
		int newx = fileToCoordinate(newLoc);
		int newy =rankToCoordinate(newLoc);
		
		
		//System.out.println(currentLoc +" "+ newLoc);
		
		if (legalInput(oldx, oldy, newx, newy) == false){ //are the coordinates entered legal?
			System.out.println("Invalid file and rank. Please try again.");
			return;
		}
		
		if (gameboard.board[oldx][oldy] == null){ // is there a piece in the chosen spot?
			System.out.println("A piece does not exist in this spot. Please try again.");
			return;
		}
		
		
		if (whiteTurn != gameboard.board[oldx][oldy].isWhite()) {
			System.out.println("The piece you are attempting to move does not belong to you. Please try again.");
			return;
		}
		
		boolean isNewSpotEmpty = true;
		if (gameboard.board[newx][newy] != null) {
			if (gameboard.board[newx][newy].isWhite() == whiteTurn) {
				System.out.println("Cannot advance to a location taken by a piece of the same color. Please Try again.");
				return;
			}
			isNewSpotEmpty = false;
		}
		
		if (gameboard.testCastling(oldx, oldy, newx, newy)) {
			//gameboard.detectCheckMate();
			
			//no longer the first move of the piece
			gameboard.board[newx][newy].firstMove = false;
			
			whiteTurn = !whiteTurn;
			return;
		}
		
		if (enPassant == true) {
			enPassant = false;
			if (gameboard.testEnPassant(oldx, oldy, newx, newy) == true) {
				//gameboard.detectCheckMate();
				
				//no longer the first move of the piece
				gameboard.board[newx][newy].firstMove = false;
				
				whiteTurn = !whiteTurn;
				return;
			}
		} 
		
		if (gameboard.isPathClear(oldx, oldy, newx, newy) == false) {
			System.out.println("Cannot move to that location because there are pieces on the way.");
			return;
		}
		 
		if (gameboard.board[oldx][oldy].canMove(oldx, oldy, newx, newy, isNewSpotEmpty) == false) {
			System.out.println("Illegal move. This piece cannot move this way.");
			return;
		}
		
		
		
		gameboard.board[newx][newy] = gameboard.board[oldx][oldy];//move the piece to the new location
		//no longer the first move of the piece
		gameboard.board[newx][newy].firstMove = false;
		
		if ( gameboard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || gameboard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
			pawnPromotion(newx, newy);
		}
		
		gameboard.board[oldx][oldy] = null;
		
		
		//detect enpassant scenario for the next turn
		if (gameboard.detectEnPassant(oldx, oldy, newx, newy)){
			System.out.println("en passant");
		//	System.out.println("enPassantx:"+enPassantX);
		//	System.out.println("enPassanty:"+enPassantY);
		}
		
		
		//gameboard.detectCheckMate();
		whiteTurn = !whiteTurn;
		
		return;
		
	}
}

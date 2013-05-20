
/**
 * The data structure that stores the pieces, tracks their locations on the board as the game progresses, checks whether a movement of a piece is legal. 
 * 
 * @author Gal Cohen
 *
 */
public class Board {
	
	Piece[][] board;
	
	
	/**
	 * Initializes the board and the appropriate amount of black and white pieces in their initial states.
	 * 
	 */	
	public Board(){
		board = new Piece[8][8];
		
		initializeBoard();
		
	}
	
	
	public void initializeBoard(){
		//initialize the board
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				board[i][j] = null;
			}
		}
		
		
		/*
		 *|bR|bN|bB|bQ|bK|bB|bN|bR| 8 
		 *|wP|wP|wP|wP|wP|wP|wP|wP| 7
		 *|  |##|  |##|  |##|  |##| 4
		 *|##|  |##|  |##|  |##|  | 5
		 *|  |##|  |##|  |##|  |##| 4
		 *|##|  |##|  |##|  |##|  | 3
		 *|bP|bP|bP|bP|bP|bP|bP|bP| 2
		 *|bR|bN|bB|bQ|bK|bB|bN|bR| 1
		 * a   b  c  d  e  f  g  h
		 */
		
		/*  i
		 *  0   1  2  3  4  5  6  7  
		 *|bR|bN|bB|bQ|bK|bB|bN|bR| 0  j
		 *|wP|wP|wP|wP|wP|wP|wP|wP| 1
		 *|  |##|  |##|  |##|  |##| 2
		 *|##|  |##|  |##|  |##|  | 3
		 *|  |##|  |##|  |##|  |##| 4
		 *|##|  |##|  |##|  |##|  | 5
		 *|bP|bP|bP|bP|bP|bP|bP|bP| 6
		 *|bR|bN|bB|bQ|bK|bB|bN|bR| 7
		 * 
		 */
		
		//initialize the pieces and place them on the board.
		//white
		board[0][1] = new Pawn(false);
		board[1][1] = new Pawn(false);
		board[2][1] = new Pawn(false);
		board[3][1] = new Pawn(false);
		board[4][1] = new Pawn(false);
		board[5][1] = new Pawn(false);
		board[6][1] = new Pawn(false);
		board[7][1] = new Pawn(false);
		
		board[0][0] = new Rook(false);
		board[1][0] = new Knight(false);
		board[2][0] = new Bishop(false);
		board[3][0] = new Queen(false);
		board[4][0] = new King(false);
		board[5][0] = new Bishop(false);
		board[6][0] = new Knight(false);
		board[7][0] = new Rook(false);
		
		//black
		board[0][6] = new Pawn(true);
		board[1][6] = new Pawn(true);
		board[2][6] = new Pawn(true);
		board[3][6] = new Pawn(true);
		board[4][6] = new Pawn(true);
		board[5][6] = new Pawn(true);
		board[6][6] = new Pawn(true);
		board[7][6] = new Pawn(true);
		
		board[0][7] = new Rook(true);
		board[1][7] = new Knight(true);
		board[2][7] = new Bishop(true);
		board[3][7] = new Queen(true);
		board[4][7] = new King(true);
		board[5][7] = new Bishop(true);
		board[6][7] = new Knight(true);
		board[7][7] = new Rook(true);
	
		//board[4][4] = new Pawn(true);
		//board[5][2] = new Pawn(true);
		//board[5][0] = new Pawn(false);
		//board[5][5] = new King(true);
		
		
	}
	
	public boolean isPathClear(int oldX, int oldY, int newX, int newY){
		
		//System.out.println("isPathClear");
		
		String pieceName = board[oldX][oldY].drawPiece();
		
		if (pieceName.equalsIgnoreCase("wk") || pieceName.equalsIgnoreCase("bk")) {
			return true;
		} else if (pieceName.equalsIgnoreCase("wn") || pieceName.equalsIgnoreCase("bn")) {
			return true;
		//} else if (pieceName.equalsIgnoreCase("wp") || pieceName.equalsIgnoreCase("bp")) {
		//	return true;
		}
		
		int deltaX;
		int deltaY;
		
		deltaX = newX-oldX;
		deltaY = newY-oldY;
		
		int tempx = oldX;
		int tempy = oldY;
		int dx = 0;
		int dy = 0;
		
		if (deltaX > 0) {
			dx = 1;
		}else if (deltaX < 0) {
			dx = -1;
		}
		
		if (deltaY > 0) {
			dy = 1;
		}else if (deltaY < 0) {
			dy = -1;
		}
		
		
		boolean clearPath = true;
		
		
		
		if (deltaY == 0) {
			tempx = tempx + dx;
			//System.out.println("newx:"+newX+" newy:"+newY);
			for (int i = 0; i < Math.abs(deltaX)-1; i++) {
				//System.out.println("tempx:"+tempx+ " tempy:"+tempy);
				if (board[tempx][tempy] != null) {
					clearPath = false;
					break;
				}
				
				tempx = tempx + dx;
			} 
			return clearPath;
		}
		
		
		if (deltaX == 0) {
		/*	System.out.println(deltaX + " "+ deltaY + " " + dx + " " + dy+ " " + tempx + " " + tempy);
			System.out.println(oldX + " " + oldY );
			System.out.println(newX + " " + newY ); */
			tempy = tempy + dy;
			//System.out.println("newx:"+newX+" newy:"+newY);
			for (int i = 0; i < Math.abs(deltaY)-1; i++) {
				//System.out.println("tempx:"+tempx+ " tempy:"+tempy);
				if (board[tempx][tempy] != null) {
					clearPath = false;
					break;
				}
				
				tempy = tempy + dy;
			}
			
			return clearPath;
		}
		
		if (deltaX != 0 && deltaY != 0) {
			tempx = tempx + dx;
			tempy = tempy + dy;
			//System.out.println("newx:"+newX+" newy:"+newY);
			for (int i = 0; i < Math.abs(deltaY)-1; i++) {
			//while ((tempx != newX && tempy != newY)) { 
				//System.out.println("tempx:"+tempx+ " tempy:"+tempy);
				if (board[tempx][tempy] != null) {
					clearPath = false;
					break;
				}
				
				tempx = tempx + dx;
				tempy = tempy + dy;
			} 
		}
		
		return clearPath;
		
	}
	
	public boolean testCastling(int oldX, int oldY, int newX, int newY) {

		int deltax = newX - oldX;
		if ( board[oldX][oldY] != null) {
			
			String pieceName = board[oldX][oldY].drawPiece();
			if (pieceName.equalsIgnoreCase("wk") && board[oldX][oldY].firstMove == true) { //is the king white and hasnt been moved
				
				if (deltax == 2) { // is it right castling?

					if (board[7][7] != null) {
						if (board[7][7].drawPiece().equalsIgnoreCase("wr") ) { //check if the rook is in its original place

							if (isPathClear(oldX, oldY, newX, newY)) {
								board[oldX][oldY] = null;
								board[7][7] = null;
								
								board[newX][newY] = new King(true);
								board[newX][newY].firstMove = false;
								
								board[5][7] = new Rook(true);
								board[5][7].firstMove = false;
								
								return true;
							}
						}
					}
				}
			}
		}
		
		if ( board[oldX][oldY] != null) {
			String pieceName = board[oldX][oldY].drawPiece();

			if (pieceName.equalsIgnoreCase("bk") && board[oldX][oldY].firstMove == true) { //is the king black and hasnt been moved

				if (deltax == 2) { // is it right castling?
					if (board[7][0] != null) { //is there a rook at the place for castling?
						if (board[7][0].drawPiece().equalsIgnoreCase("br") ) { //check if the rook is in its original place
							if (isPathClear(oldX, oldY, newX, newY)) {
								board[oldX][oldY] = null;
								board[7][0] = null;
								
								board[newX][newY] = new King(false);
								board[newX][newY].firstMove = false;
								
								board[5][0] = new Rook(false);
								board[5][0].firstMove = false;
								
								return true;
							}
						}
					}
				}
			}
		}
		
		if ( board[oldX][oldY] != null) {
			String pieceName = board[oldX][oldY].drawPiece();

			if (pieceName.equalsIgnoreCase("wk") && board[oldX][oldY].firstMove == true) { //is the king white and hasnt been moved

				if (deltax == -2) { // is it left castling?

					if (board[0][7] != null) { // check if the rook exists

						if (board[0][7].drawPiece().equalsIgnoreCase("wr") ) { //check if the rook is in its original place

							if (isPathClear(oldX, oldY, newX, newY)) {

								board[oldX][oldY] = null; //remove the king
								board[0][7] = null; //remove the rook
								
								board[newX][newY] = new King(true);
								board[newX][newY].firstMove = false;
								
								board[3][7] = new Rook(true);
								board[3][7].firstMove = false;

								
								return true;
							}
						}
					}
				}
			}
		}
		
		if ( board[oldX][oldY] != null) {
			String pieceName = board[oldX][oldY].drawPiece();

			if (pieceName.equalsIgnoreCase("bk") && board[oldX][oldY].firstMove == true) { //is the king black and hasnt been moved


				if (deltax == -2) { // is it left castling?

					if (board[0][0] != null) { // check if the rook exists

						if (board[0][0].drawPiece().equalsIgnoreCase("br") ) { //check if the rook is in its original place

							if (isPathClear(oldX, oldY, newX, newY)) {

								board[oldX][oldY] = null; //remove the king
								board[0][0] = null; //remove the rook
								
								board[newX][newY] = new King(false);
								board[newX][newY].firstMove = false;
								
								board[3][0] = new Rook(false);
								board[3][0].firstMove = false;
								
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean detectEnPassant(int oldX, int oldY, int newX, int newY) { //call this after the move had been performed for this turn
		Chess.enPassantToEliminateX = newX;
		Chess.enPassantToEliminateY = newY;
		
		if (board[newX][newY] == null) {
			return false;
		}

		if (!(board[newX][newY].drawPiece().equalsIgnoreCase("wp") || 
				board[newX][newY].drawPiece().equalsIgnoreCase("bp"))) { // white pawn or black is in the new location
			return false;
		}
		if (Math.abs(newY - oldY) != 2) { //white pawn had just moved forward by 2
			return false;
		}
		
		int tempx = newX - 1;
		
		if (tempx >= 0) {
			Chess.enPassantCapturer1X = tempx;
			Chess.enPassantCapturer1Y = newY;
			
			//System.out.println("5");
			if (board[tempx][newY] != null) {
				if (board[tempx][newY].drawPiece().equalsIgnoreCase("wp") && Chess.whiteTurn == false) {
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 2;
					
					return true;
				}
				
				if (board[tempx][newY].drawPiece().equalsIgnoreCase("bp") && Chess.whiteTurn == true) {
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 5;
					
					return true;
				}
			}
		}
		
	
		tempx = newX + 1;
		if (tempx <= 7) {
			Chess.enPassantCapturer2X = tempx;
			Chess.enPassantCapturer2Y = newY;
			
			//System.out.println("6");
			if (board[tempx][newY] != null) {
				if (board[tempx][newY].drawPiece().equalsIgnoreCase("wp") && Chess.whiteTurn == false) {
					//System.out.println("7");
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 2;
					return true;
				}
				
				if (board[tempx][newY].drawPiece().equalsIgnoreCase("bp") && Chess.whiteTurn == true) {
					//System.out.println("10");
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 5;
					return true;
				}
			}
		}
	
		
		return false;
	}
	
	public  boolean testEnPassant(int oldX, int oldY, int newX, int newY) {
		if (((Chess.enPassantCapturer1X == oldX) && (Chess.enPassantCapturer1Y == oldY)) 
				|| ((Chess.enPassantCapturer2X == oldX) && (Chess.enPassantCapturer2Y == oldY))) {
			if (newX == Chess.enPassantX && newY == Chess.enPassantY) {
				board[Chess.enPassantToEliminateX][Chess.enPassantToEliminateY] = null;
				board[newX][newY] = board[oldX][oldY];
				board[oldX][oldY] = null;
				
				return true;
			}
		}
		
		return false;
	}

	
/*	public boolean detectCheckMate(){
		if (Chess.whiteTurn) {
			if (detectCheck().equalsIgnoreCase("wK")){
				System.out.println("Checkmate. White wins");
				return true;
			}
		}else {
			if (detectCheck().equalsIgnoreCase("bK")){
				System.out.println("Checkmate. Black wins");
				return true;
			}
		}
		
		return false;
	}
*/	
	public boolean detectCheck(boolean whiteturn) {
		int kingLocX = 0;
		int kingLocY = 0;
		
			
		if (whiteturn == true) {//if whiteturn is false, check if the white is in check
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (board[x][y] != null) {
						if (board[x][y].drawPiece().equalsIgnoreCase("wk")) {
							kingLocX = x;
							kingLocY = y;
							break;
						}
					}
				}
			}
			
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (board[x][y] != null) {
						if (board[x][y].isWhite() == false) {
							if (board[x][y].canMove(x, y, kingLocX, kingLocY, true)) {
								if (isPathClear(x, y, kingLocX, kingLocY)) {
									//System.out.println("white king is in check by "+board[x][y].drawPiece());
									//System.out.println("CHECK");
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		
		if (whiteturn == false) {//if whiteturn is true, check if the black is in check
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (board[x][y] != null) {
						if (board[x][y].drawPiece().equalsIgnoreCase("bk")) {
							kingLocX = x;
							kingLocY = y;
							break;
						}
					}
				}
			}
			
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (board[x][y] != null) {
						if (board[x][y].isWhite() == true) {
							if (board[x][y].canMove(x, y, kingLocX, kingLocY, true)) {
								if (isPathClear(x, y, kingLocX, kingLocY)) {
									//System.out.println("black king is in check by "+board[x][y].drawPiece());
									//System.out.println("CHECK");
									return true;
								}
							}
						}
					}
				}
			}
		}
		
	
		return false;
	}
	
	public boolean checkStalemate(boolean white) {
		
		for (int y = 0; y < 8; y++){
			for (int x = 0; x < 8; x++){
				if (board[x][y] != null) {
					if (board[x][y].isWhite() == white) {
						for (int yy = 0; yy < 8; yy++){
							for (int xx = 0; xx < 8; xx++){
								boolean isNewSpotEmpty = true;
								if (board[xx][yy] != null) {
									isNewSpotEmpty = false;
								}
								if (board[x][y].canMove(x, y, xx, yy, isNewSpotEmpty)) {
									if (isPathClear(x, y, xx, yy)) {
										return false;
									}
								}
							}
						}	
					}
				}
			}
		}	
		
		return true;  //stalemate found
	}
}

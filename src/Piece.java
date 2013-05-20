

/**
 * An abstract Piece, contains the methods that a Piece must have to navigate the board correctly.
 * 
 * @author Gal
 *
 */
public abstract class Piece {
	
	/**
	 * Says which player the piece belongs to. True is white, false is black.
	 */
	private boolean white = true;
	
	public boolean firstMove = true;
	
	/**
	 * Sets the piece to belong to the white or black player. White is represented as True, black is represented as false.
	 * @param t - the true or false value.
	 */
	public void setWhite(boolean t){
		white = t;
	}
	
	
	
	/**
	 * Tells whether the piece is which or black. White is true, black is false.
	 * @return
	 */
	public boolean isWhite(){
		return this.white;
	}
	
	
	
	public abstract boolean canMove(int oldX, int oldY, int newX, int newY, boolean isNewSpotEmpty);
	
	public abstract void movePiece();
	
	public abstract String drawPiece();
}

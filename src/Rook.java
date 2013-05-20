/**
 * 
 * @author Gal Cohen
 */
public class Rook extends Piece{

	private String name = "R";
	
	public Rook(boolean w){
		this.setWhite(w);
		
		if (w == true){
			this.name = "w" + this.name; 
		}else{
			this.name = "b" + this.name; 
		}
	}
	
	public boolean canMove(int oldX, int oldY, int newX, int newY, boolean isNewSpotEmpty) {

		int deltaX;
		int deltaY;
		
		deltaX = Math.abs(newX-oldX);
		deltaY = Math.abs(newY-oldY);
		
		if (deltaX == 0 && deltaY != 0){
			return true;
		} else if (deltaX != 0 && deltaY == 0) {
			return true;
		}
		
		return false;
	}

	public String drawPiece() {
		return this.name;
	}

	public void movePiece() {
		
	}

}

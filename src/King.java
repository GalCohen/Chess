/**
 * 
 * @author Gal Cohen
 */
public class King extends Piece{

	private String name = "K";
	
	public King(boolean w){
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
		
		if (deltaX == deltaY && deltaX == 1 && deltaY == 1){
			return true;
		}else if (deltaX == 0 && deltaY == 1){
			return true;
		} else if (deltaX == 1 && deltaY == 0) {
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

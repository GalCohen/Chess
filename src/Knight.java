/**
 * 
 * @author Gal Cohen
 */
public class Knight extends Piece{

	private String name = "N";
	
	public Knight(boolean w){
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
		
		if (deltaX == 2 && deltaY == 1){
			return true;
		} else if (deltaX == 1 && deltaY == 2) {
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

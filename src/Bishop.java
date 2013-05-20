/**
 * 
 * @author Gal Cohen
 */
public class Bishop extends Piece{

	private String name = "B";
	
	public Bishop(boolean w){
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
		
		if (deltaX == deltaY) {
			//System.out.println("canMove:true");
			return true;
		}
		
		//System.out.println("canMove:false");
		
		return false;
	}

	public String drawPiece() {
		return this.name;
	}

	public void movePiece() {
		
	}

}

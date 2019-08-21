package com.revature.oopexercise;


// Inheretence Example
// Pawn inherits from abstract class Piece as well as the interface Movable. Thus, pawn will be both a Movable type and a Piece object
public class Pawn extends Piece{

	public Pawn() {
		
		super();
		this.status = 1; this.value = 1;
		this.label = 'P';
	}
	
	public Pawn(int xpos, int ypos, int ctype) {
		
		this();
		this.pos[0] = xpos; this.pos[1] = ypos;
		
		if((this.pos[0] < 0 || this.pos[0] >= 8 ) || (this.pos[1] < 0 || this.pos[1] >= 8) ) {
			
			throw new InvalidMoveException();
		}
		
		this.color = ctype;
		this.id = xpos + ypos + ctype + this.label;
	}
	
	// Implement specific verification technique as required by superclass
	public boolean verify(int[] pos) {
		
		if((pos[0] < 0 || pos[0] >= 8 ) || (pos[1] < 0 || pos[1] >= 8) ) {
			
			return false;
		}
		
		if(status == 1) {
			
			int xdiff = this.pos[0] - pos[0];
			int ydiff = this.pos[1] - pos[1];
			
			if(xdiff == 0 && ydiff == 0) return false;
			
			if( (xdiff == 0) && ((ydiff * ydiff) == (1 - 2 * this.color))){
				
				return true;
			}
		}
		
		return false;
	}
}

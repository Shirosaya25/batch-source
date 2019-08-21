package com.revature.oopexercise;

// Example of Encapsulation
// Building an abstract framework for a piece without concretely denoting various parameters that differ from piece to piece
public abstract class Piece implements Movable{
	
	// Abstraction example. Things that must be publically accesable like color and label are visible. However, noessential knowledge and values like value and status that might be used for internal work are not visible.
	// Additionally, each of the methods are representative of what action will be performed, the inner workings are nonessential knowledge

	protected int value = 0;
	protected int status = 0;
	protected int id = 0;
	
	public int color = 0;
	public char label;
	
	public int[] pos = {0, 0};
	
	// Each piece must contain a verify function, however, implementation will differ with each object
	public abstract boolean verify(int[] pos);
	
	public boolean verify(int x, int y) {
		
		int[] param = {x, y};
		
		return this.verify(param);
	}

	public void move(int[] pos) {
		
		if(this.verify(pos)) {
			
			System.out.printf("%C: (%d, %d) -> (%d, %d)\n", this.label, this.pos[0], this.pos[1], pos[0], pos[1]);
			this.pos[0] = pos[0]; this.pos[1] = pos[1];
			return;
		}
		
		throw new InvalidMoveException();
	}
	
	public void move(int x, int y){
		
		int[] param = {x, y};
		
		this.move(param);
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o instanceof Piece) {
			
			if(((Piece) o).label == this.label && ((Piece) o).color == this.color) {
				
				System.out.printf("%c @ (%d, %d) same piece as %c @ (%d, %d)\n", this.label, this.pos[0], this.pos[1], ((Piece) o).label, ((Piece) o).pos[0], ((Piece) o).pos[1]);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		
		return this.id;
	}
}

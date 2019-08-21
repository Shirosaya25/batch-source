package com.revature.oopexercise;

import java.util.List;
import java.util.ArrayList;

public class Main {
	
	public static void print(List<Piece> objs) {
		
		//Polymorphism Example. Using the superclass Piece as opposed to specified subclasses to more effective iterate
		for(Piece p : objs) {
			
			System.out.printf("%c at (%d, %d)\n", p.label, p.pos[0], p.pos[1]);
		}
	}

	public static void main(String[] args) {
		
		//Creation
		
		//Valid Creation
		List<Piece> pieces = new ArrayList<Piece>();
		pieces.add(new Pawn(3, 4, 0));
		pieces.add(new Rook(7, 6, 1));
		pieces.add(new Queen(1, 2, 1));
		pieces.add(new Queen(5, 1, 1));
		
		//Invalid Creation / Error Catching #1
		try {
			
			pieces.add(new Pawn(-1, -1, 0));
		}
		
		catch (InvalidMoveException e){
			
			System.out.printf("Attempt to create Piece with invalid initial position\n");
		}
		
		print(pieces);
		
		//Movement
		
		//Valid Movement
		
		for(Piece p : pieces) {
			p.move(p.pos[0], p.pos[1] + 1);
		}
		
		//Invalid Movement
		try {
			
			pieces.get(0).move(-9, -9);
		}
		
		catch (InvalidMoveException e) {
			
			System.out.printf("Attempt to move Piece to invalid ending position\n");
		}
		
		//Equals
		pieces.get(2).equals(pieces.get(3));
		
		//Hashcode
		System.out.printf("%d", pieces.get(2).hashCode());
		
	}
}

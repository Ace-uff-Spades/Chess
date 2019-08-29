package Pieces;

import java.util.ArrayList;

import chess.*;
import chess.Point;

public class Knight extends Piece{
	
	ArrayList<Point> validmoves;
	public Knight(boolean iswhite){ 
		super(iswhite);
		
		if(iswhite) 
			name = "wN";
		else 
			name = "bN";
		
	}
	
	@Override
	public boolean isValidMove(Point p1, Point p2, Piece[][] currBoard) {
		
		//total spaces moved is 3 can be any combination and in any direction
		int x_difference = Math.abs(p2.getX() - p1.getX());
		int y_difference = Math.abs(p2.getY() - p1.getY());
		
		boolean isWhite = false;
		
		if(currBoard[p1.getY()][p1.getX()].getName().contains("w")) 
			isWhite = true;
		
		if((x_difference+y_difference==3) && (p1.getX() != p2.getX() && p1.getY() != p2.getY())) {
			
			if(currBoard[p2.getY()][p2.getX()] == null) {
				return true;
			}
			
			if(isWhite) {
				if(currBoard[p2.getY()][p2.getX()] !=null && currBoard[p2.getY()][p2.getX()].getName().contains("b")) {
					return true;
				}
			}
			else if(!isWhite) {
				if(currBoard[p2.getY()][p2.getX()] !=null && currBoard[p2.getY()][p2.getX()].getName().contains("w")) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public ArrayList<Point> futureValidMoves(Point p1, Piece[][] currBoard) {
		
		validmoves = new ArrayList<Point>();
		
		for(int r = 0; r<currBoard.length; r++) {
			for(int c = 0; c<currBoard[0].length; c++) {
				if(isValidMove(p1,new Point(c,r),currBoard)) {
					validmoves.add(new Point(c,r));
				}
			}
		}
	
		return validmoves;
	}
	
}

package Pieces;

import java.util.ArrayList;

import chess.Point;

public class Pawn extends Piece{
		boolean enPass; 
		boolean isWhite; 
		ArrayList<Point> validmoves;
		
	public Pawn(boolean iswhite){ 
		super(iswhite); 
		isWhite = iswhite;
		enPass = false;
		if(iswhite)
			name = "wp";
		else
			name = "bp";
	}
	
	public void setEnPass(boolean newValue) {
		enPass = newValue;
	}
	
	public boolean isValidMove(Point p1, Point p2, Piece[][] currBoard) {
		
		validmoves = new ArrayList<Point>();
		boolean isWhite = false;
		
		if(currBoard[p1.getY()][p1.getX()].getName().contains("w")) 
			isWhite = true;
		
		//populate the valid moves arraylist
		if(isWhite) {
			//enpassant move
			if(enPass) {
				if(p1.getX()>1) 
					validmoves.add(new Point(p1.getY()-1, p1.getX()-1));
				if(p1.getX()<7)
					validmoves.add(new Point(p1.getY()-1, p1.getX()+1));
			}
			
			//must move forward
			if(p1.getY()>p2.getY()) {
				
				//Pawn Promotion
				if(p2.getY() == 0) {
					
				}
				//going forward one step
				if(currBoard[p1.getY()-1][p1.getX()] == null)  {
					validmoves.add(new Point(p1.getX(), p1.getY()-1));
					
					//initial move can go two steps
					if(p1.getY()==6 && currBoard[p1.getY()-2][p1.getX()] == null) {
						validmoves.add(new Point(p1.getX(), p1.getY()-2));
					}
				}
				//killing diagonally to the left
				if(p1.getX()>0 && (currBoard[p1.getY()-1][p1.getX()-1] != null) &&
						(currBoard[p1.getY()-1][p1.getX()-1].getName().contains("b"))) {
					validmoves.add(new Point(p1.getX()-1, p1.getY()-1));
				}
				//killing diagonally to the right
				if(p1.getX()<7 && (currBoard[p1.getY()-1][p1.getX()+1] != null) && 
						(currBoard[p1.getY()-1][p1.getX()+1].getName().contains("b"))) {
					validmoves.add(new Point(p1.getX()+1, p1.getY()-1));
				}				
			}
		}
		
		//repeat for black pawn going down.
		if(!isWhite) {
			//must move forward
			if(p1.getY()<p2.getY()) {
				
				//Pawn Promotion
				if(p2.getY() == 7) {
					
				}
				//going forward one step
				if(currBoard[p1.getY()+1][p1.getX()] == null)  {
					validmoves.add(new Point(p1.getX(), p1.getY()+1));
					
					//initial move can go two steps
					if(p1.getY()==1 && currBoard[p1.getY()+2][p1.getX()] == null) {
						validmoves.add(new Point(p1.getX(), p1.getY()+2));
					}
				}
				//killing diagonally to the left
				if(p1.getX()>0 && (currBoard[p1.getY()+1][p1.getX()-1] != null) && 
						currBoard[p1.getY()+1][p1.getX()-1].getName().contains("w")) {
					validmoves.add(new Point(p1.getX()-1, p1.getY()+1));
				}
				//killing diagonally to the right
				if(p1.getX()<7 && (currBoard[p1.getY()+1][p1.getX()+1] != null) &&  
						currBoard[p1.getY()+1][p1.getX()+1].getName().contains("w")) {
					validmoves.add(new Point(p1.getX()+1, p1.getY()+1));
				}
			}
		}
	
		
		if(validmoves.contains(p2))
			return true; 
		
		return false;
	}


	public ArrayList<Point> futureValidMoves(Point p1, Piece[][] currBoard) {
	
		validmoves = new ArrayList<Point>();
		boolean isWhite = false;
		
		if(currBoard[p1.getY()][p1.getX()].getName().contains("w")) 
			isWhite = true;
		
		//populate the valid moves arraylist
		if(isWhite) {
			//must move forward

			//going forward one step
			if(currBoard[p1.getY()][p1.getX()] == null)  {
				validmoves.add(new Point(p1.getX(), p1.getY()-1));
				
				//initial move can go two steps
				if(p1.getY()==6 && currBoard[p1.getY()-2][p1.getX()] == null) {
					validmoves.add(new Point(p1.getX(), p1.getY()-2));
				}
			}
			//killing diagonally to the left
			if(p1.getX()>0 && p1.getY()>0 && (currBoard[p1.getY()-1][p1.getX()-1] != null) &&
					(currBoard[p1.getY()-1][p1.getX()-1].getName().contains("b"))) {
				validmoves.add(new Point(p1.getX()-1, p1.getY()-1));
			}
			//killing diagonally to the right
			if(p1.getX()<7 && p1.getY()>0 && (currBoard[p1.getY()-1][p1.getX()+1] != null) && 
					(currBoard[p1.getY()-1][p1.getX()+1].getName().contains("b"))) {
				validmoves.add(new Point(p1.getX()+1, p1.getY()-1));
			}				
		}
	
		
		//repeat for black pawn going down.
		if(!isWhite) {
			//must move forward
	
			//going forward one step
			if(currBoard[p1.getY()+1][p1.getX()] == null)  {
				validmoves.add(new Point(p1.getX(), p1.getY()+1));
				
				//initial move can go two steps
				if(p1.getY()==1 && currBoard[p1.getY()+2][p1.getX()] == null) {
					validmoves.add(new Point(p1.getX(), p1.getY()+2));
				}
			}
			//killing diagonally to the left
			if(p1.getX()>0 && (currBoard[p1.getY()+1][p1.getX()-1] != null) && 
					currBoard[p1.getY()+1][p1.getX()-1].getName().contains("w")) {
				validmoves.add(new Point(p1.getX()-1, p1.getY()+1));
			}
			//killing diagonally to the right
			if(p1.getX()<7 && (currBoard[p1.getY()+1][p1.getX()+1] != null) &&  
					currBoard[p1.getY()+1][p1.getX()+1].getName().contains("w")) {
				validmoves.add(new Point(p1.getX()+1, p1.getY()+1));
			}
		}
		
		
		return validmoves;
	}


}

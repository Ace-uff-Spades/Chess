package Pieces;

import java.util.ArrayList;

import chess.*;
import chess.Point;

public class Bishop extends Piece {

	ArrayList<Point> validmoves;
	public Bishop(boolean iswhite){ 
		super(iswhite);
		if(iswhite)
			name = "wB";
		else
			name = "bB";
	}
	/*
	@Override
	public boolean isValidMov(Point p1, Point p2, Piece[][] currBoard) {
		//need to calculate the diagonal
		//if it is a true diagonal, then the x and y displacement should be the same.
		if(p1.getX()  == p2.getX() && p1.getY()==p2.getY()) {
			return false;	//there has not been any move
		}
		int x_difference = Math.abs(p2.getX() - p1.getX());
		int y_difference = Math.abs(p2.getY() - p1.getY());
	
		if (x_difference == y_difference) {
			return true;
		}
		
		// TODO Auto-generated method stub
		return false;
	}
	*/
	
	public boolean isValidMove(Point p1, Point p2, Piece[][] currBoard) {
		
		//store all valid points to move to in here
		validmoves = futureValidMoves(p1, currBoard);
		
		
		//Once validmoves arraylist is filled. just see if p2 is in it
		
		if(validmoves.contains(p2))
			return true;
		
		return false;
	}

	
	public ArrayList<Point> futureValidMoves(Point p1, Piece[][] currBoard) {
		
		validmoves = new ArrayList<Point>();
		
		int row;
		int col;
		boolean isWhite = false;
		
		if(currBoard[p1.getY()][p1.getX()].getName().contains("w")) 
			isWhite = true;
		
		for(int i = 0 ; i<4; i++) {
			//Go through the four directions
			row = p1.getY();
			col = p1.getX();
			
		
			//Top Left diagonal
			if(i == 0) {
				row--;
				col--;
				
				while(col>=0 && row>=0) {
					if(currBoard[row][col] == null) {
						validmoves.add(new Point(col,row));
					}
					else if(isWhite && currBoard[row][col].getName().contains("b")){
						//this is if there is a black piece in the way of a white piece, you can 
						//kill it but you cannot go past that piece. thats why we break;
						validmoves.add(new Point(col,row));
						break;
					}
					else if(!isWhite && currBoard[row][col].getName().contains("w")){
						//same thing as above but for a black piece killing a white piece
						validmoves.add(new Point(col,row));
						break;
					}
					else if(isWhite && currBoard[row][col].getName().contains("w")) {
						//if there is a piece from your own team you can't go past it or on to its square
						break;
					}
					else if(!isWhite && currBoard[row][col].getName().contains("b")) {
						//same as above but for black
						break;
					}
					
					row--;
					col--;
				}
			}
			//top right diagonal
			else if(i == 1) {
				row--;
				col++;
				while(col<8 && row>=0) {
					if(currBoard[row][col] == null) {
						validmoves.add(new Point(col,row));
					}
					else if(isWhite && currBoard[row][col].getName().contains("b")){
						//this is if there is a black piece in the way of a white piece, you can 
						//kill it but you cannot go past that piece. thats why we break;
						validmoves.add(new Point(col,row));
						break;
					}
					else if(!isWhite && currBoard[row][col].getName().contains("w")){
						//same thing as above but for a black piece killing a white piece
						validmoves.add(new Point(col,row));
						break;
					}
					else if(isWhite && currBoard[row][col].getName().contains("w")) {
						//if there is a piece from your own team you can't go past it or on to its square
						break;
					}
					else if(!isWhite && currBoard[row][col].getName().contains("b")) {
						//same as above but for black
						break;
					}
					
					row--;
					col++;
				}
			}
			//bottom right diagonal
			else if(i == 2) {
				row++;
				col++;
				while(col<8 && row<8) {
					if(currBoard[row][col] == null) {
						validmoves.add(new Point(col,row));
					}
					else if(isWhite && currBoard[row][col].getName().contains("b")){
						//this is if there is a black piece in the way of a white piece, you can 
						//kill it but you cannot go past that piece. thats why we break;
						validmoves.add(new Point(col,row));
						break;
					}
					else if(!isWhite && currBoard[row][col].getName().contains("w")){
						System.out.println(currBoard[row][col].getName());
						//same thing as above but for a black piece killing a white piece
						validmoves.add(new Point(col,row));
						break;
					}
					else if(isWhite && currBoard[row][col].getName().contains("w")) {
						//if there is a piece from your own team you can't go past it or on to its square
						break;
					}
					else if(!isWhite && currBoard[row][col].getName().contains("b")) {
						//same as above but for black
						break;
					}
					
					row++;
					col++;
				}
			}
			//bottom left diagonal
			else if(i == 3) {
				row++;
				col--;
				while(col>=0 && row<8) {
					if(currBoard[row][col] == null) {
						validmoves.add(new Point(col,row));
					}
					else if(isWhite && currBoard[row][col].getName().contains("b")){
						//this is if there is a black piece in the way of a white piece, you can 
						//kill it but you cannot go past that piece. thats why we break;
						validmoves.add(new Point(col,row));
						break;
					}
					else if(!isWhite && currBoard[row][col].getName().contains("w")){
						//same thing as above but for a black piece killing a white piece
						validmoves.add(new Point(col,row));
						break;
					}
					else if(isWhite && currBoard[row][col].getName().contains("w")) {
						//if there is a piece from your own team you can't go past it or on to its square
						break;
					}
					else if(!isWhite && currBoard[row][col].getName().contains("b")) {
						//same as above but for black
						break;
					}
					
					row++;
					col--;
				}
			}
		}
		
		return validmoves;
	}

}

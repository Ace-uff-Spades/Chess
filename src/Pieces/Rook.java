package Pieces;

import java.util.ArrayList;

import chess.Point;

public class Rook extends Piece{
	
	ArrayList<Point> validmoves;

	public Rook(boolean iswhite){ 
		super(iswhite);
		if(iswhite)
			name = "wR";
		else
			name = "bR";
		
	}
	

//Have to re-implement isValidMove
public boolean isValidMove(Point p1, Point p2, Piece[][] currBoard) {
	
	//store all valid points to move to in here
	validmoves = futureValidMoves(p1, currBoard);
	
	
	//Once validmoves arraylist is filled. just see if p2 is in it
	
	if(validmoves.contains(p2)) {
		return true;
	}

	return false;
}

//------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------


public ArrayList<Point> futureValidMoves(Point p1, Piece[][] currBoard){
		
	//store all valid points to move to in here
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
			
		
			//go forward
			if(i == 0) {
				row++;
				
				while(row<=7) {
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
				}
			}
			//go down
			else if(i == 1) {
				row--;
				while(row>=0) {
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
				}
			}
			//go left
			else if(i == 2) {
				col--;
				while(col>=0) {
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
						System.out.println(currBoard[row][col].getName());
						//same as above but for black
						break;
					}
					
					col--;
				}
			}
			//right
			else if(i == 3) {
				col++;
				while(col<=7) {
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
					
					col++;
				}
			}
		}
	
		return validmoves;
	}
}
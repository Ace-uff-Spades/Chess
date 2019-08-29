package Pieces;
import java.util.ArrayList;

import chess.*;
import chess.Point;
/**
 * 
 * The Piece interface centralizes all the pieces on the board
 * It adds common functionality to all the pieces.
 *
 */
public abstract class Piece {
	
	boolean isWhite;
	String name;
	
	/**
	 * 
	 * @param whiteorNaw whether the piece being created is a white piece or a black piece 
	 */
	public Piece(boolean whiteorNaw) {
		whiteorNaw = isWhite; 
	}
	
	/**
	 * this method basically shows whether the certain piece can move from point A to point B.
	 * @param p1 first point where the current piece is
	 * @param p2 the point where the current piece wants to move to
	 * @param currBoard the current state of the board, with updated positions 
	 * @return false if the move is invalid and true if it is valid.
	 */
	public abstract boolean isValidMove(Point p1, Point p2, Piece[][] currBoard);
	
	/**
	 * This method calculates all the valid moves from a certain point on the board.
	 * 
	 * @param p1 Point at which the piece is at.
	 * @param currBoard the current state of the board.
	 * @return
	 */
	public abstract ArrayList<Point> futureValidMoves(Point p1, Piece[][] currBoard);
	
	public String getName() {
		return name;
	}
	public boolean isWhite() {
		return isWhite;
	}
	//This should be a helper method in determining isvalidMove
	//Thus it shouldn't be in the interface.
	//abstract Point[][] validMoves();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

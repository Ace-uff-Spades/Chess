package Pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Point;

public class King extends Piece{	

	ArrayList<Point> validmoves; 
	boolean check;
	
	public King(boolean iswhite){ 
		super(iswhite); 
		if(iswhite)
			name = "wK";
		else
			name = "bK";
		
		check = false;
	}
	
	/*@Override
	public boolean isValidMove(Point p1, Point p2, Piece[][] currBoard) {
		// TODO Auto-generated method stub
		
		if(p1.getX()  == p2.getX() && p1.getY()==p2.getY()) {
			return false;	//there has not been any move
		}
		//if king moves diagonally only one space
		
		int x_difference = Math.abs(p2.getX() - p1.getX());
		int y_difference = Math.abs(p2.getY() - p2.getY());
		
		if (x_difference==1 && y_difference==1) {
			return true;
		}
		
		//if Queen moves up or down either the x or y coordinate will be the same
		
		if ((p1.getX() == p2.getX() && y_difference==1) || (p1.getY() == p2.getY()&& x_difference==1)) {
			return true;
		}
		return false;
	}

}
*/
	
	public boolean isValidMove(Point p1, Point p2, Piece[][] currBoard) {
		
		//store all valid points to move to in here
		validmoves = new ArrayList<Point>();
		
		int row;
		int col;
		boolean isWhite = false;
		int x_difference = Math.abs(p2.getX() - p1.getX());
		int y_difference = Math.abs(p2.getY() - p1.getY());
		System.out.println("x diff= " + x_difference + " and " + "y diff= " + y_difference);
		
		if(currBoard[p1.getY()][p1.getX()].getName().contains("w")) 
			isWhite = true;
		System.out.println("this is a white piece");
		
		for(int i = 0 ; i<8; i++) {
			//Go through all eight directions only one space
			row = p1.getY();
			col = p1.getX();
			
		
			//go forward
			if(i == 0 && (p1.getX() == p2.getX() && y_difference==1)) {
				row= row+1;
				
				if(row<8) {
					
					//check if you are going to move into a spot in another piece's line of attack
					for(int r = 0; r<currBoard.length; r++) {
						for(int c = 0; c<currBoard[0].length; c++) {
							Piece piece = currBoard[r][c];
							if(piece != null) {
								ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
								for(Point p: futuremoves) {
									if(p.getX() == col && p.getY()==row){
										check = true;
									}
								}
							}
						}
					}
					
					if(!check){
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
					}
				}
			}
			//go back
			else if(i == 1 && (p1.getX() == p2.getX() && y_difference==1)) {
				row= row-1;
				
				if(row>=0) {
					//check if you are going to move into a spot in another piece's line of attack
					for(int r = 0; r<currBoard.length; r++) {
						for(int c = 0; c<currBoard[0].length; c++) {
							Piece piece = currBoard[r][c];
							if(piece != null) {
								ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
								
								for(Point p: futuremoves) {
									if(p.getX() == col && p.getY()==row){
										check = true;
									}
								}
							}
							
						}
					}
					if(!check) {
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
					}
				}
			}
			//go left
			else if(i == 2 && (p1.getY() == p2.getY()&& x_difference==1)) {
				col = col-1;
				
				if(col>=0) {
					//check if you are going to move into a spot in another piece's line of attack
					for(int r = 0; r<currBoard.length; r++) {
						for(int c = 0; c<currBoard[0].length; c++) {
							Piece piece = currBoard[r][c];
							if(piece != null) {
								ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
								for(Point p: futuremoves) {
									if(p.getX() == col && p.getY()==row){
										check = true;
									}
								}
							}
						}
					}
					if(!check) {
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
					}
				}
			}
			//go right
			else if(i == 3 && (p1.getY() == p2.getY()&& x_difference==1)) {
				col=col+1;
				
				if(col<8) {
					
					//check if you are going to move into a spot in another piece's line of attack
					for(int r = 0; r<currBoard.length; r++) {
						for(int c = 0; c<currBoard[0].length; c++) {
							Piece piece = currBoard[r][c];
							if(piece != null) {
								ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
								for(Point p: futuremoves) {
									if(p.getX() == col && p.getY()==row){
										check = true;
									}
								}
							}
						}
					}
					
					if(!check) {
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
					}
				}
			}
			//Top Left diagonal
			if(i == 4 && x_difference==1 && y_difference==1) {
				row=row-1;
				col=col-1;
				
				if(col>=0 && row>=0) {
					//check if you are going to move into a spot in another piece's line of attack
					for(int r = 0; r<currBoard.length; r++) {
						for(int c = 0; c<currBoard[0].length; c++) {
							Piece piece = currBoard[r][c];
							if(piece != null) {
								ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
								for(Point p: futuremoves) {
									if(p.getX() == col && p.getY()==row){
										check = true;
									}
								}
							}
						}
					}
					
					if(!check) {
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
					}
				}
			}
			//top right diagonal
			else if(i == 5 && x_difference==1 && y_difference==1) {
				row--;
				col++;
				
				
				if(col<8 && row>=0) {
					//check if you are going to move into a spot in another piece's line of attack
					for(int r = 0; r<currBoard.length; r++) {
						for(int c = 0; c<currBoard[0].length; c++) {
							Piece piece = currBoard[r][c];
							if(piece != null) {
								ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
								for(Point p: futuremoves) {
									if(p.getX() == col && p.getY()==row){
										check = true;
									}
								}
							}
						}
					}
					
					if(!check) {
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
					}
				}
			}
			//bottom right diagonal
			else if(i == 6 && x_difference==1 && y_difference==1) {
				row++;
				col++;
				
				
				
				if(col<8 && row<8) {
					
					//check if you are going to move into a spot in another piece's line of attack
					for(int r = 0; r<currBoard.length; r++) {
						for(int c = 0; c<currBoard[0].length; c++) {
							Piece piece = currBoard[r][c];
							if(piece != null) {
								ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
								for(Point p: futuremoves) {
									if(p.getX() == col && p.getY()==row){
										check = true;
									}
								}
							}
						}
					}
					
					if(!check) {
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
					}
				}
			}
			//bottom left diagonal
			else if(i == 7 && x_difference==1 && y_difference==1) {
				row++;
				col--;
			
				
				while(col>=0 && row<8) {
					//check if you are going to move into a spot in another piece's line of attack
					for(int r = 0; r<currBoard.length; r++) {
						for(int c = 0; c<currBoard[0].length; c++) {
							Piece piece = currBoard[r][c];
							if(piece != null) {
								ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
								for(Point p: futuremoves) {
									if(p.getX() == col && p.getY()==row){
										check = true;
									}
								}
							}
						}
					}
					
					if(!check) {
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
					}
				}
			}
		}
		
		
		//Once all of validmoves arraylist is filled. just see if p2 is in it
		
		if(validmoves.contains(p2))
			return true;
		
		return false;
	}

	@Override
	public ArrayList<Point> futureValidMoves(Point p1, Piece[][] currBoard) {
		
		validmoves = new ArrayList<Point>();
		
		
		//store all valid points to move to in here
				validmoves = new ArrayList<Point>();
				
				int row;
				int col;
				boolean isWhite = false;
		
				
				if(currBoard[p1.getY()][p1.getX()].getName().contains("w")) 
					isWhite = true;
				
				for(int i = 0 ; i<8; i++) {
					//Go through all eight directions only one space
					row = p1.getY();
					col = p1.getX();
					
				
					//go forward
					if(i == 0) {
						row= row+1;
						
						if(row<8) {
							
							//check if you are going to move into a spot in another piece's line of attack
							for(int r = 0; r<currBoard.length; r++) {
								for(int c = 0; c<currBoard[0].length; c++) {
									Piece piece = currBoard[r][c];
									if(piece != null && !piece.getName().contains("K")) {
										ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
										for(Point p: futuremoves) {
											if(p.getX() == col && p.getY()==row){
												check = true;
											}
										}
									}
								}
							}
							
							if(!check){
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
							}
						}
					}
					//go back
					else if(i == 1) {
						row= row-1;
						
						if(row>=0) {
							//check if you are going to move into a spot in another piece's line of attack
							for(int r = 0; r<currBoard.length; r++) {
								for(int c = 0; c<currBoard[0].length; c++) {
									Piece piece = currBoard[r][c];
									if(piece != null) {
										ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
										
										for(Point p: futuremoves) {
											if(p.getX() == col && p.getY()==row){
												check = true;
											}
										}
									}
									
								}
							}
							if(!check) {
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
							}
						}
					}
					//go left
					else if(i == 2) {
						col = col-1;
						
						if(col>=0) {
							//check if you are going to move into a spot in another piece's line of attack
							for(int r = 0; r<currBoard.length; r++) {
								for(int c = 0; c<currBoard[0].length; c++) {
									Piece piece = currBoard[r][c];
									if(piece != null) {
										ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
										for(Point p: futuremoves) {
											if(p.getX() == col && p.getY()==row){
												check = true;
											}
										}
									}
								}
							}
							if(!check) {
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
							}
						}
					}
					//go right
					else if(i == 3) {
						col=col+1;
						
						if(col<8) {
							
							//check if you are going to move into a spot in another piece's line of attack
							for(int r = 0; r<currBoard.length; r++) {
								for(int c = 0; c<currBoard[0].length; c++) {
									Piece piece = currBoard[r][c];
									if(piece != null) {
										ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
										for(Point p: futuremoves) {
											if(p.getX() == col && p.getY()==row){
												check = true;
											}
										}
									}
								}
							}
							
							if(!check) {
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
							}
						}
					}
					//Top Left diagonal
					if(i == 4) {
						row=row-1;
						col=col-1;
						
						if(col>=0 && row>=0) {
							//check if you are going to move into a spot in another piece's line of attack
							for(int r = 0; r<currBoard.length; r++) {
								for(int c = 0; c<currBoard[0].length; c++) {
									Piece piece = currBoard[r][c];
									if(piece != null) {
										ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
										for(Point p: futuremoves) {
											if(p.getX() == col && p.getY()==row){
												check = true;
											}
										}
									}
								}
							}
							
							if(!check) {
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
							}
						}
					}
					//top right diagonal
					else if(i == 5) {
						row--;
						col++;
						
						
						if(col<8 && row>=0) {
							//check if you are going to move into a spot in another piece's line of attack
							for(int r = 0; r<currBoard.length; r++) {
								for(int c = 0; c<currBoard[0].length; c++) {
									Piece piece = currBoard[r][c];
									if(piece != null) {
										ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
										for(Point p: futuremoves) {
											if(p.getX() == col && p.getY()==row){
												check = true;
											}
										}
									}
								}
							}
							
							if(!check) {
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
							}
						}
					}
					//bottom right diagonal
					else if(i == 6) {
						row++;
						col++;
						
						
						
						if(col<8 && row<8) {
							
							//check if you are going to move into a spot in another piece's line of attack
							for(int r = 0; r<currBoard.length; r++) {
								for(int c = 0; c<currBoard[0].length; c++) {
									Piece piece = currBoard[r][c];
									if(piece != null) {
										ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
										for(Point p: futuremoves) {
											if(p.getX() == col && p.getY()==row){
												check = true;
											}
										}
									}
								}
							}
							
							if(!check) {
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
							}
						}
					}
					//bottom left diagonal
					else if(i == 7) {
						row++;
						col--;
					
						
						while(col>=0 && row<8) {
							//check if you are going to move into a spot in another piece's line of attack
							for(int r = 0; r<currBoard.length; r++) {
								for(int c = 0; c<currBoard[0].length; c++) {
									Piece piece = currBoard[r][c];
									if(piece != null) {
										ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
										for(Point p: futuremoves) {
											if(p.getX() == col && p.getY()==row){
												check = true;
											}
										}
									}
								}
							}
							
							if(!check) {
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
							}
						}
					}
				}
				
				
		return validmoves;
	}
	
	public boolean checkMate(Piece[][] currBoard) {
		
		validmoves = new ArrayList<Point>();
		
		if(validmoves.size() == 0) {
			
			for(int r = 0; r<currBoard.length; r++) {
				for(int c = 0; c<currBoard[0].length; c++) {
					Piece piece = currBoard[r][c];
					if(piece != null) {
						ArrayList<Point> futuremoves= piece.futureValidMoves(new Point(c,r),currBoard);
						for(Point p: futuremoves) {
							
							//switch the piece into it's future valid move
							Piece[][] tempBoard = currBoard;
							Piece tempPiece = tempBoard[r][c];
							tempBoard[r][c] = null;
							tempBoard[p.getY()][p.getX()] = tempPiece;
							
							//check whether this saves the king
							for(int r1 = 0; r1<tempBoard.length; r1++) {
								for(int c1 = 0; c1<tempBoard[0].length; c1++) {
									Piece piece1 = tempBoard[r][c];
									if(piece != null) {
										ArrayList<Point> futuremoves1= piece1.futureValidMoves(new Point(c,r),tempBoard);
										for(Point p1: futuremoves1) {
											if(tempBoard[p1.getY()][p1.getX()].getName().contains("K")) {
												return false;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		return true;
	}
}

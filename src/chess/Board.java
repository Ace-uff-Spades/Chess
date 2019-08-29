package chess;
import Pieces.*;

public class Board {
	
	Piece[][] board; 
	
	public Board() {
		board = new Piece[8][8];
		initialize(); 
	}
	
	/**
	 * Initializes the board in the beginning. setting up the pieces in proper order.
	 */
	public void initialize() {		
	
		//white pieces
		board[7][0] = new Rook(true);
		board[7][1] = new Knight(true);
		board[7][2] = new Bishop(true);
		board[7][3] = new Queen(true);
		board[7][4] = new King(true);
		board[7][5] = new Bishop(true);
		board[7][6] = new Knight(true);
		board[7][7] = new Rook(true);
		
		//load the pawns
		for(int c = 0; c<8; c++) 
			board[6][c] = new Pawn(true);
	
		//black pieces
		//load the top black row
		board[0][0] = new Rook(false);
		board[0][1] = new Knight(false);
		board[0][2] = new Bishop(false);
		board[0][3] = new Queen(false);
		board[0][4] = new King(false);
		board[0][5] = new Bishop(false);
		board[0][6] = new Knight(false);
		board[0][7] = new Rook(false);
		
		//load the pawns
		for(int c = 0; c<8; c++) 
			board[1][c] = new Pawn(false);
	}
	
	/**
	 * displays the current board.
	 */
	public void display() {
		
		for(int r = 0; r<8; r++) {
			for(int c =0; c<8; c++) {
				if(board[r][c] != null)
					System.out.print(board[r][c].getName()+ " ");
				else{
					if((r+c)%2==1)
						System.out.print("## ");
					else
						System.out.print("   ");
				}		
			}
			System.out.println(8-r);
		}
		
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();
	}
		
	/**
	 * Method responsible for checking illegal moves by calling the isValidMove in each of the 
	 * Pieces class. 
	 * 
	 * @param p1 starting point	
	 * @param p2 ending point
	 * @param isWhite to ensure we move the right player's piece.
	 * @return
	 */
	public boolean playerMove(Point p1, Point p2, boolean isWhite) {
		
		Piece piece = board[p1.getY()][p1.getX()];
		if(piece == null) {
			System.out.println("Illegal Move. Try Again.");
			return false;
		}
		if((piece.getName().contains("w") && !isWhite) || (piece.getName().contains("b") && isWhite)) {
			System.out.println("Illegal Move. Try Again.");
			return false;
		}
		if(piece.isValidMove(p1,p2, board)) {
			board[p2.getY()][p2.getX()] = piece;
			board[p1.getY()][p1.getX()] = null;
			return true;
		}
		else {
			System.out.println("Illegal Move. Try Again.");
			return false;
		}
		
	}

}

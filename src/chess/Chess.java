/**
 * @author Abhishek Kondila
 * @author Kaavya Krishna Kumar
 */


package chess;
import java.util.ArrayList;
import java.util.Scanner;
import Pieces.*;


/**
 * 
 * This the main class where most of the logic is taking place. The Chess Class displays and 
 * runs the logic to keep the game running. 
 *
 */
public class Chess {
	
	/**
	 * The board that is being played on. The Board is initialized in its constructor. 
	 */
	Board mainBoard;

	public Chess() {
		mainBoard = new Board(); 
		start(); 
	}
	
	/**
	 * Starts the game. Runs until there's a winner.
	 */
	public void start() { 
		Scanner input = new Scanner(System.in); 
		String wMove = "";
		String bMove = "";
		boolean endGame = false; 
		boolean validMove = false;
		boolean draw = false;
		boolean inCheck = false;
		boolean pawnPromotion = false;
		boolean enPassant = false;
		boolean castle = false;
		boolean cRight = false;
		boolean cLeft = false;
		Point pastPoint1, pastPoint2; 
		Point p1, p2; 
		
		mainBoard.display();
		
		while(!endGame) {
			
			//whites move
			validMove = false;
			while(!validMove) {
				System.out.print("White's move: ");
				wMove = input.nextLine();

				
			    //Special inputs
				if(draw) {
					if(wMove.length() == 4) {
						if(wMove.equals("draw")) {
							endGame = true;
						}
					}
					else {
						draw = false;
					}
				}
				//draw
				if(endGame) {
					break;
				}
				if(wMove.length() == 6) {
					if(wMove.equals("resign")) {
						System.out.println("Black Wins!");
						endGame = true;
					}
				}
				//white resigns
				if(endGame) {
					break;
				}
				if(wMove.length()>6) {
					String extra = wMove.substring(6);
					if(extra.equals("draw?")) {
						draw = true;
					}
					
				}
				//prompt draw from other player
				if(draw) {
					mainBoard.display();
					break;
				}
				
				if(!endGame && !draw && !castle) {
					validMove = true;
					System.out.println();
					
					p1 = new Point(wMove.substring(0, 2));
					p2 = new Point(wMove.substring(3, 5));

					//castling
					//determining if king is in the right position
					if(mainBoard.board[p1.getY()][p1.getX()] != null && 
							mainBoard.board[p1.getY()][p1.getX()].getName().contains("K")&& p1.getY()==7) {
						if (mainBoard.board[p2.getY()][p2.getX()] != null && 
								mainBoard.board[p2.getY()][p2.getX()].getName().contains("R")&& p1.getY()==7) {

							//check if rook is to the right of the king, and if there are two null spaces in between
							if (p2.getX()-p1.getX()==2 && 
									(mainBoard.board[p1.getY()][p1.getX()+1] == null) && 
									(mainBoard.board[p1.getY()][p1.getX()+2] == null)) {
								castle = true;
								cRight = true;
							}
							//check if rook is to the right of the king, and if there are three null spaces in between

							else if (p2.getX()-p1.getX()==3 && 
									(mainBoard.board[p1.getY()][p1.getX()+1] == null) && 
									(mainBoard.board[p1.getY()][p1.getX()+2] == null)&&
									(mainBoard.board[p1.getY()][p1.getX()+3] == null)) {
								castle = true;
								cRight = true;
							}
							//check if rook is to the left of the king, and if there are two null spaces in between
							if (p1.getX()-p2.getX()==2 &&
									(mainBoard.board[p1.getY()][p1.getX()+1] == null) && 
									(mainBoard.board[p1.getY()][p1.getX()+2] == null)) {
								castle = true;
								cLeft = true;
							}
							//check is rook is to the left of the king and if there are three null spaces in between
							else if (p1.getX()-p2.getX()==3 && 
									(mainBoard.board[p1.getY()][p1.getX()+1] == null) && 
									(mainBoard.board[p1.getY()][p1.getX()+2] == null) &&
									(mainBoard.board[p1.getY()][p1.getX()+3] == null)) {
								castle = true;
								cLeft = true;
							} 
						}
					}
					
					//actually move piece
					if(castle == true && cRight == true) {
						mainBoard.board[p1.getY()+2][p1.getX()] = new King(true);
						mainBoard.board[p1.getY()][p1.getX()] = null;
						mainBoard.board[p1.getY()+1][p1.getX()] = new Rook(true);
						mainBoard.board[p2.getY()][p2.getX()]= null;	
					}
					if(castle == true && cLeft == true) {
						mainBoard.board[p1.getY()-2][p1.getX()] = new King(true);
						mainBoard.board[p1.getY()][p1.getX()] = null;
						mainBoard.board[p1.getY()-1][p1.getX()] = new Rook(true);
						mainBoard.board[p2.getY()][p2.getX()]= null;	
					}
					
					//pawn promotion
					if(mainBoard.board[p1.getY()][p1.getX()] != null && 
							mainBoard.board[p1.getY()][p1.getX()].getName().contains("p") && (p1.getY() == 1 && p2.getY() == 0)
							&& (p2.getX() == p1.getX())) {
						
						pawnPromotion = true;
						
						if(wMove.length() == 5) {
							mainBoard.board[p2.getY()][p2.getX()] = new Queen(true);
							mainBoard.board[p1.getY()][p1.getX()] = null;
						}
						else if(wMove.length() > 6) {
							String extra = wMove.substring(6);
							if(extra.equals("B")) {
								mainBoard.board[p2.getY()][p2.getX()] = new Bishop(true);
								mainBoard.board[p1.getY()][p1.getX()] = null;
							}
							if(extra.equals("R")) {
								mainBoard.board[p2.getY()][p2.getX()] = new Rook(true);
								mainBoard.board[p1.getY()][p1.getX()] = null;
							}
							if(extra.equals("N")) {
								mainBoard.board[p2.getY()][p2.getX()] = new Knight(true);
								mainBoard.board[p1.getY()][p1.getX()] = null;
							}
							if(extra.equals("Q")) {
								mainBoard.board[p2.getY()][p2.getX()] = new Queen(true);
								mainBoard.board[p1.getY()][p1.getX()] = null;
							}
						}
						mainBoard.display();
					}
					else if(mainBoard.playerMove(p1, p2, true)) {
						
						
						Piece piece = mainBoard.board[p2.getY()][p2.getX()];
						
						
						//enpassant check
						if(!enPassant) {
							if(piece.getName().contains("wp")) {
								if(p2.getY() == 4) {
									if(p2.getX()>1 && p2.getX()<8 && mainBoard.board[p2.getY()][p2.getX()-1]!= null && 
											mainBoard.board[p2.getY()][p2.getX()-1].getName().contains("bp")) {
										Pawn temp = (Pawn) mainBoard.board[p2.getY()][p2.getX()];
										temp.setEnPass(true);
										mainBoard.board[p2.getY()][p2.getX()] = temp;
										enPassant = true;
										pastPoint1 = p2; 
									}
									if(p2.getX()<7 && p2.getX()>0 && mainBoard.board[p2.getY()][p2.getX()+1]!= null && 
											mainBoard.board[p2.getY()][p2.getX()+1].getName().contains("bp")) {
										Pawn temp = (Pawn) mainBoard.board[p2.getY()][p2.getX()];
										temp.setEnPass(true);
										mainBoard.board[p2.getY()][p2.getX()] = temp;
										enPassant = true;
										pastPoint1 = p2;
									}
								}
							}
						}
						
						ArrayList<Point> futureValidMoves = piece.futureValidMoves(p2, mainBoard.board);
						
						for(Point currPoint: futureValidMoves) {
							if(mainBoard.board[currPoint.getY()][currPoint.getX()] != null && 
									mainBoard.board[currPoint.getY()][currPoint.getX()].getName().equals("bK")) {
								System.out.println("Check!");
								
								King blackKing = (King) mainBoard.board[currPoint.getY()][currPoint.getX()];
								inCheck = true;
								ArrayList<Point> KingsMoves = blackKing.futureValidMoves(currPoint, mainBoard.board);
								if(KingsMoves.size() == 0) {
									endGame = true;
								}
							}
						}
						mainBoard.display();
					}
					else {
						validMove = false;
					}
				}
			}
			
			//check if game ended.
			if(endGame) {
				System.out.println("White Wins");
				break;
			}
		
			
				
			
			//blacks move
			validMove = false;
			while(!validMove) {
				System.out.print("Black's move: ");
				bMove = input.nextLine();
				
				
				//Special inputs
				if(draw) {
					if(bMove.length() == 4) {
						if(bMove.equals("draw")) {
							endGame = true;
						}
					}
					else {
						draw = false;
					}
				}
				//draw
				if(endGame) {
					break;
				}
				if(bMove.length() == 6) {
					if(bMove.equals("resign")) {
						System.out.println("White Wins!");
						endGame = true;
					}
				}
				//white resigns
				if(endGame) {
					break;
				}
				if(bMove.length()>6) {
					String extra = bMove.substring(6);
					if(extra.equals("draw?")) {
						draw = true;
					}
				}
				//prompt draw from other player
				if(draw) {
					mainBoard.display();
					break;
				}
				if(!endGame && !draw) {
					validMove = true;
					System.out.println();
					p1 = new Point(bMove.substring(0, 2));
					p2 = new Point(bMove.substring(3, 5));
					//System.out.println(p1 + " " + p2);
					/*
					if(wMove.length()>5) {
						p1 = new Point(wMove.substring(0, 2));
						p2 = new Point(wMove.substring(3, 5));
						
						//check here for any extra inputs like pawn promotion or draw
					}
					else(wMove.length()==5){
						p1 = new Point(wMove.substring(0, 2));
						p2 = new Point(wMove.substring(3, 5));
					}
					*/
					if(mainBoard.board[p1.getY()][p1.getX()] != null && 
							mainBoard.board[p1.getY()][p1.getX()].getName().contains("p") && (p1.getY() == 6 && p2.getY() == 7)
							&& (p2.getX() == p1.getX())) {
					
						pawnPromotion = true;
						
						if(wMove.length() == 5) {
							mainBoard.board[p2.getY()][p2.getX()] = new Queen(false);
							mainBoard.board[p1.getY()][p1.getX()] = null;
						}
						else if(wMove.length() > 6) {
							String extra = wMove.substring(6);
							if(extra.equals("B")) {
								mainBoard.board[p2.getY()][p2.getX()] = new Bishop(false);
								mainBoard.board[p1.getY()][p1.getX()] = null;
							}
							if(extra.equals("R")) {
								mainBoard.board[p2.getY()][p2.getX()] = new Rook(false);
								mainBoard.board[p1.getY()][p1.getX()] = null;
							}
							if(extra.equals("N")) {
								mainBoard.board[p2.getY()][p2.getX()] = new Knight(false);
								mainBoard.board[p1.getY()][p1.getX()] = null;
							}
							if(extra.equals("Q")) {
								mainBoard.board[p2.getY()][p2.getX()] = new Queen(false);
								mainBoard.board[p1.getY()][p1.getX()] = null;
							}
						}
						mainBoard.display();
					}
					else if(mainBoard.playerMove(p1, p2, false)) {
						Piece piece = mainBoard.board[p2.getY()][p2.getX()];
						ArrayList<Point> futureValidMoves = piece.futureValidMoves(p2, mainBoard.board);
						
						for(Point currPoint: futureValidMoves) {
							if(mainBoard.board[currPoint.getY()][currPoint.getX()] != null &&  
									mainBoard.board[currPoint.getY()][currPoint.getX()].getName().equals("wK")) {
								System.out.println("Check!");

								King whiteKing = (King) mainBoard.board[currPoint.getY()][currPoint.getX()];
								inCheck = true;
								ArrayList<Point> KingsMoves = whiteKing.futureValidMoves(currPoint, mainBoard.board);
								if(KingsMoves.size() == 0) {
									endGame = true;
								}
							}
						}
						mainBoard.display();
					}
					else
						validMove = false;
				}
			}
			
			//check if game ended
			if(endGame) {
				System.out.println("Black wins");
				break;
			}
			
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chess one = new Chess(); 

	}

}

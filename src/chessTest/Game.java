package chessTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;

import boards.Board;
import rules.AtomicRules;
import rules.ClassicRules;
import rules.LosAlamosRules;
import rules.Rules;
import tests.TestGameStateCallback;

public class Game {
	public static Rules rules;
	public static Board board;

	public static void main(String[] args) {
		main(args, false, null, null);
	}
	
	public static void main(String[] args, boolean testMode, String testInput, TestGameStateCallback stateCallback) {	
		View v;
		mainGameLoop:
		for (;;) {
			v = testMode ? new TextView(testInput) : new TextView(null);
			int gameType = 0;
			
			//Get user's chosen game type
			while (gameType != 1 && gameType != 2 && gameType != 3) {
				gameType = v.getGameType();
			}
			
			//Get desired game type this includes the correct rules and board
			rules = getRules(gameType);
			board = rules.createBoard();
			v.initTextView(board);
			
			v.displayMsg("Welcome to chess!");
			boolean gameOver = false;
			Color playersTurn = Color.WHITE;
			innerGameLoop:
			while(!gameOver){
				boolean invalidMove; 
				do{
					invalidMove = false;
					v.drawBoard();
					if (playersTurn == Color.WHITE) {
						v.displayMsg("White's turn");
					}else{
						v.displayMsg("Black's turn");
					}
					
					Coord[] move = v.getMove();
					
					if(move==null) {
						v.displayMsg("Invalid input. Enter your move eg(A2,A3), or q to quit");
						invalidMove = true;
						continue;
					}
					if (move.length == 1) {
						if (playersTurn == Color.WHITE) {
							v.displayMsg("Player two (Black) Wins!"); 
						}else{
							v.displayMsg("Player one (White) Wins!"); 
						}
						break mainGameLoop;
					}
					
					//check if first coord is a piece
					Piece select = null;
					try {
						select = board.getTile(move[0]).curPiece;
					} catch (NullPointerException e) {
						//if getTile returns null when a user enters an invalid coordinate like W2T4
						v.displayMsg("Invalid coordinate.");
						invalidMove = true;
						continue;
					}
					if(select==null || select.color != playersTurn){
						v.displayMsg("No piece of your color selected.");
						invalidMove = true;
						continue;
					}
					//TODO HANDLE BAD RETURN FROM MOVEPIECE
					String err = rules.movePiece(select, move[1], playersTurn, v, board);
					
					if ( err != null ){
						invalidMove = true;
						v.displayMsg(err);
					}
					
				} while(invalidMove);
				
				
				//Switch turn
				playersTurn = (playersTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
				
				
				GameState g = rules.checkGameOver(board);
				if(g!=GameState.IN_PROGRESS){
					gameOver=true;
					v.drawBoard();
					if (g == GameState.DRAW) {
						v.displayMsg("Draw game!");
					}
					else if (g == GameState.ONE_WIN) {
						v.displayMsg("Player one (White) Wins!"); 
					}
					else if (g == GameState.TWO_WIN) {
						v.displayMsg("Player two (Black) Wins!");
					}
				}
			}
			break;
		}
		if(testMode){
			try {
				stateCallback.call(v.getOutput(), board);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public static Rules getRules(int choice){
		 
		Rules rules = null;
		
		if( choice == 3 ){
			rules = new AtomicRules();
		}
		else if ( choice == 2){
			rules = new LosAlamosRules();
		}
		else{
			rules = new ClassicRules();
		}
		
		return rules;
	}

}

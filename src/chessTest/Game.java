package chessTest;

import rules.ClassicRules;
import rules.Rules;

public class Game {
	public static Rules rules;
	public static void main(String[] args) {
		rules = new ClassicRules();
		View v = new TextView(rules.board);
		v.displayMsg("Welcome to chess!");
		boolean gameOver = false;
		Color playersTurn = Color.WHITE;
		while(!gameOver){
			boolean invalidMove; 
			do{
				invalidMove = false;
				v.drawBoard();
				if (playersTurn == Color.WHITE) {
					v.displayMsg("Whites turn");
				}else{
					v.displayMsg("Blacks turn");
				}
				
				Coord[] move = v.getMove();
				if(move==null){
					v.displayMsg("Invalid input. Enter you're move eg(A2,A3)");
					invalidMove = true;
					continue;
				}
				
				//check if first coord is a piece
				Piece select = rules.board.getTile(move[0]).curPiece;
				if(select==null || select.color != playersTurn){
					v.displayMsg("No peice of your color selected.");
					invalidMove = true;
					continue;
				}
				//TODO HANDLE BAD RETURN FROM MOVEPIECE
				rules.movePiece(select, move[1], playersTurn, v);
			} while(invalidMove);
			
			
			//Switch turn
			playersTurn = (playersTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
			
			
			GameState g = rules.checkGameOver();
			if(g!=GameState.IN_PROGRESS){
				gameOver=true;
			}
		}
	}

}

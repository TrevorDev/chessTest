package chessTest;

import rules.ClassicRules;
import rules.Rules;

public class Game {
	public static Rules rules;
	public static void main(String[] args) {
		rules = new ClassicRules();
		View v = new TextView(rules.board);
		System.out.println("started");
		boolean gameOver = false;
		while(!gameOver){
			v.drawBoard();
			Coord[] move = v.getMove();
			if(move!=null){
				Piece select;
				try {
					select = rules.board.getTile(move[0]).curPiece;
					rules.movePiece(select, move[1]);
				} catch (Exception e) {
				}
			}
			GameState g = rules.checkGameOver();
			if(g!=GameState.IN_PROGRESS){
				gameOver=true;
			}
		}
	}

}

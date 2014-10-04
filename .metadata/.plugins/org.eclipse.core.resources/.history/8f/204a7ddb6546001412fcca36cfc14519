package rules;

import chessTest.Coord;
import chessTest.Piece;
import chessTest.Tile;
import boards.ClassicBoard;

public class ClassicRules extends Rules {
	public ClassicRules(){
		this.board = new ClassicBoard();
	}
	
	public void movePiece(Piece p, Coord c){
		try {
			Tile t = this.board.getTile(c);
			t.setPiece(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

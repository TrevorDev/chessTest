package rules;

import java.util.ArrayList;

import chessTest.Coord;
import chessTest.Move;
import chessTest.Piece;
import chessTest.Tile;
import boards.Board;
import boards.LosAlamosBoard;

/* TODO: change textview so that classic rules deals with invalid input
 * 		 so that los alamos can change it so that users cannot promote
 * 		 to bishops */

public class LosAlamosRules extends ClassicRules {
	public LosAlamosRules(){
		
	}
	
	public Board createBoard(){
		return new LosAlamosBoard();
	}
	
	@Override
	public void addPawnMoves(ArrayList<Move> ret, int direction, Coord c,
			Piece p, Board b) {
		//Moving the pawn 2 spaces forward on its first move has been
		//removed
		Tile t = b.getTile(c.x, c.y + (1 * direction));
		if (t != null && t.curPiece == null) {
			ret.add(p.createMove(t.coord.clone(), false));
		}

		t = b.getTile(c.x + 1, c.y + (1 * direction));
		if (t != null && t.curPiece != null) {
			ret.add(p.createMove(t.coord.clone()));
		}

		t = b.getTile(c.x - 1, c.y + (1 * direction));
		if (t != null && t.curPiece != null) {
			ret.add(p.createMove(t.coord.clone()));
		}
	}

	public void addKingMoves(ArrayList<Move> ret, int direction, Coord c,
			Piece p, Board b) {
		//Castling has been removed
		Tile t;
		// loop through the 2D grid around the current piece
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0) { // can't move the king to it's current
										// position
					continue;
				}
				t = b.getTile(c.x + j, c.y + i);
				if (t != null) {
					ret.add(p.createMove(t.coord.clone()));

				}
			}
		}
	}
}
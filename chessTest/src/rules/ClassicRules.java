package rules;

import java.util.ArrayList;

import chessTest.Color;
import chessTest.Coord;
import chessTest.Piece;
import chessTest.PieceName;
import chessTest.Tile;
import boards.ClassicBoard;

public class ClassicRules extends Rules {
	public ClassicRules(){
		this.board = new ClassicBoard();
	}
	
	public void movePiece(Piece p, Coord c, Color playersTurn){
		Tile t = this.board.getTile(c);
		t.setPiece(p);
		t.curPiece.hasMoved = true;
	}
	
	public ArrayList<Coord> listAvailableMoves(Piece p){
		ArrayList<Coord> ret = new ArrayList<Coord>();
		int direction = p.color == Color.WHITE ? 1 : -1;
		if(p.name == PieceName.PAWN){
			Coord c = p.curTile.coord.clone();
			Tile t = this.board.getTile(c.x, c.y+(1*direction));
			if(t!=null && t.curPiece == null){
				ret.add(t.coord.clone());
			}
			if(!p.hasMoved){
				t = this.board.getTile(c.x, c.y+(2*direction));
				if(t!=null && t.curPiece == null){
					ret.add(t.coord.clone());
				}
			}
			
		}
		return ret;
	}
	
}

package rules;

import java.util.ArrayList;
import java.util.Iterator;

import boards.Board;
import boards.ClassicBoard;
import chessTest.Color;
import chessTest.Coord;
import chessTest.Move;
import chessTest.Piece;
import chessTest.PieceName;
import chessTest.Tile;
import chessTest.View;

public class ClassicRules extends Rules {
	public ClassicRules(){
		this.board = new ClassicBoard();
	}
	
	public boolean isInCheck(Board b, Color c) {
		
		Piece king = null;
		boolean check = false;
		
		kingLoop:
		for ( int y = 0; y < board.tiles.length; y++){
			for ( int x = 0; x < board.tiles.length; x++){
				Piece p = board.getTile(new Coord(x,y)).curPiece;
				if ( p.name == PieceName.KING ){
					king = p;
					break kingLoop;
				}
			}
		}
		
		checkLoop:
		for ( int y = 0; y < board.tiles.length; y++){
			for ( int x = 0; x < board.tiles.length; x++){
				Piece p = board.getTile(new Coord(x,y)).curPiece;
				ArrayList<Move> moves = this.listAvailableMoves(p);
				for(Move move : moves){
					if ( move.coord == king.curTile.coord && move.isKillMove ){
						check = true;
						break checkLoop;
					}
				}
			}
		}
		
		return check;
	}
	
	public void movePiece(Piece p, Coord c, Color playersTurn, View view){
		Tile t = this.board.getTile(c);
		ArrayList<Move> moves = this.listAvailableMoves(p);
		for(Move move : moves){
			if(move.coord.x == c.x && move.coord.y == c.y){
				t.setPiece(p);
				t.curPiece.hasMoved = true;
				p = checkPromotion(p, move, view);
				break;
			}
		}
	}
	
	public Piece checkPromotion(Piece p, Move move, View view){
		
		if( p.name == PieceName.PAWN ){
			
			if ( p.color == Color.WHITE ){
				if ( move.coord.y == (board.tiles.length - 1) ){
					p.name = view.getPromotion();
				}
			}
			
			if ( p.color == Color.BLACK ){
				if ( move.coord.y == 0 ){
					p.name = view.getPromotion();
				}
			}
		}
		
		return p;
	}
	
	public ArrayList<Move> listAvailableMoves(Piece p){
		ArrayList<Move> ret = new ArrayList<Move>();
		int direction = p.color == Color.WHITE ? 1 : -1;
		Coord c = p.curTile.coord.clone();
		Tile t;
		if (p.name == PieceName.PAWN){
			t = this.board.getTile(c.x, c.y+(1*direction));
			if(t!=null && t.curPiece == null){
				ret.add(p.createMove(t.coord.clone(),false));
			}
			if(!p.hasMoved){
				t = this.board.getTile(c.x, c.y+(2*direction));
				if(t!=null && t.curPiece == null){
					ret.add(p.createMove(t.coord.clone(),false));
				}
			}
			
			t = this.board.getTile(c.x+1, c.y+(1*direction));
			if(t!=null && t.curPiece != null){
				ret.add(p.createMove(t.coord.clone()));
			}
			
			t = this.board.getTile(c.x-1, c.y+(1*direction));
			if(t!=null && t.curPiece != null){
				ret.add(p.createMove(t.coord.clone()));
			}
			
		} else if (p.name == PieceName.KING){
			// loop through the 2D grid around the current piece
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (i == 0 && j == 0) { // can't move the king to it's current position
						continue;
					}
					t = this.board.getTile(c.x + j, c.y + i);
					if (t != null) {
						ret.add(p.createMove(t.coord.clone()));
						
					}
				}
			}
		} else if (p.name == PieceName.ROOK) {
			// loop through the vertical, going up
			int offset;
			for (offset = 1;; offset++) {
				t = this.board.getTile(c.x, c.y + offset);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
			
			// loop through the vertical, going down
			for (offset = -1;; offset--) {
				t = this.board.getTile(c.x, c.y + offset);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
			
			// loop through the horizontal, going left
			for (offset = 1;; offset++) {
				t = this.board.getTile(c.x + offset, c.y);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
			
			// loop through the horizontal, going right
			for (offset = -1;; offset--) {
				t = this.board.getTile(c.x + offset, c.y);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
				    break;
				}
		    }
		} else if (p.name == PieceName.BISHOP) {
			// loop through the diagonal, going up and left
			int offset;
			for (offset = 1;; offset++) {
				t = this.board.getTile(c.x + offset, c.y + offset);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
			// loop through the diagonal, going up and right
			for (offset = 1;; offset++) {
				t = this.board.getTile(c.x - offset, c.y - offset);
				if (t == null) {
				    break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
			// loop through the diagonal, going down and left
			for (offset = 1;; offset++) {
			    t = this.board.getTile(c.x + offset, c.y - offset);
				if (t == null) {
				   break;
				}
				ret.add(p.createMove(t.coord.clone()));
			    if (t.curPiece != null) {	
					break;
				}
			}
			// loop through the diagonal, going down and right
			for (offset = 1;; offset++) {
			    t = this.board.getTile(c.x - offset, c.y + offset);
				if (t == null) {
				   break;
				}
				ret.add(p.createMove(t.coord.clone()));
			    if (t.curPiece != null) {	
					break;
				}
			}
		}
		else if (p.name == PieceName.QUEEN) {
			// loop through the diagonal, going up and left
			int offset;
		    for (offset = 1;; offset++) {
				t = this.board.getTile(c.x + offset, c.y + offset);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
			// loop through the diagonal, going up and right
			for (offset = 1;; offset++) {
				t = this.board.getTile(c.x - offset, c.y - offset);
				if (t == null) {
				    break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
			// loop through the diagonal, going down and left
			for (offset = 1;; offset++) {
			    t = this.board.getTile(c.x + offset, c.y - offset);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
			// loop through the diagonal, going down and right
			for (offset = 1;; offset++) {
			    t = this.board.getTile(c.x - offset, c.y + offset);
				if (t == null) {
				    break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
						
			// loop through the vertical, going up
			for (offset = 1;; offset++) {
				t = this.board.getTile(c.x, c.y + offset);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
						
			// loop through the vertical, going down
			for (offset = -1;; offset--) {
				t = this.board.getTile(c.x, c.y + offset);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
						
			// loop through the horizontal, going left
			for (offset = 1;; offset++) {
				t = this.board.getTile(c.x + offset, c.y);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
					break;
				}
			}
						
			// loop through the horizontal, going right
			for (offset = -1;; offset--) {
				t = this.board.getTile(c.x + offset, c.y);
				if (t == null) {
					break;
				}
				ret.add(p.createMove(t.coord.clone()));
				if (t.curPiece != null) {	
				    break;
				}
			}
		}
		else if (p.name == PieceName.KNIGHT) {
			t = this.board.getTile(c.x + 1, c.y + 2);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = this.board.getTile(c.x + 1, c.y - 2);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = this.board.getTile(c.x - 1, c.y + 2);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = this.board.getTile(c.x - 1, c.y - 2);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			
			t = this.board.getTile(c.x + 2, c.y + 1);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = this.board.getTile(c.x + 2, c.y - 1);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = this.board.getTile(c.x - 2, c.y + 1);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = this.board.getTile(c.x - 2, c.y - 1);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
		}
		
		// remove all moves that move the piece to a tile that doesn't exist, or to a tile occupied by a piece of our own colour
		Iterator<Move> i = ret.iterator();
		while (i.hasNext()) {
			Move x = i.next();
			if (this.board.getTile(x.coord).curPiece != null && this.board.getTile(x.coord).curPiece.color == p.color) {
				i.remove();
			}
		}
		return ret;
	}
	
}

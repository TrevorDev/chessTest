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
		
	}
	
	public Board createBoard(){
		return new ClassicBoard();
	}
	
	public boolean isInCheck(Board b, Color c) {
		
		Piece king = null;
		boolean check = false;
		
		kingLoop:
		for ( int y = 0; y < b.tiles.length; y++){
			for ( int x = 0; x < b.tiles.length; x++){
				Piece p = b.getTile(new Coord(x,y)).curPiece;
				if ( p != null && p.name == PieceName.KING ){
					king = p;
					break kingLoop;
				}
			}
		}
		
		checkLoop:
		for ( int y = 0; y < b.tiles.length; y++){
			for ( int x = 0; x < b.tiles.length; x++){
				Piece p = b.getTile(new Coord(x,y)).curPiece;
				if(p != null && p.color != c ){
					ArrayList<Move> moves = this.listAvailableMoves(p, b);
					for(Move move : moves){
						if ( move.coord.equals(king.curTile.coord) && move.isKillMove ){
							check = true;
							break checkLoop;
						}
					}
				}
			}
		}
		
		return check;
	}
	
	public String movePiece(Piece p, Coord c, Color playersTurn, View view, Board board){
		Board cloneB = board.clone();
		Tile t = cloneB.getTile(c);
		boolean isValidMove = false;
		Piece cloneP = cloneB.getTile(p.curTile.coord).curPiece;
		ArrayList<Move> moves = this.listAvailableMoves(cloneP, board);
		for(Move move : moves){
			if(move.coord.x == c.x && move.coord.y == c.y){
				t.setPiece(cloneP);
				t.curPiece.hasMoved = true;
				cloneP = checkPromotion(cloneP, move, view, board);
				isValidMove = true;
				break;
			}
		}
		if ( !isValidMove ){
			return "Invalid Move: This piece cannot be moved to your desired tile.";
		}
		
		if( !isInCheck(cloneB, playersTurn)){
			board.set(cloneB);
		}
		else{
			return "Invalid Move: This move leaves your king in check.";
		}
		
		return null;
	}
	
	public Piece checkPromotion(Piece p, Move move, View view, Board b){
		
		if( p.name == PieceName.PAWN ){
			
			if ( p.color == Color.WHITE ){
				if ( move.coord.y == (b.tiles.length - 1) ){
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
	
	public ArrayList<Move> listAvailableMoves(Piece p, Board b){
		ArrayList<Move> ret = new ArrayList<Move>();
		int direction = p.color == Color.WHITE ? 1 : -1;
		Coord c = p.curTile.coord.clone();
		Tile t;
		if (p.name == PieceName.PAWN){
			t = b.getTile(c.x, c.y+(1*direction));
			if(t!=null && t.curPiece == null){
				ret.add(p.createMove(t.coord.clone(),false));
			}
			if(!p.hasMoved){
				t = b.getTile(c.x, c.y+(2*direction));
				if(t!=null && t.curPiece == null){
					ret.add(p.createMove(t.coord.clone(),false));
				}
			}
			
			t = b.getTile(c.x+1, c.y+(1*direction));
			if(t!=null && t.curPiece != null){
				ret.add(p.createMove(t.coord.clone()));
			}
			
			t = b.getTile(c.x-1, c.y+(1*direction));
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
					t = b.getTile(c.x + j, c.y + i);
					if (t != null) {
						ret.add(p.createMove(t.coord.clone()));
						
					}
				}
			}
		} else if (p.name == PieceName.ROOK) {
			// loop through the vertical, going up
			int offset;
			for (offset = 1;; offset++) {
				t = b.getTile(c.x, c.y + offset);
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
				t = b.getTile(c.x, c.y + offset);
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
				t = b.getTile(c.x + offset, c.y);
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
				t = b.getTile(c.x + offset, c.y);
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
				t = b.getTile(c.x + offset, c.y + offset);
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
				t = b.getTile(c.x - offset, c.y - offset);
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
			    t = b.getTile(c.x + offset, c.y - offset);
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
			    t = b.getTile(c.x - offset, c.y + offset);
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
				t = b.getTile(c.x + offset, c.y + offset);
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
				t = b.getTile(c.x - offset, c.y - offset);
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
			    t = b.getTile(c.x + offset, c.y - offset);
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
			    t = b.getTile(c.x - offset, c.y + offset);
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
				t = b.getTile(c.x, c.y + offset);
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
				t = b.getTile(c.x, c.y + offset);
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
				t = b.getTile(c.x + offset, c.y);
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
				t = b.getTile(c.x + offset, c.y);
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
			t = b.getTile(c.x + 1, c.y + 2);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = b.getTile(c.x + 1, c.y - 2);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = b.getTile(c.x - 1, c.y + 2);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = b.getTile(c.x - 1, c.y - 2);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			
			t = b.getTile(c.x + 2, c.y + 1);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = b.getTile(c.x + 2, c.y - 1);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = b.getTile(c.x - 2, c.y + 1);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
			t = b.getTile(c.x - 2, c.y - 1);
			if (t != null) {
				ret.add(p.createMove(t.coord.clone()));
			}
		}
		
		// remove all moves that move the piece to a tile that doesn't exist, or to a tile occupied by a piece of our own colour
		Iterator<Move> i = ret.iterator();
		while (i.hasNext()) {
			Move x = i.next();
			if (b.getTile(x.coord).curPiece != null && b.getTile(x.coord).curPiece.color == p.color) {
				i.remove();
			}
		}
		return ret;
	}
	
}

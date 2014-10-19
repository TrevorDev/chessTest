package rules;

import java.util.ArrayList;

import boards.Board;
import chessTest.Color;
import chessTest.Coord;
import chessTest.Move;
import chessTest.Pair;
import chessTest.Piece;
import chessTest.PieceName;
import chessTest.Tile;
import chessTest.View;

public class AtomicRules extends ClassicRules {
	public AtomicRules(){
		
	}
	
	public String movePiece(Piece p, Coord c, Color playersTurn, View view,
			Board board) {
		Board cloneB = board.clone();
		Tile t = cloneB.getTile(c);
		boolean isValidMove = false;
		boolean isCaptureMove = false;
		Piece cloneP = cloneB.getTile(p.curTile.coord).curPiece;
		ArrayList<Move> moves = this.listAvailableMoves(cloneP, board);
		for (Move move : moves) {
			if (move.coord.x == c.x && move.coord.y == c.y) {
				for (Pair<Piece, Coord> subMove : move.subMoves) {
					if(subMove.getElement1() == null){
						cloneB.getTile(subMove.getElement0().curTile.coord).curPiece = null;
						continue;
					}
					
					t = cloneB.getTile(subMove.getElement1());
					//If there is a capture, go BOOM!
					if (t != null && t.curPiece != null) {
						isCaptureMove = true;
					}
					Piece cloneBP = cloneB
							.getTile(subMove.getElement0().curTile.coord).curPiece;
					t.setPiece(cloneBP);
					if(move.pawnMoveTwo){
						cloneBP.enPassantKillable = true;
					}
					cloneBP.hasMoved = true;
				}
				if (!isCaptureMove) {
					cloneP = checkPromotion(cloneP, move, view, board);
				}
				else {
					atomicCapture(c, cloneB);
				}
				isValidMove = true;
				break;
			}
		}
		if (!isValidMove) {
			return "Invalid Move: This piece cannot be moved to your desired tile.";
		}

		if (!isInCheck(cloneB, playersTurn)) {
			board.set(cloneB);
		} else {
			return "Invalid Move: This move leaves your king in check.";
		}

		return null;
	}
	
	public void atomicCapture(Coord c, Board b) {
		//grabs all tiles surrounding capture at c
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				//Checks that x and y are in board range
				if (c.x + x > -1 && c.y + y > -1 
						&& c.x + x < b.tiles.length 
						&& c.y + y < b.tiles[0].length) {
					
					Tile t = b.getTile(c.x + x, c.y + y);
					if ((t.curPiece != null && t.curPiece.name != PieceName.PAWN)
							|| (x == 0 && y == 0)) {
						//Removes piece
						t.setPiece(null);
					}
				}
			}
		}
	}

	public boolean isInCheck(Board b, Color c) {

		Piece king = null;
		boolean check = false;

		// Find King piece for current player
		kingLoop: for (int y = 0; y < b.tiles.length; y++) {
			for (int x = 0; x < b.tiles.length; x++) {
				Piece p = b.getTile(new Coord(x, y)).curPiece;
				if (p != null && p.name == PieceName.KING && p.color == c) {
					king = p;
					break kingLoop;
				}
			}
		}
		
		//King was killed. Checkmate will return true if check is always true
		if (king == null) {
			return true;
		}

		// Check if King is in check
		checkLoop: for (int y = 0; y < b.tiles.length; y++) {
			for (int x = 0; x < b.tiles.length; x++) {
				Piece p = b.getTile(new Coord(x, y)).curPiece;
				if (p != null && p.color != c) {
					ArrayList<Move> moves = this.listAvailableMoves(p, b);
					for (Move move : moves) {
						if (move.coord.equals(king.curTile.coord)
								&& move.isKillMove) {
							check = true;
							break checkLoop;
						}
					}
				}
			}
		}

		return check;
	}
}

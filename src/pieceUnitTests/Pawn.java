package pieceUnitTests;

import java.util.ArrayList;

import chessTest.Coord;
import chessTest.Move;
import chessTest.Pair;
import chessTest.Piece;
import chessTest.PieceName;
import boards.Board;
import boards.ClassicBoard;
import rules.ClassicRules;
import junit.framework.Assert;
import junit.framework.TestCase;

public class Pawn extends TestCase {
	 public void testListMoves() {
		 ClassicRules r = new ClassicRules();
		 Board b = new ClassicBoard();
		 Piece pawn = b.getTile(0, 1).curPiece;
		 Assert.assertEquals(pawn.name, PieceName.PAWN);
		 ArrayList<Move> moves = r.listAvailableMoves(pawn, b);
		 ArrayList<Move> assertMoves = new ArrayList<Move>();
		 assertMoves.add(new Move(new Pair<Piece, Coord>(pawn, new Coord(0,2))));
		 assertMoves.add(new Move(new Pair<Piece, Coord>(pawn, new Coord(0,3))));
		 moves.containsAll(assertMoves);
	 }
}

package pieceUnitTests;

import java.util.Arrays;

import junit.framework.Assert;
import junit.framework.TestCase;
import rules.ClassicRules;
import boards.Board;
import boards.ClassicBoard;
import chessTest.Coord;
import chessTest.Piece;
import chessTest.PieceName;

public class ListMoves extends TestCase {
	ClassicRules r = new ClassicRules();
	Board b = new ClassicBoard();
	
	 public void testPawn() {
		 Piece piece = b.getTile(0, 1).curPiece;
		 Assert.assertEquals(piece.name, PieceName.PAWN);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(0,2), new Coord(0,3))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(1,2))));
	 }
	 
	 public void testRook() {
		 Piece piece = b.getTile(0, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.ROOK);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(1,2))));
	 }
	 
	 public void testKnight() {
		 Piece piece = b.getTile(1, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.KNIGHT);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(0,2), new Coord(2,2))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(1,2))));
	 }
	 
	 public void testBishup() {
		 Piece piece = b.getTile(2, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.BISHOP);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,1))));
	 }
	 
	 public void testKing() {
		 Piece piece = b.getTile(3, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.QUEEN);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,1))));
	 }
	 
	 public void testQueen() {
		 Piece piece = b.getTile(4, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.KING);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,1))));
	 }
}

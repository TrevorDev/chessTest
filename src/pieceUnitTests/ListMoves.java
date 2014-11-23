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
	 
	 public void testPawnMidGame() {
		 Piece piece = b.getTile(0, 1).curPiece;
		 b.getTile(3, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.PAWN);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,4))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,5))));
	 }
	 
	 public void testPawnEnPassant() {
		 Piece firstPiece = b.getTile(0, 1).curPiece;
		 Piece secondPiece = b.getTile(1, 6).curPiece;
		 b.getTile(0, 4).setPiece(firstPiece);firstPiece.hasMoved = true;
		 b.getTile(1, 4).setPiece(secondPiece);secondPiece.hasMoved = true;secondPiece.enPassantKillable = true;
		 Assert.assertEquals(firstPiece.name, PieceName.PAWN);
		 Assert.assertEquals(secondPiece.name, PieceName.PAWN);
		 Assert.assertTrue(firstPiece.canMoveTo(r, b, Arrays.asList(new Coord(1,5))));
		 Assert.assertFalse(firstPiece.canMoveTo(r, b, Arrays.asList(new Coord(1,6))));
	 }
	 
	 public void testPawnPromotion() {
		 Piece piece = b.getTile(0, 1).curPiece;
		 b.getTile(0, 6).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.PAWN);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(1,7))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(0,7))));
	 }

	 public void testRook() {
		 Piece piece = b.getTile(0, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.ROOK);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(1,2))));
	 }
	 
	 public void testRook2() {
		 Piece piece = b.getTile(7, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.ROOK);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(1,2))));
	 }
	 
	 public void testRookMidGame() {
		 Piece piece = b.getTile(0, 0).curPiece;
		 b.getTile(3, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.ROOK);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,4),new Coord(3,2),new Coord(4,3),new Coord(2,3))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(4,4))));
	 }
	 
	 public void testRookMidGame2() {
		 Piece piece = b.getTile(0, 0).curPiece;
		 b.getTile(0, 7).setPiece(null);
		 b.getTile(0, 6).setPiece(null);
		 b.getTile(0, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.ROOK);
		 //Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,4),new Coord(3,2),new Coord(4,3),new Coord(2,3))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(4,4))));
	 }
	 
	 public void testKnight() {
		 Piece piece = b.getTile(1, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.KNIGHT);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(0,2), new Coord(2,2))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(1,2))));
	 }
	 
	 public void testKnightMidGame() {
		 Piece piece = b.getTile(1, 0).curPiece;
		 b.getTile(3, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.KNIGHT);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(5,4),new Coord(5,2))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(4,4))));
	 }
	 
	 public void testBishop() {
		 Piece piece = b.getTile(2, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.BISHOP);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,1))));
	 }
	 
	 public void testBishopMid2() {
		 Piece piece = b.getTile(2, 0).curPiece;
		 b.getTile(7, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.BISHOP);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,1))));
	 }
	 
	 public void testBishopMid3() {
		 Piece piece = b.getTile(2, 0).curPiece;
		 b.getTile(0, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.BISHOP);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,1))));
	 }
	 
	 public void testBishopMidGame() {
		 Piece piece = b.getTile(2, 0).curPiece;
		 b.getTile(3, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.BISHOP);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(4,4),new Coord(2,2),new Coord(2,4),new Coord(4,2))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(4,5))));
	 }
	 
	 public void testQueen() {
		 Piece piece = b.getTile(3, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.QUEEN);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,1))));
	 }
	 
	 public void testQueenMidGame() {
		 Piece piece = b.getTile(3, 0).curPiece;
		 b.getTile(3, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.QUEEN);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,4),new Coord(3,2),new Coord(4,3),new Coord(2,3),new Coord(4,4),new Coord(2,2),new Coord(2,4),new Coord(4,2))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,3))));
	 }
	 
	 public void testQueenMidGame2() {
		 Piece piece = b.getTile(3, 0).curPiece;
		 b.getTile(7, 4).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.QUEEN);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(7,5))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,3))));
	 }
	 
	 public void testQueenMidGame3() {
		 Piece piece = b.getTile(3, 0).curPiece;
		 b.getTile(0, 7).setPiece(null);
		 b.getTile(0, 6).setPiece(null);
		 b.getTile(0, 4).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.QUEEN);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(1,4))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,3))));
	 }
	 
	 public void testKing() {
		 Piece piece = b.getTile(4, 0).curPiece;
		 Assert.assertEquals(piece.name, PieceName.KING);
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,1))));
	 }
	 
	 public void testKingMidGame() {
		 Piece piece = b.getTile(4, 0).curPiece;
		 b.getTile(3, 3).setPiece(piece);piece.hasMoved = true;
		 Assert.assertEquals(piece.name, PieceName.KING);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,4),new Coord(3,2),new Coord(4,3),new Coord(2,3),new Coord(4,4),new Coord(2,2),new Coord(2,4),new Coord(4,2))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(3,3))));
	 }
	 
	 public void testKingCastling() {
		 Piece piece = b.getTile(4, 0).curPiece;
		 b.getTile(3, 0).setPiece(null);
		 b.getTile(2, 0).setPiece(null);
		 b.getTile(1, 0).setPiece(null);
		 b.getTile(5, 0).setPiece(null);
		 b.getTile(6, 0).setPiece(null);
		 Assert.assertEquals(piece.name, PieceName.KING);
		 Assert.assertTrue(piece.canMoveTo(r, b, Arrays.asList(new Coord(2,0), new Coord(6,0))));
		 Assert.assertFalse(piece.canMoveTo(r, b, Arrays.asList(new Coord(4,0))));
	 }
}

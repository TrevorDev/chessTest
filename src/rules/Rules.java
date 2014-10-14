package rules;

import java.util.ArrayList;

import boards.Board;
import chessTest.Color;
import chessTest.Coord;
import chessTest.GameState;
import chessTest.Move;
import chessTest.Piece;
import chessTest.View;

public abstract class Rules {

	public abstract Board createBoard();

	public abstract ArrayList<Move> listAvailableMoves(Piece p, Board b);

	public abstract String movePiece(Piece p, Coord c, Color playersTurn,
			View view, Board b);

	public abstract GameState checkGameOver(Board b);
}

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
	public Board board;
	public ArrayList<Move> listAvailableMoves(Piece p){
		return null;
	}
	public void movePiece(Piece p, Coord c, Color playersTurn, View view){
		
	}
	public GameState checkGameOver(){
		return GameState.IN_PROGRESS;
	}
}

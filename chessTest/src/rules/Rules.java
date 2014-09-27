package rules;

import java.util.ArrayList;

import boards.Board;
import chessTest.Coord;
import chessTest.GameState;
import chessTest.Piece;

public abstract class Rules {
	public Board board;
	public ArrayList<Coord> listAvailableMoves(){
		return null;
	}
	public void movePiece(Piece p, Coord c){
		
	}
	public GameState checkGameOver(){
		return GameState.IN_PROGRESS;
	}
}

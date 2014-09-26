package rules;

import java.util.ArrayList;

import boards.Board;
import chessTest.Coord;
import chessTest.GameState;

public abstract class Rules {
	public Board board;
	public ArrayList<Coord> listAvailableMoves(){
		return null;
	}
	public void MovePiece(){
		
	}
	public GameState checkGameOver(){
		return null;
	}
}

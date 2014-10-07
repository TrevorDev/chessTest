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
	public Board createBoard(){
		return null;
	}
	public ArrayList<Move> listAvailableMoves(Piece p, Board b){
		return null;
	}
	public String movePiece(Piece p, Coord c, Color playersTurn, View view, Board b){
		return null;
	}
	public GameState checkGameOver(){
		return GameState.IN_PROGRESS;
	}
}

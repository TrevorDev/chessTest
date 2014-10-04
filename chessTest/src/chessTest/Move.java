package chessTest;

import java.util.ArrayList;

public class Move {
	ArrayList<Pair<Piece, Coord>> subMoves;
	public Move(Pair<Piece, Coord> x){
		subMoves = new ArrayList<Pair<Piece, Coord>>();
		this.addSubMove(x);
	}
	
	public void addSubMove(Pair<Piece, Coord> x){
		subMoves.add(x);
	}
}


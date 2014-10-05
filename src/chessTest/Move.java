package chessTest;

import java.util.ArrayList;

public class Move {
	public Coord coord = null;
	ArrayList<Pair<Piece, Coord>> subMoves;
	public Move(Pair<Piece, Coord> x){
		coord = x.getElement1();
		subMoves = new ArrayList<Pair<Piece, Coord>>();
		this.addSubMove(x);
	}
	
	public void addSubMove(Pair<Piece, Coord> x){
		subMoves.add(x);
	}
}


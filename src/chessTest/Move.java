package chessTest;

import java.util.ArrayList;

public class Move {
	
	public Coord coord = null;
	public boolean isKillMove = true;
	public ArrayList<Pair<Piece, Coord>> subMoves;
	
	public Move(Pair<Piece, Coord> x){
		this(x,true);
	}
	
	public Move(Pair<Piece, Coord> x, boolean isKillMove){
		coord = x.getElement1();
		subMoves = new ArrayList<Pair<Piece, Coord>>();
		this.isKillMove = isKillMove;
		this.addSubMove(x);
	}
	
	public void addSubMove(Pair<Piece, Coord> x){
		subMoves.add(x);
	}
}


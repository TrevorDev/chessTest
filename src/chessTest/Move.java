package chessTest;

import java.util.ArrayList;

public class Move {
	
	public Coord coord = null;
	public boolean isKillMove = true;
	public ArrayList<Pair<Piece, Coord>> subMoves;
	public boolean pawnMoveTwo = false;
	
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
	
	@Override
	public boolean equals(Object object){
		 if (object != null && object instanceof Move)
        {
			 Move m = (Move)object;
			 if(this.coord.equals(m.coord)&&this.subMoves.get(0).getElement0() == m.subMoves.get(0).getElement0()){
				return true;
			}
        }
		
		return false;
	}
	
}


package boards;

import java.util.ArrayList;

import chessTest.Piece;
import chessTest.Tile;

public abstract class Board {
	public ArrayList<Piece> pieces;
	public Tile[][] tiles;

	public Tile getTile(int x, int y){
		return null;
	}
}

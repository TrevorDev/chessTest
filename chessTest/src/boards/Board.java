package boards;

import java.util.ArrayList;

import chessTest.Coord;
import chessTest.Piece;
import chessTest.Tile;

public abstract class Board {
	public ArrayList<Piece> pieces;
	public Tile[][] tiles;

	public Tile getTile(int x, int y){
		return null;
	}
	
	public Tile getTile(Coord c) throws Exception{
		if(c.x>tiles[0].length || c.y>tiles.length){
			throw new Exception();
		}
		return tiles[c.y][c.x];
	}
}

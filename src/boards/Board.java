package boards;

import java.util.ArrayList;

import chessTest.Coord;
import chessTest.Piece;
import chessTest.Tile;

public abstract class Board {
	public ArrayList<Piece> pieces;
	public Tile[][] tiles;

	public Tile getTile(int x, int y){
		return getTile(new Coord(x,y));
	}
	
	public Tile getTile(Coord c){
		if(c.x>tiles[0].length || c.y>tiles.length){
			return null;
		}
		return tiles[c.y][c.x];
	}
}

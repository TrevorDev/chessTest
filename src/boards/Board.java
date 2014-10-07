package boards;

import chessTest.Coord;
import chessTest.Tile;

public abstract class Board {
	public Tile[][] tiles;

	public Tile getTile(int x, int y){
		return getTile(new Coord(x,y));
	}
	
	public Tile getTile(Coord c){
		if(c.x < 0 || c.y < 0 || c.x>=tiles[0].length || c.y>=tiles.length){
			return null;
		}
		return tiles[c.y][c.x];
	}
	
	public Board clone(){
		return null;
	}
	
	public void set(Board b){
		return;
	}
}

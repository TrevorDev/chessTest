package chessTest;

public class Tile {
	public Piece curPiece;
	public Coord coord;
	
	public Tile(Coord c, Piece p){
		this.curPiece = p;
		this.coord = c;
	}
	
	public void setPiece(Piece p){
		if(p.curTile!=null){
			p.curTile.curPiece=null;
		}
		this.curPiece = p;
		p.curTile = this;
	}
}
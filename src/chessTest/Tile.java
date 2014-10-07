package chessTest;

public class Tile {
	public Piece curPiece;
	public Coord coord;
	
	public Tile(Coord c, Piece p){
		this.curPiece = p;
		this.coord = c;
	}
	
	public Tile clone(){
		Tile ret = new Tile(this.coord.clone(), null);
		if(this.curPiece != null){
			ret.curPiece = this.curPiece.clone();
			ret.curPiece.curTile = ret;
		}
		
		return ret;
	}
	
	public void setPiece(Piece p){
		if(p.curTile!=null){
			p.curTile.curPiece=null;
		}
		this.curPiece = p;
		p.curTile = this;
	}
}

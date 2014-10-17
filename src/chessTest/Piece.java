package chessTest;

public class Piece {
	public PieceName name;
	public Color color;
	public Tile curTile;
	public boolean hasMoved;
	public boolean inCheck;
	public boolean enPassantKillable = false;
	
	public Piece(PieceName name, Color color){
		this.name = name;
		this.color = color;
	}
	
	public Move createMove(Coord x){
		return new Move(new Pair<Piece, Coord>(this, x));
	}
	
	public Move createMove(Coord x, boolean isKillMove){
		return new Move(new Pair<Piece, Coord>(this, x), isKillMove);
	}
	
	public Piece clone(){
		Piece p = new Piece(this.name, this.color);
		p.curTile = null;
		p.hasMoved = this.hasMoved;
		p.inCheck = this.inCheck;
		p.enPassantKillable = this.enPassantKillable;
		return p;
		
	}
}

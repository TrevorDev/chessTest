package chessTest;

public class Piece {
	public PieceName name;
	public Color color;
	public Tile curTile;
	public boolean hasMoved;
	public Piece(PieceName name, Color color){
		this.name = name;
		this.color = color;
	}
}

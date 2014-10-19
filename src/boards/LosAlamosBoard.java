package boards;

import chessTest.Color;
import chessTest.Coord;
import chessTest.Piece;
import chessTest.PieceName;
import chessTest.Tile;

public class LosAlamosBoard extends Board{
	public LosAlamosBoard(){
		this.tiles = new Tile[6][6];
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				this.tiles[i][j] = new Tile(new Coord(j,i), null);
			}
		}
		for(int i=0;i<6;i++){
			this.tiles[1][i].setPiece(new Piece(PieceName.PAWN, Color.WHITE));
		}
		this.tiles[0][0].setPiece(new Piece(PieceName.ROOK, Color.WHITE));
		this.tiles[0][1].setPiece(new Piece(PieceName.KNIGHT, Color.WHITE));
		this.tiles[0][2].setPiece(new Piece(PieceName.QUEEN, Color.WHITE));
		this.tiles[0][3].setPiece(new Piece(PieceName.KING, Color.WHITE));
		this.tiles[0][4].setPiece(new Piece(PieceName.KNIGHT, Color.WHITE));
		this.tiles[0][5].setPiece(new Piece(PieceName.ROOK, Color.WHITE));
		
		
		
		for(int i=0;i<6;i++){
			this.tiles[4][i].setPiece(new Piece(PieceName.PAWN, Color.BLACK));
		}
		this.tiles[5][0].setPiece(new Piece(PieceName.ROOK, Color.BLACK));
		this.tiles[5][1].setPiece(new Piece(PieceName.KNIGHT, Color.BLACK));
		this.tiles[5][2].setPiece(new Piece(PieceName.QUEEN, Color.BLACK));
		this.tiles[5][3].setPiece(new Piece(PieceName.KING, Color.BLACK));
		this.tiles[5][4].setPiece(new Piece(PieceName.KNIGHT, Color.BLACK));
		this.tiles[5][5].setPiece(new Piece(PieceName.ROOK, Color.BLACK));
	}
	
	public Board clone(){
		Board clone = new LosAlamosBoard();
		for(int y = 0;y<this.tiles.length;y++){
			for(int x = 0;x<this.tiles.length;x++){
				Tile t = this.getTile(x, y);
				clone.tiles[y][x] = t.clone();
			}
		}
		return clone;
	}
	
	public void set(Board b){
		for(int y = 0;y<b.tiles.length;y++){
			for(int x = 0;x<b.tiles.length;x++){
				this.tiles[y][x] = b.getTile(x, y).clone();
			}
		}
	}
}

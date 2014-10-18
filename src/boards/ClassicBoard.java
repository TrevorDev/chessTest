package boards;

import chessTest.Color;
import chessTest.Coord;
import chessTest.Piece;
import chessTest.PieceName;
import chessTest.Tile;

public class ClassicBoard extends Board {
	public ClassicBoard(){
		this.tiles = new Tile[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				this.tiles[i][j] = new Tile(new Coord(j,i), null);
			}
		}
		for(int i=0;i<8;i++){
			this.tiles[1][i].setPiece(new Piece(PieceName.PAWN, Color.WHITE));
		}
		this.tiles[0][0].setPiece(new Piece(PieceName.ROOK, Color.WHITE));
		this.tiles[0][1].setPiece(new Piece(PieceName.KNIGHT, Color.WHITE));
		this.tiles[0][2].setPiece(new Piece(PieceName.BISHOP, Color.WHITE));
		this.tiles[0][3].setPiece(new Piece(PieceName.QUEEN, Color.WHITE));
		this.tiles[0][4].setPiece(new Piece(PieceName.KING, Color.WHITE));
		this.tiles[0][5].setPiece(new Piece(PieceName.BISHOP, Color.WHITE));
		this.tiles[0][6].setPiece(new Piece(PieceName.KNIGHT, Color.WHITE));
		this.tiles[0][7].setPiece(new Piece(PieceName.ROOK, Color.WHITE));
		
		
		
		for(int i=0;i<8;i++){
			this.tiles[6][i].setPiece(new Piece(PieceName.PAWN, Color.BLACK));
		}
		this.tiles[7][0].setPiece(new Piece(PieceName.ROOK, Color.BLACK));
		this.tiles[7][1].setPiece(new Piece(PieceName.KNIGHT, Color.BLACK));
		this.tiles[7][2].setPiece(new Piece(PieceName.BISHOP, Color.BLACK));
		this.tiles[7][3].setPiece(new Piece(PieceName.QUEEN, Color.BLACK));
		this.tiles[7][4].setPiece(new Piece(PieceName.KING, Color.BLACK));
		this.tiles[7][5].setPiece(new Piece(PieceName.BISHOP, Color.BLACK));
		this.tiles[7][6].setPiece(new Piece(PieceName.KNIGHT, Color.BLACK));
		this.tiles[7][7].setPiece(new Piece(PieceName.ROOK, Color.BLACK));
	}
	
	public Board clone(){
		Board clone = new ClassicBoard();
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

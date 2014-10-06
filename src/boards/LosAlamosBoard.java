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
		this.tiles[0][2].setPiece(new Piece(PieceName.KING, Color.WHITE));
		this.tiles[0][3].setPiece(new Piece(PieceName.QUEEN, Color.WHITE));
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
}

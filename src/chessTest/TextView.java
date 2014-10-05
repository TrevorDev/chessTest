package chessTest;

import java.util.Scanner;

import boards.Board;

public class TextView implements View {
	Board board;
	static Scanner in = new Scanner(System.in);
	public TextView(Board b){
		this.board=b;
	}
	
	@Override
	public void drawBoard() {
		System.out.println("   a b c d e f g h");
		System.out.println("   - - - - - - - -");
		for(int i=board.tiles.length-1;i>=0;i--){
			System.out.print((i+1)+" |");
			for(int j=0;j<board.tiles[0].length;j++){
				
				char out = 'º';
				if((i+j)%2==0){
					out = '×';
				}
				Piece p = board.tiles[i][j].curPiece;
				if(p!=null){
					//System.out.println(p.name);
					if(p.name == PieceName.BISHOP){
						out = 'b';
					}else if(p.name == PieceName.PAWN){
						out = 'p';
					}else if(p.name == PieceName.ROOK){
						out = 'r';
					}else if(p.name == PieceName.KNIGHT){
						out = 'n';
					}else if(p.name == PieceName.KING){
						out = 'k';
					}else if(p.name == PieceName.QUEEN){
						out = 'q';
					}
					if(p.color == Color.WHITE){
						 out = Character.toUpperCase(out);
					}
				}				
				System.out.print(out+" ");
			}
			System.out.println("|");
		}
		System.out.println("   - - - - - - - -");
	}

	@Override
	public Coord[] getMove() {
		System.out.println("Enter you're move eg(A2,A3)");
		String input = "";
		input = in.nextLine();
		input = input.toLowerCase();
		input = input.replaceAll("[^A-Za-z0-9]", "");
		
		//clear screen
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		if(input.length()!=4){
			return null;
		}
		
		try{
			Coord fromCoord;
			Coord toCoord;
			fromCoord = createCoord(input.toCharArray()[0], input.toCharArray()[1]);
			toCoord = createCoord(input.toCharArray()[2], input.toCharArray()[3]);
			Coord[] ret = new Coord[2];
			ret[0]=fromCoord;
			ret[1]=toCoord;
			return ret; 
		}catch(Exception e){
			return null;
		}
	}
	
	public Coord createCoord(char alpha, char num) throws Exception{
			int x = alpha - 'a';
			int y = Integer.parseInt(num+"")-1;
			return new Coord(x, y);
	}

	@Override
	public void displayMsg(String msg) {
		System.out.println(msg);
	}

}
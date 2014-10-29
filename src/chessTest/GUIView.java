package chessTest;

import java.util.ArrayList;

import boards.Board;

public class GUIView implements View{
	Board board;
	@Override
	public void drawBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMsg(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coord[] getMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PieceName getPromotion(ArrayList<Pair<Character,PieceName>> pieceChoice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initTextView(Board b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGameType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

}

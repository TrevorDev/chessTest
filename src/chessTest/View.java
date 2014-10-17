package chessTest;

import boards.Board;

public interface View {
	public void initTextView(Board b);
	public int getGameType();
	public void drawBoard();
	public void displayMsg(String msg);
	public Coord[] getMove();
	public PieceName getPromotion();
}

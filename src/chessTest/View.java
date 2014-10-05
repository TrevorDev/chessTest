package chessTest;

public interface View {
	public void drawBoard();
	public void displayMsg(String msg);
	public Coord[] getMove();
}

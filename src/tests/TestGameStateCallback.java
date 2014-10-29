package tests;

import boards.Board;

public interface TestGameStateCallback {
	public void call(String output, Board b);
}

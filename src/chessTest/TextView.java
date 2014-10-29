package chessTest;

import java.util.ArrayList;
import java.util.Scanner;

import boards.Board;

public class TextView implements View {
	Board board;
	boolean testMode = false;
	String[] testInput;
	int testInputPos = -1;
	String testOutput = "";
	static Scanner in = new Scanner(System.in);

	public TextView(String testInputString) {
		if(testInputString != null){
			testMode = true;
			testInput = testInputString.split("\n");
		}
		this.board = null;
	}

	public void initTextView(Board b) {
		this.board = b;
	}

	public void printDashLine() {
		displayMsg("   ", 0);
		for (int i = 0; i < board.tiles.length; i++) {
			displayMsg("- ", 0);
		}
		displayMsg("");
	}

	public void printLetters() {
		displayMsg("   ", 0);
		for (int i = 0; i < board.tiles.length; i++) {
			displayMsg(Character.toChars((i + 65))[0] + " ", 0);
		}
		displayMsg("");
	}

	@Override
	public void drawBoard() {
		printLetters();
		printDashLine();
		for (int i = board.tiles.length - 1; i >= 0; i--) {
			displayMsg((i + 1) + " |", 0);
			for (int j = 0; j < board.tiles[0].length; j++) {

				char out = '.';
				if ((i + j) % 2 == 0) {
					out = '.';
				}
				Piece p = board.tiles[i][j].curPiece;
				if (p != null) {
					// displayMsg(p.name);
					if (p.name == PieceName.BISHOP) {
						out = 'b';
					} else if (p.name == PieceName.PAWN) {
						out = 'p';
					} else if (p.name == PieceName.ROOK) {
						out = 'r';
					} else if (p.name == PieceName.KNIGHT) {
						out = 'n';
					} else if (p.name == PieceName.KING) {
						out = 'k';
					} else if (p.name == PieceName.QUEEN) {
						out = 'q';
					}
					if (p.color == Color.WHITE) {
						out = Character.toUpperCase(out);
					}
				}
				displayMsg(out + " ", 0);
			}
			displayMsg("| " + (i + 1));
		}
		printDashLine();
		printLetters();
	}

	@Override
	public Coord[] getMove() {
		displayMsg("Enter your move eg(A2,A3), or q to quit");
		String input = "";
		input = getNextLine();
		input = input.toLowerCase();
		input = input.replaceAll("[^A-Za-z0-9]", "");

		// clear screen
		displayMsg("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		if (input.equals("q")) {
			Coord[] ret = new Coord[1];
			ret[0] = new Coord(-1, -1);
			return ret;
		}

		if (input.length() != 4) {
			return null;
		}

		try {
			Coord fromCoord;
			Coord toCoord;
			fromCoord = createCoord(input.toCharArray()[0],
					input.toCharArray()[1]);
			toCoord = createCoord(input.toCharArray()[2],
					input.toCharArray()[3]);
			Coord[] ret = new Coord[2];
			ret[0] = fromCoord;
			ret[1] = toCoord;
			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	public int getGameType() {

		String input = "";
		int gameType = -1;

		displayMsg("Please Enter the number corresponding to the Chess Type you would like to play");
		displayMsg("1. Classic Chess");
		displayMsg("2. Los Alamos Chess");
		displayMsg("3. Atomic Chess");

		input = getNextLine();
		input = input.replaceAll("[^0-9]", "");

		// clear screen
		displayMsg("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		if (input.length() != 1) {
			return -1;
		}

		gameType = Integer.parseInt(input);

		return gameType;
	}

	public Coord createCoord(char alpha, char num) throws Exception {
		int x = alpha - 'a';
		int y = Integer.parseInt(num + "") - 1;
		return new Coord(x, y);
	}

	@Override
	public void displayMsg(String msg) {
		if(!testMode){
			System.out.println(msg);
		}
		
		this.testOutput = this.testOutput + msg+ "\n";
	}
	
	public void displayMsg(String msg, int newlines) {
		if(!testMode){
			System.out.print(msg);
		}
		
		this.testOutput = this.testOutput + msg;
	}

	public String getNextLine(){
		testInputPos++;
		try{
			return this.testMode ? this.testInput[testInputPos] : in.nextLine();
		}catch(Exception e){
			System.out.println("Not enuf input to end the game");
		}
		return null;
	}
	
	public PieceName getPromotion(ArrayList<Pair<Character,PieceName>> pieceChoices) {

		while (true) {
			displayMsg("Enter the piece you would like to promote your pawn into from the options above:");
			String input = "";
			input = getNextLine();

			if (input.length() != 1) {
				displayMsg("Invaild input, please try again.");
				continue;
			}
			
			for (int i = 0; i< pieceChoices.size(); i++) {
				if (input.charAt(0) == pieceChoices.get(i).getElement0()) {
					// clear screen
					displayMsg("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					
					return pieceChoices.get(i).getElement1();
				}
			}
			
			displayMsg("Invaild promotion piece, please try again.");
		}
	}

	@Override
	public String getOutput() {
		return this.testOutput;
	}
}

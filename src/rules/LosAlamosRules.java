package rules;

import boards.Board;
import boards.LosAlamosBoard;

public class LosAlamosRules extends ClassicRules {
	public LosAlamosRules(){
		
	}
	
	public Board createBoard(){
		return new LosAlamosBoard();
	}
}

package chessTest;

import java.awt.Point;

@SuppressWarnings("serial")
public class Coord extends Point{

	public Coord(String userInput){
		
	}

	public Coord(int j, int i) {
		this.x = j;
		this.y=i;
		// TODO Auto-generated constructor stub
	}
	
	public Coord clone(){
		return new Coord(this.x, this.y);
	}
}

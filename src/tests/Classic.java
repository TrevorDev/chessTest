package tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import boards.Board;
import chessTest.Game;

public class Classic extends TestCase {
	 
	  public void testOne() {
		  Game.main(null, true, TestHelper.fileToString("testFiles/1.txt"), new TestGameStateCallback() {
			   public void call(String output, Board b) {
				   //System.out.println(output);
				   Assert.assertNotNull(output);
			   }
			});
		  Assert.assertNotNull("");
	  }
	  
	  public void testTwo() {
		    Assert.assertEquals("Test1", "Test1");
	  }
}

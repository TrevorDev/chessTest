package tests;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Classic extends TestCase {
	public void setUp() throws Exception {
	    System.out.println("Setting up ...");
	  }
	 
	  public void teanDown() throws Exception {
	    System.out.println("Tearing down ...");
	  }
	 
	  public void testOne() {
	    Assert.assertNotNull(null);
	  }
	  
	  public void testTwo() {
		    Assert.assertEquals("Test1", "Test1");
	  }
}

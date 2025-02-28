package org.jfree.data.test;

import static org.junit.Assert.*; 

import org.jfree.data.DataUtilities;
import org.jfree.data.*;
import org.junit.*;

public class calculateRowTotalTest  {
	DefaultKeyedValues2D exampleTable = new DefaultKeyedValues2D();
	
	@Before
	public void setUp() {
		exampleTable.addValue(3.0, "R0", "C0");
		exampleTable.addValue(5.0, "R0", "C1");
		exampleTable.addValue(7.0, "", null);
		exampleTable.addValue(11.0, null, null);
	}
	@Test
	public void calculateRowTotal() {
		fail("Not yet implemented");
	} 

}

package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
/*	constrain 
 * 	- values is less than lower, equals lower, within range, equals upper, 
 * greater than upper, and negative 
	combine 
	- ranges are separated, overlapping, equal
	- check null cases 
	getCentralValue
	- tests 
		range is positive, mixed, negative, even length, odd length
		
*/    
	
//	constrain testing 
	@Test
	public void valueLessThanRange() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = -5.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(-2.0, result, .000000001d);
	}
	@Test
	public void valueEqualsLowerBound() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = -2.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(-2.0, result, .000000001d);
	}
	@Test
	public void valueWithinRangeExcludingBounds() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = 3.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(3.0, result, .000000001d);
	}
	@Test

	public void valueEqualsUpperBound() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = 6.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(6.0, result, .000000001d);
	}
	@Test
	public void valueGreaterThanRange() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = 11.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(6.0, result, .000000001d);
	}
	
// 	combine testing
	@Test
	public void oneNullRange() {
		Range exampleRangeOne = new Range(1,4);
		Range exampleRangeTwo = null;
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		assertEquals(exampleRangeOne, resultRange);
	}
	
	@Test
	public void bothNullRange() {
		Range exampleRangeOne = null;
		Range exampleRangeTwo = null;
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNull(resultRange);
	}
	
	@Test
	public void combineSeperateRanges() {
		Range exampleRangeOne = new Range(6, 8);
		Range exampleRangeTwo = new Range(-7, -3);
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		Range finalRange = new Range(-7,8);
		assertEquals(finalRange, resultRange);
	}
	
	@Test 
	public void combineSimilarRanges() {
		Range exampleRangeOne = new Range(7, 10);
		Range exampleRangeTwo = new Range(5, 8);
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		Range finalRange = new Range(5,10);
		assertEquals(finalRange, resultRange);
	}
	
	@Test 
	public void combineEqualRanges() {
		Range exampleRangeOne = new Range(2,9);
		Range exampleRangeTwo = new Range(2,9);
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		Range finalRange = new Range(2,9);
		assertEquals(finalRange, resultRange);
	}
	
//	getCentralValue testing
    @Test
    public void centralValueShouldBeZero() {
        Range exampleRange = new Range(-1,1);
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void positiveRangeEvenLengthCentralValue() {
    	// calculate the central value of a range that has an even length and positive upper and lower
    	double upper = 5.0;
    	double lower = 3.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 == 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void positiveRangeOddLengthCentralValue() {
    	double upper = 7.0;
    	double lower = 4.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 != 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void mixedRangeEvenLengthCentralValue() {
    	double upper = 5.0;
    	double lower = -3.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 == 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void mixedRangeOddLengthCentralValue() {
    	double upper = 4.0;
    	double lower = -7.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 != 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void negativeRangeEvenLengthCentralValue() {
    	double upper = -3.0;
    	double lower = -11.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 == 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void negativeRangeOddLengthCentralValue() {
    	double upper = -6.0;
    	double lower = -13.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 != 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    
    // Intersects Test :: MARK JIMENEZ (30193166)
    // Intersects Test :: MARK JIMENEZ (30193166)
    // Intersects Test :: MARK JIMENEZ (30193166)
    
    @Test
    public void testRangeIsBigger() {
        // MUST :: If testRange is bigger than exampleRange, returns true
    	Range exampleRange = new Range(-10, 10);
        assertTrue(exampleRange.intersects(-20, 20));
    }
    @Test
    public void testRangeWithinExampleRange() {
    //MUST :: If testRane is within the exampleRAnge, return True 
    	Range exampleRange = new Range(-10, 10);
        assertTrue("A range fully inside exampleRange should intersect.", 
        exampleRange.intersects(-5, 5));
    }

    @Test
    public void testRangeTouchingLower() {
        // MUST :: If testRange touches exampleRange's lower bound, returns true
    	Range exampleRange = new Range(-10, 10);
        double testValueLower = exampleRange.getLowerBound();
        assertTrue(exampleRange.intersects(testValueLower, 5));
    }

    @Test
    public void testRangeTouchingUpper() {
        // MUST :: If testRange touches exampleRange's upper bound, returns true
    	Range exampleRange = new Range(-10, 10);
        double testValueUpper = exampleRange.getUpperBound();
        assertTrue(exampleRange.intersects(0, testValueUpper));
    }

  
    @Test
    public void testRangeSameSize() {
        // MUST :: If testRange == exampleRange boundaries, returns true
    	Range exampleRange = new Range(-10, 10);
        double upper = exampleRange.getUpperBound();
        double lower = exampleRange.getLowerBound();
        assertTrue(exampleRange.intersects(lower, upper));
    }
    
    @Test
    public void testRangeBefore() {
        // MUST :: If testRange is before lower bound exampleRange, returns false
    	Range exampleRange = new Range(-10, 10);
        assertFalse(exampleRange.intersects(-30, -15));
    }
    @Test
    public void testRangeAfter() {
        // MUST :: If testRange is after upper bound exampleRange, returns false
    	Range exampleRange = new Range(-10, 10);
        assertFalse(exampleRange.intersects(15, 30));
    }
    
    @Test 
    public void testRangeAroundLowerBound() {
    // MUST :: If testRange is touching lower bound, return True
    	Range exampleRange = new Range(-10, 10);
    	assertTrue(exampleRange.intersects(-15,-5));
    
    }
    @Test 
    public void testRangeAroundUpperBound() {
    //MUST :: If testRange is touching upperBound, return TRUE
    	Range exampleRange = new Range(-10, 10);
    	assertTrue(exampleRange.intersects(5,15));
    }
    
 // toString Method Testing 
 	//Returns a string of the range "Range[lower,upper]"
 	
 	// Tests if toString() correctly formats positive ranges
 	 @Test
 	 public void PositiveRange() {
 		 
 		 Range range = new Range(7, 12);
 		 
 		// Expected output: "Range[7.0,12.0]"
 		 
 	     assertEquals("toString range check (positive).",
 	     "Range[7.0,12.0]", range.toString());
 	 }

 	// Tests if toString() correctly formats negative ranges
 	 @Test
 	 public void NegativeRange() {
 		 
 	     Range range = new Range(-10, -3);
 	     
 	     // Expected output: "Range[-10.0,-3.0]" 
 		 
 	     assertEquals("toString range check (negative).",
 	     "Range[-10.0,-3.0]", range.toString());
 	 }
 	 
 	// Tests if toString() correctly formats negative-positive ranges
 	 @Test
 	 public void NegativePositiveRange() {
 		 
 	     Range range = new Range(-7, 10);    
 	     
 	     // Expected output: "Range[-7.0,10.0]"
 	     
 	     assertEquals("toString range check (negative & positive).",
 	     "Range[-7.0,10.0]", range.toString());
 	 }
  
 	// Tests if toString() correctly formats zero ranges
 	 @Test
 	 public void ZeroRange() {

 	     Range range = new Range(0, 0);
 	     
 	     // Expected output: "Range[0.0,0.0]"
 	     
 	     assertEquals("toString range check (both zero).",
 	     "Range[0.0,0.0]", range.toString());
 	 }
    
}

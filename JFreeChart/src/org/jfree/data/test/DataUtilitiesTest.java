package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.*;
import org.junit.*;

import org.jmock.Expectations;
import org.jmock.Mockery;


public class DataUtilitiesTest {
	@Test
	public void simpleCalculateRowTotal() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0,0);
				will(returnValue(3.0));
				one(values).getValue(1,0);
				will(returnValue(-7.0));
			}
		});
		// calculate the row total of a row containing a positive value, expected: 3.0
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(3.0, result, .000000001d);

	}
	
	@Test
	public void negativeValueCalculateRowTotal() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0,0);
				will(returnValue(3.0));
				one(values).getValue(1,0);
				will(returnValue(-7.0));
			}
		});
		// calculate the row total of a row containing a negative value, expected: -7.0
		double result = DataUtilities.calculateRowTotal(values, 1);
		assertEquals(-7.0, result, .000000001d);

	}
	
	@Test
	public void calculateRowTotalOf2x2Table() {
		
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0,0);
				will(returnValue(3.0));
				one(values).getValue(0,1);
				will(returnValue(5.0));
				one(values).getValue(1,0);
				will(returnValue(7.0));
				one(values).getValue(1,1);
				will(returnValue(11.0));
			}
		});
		// calculate the row total for the 2nd row, expected value: 18.0
		double result = DataUtilities.calculateRowTotal(values, 1);
		assertEquals(18.0, result, .000000001d);
		
	}
	 // calculateColumnTotal Test :: MARK JIMENEZ (30193166)
	 // calculateColumnTotal Test :: MARK JIMENEZ (30193166)
	 // calculateColumnTotal Test :: MARK JIMENEZ (30193166)
	 
	 @Test
	    public void sumColumnWithPositiveNumbers() {
	        // MUST :: the sum of all the column must be equal to 22
	        Mockery mockingContext = new Mockery();
	        final Values2D mockData = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations()
	        {
	            {
	            // ADDING POSITIVE VALUES TO COLUMN
	                allowing(mockData).getRowCount(); will(returnValue(4));
	                allowing(mockData).getValue(0, 2); will(returnValue(9.0)); 
	                allowing(mockData).getValue(1, 2);will(returnValue(6.0));
	                allowing(mockData).getValue(2, 2);will(returnValue(4.0));
	                allowing(mockData).getValue(3, 2); will(returnValue(3.0));
	            }
	          }
	        );
	        double myResult = DataUtilities.calculateColumnTotal(mockData, 2);
	        assertEquals(22, myResult, 0.0001);
	    }
	 
	 
	    @Test
	    public void sumColumnWithNegativeNumbers() {
	     // MUST :: the sum of all the column must be equal to -12
	        Mockery mockingContext = new Mockery();
	        final Values2D negativeValues = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations()
	        {
	        
	            { 
	            // ADDING NEAGTIVE VALUES TO COLUMN
	                one(negativeValues).getRowCount(); will(returnValue(3));
	                one(negativeValues).getValue(0, 1); will(returnValue(-7.0));
	                one(negativeValues).getValue(1, 1); will(returnValue(-3.0));
	                one(negativeValues).getValue(2, 1); will(returnValue(-2.0));
	            }
	          }
	        );
	        double result = DataUtilities.calculateColumnTotal(negativeValues, 1);
	        assertEquals(-12.0, result, .000000001d);
	    }
	    
	    @Test
	    public void sumColumnWithNoValues() {
	        // Must return 0.0 when the column is empty
	        Mockery mockingContext = new Mockery();
	        final Values2D emptyValues = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	            //EMPTY COLUMN
	                one(emptyValues).getRowCount(); will(returnValue(0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(emptyValues, 0);
	        assertEquals(0.0, result, .000000001d);
	    }
	 // getCumulativePercentages Method Testing
		 
		@Test
		public void dataHasSingleValue(){
			
			Mockery mockingContext = new Mockery();
			final KeyedValues values = mockingContext.mock(KeyedValues.class);
			mockingContext.checking(new Expectations() {
				{
					allowing(values).getItemCount();
					will(returnValue(1));
					allowing(values).getValue(0);
					will(returnValue(1));
					allowing(values).getKey(0);
					will(returnValue(0));
				}
			});
			KeyedValues result = DataUtilities.getCumulativePercentages(values);
			
			// Input Value: {1}, Expected Cumulative Percentage: {1.0}
			
			assertEquals(1.0, result.getValue(0));
		
		}
		
		@Test
		public void dataHasFiveValues(){
			Mockery mockingContext = new Mockery();
			final KeyedValues values = mockingContext.mock(KeyedValues.class);
			mockingContext.checking(new Expectations() {
				{
					allowing(values).getItemCount();
					will(returnValue(5));
					allowing(values).getValue(0);
					will(returnValue(1));
					allowing(values).getValue(1);
					will(returnValue(1));
					allowing(values).getValue(2);
					will(returnValue(1));
					allowing(values).getValue(3);
					will(returnValue(1));
					allowing(values).getValue(4);
					will(returnValue(1));
					allowing(values).getKey(0);
					will(returnValue(0));
					allowing(values).getKey(1);
					will(returnValue(1));
					allowing(values).getKey(2);
					will(returnValue(2));
					allowing(values).getKey(3);
					will(returnValue(3));
					allowing(values).getKey(4);
					will(returnValue(4));

				}
			});
			
			KeyedValues result = DataUtilities.getCumulativePercentages(values);
			
			// Input Value: {1, 1, 1, 1 ,1}, Expected Cumulative Percentage: {0.2, 0.4, 0.6, 0.8, 1.0}
			
			assertEquals(0.2, result.getValue(0));
			assertEquals(0.4, result.getValue(1));
			assertEquals(0.6, result.getValue(2));
			assertEquals(0.8, result.getValue(3));
			assertEquals(1.0, result.getValue(4));
		}
		
		@Test
		public void dataHasFirstTwoValuesZero(){
			Mockery mockingContext = new Mockery();
			final KeyedValues values = mockingContext.mock(KeyedValues.class);
			mockingContext.checking(new Expectations() {
				{
					allowing(values).getItemCount();
					will(returnValue(3));
					allowing(values).getValue(0);
					will(returnValue(0));
					allowing(values).getValue(1);
					will(returnValue(0));
					allowing(values).getValue(2);
					will(returnValue(1));
					allowing(values).getKey(0);
					will(returnValue(0));
					allowing(values).getKey(1);
					will(returnValue(1));
					allowing(values).getKey(2);
					will(returnValue(2));
		
				}
			});
			
			KeyedValues result = DataUtilities.getCumulativePercentages(values);
			
			// Input Value: {0, 0, 1}, Expected Cumulative Percentage: {0.0, 0.0, 1.0}
			
			assertEquals(0.0, result.getValue(0));
			assertEquals(0.0, result.getValue(1));
			assertEquals(1.0, result.getValue(2));

		}
		
		@Test
		public void dataHasLastTwoValuesZero(){
			Mockery mockingContext = new Mockery();
			final KeyedValues values = mockingContext.mock(KeyedValues.class);
			mockingContext.checking(new Expectations() {
				{
					allowing(values).getItemCount();
					will(returnValue(3));
					allowing(values).getValue(0);
					will(returnValue(1));
					allowing(values).getValue(1);
					will(returnValue(0));
					allowing(values).getValue(2);
					will(returnValue(0));
					allowing(values).getKey(0);
					will(returnValue(0));
					allowing(values).getKey(1);
					will(returnValue(1));
					allowing(values).getKey(2);
					will(returnValue(2));
				
				}
			});
			
			KeyedValues result = DataUtilities.getCumulativePercentages(values);
			
			// Input Value: {1, 0, 0}, Expected Cumulative Percentage: {1.0, 1.0, 1.0}
			
			assertEquals(1.0, result.getValue(0));
			assertEquals(1.0, result.getValue(1));
			assertEquals(1.0, result.getValue(2));
		}
		
		@Test
		public void dataHasAllZero(){
			Mockery mockingContext = new Mockery();
			final KeyedValues values = mockingContext.mock(KeyedValues.class);
			mockingContext.checking(new Expectations() {
				{
					allowing(values).getItemCount();
					will(returnValue(3));
					allowing(values).getValue(0);
					will(returnValue(0));
					allowing(values).getValue(1);
					will(returnValue(0));
					allowing(values).getValue(2);
					will(returnValue(0));
					allowing(values).getKey(0);
					will(returnValue(0));
					allowing(values).getKey(1);
					will(returnValue(1));
					allowing(values).getKey(2);
					will(returnValue(2));

				}
			});
			
			KeyedValues result = DataUtilities.getCumulativePercentages(values);
			
			// Input Value: {0, 0, 0}, Gives NaN Value 0/0 cause of formula, not a number/percentage
			
			assertEquals(0.0 / 0.0, result.getValue(0));
			assertEquals(0.0 / 0.0, result.getValue(1));
			assertEquals(0.0 / 0.0, result.getValue(2));
		}	    
		/**
	 * Test for DataUtilities.createNumberArray with valid nonzero values.
	 * This test verifies that the returned array is non-null, has the expected length,
	 * and that each element matches the corresponding input value.
	 */
	@Test
	public void createNumberArrayWithValidValues() {
	    double[] input = {1.5, 2.5, 3.5};
	    Number[] result = DataUtilities.createNumberArray(input);

	    // Check that the result is not null and has the correct length.
	    assertNotNull("Result should not be null", result);
	    assertEquals("Array length should match", input.length, result.length);

	    // Compare each value numerically (with a null-check).
	    for (int i = 0; i < input.length; i++) {
	        assertNotNull("Element at index " + i + " should not be null", result[i]);
	        assertEquals("Mismatch at index " + i, input[i], result[i].doubleValue(), 0.00001);
	    }
	}

	/**
	 * Test for DataUtilities.createNumberArray with an empty input array.
	 * Verifies that an empty input array returns an empty Number array.
	 */
	@Test
	public void createNumberArrayWithEmptyArray() {
	    double[] input = {};
	    Number[] expected = {};
	    Number[] result = DataUtilities.createNumberArray(input);
	    assertArrayEquals("Empty arrays should be equal", expected, result);
	}

	/**
	 * Test for DataUtilities.createNumberArray with negative numbers.
	 * This test checks that each negative value is properly converted.
	 */
	@Test
	public void createNumberArrayWithNegativeNumbers() {
	    double[] input = {-1.2, -2.3, -3.4};
	    Number[] result = DataUtilities.createNumberArray(input);

	    // Ensure array lengths match.
	    assertEquals("Array length should match", input.length, result.length);

	    // Compare each element numerically, ensuring no element is null.
	    for (int i = 0; i < input.length; i++) {
	        assertNotNull("Element at index " + i + " should not be null", result[i]);
	        assertEquals("Mismatch at index " + i, input[i], result[i].doubleValue(), 0.00001);
	    }
	}

	/**
	 * Test for DataUtilities.createNumberArray with zero values.
	 * This test verifies that the returned array for zero values matches the expected values.
	 */
	@Test
	public void createNumberArrayWithZeroValues() {
	    double[] input = {0.0, 0.0, 0.0};
	    Number[] result = DataUtilities.createNumberArray(input);

	    assertNotNull("Result should not be null", result);

	    // Define expected output as a Double array.
	    Double[] expected = {0.0, 0.0, 0.0};
	    assertArrayEquals("Arrays with zeroes should be equal", expected, result);
	}
	/**
     * Test 5 for createNumberArray: Using assertArrayEquals directly.
     * Purpose: Verify that the whole returned array exactly matches the expected array.
     * (This test is concise but may fail if there’s a type mismatch.)
     */
    @Test
    public void createNumberArrayAssertArrayEquals() {
        double[] input = {3.3, 4.4, 5.5};
        // Explicitly declare expected as Double[] so that type matches.
        Double[] expected = {3.3, 4.4, 5.5};
        Number[] result = DataUtilities.createNumberArray(input);
        assertArrayEquals("Arrays should be equal", expected, result);
    }

    /**
     * Test for DataUtilities.createNumberArray2D with valid values.
     * This test verifies that a 2D array of doubles is correctly converted to a 2D Number array.
     */
    @Test
    public void createNumberArray2DWithValidValues() {
        double[][] input = {
            {1.5, 2.5},
            {3.5, 4.5}
        };
        // Define the expected 2D array. (You might need to use a loop to compare the rows if assertArrayEquals does not handle 2D arrays directly.)
        Number[][] expected = {
            {1.5, 2.5},
            {3.5, 4.5}
        };

        Number[][] result = DataUtilities.createNumberArray2D(input);

        // Verify that the result is not null.
        assertNotNull("Result should not be null", result);
        // Check that the outer array length matches.
        assertEquals("Outer array length should match", input.length, result.length);

        // Compare each row.
        for (int i = 0; i < input.length; i++) {
            // Check that each row is not null and has the correct length.
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Row length for row " + i + " should match", input[i].length, result[i].length);

            // Compare each element in the row.
            for (int j = 0; j < input[i].length; j++) {
                assertNotNull("Element at (" + i + "," + j + ") should not be null", result[i][j]);
                assertEquals("Mismatch at (" + i + "," + j + ")",
                             input[i][j], result[i][j].doubleValue(), 0.00001);
            }
        }
    }

    /**
     * Test for DataUtilities.createNumberArray2D with an empty 2D array.
     * Verifies that an empty 2D input array returns an empty 2D Number array.
     */
    @Test
    public void createNumberArray2DWithEmptyArray() {
        double[][] input = {};
        Number[][] expected = {};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertArrayEquals("Empty 2D arrays should be equal", expected, result);
    }
    /**
     * Test 3 for createNumberArray2D: 2D array with negative numbers.
     * Purpose: Check that a 2D array containing negative values is converted correctly.
     */
    @Test
    public void createNumberArray2DNegativeNumbers() {
        double[][] input = {
            {-1.1, -2.2},
            {-3.3, -4.4}
        };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Outer array length should match", input.length, result.length);
        for (int i = 0; i < input.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Row " + i + " length should match", input[i].length, result[i].length);
            for (int j = 0; j < input[i].length; j++) {
                assertNotNull("Element at (" + i + "," + j + ") should not be null", result[i][j]);
                assertEquals("Mismatch at (" + i + "," + j + ")",
                             input[i][j], result[i][j].doubleValue(), 0.00001);
            }
        }
    }

    /**
     * Test 4 for createNumberArray2D: 2D array with all zero values.
     * Purpose: Verify that a 2D array of zeros is converted correctly,
     * with every element equal to 0.0.
     */
    @Test
    public void createNumberArray2DZeroValues() {
        double[][] input = {
            {0.0, 0.0},
            {0.0, 0.0}
        };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Outer array length should match", input.length, result.length);
        for (int i = 0; i < input.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Row " + i + " length should match", input[i].length, result[i].length);
            for (int j = 0; j < input[i].length; j++) {
                assertNotNull("Element at (" + i + "," + j + ") should not be null", result[i][j]);
                assertEquals("Mismatch at (" + i + "," + j + ")",
                             input[i][j], result[i][j].doubleValue(), 0.00001);
            }
        }
    }

    /**
     * Test 5 for createNumberArray2D: 2D array with mixed values.
     * Purpose: Verify that a 2D array containing positive, negative, and zero values
     * is converted correctly into a 2D Number array.
     */
    @Test
    public void createNumberArray2DMixedValues() {
        double[][] input = {
            {1.1, 0.0, -1.1},
            {2.2, -2.2, 0.0}
        };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Outer array length should match", input.length, result.length);
        for (int i = 0; i < input.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Row " + i + " length should match", input[i].length, result[i].length);
            for (int j = 0; j < input[i].length; j++) {
                assertNotNull("Element at (" + i + "," + j + ") should not be null", result[i][j]);
                assertEquals("Mismatch at (" + i + "," + j + ")",
                             input[i][j], result[i][j].doubleValue(), 0.00001);
            }
        }
    }

}

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
				will(returnValue(7.0));
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(3.0, result, .000000001d);

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
}

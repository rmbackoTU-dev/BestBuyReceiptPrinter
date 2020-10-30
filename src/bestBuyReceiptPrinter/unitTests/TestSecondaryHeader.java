package bestBuyReceiptPrinter.unitTests;

import bestBuyReceiptPrinter.addOns.SecondaryHeader;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSecondaryHeader {

	@Test
	public void testSecondaryHeaderDefaultConstruct()
	{
		SecondaryHeader newHeader=new SecondaryHeader();
		Assertions.assertEquals("", newHeader.getLines());
	}

	/**
	 * Subsumes test of test SetHeader
	 * and test getLines
	 */
	@Test
	public void testSecondaryHeaderParamConstruct()
	{
		String headerParam="Best Buy: Happy Halloween";
		SecondaryHeader newHeader=new SecondaryHeader(headerParam);
		Assertions.assertEquals(headerParam, newHeader.getLines());
	}
	
	
	/**
	 * This test may not be entirely necessary 
	 * since the result never varies
	 * but code coverage.
	 */
	@Test
	public void testApplies()
	{
		SecondaryHeader newHeader=new SecondaryHeader();
		PurchasedItems items=new PurchasedItems();
		Assertions.assertTrue(newHeader.applies(items));
	}
	
}
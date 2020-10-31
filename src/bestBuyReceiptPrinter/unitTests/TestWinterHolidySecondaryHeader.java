package bestBuyReceiptPrinter.unitTests;

import bestBuyReceiptPrinter.addOns.WinterHolidayHeader;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.LocalDate;

public class TestWinterHolidySecondaryHeader {

	
	
	@Test
	public void testWinterHolidayHeaderDefaultConstruct()
	{
		WinterHolidayHeader newHeader=new WinterHolidayHeader();
		Assertions.assertEquals("Happy Holidays", newHeader.getLines());
	}

	@Test
	public void testHolidayHeaderGetDates()
	{
		
	}
	
	
	@Test
	public void testAppliesTrue()
	{
		WinterHolidayHeader newHeader=new WinterHolidayHeader();
		PurchasedItems items=new PurchasedItems();
		Assertions.assertTrue(newHeader.applies(items));
	}
	
	
	/**
	 * Subsumes test of test SetHeader
	 * and test getLines
	 */
	@Test
	public void testWinterHolidayHeaderParamConstruct()
	{
		String headerParam=" Merry Christmas";
		WinterHolidayHeader newHeader=new WinterHolidayHeader(headerParam);
		Assertions.assertEquals("Happy Holidays"+headerParam, newHeader.getLines());
	}
	
}
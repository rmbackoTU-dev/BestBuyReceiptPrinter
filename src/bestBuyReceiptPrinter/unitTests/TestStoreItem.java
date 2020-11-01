package bestBuyReceiptPrinter.unitTests;

import org.junit.jupiter.api.Test;

import bestBuyReceiptPrinter.clientCode.data.StoreItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;

public class TestStoreItem {

	/**
	 * Subsumes any getter testing
	 */
	@Test
	public void testStoreItemDefaultConstructor()
	{
		StoreItem newStoreItem=new StoreItem();
		BigDecimal actualPrice=newStoreItem.getPrice();
		double actualPriceDouble=actualPrice.doubleValue();
		String actualDescript=newStoreItem.getDescription();
		int actualInventoryNum=newStoreItem.getInventoryNum();
		Assertions.assertEquals(0.00, actualPriceDouble);
	    Assertions.assertEquals("A new Item : 1", actualDescript);
	    Assertions.assertEquals(1, actualInventoryNum);
	    newStoreItem.finalize();
	}
	    
	@Test
	public void testStoreItemParameterConstructor()
	{
		BigDecimal expectedPrice=new BigDecimal(1899.98);
		expectedPrice=expectedPrice.setScale(2, RoundingMode.CEILING);
		double expectedPriceDouble=expectedPrice.doubleValue();
		String expectedDescription="Dell XPS 15";
		int expectedInventoryNum=1;
		StoreItem newStoreItem=new StoreItem(expectedDescription,
				expectedPrice);
		BigDecimal actualPrice=newStoreItem.getPrice();
		double actualPriceDouble=actualPrice.doubleValue();
		String actualDescript=newStoreItem.getDescription();
		int actualInventoryNum=newStoreItem.getInventoryNum();
		Assertions.assertEquals(expectedPriceDouble, actualPriceDouble);
	    Assertions.assertEquals(expectedDescription+" : 1", actualDescript);
	    Assertions.assertEquals(expectedInventoryNum, actualInventoryNum);
	    newStoreItem.finalize();

		
	}
	
	@Test
	public void testFinalize()
	{
		StoreItem newStoreItem=new StoreItem();
		StoreItem newStoreItemTwo=new StoreItem();
		Assertions.assertEquals(2, StoreItem.getItemCount());
		newStoreItemTwo.finalize();
		Assertions.assertEquals(1, StoreItem.getItemCount());
		newStoreItemTwo.finalize();
		Assertions.assertEquals(0, StoreItem.getItemCount());
	}
	
	
	
	
}

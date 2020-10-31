package bestBuyReceiptPrinter.unitTests;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems.PurchasedItemsIterator;

public class TestPurchasedItems {

	private PurchasedItems testItems;
	
	@BeforeEach
	public void setUp()
	{
		testItems=new PurchasedItems();
	}
	
	@Test
	public void testPurchasedItemsConstructors()
	{
		Assertions.assertNotNull(testItems);
	}
	
	//Subsumes getCurrentItem test and purchasedItems Iterator Constructor
	//Non-empty test
	@Test
	public void testAddPurchasedItems()
	{
		BigDecimal price=new BigDecimal(10.99);
		price=price.setScale(2, RoundingMode.CEILING);
		StoreItem newItemOne= new StoreItem("itemOne", price ,-1);
		this.testItems.addItem(newItemOne);
		PurchasedItemsIterator itemsIter=this.testItems.getPurchasedItemsIter();
		String expectedDescription="itemOne";
		int expectedRebateId=-1;
		double expectedPriceDouble=price.doubleValue();
		StoreItem actualItem=itemsIter.getCurrentItem();
		Assertions.assertEquals(expectedDescription+" : 1", actualItem.getDescription());
		Assertions.assertEquals(expectedRebateId, actualItem.getRebateId());
		Assertions.assertEquals(expectedPriceDouble, actualItem.getPrice().doubleValue());
		newItemOne.finalize();
	}
	
	@Test
	public void testPurchasedItemsIterConstructEmpty()
	{
		PurchasedItemsIterator itemIter=this.testItems.getPurchasedItemsIter();
		Assertions.assertNotNull(itemIter);
		Assertions.assertThrows(IllegalStateException.class, () -> 
		{
			itemIter.getCurrentItem();
		});
	}
	
	
	@Test
	public void testPurchasedItemsIterNext()
	{
		BigDecimal price=new BigDecimal(10.99);
		price=price.setScale(2, RoundingMode.CEILING);
		String expectedDescription="itemOne";
		String expectedDescriptionTwo="itemTwo";
		int expectedRebateId=-1;
		StoreItem newItemOne= new StoreItem(expectedDescription, price , expectedRebateId);
		StoreItem newItemTwo= new StoreItem(expectedDescriptionTwo, price , expectedRebateId);
		this.testItems.addItem(newItemOne);
		this.testItems.addItem(newItemTwo);
		PurchasedItemsIterator itemsIter=this.testItems.getPurchasedItemsIter();
		itemsIter.next();
		StoreItem actualItem=itemsIter.getCurrentItem();
		Assertions.assertEquals(expectedDescriptionTwo +" : 2", actualItem.getDescription());
		newItemOne.finalize();
		newItemTwo.finalize();
	}
	
	@Test
	public void testPurchasedItemsIterHasNextTrue()
	{
	    BigDecimal price=new BigDecimal(10.99);
		price=price.setScale(2, RoundingMode.CEILING);
		String expectedDescription="itemOne";
		String expectedDescriptionTwo="itemTwo";
		int expectedRebateId=-1;
		StoreItem newItemOne= new StoreItem(expectedDescription, price , expectedRebateId);
		StoreItem newItemTwo= new StoreItem(expectedDescriptionTwo, price , expectedRebateId);
		this.testItems.addItem(newItemOne);
		this.testItems.addItem(newItemTwo);
		PurchasedItemsIterator itemsIter=this.testItems.getPurchasedItemsIter();
		Assertions.assertTrue(itemsIter.hasNext());
		newItemOne.finalize();
		newItemTwo.finalize();
	}
	
	@Test
	public void testPurchasedItemsIterHasNextFalse()
	{
		BigDecimal price=new BigDecimal(10.99);
		price=price.setScale(2, RoundingMode.CEILING);
		String expectedDescription="itemOne";
		int expectedRebateId=-1;
		StoreItem newItemOne= new StoreItem(expectedDescription, price , expectedRebateId);
		this.testItems.addItem(newItemOne);
		PurchasedItemsIterator itemsIter=this.testItems.getPurchasedItemsIter();
		Assertions.assertFalse(itemsIter.hasNext());
		newItemOne.finalize();
	}
	
	
	@Test
	public void testResetIter()
	{
		BigDecimal price=new BigDecimal(10.99);
		price=price.setScale(2, RoundingMode.CEILING);
		String expectedDescription="itemOne";
		String expectedDescriptionTwo="itemTwo";
		int expectedRebateId=-1;
		StoreItem newItemOne= new StoreItem(expectedDescription, price , expectedRebateId);
		StoreItem newItemTwo= new StoreItem(expectedDescriptionTwo, price , expectedRebateId);
		this.testItems.addItem(newItemOne);
		this.testItems.addItem(newItemTwo);
		PurchasedItemsIterator itemsIter=this.testItems.getPurchasedItemsIter();
		itemsIter.next();
		StoreItem actualItem=itemsIter.getCurrentItem();
		Assertions.assertEquals(expectedDescriptionTwo+" : 2", actualItem.getDescription());
		itemsIter.resetIter();
		actualItem=itemsIter.getCurrentItem();
		Assertions.assertEquals(expectedDescription+" : 1", actualItem.getDescription());
		newItemOne.finalize();
		newItemTwo.finalize();
	}
	
}

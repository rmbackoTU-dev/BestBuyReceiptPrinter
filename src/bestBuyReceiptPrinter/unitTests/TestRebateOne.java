package bestBuyReceiptPrinter.unitTests;

import java.math.BigDecimal;
import java.math.RoundingMode;
import bestBuyReceiptPrinter.addOns.RebateOne;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestRebateOne {

	@Test
	public void testRebateConstruct()
	{
		RebateOne newRebate=new RebateOne();
		BigDecimal defaultRebateAmount=new BigDecimal(1);
		defaultRebateAmount=defaultRebateAmount.setScale(2, RoundingMode.CEILING);
		BigDecimal actualDefaultRebateAmount=newRebate.getAmountOff();
		double expectedRebateAmountAsDouble=defaultRebateAmount.doubleValue();
		double actualRebateAmountAsDouble=actualDefaultRebateAmount.doubleValue();		
		Assertions.assertEquals(expectedRebateAmountAsDouble, 
				actualRebateAmountAsDouble);
	}

	
	@Test
	public void testRebateGetters()
	{
		BigDecimal expectedRebate=new BigDecimal(1);
		expectedRebate=expectedRebate.setScale(2, RoundingMode.CEILING);
		RebateOne newRebate= new RebateOne();
		BigDecimal actualRebate=newRebate.getAmountOff();
		double actualRebateAsDouble=actualRebate.doubleValue();
		double expectedRebateAsDouble=expectedRebate.doubleValue();
		Assertions.assertEquals(expectedRebateAsDouble, actualRebateAsDouble);
	}
	
	@Test
	public void testRebateAppliesOneItem()
	{
		RebateOne newRebate= new RebateOne();
		String storeItemDecription="Dell XPS 15";
		BigDecimal itemPrice=new BigDecimal(1899.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		StoreItem newItem=new StoreItem(storeItemDecription, itemPrice);
		newRebate.addToQualifyingPurchases(newItem);
		PurchasedItems newPurchaseList=new PurchasedItems();
		newPurchaseList.addItem(newItem);
		Assertions.assertTrue(newRebate.applies(newPurchaseList));
		newItem.finalize();
		
	}
	
	@Test
	public void testRebateAppliesSecondItems()
	{
		RebateOne newRebate= new RebateOne();
		String storeItemDescription="MSI Prestige 15";
		BigDecimal itemPrice=new BigDecimal(1799.00);
		String storeItemDescriptionTwo="Dell XPS 15";
		BigDecimal itemPriceTwo=new BigDecimal(1899.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		itemPriceTwo=itemPriceTwo.setScale(2, RoundingMode.CEILING);
		StoreItem newItem=new StoreItem(storeItemDescription, itemPrice );
		StoreItem newItemTwo=new StoreItem(storeItemDescriptionTwo, itemPriceTwo);
		newRebate.addToQualifyingPurchases(newItem);
		newRebate.addToQualifyingPurchases(newItemTwo);
		PurchasedItems newPurchaseList=new PurchasedItems();
		newPurchaseList.addItem(newItem);
		newPurchaseList.addItem(newItemTwo);
		Assertions.assertTrue(newRebate.applies(newPurchaseList));
		newItem.finalize();
		newItemTwo.finalize();
	}
	
	@Test
	public void testRebateAppliesThirdItems()
	{
		RebateOne newRebate= new RebateOne();
		String storeItemDescription="MSI Prestige 15";
		BigDecimal itemPrice=new BigDecimal(1799.00);
		String storeItemDescriptionTwo="Dell XPS 15";
		BigDecimal itemPriceTwo=new BigDecimal(1899.00);
		String storeItemDescriptionThree="Apple Macbook Pro 16";
		BigDecimal itemPriceThree=new BigDecimal(2399.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		itemPriceTwo=itemPriceTwo.setScale(2, RoundingMode.CEILING);
		itemPriceThree=itemPriceThree.setScale(2, RoundingMode.CEILING);
		StoreItem newItem=new StoreItem(storeItemDescription, itemPrice);
		StoreItem newItemTwo=new StoreItem(storeItemDescriptionTwo, itemPriceTwo);
		StoreItem newItemThree=new StoreItem(storeItemDescriptionThree, itemPriceThree);
		newRebate.addToQualifyingPurchases(newItem);
		newRebate.addToQualifyingPurchases(newItemTwo);
		newRebate.addToQualifyingPurchases(newItemThree);
		PurchasedItems newPurchaseList=new PurchasedItems();
		newPurchaseList.addItem(newItem);
		newPurchaseList.addItem(newItemTwo);
		newPurchaseList.addItem(newItemThree);
		Assertions.assertTrue(newRebate.applies(newPurchaseList));
		newItem.finalize();
		newItemTwo.finalize();
		newItemThree.finalize();

	}
	
	@Test
	public void testRebateDoesNotApplyThreeItems()
	{
		RebateOne newRebate= new RebateOne();
		String storeItemDescription="MSI Prestige 15";
		BigDecimal itemPrice=new BigDecimal(1799.00);
		String storeItemDescriptionTwo="Dell XPS 15";
		BigDecimal itemPriceTwo=new BigDecimal(1899.00);
		String storeItemDescriptionThree="Apple Macbook Pro 16";
		BigDecimal itemPriceThree=new BigDecimal(2399.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		itemPriceTwo=itemPriceTwo.setScale(2, RoundingMode.CEILING);
		itemPriceThree=itemPriceThree.setScale(2, RoundingMode.CEILING);
		StoreItem newItem=new StoreItem(storeItemDescription, itemPrice);
		StoreItem newItemTwo=new StoreItem(storeItemDescriptionTwo, itemPriceTwo);
		StoreItem newItemThree=new StoreItem(storeItemDescriptionThree, itemPriceThree);
		PurchasedItems newPurchaseList=new PurchasedItems();
		newPurchaseList.addItem(newItem);
		newPurchaseList.addItem(newItemTwo);
		newPurchaseList.addItem(newItemThree);
		Assertions.assertFalse(newRebate.applies(newPurchaseList));
	}
	
	@Test
	public void testRebateGetLines()
	{
		RebateOne newRebate=new RebateOne();
		BigDecimal rebateAmountOff=newRebate.getAmountOff();
		String expectedGetLines="$"+rebateAmountOff.toString()+" REBATE (#1)";
		String actualGetLines=newRebate.getLines();
		Assertions.assertEquals(expectedGetLines, actualGetLines);
	}
	
	//Subsumes getQualifyingItems test
	@Test
	public void addQualifyingItem()
	{
		String storeItemDescription="MSI Prestige 15";
		BigDecimal itemPrice=new BigDecimal(1799.00);
		String storeItemDescriptionTwo="Dell XPS 15";
		BigDecimal itemPriceTwo=new BigDecimal(1899.00);
		String storeItemDescriptionThree="Apple Macbook Pro 16";
		BigDecimal itemPriceThree=new BigDecimal(2399.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		itemPriceTwo=itemPriceTwo.setScale(2, RoundingMode.CEILING);
		itemPriceThree=itemPriceThree.setScale(2, RoundingMode.CEILING);
		StoreItem itemOne=new StoreItem(storeItemDescription, itemPrice);
		StoreItem itemTwo=new StoreItem(storeItemDescriptionTwo, itemPriceTwo);
		StoreItem itemThree=new StoreItem(storeItemDescriptionThree, itemPriceThree);
		RebateOne newRebate=new RebateOne();
		newRebate.addToQualifyingPurchases(itemOne);
		newRebate.addToQualifyingPurchases(itemTwo);
		newRebate.addToQualifyingPurchases(itemThree);
		String[] qualifyingItems=newRebate.getListOfQualifyingItems();
		for(int i=0; i< qualifyingItems.length; i++)
		{
			String itemString=qualifyingItems[i];
			if(i == 0)
			{
				Assertions.assertEquals(storeItemDescription+" : 1", itemString);
			}
			else if(i == 1)
			{
				Assertions.assertEquals(storeItemDescriptionTwo+" : 2", itemString);
			}
			else
			{
				Assertions.assertEquals(storeItemDescriptionThree+" : 3", itemString);
			}
		}
		itemOne.finalize();
		itemTwo.finalize();
		itemThree.finalize();
		 
	}
	
	@Test
	public void removeQualifyingItem()
	{
		String storeItemDescription="MSI Prestige 15";
		BigDecimal itemPrice=new BigDecimal(1799.00);
		String storeItemDescriptionTwo="Dell XPS 15";
		BigDecimal itemPriceTwo=new BigDecimal(1899.00);
		String storeItemDescriptionThree="Apple Macbook Pro 16";
		BigDecimal itemPriceThree=new BigDecimal(2399.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		itemPriceTwo=itemPriceTwo.setScale(2, RoundingMode.CEILING);
		itemPriceThree=itemPriceThree.setScale(2, RoundingMode.CEILING);
		StoreItem itemOne=new StoreItem(storeItemDescription, itemPrice);
		StoreItem itemTwo=new StoreItem(storeItemDescriptionTwo, itemPriceTwo);
		StoreItem itemThree=new StoreItem(storeItemDescriptionTwo, itemPriceThree);
		RebateOne newRebate=new RebateOne();
		newRebate.addToQualifyingPurchases(itemOne);
		newRebate.addToQualifyingPurchases(itemTwo);
		newRebate.addToQualifyingPurchases(itemThree);
		String[] qualifyingItems=newRebate.getListOfQualifyingItems();
		Assertions.assertEquals(3, qualifyingItems.length);
		itemOne.finalize();
		itemTwo.finalize();
		itemThree.finalize();
	}
}

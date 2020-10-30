package bestBuyReceiptPrinter.unitTests;

import java.math.BigDecimal;
import java.math.RoundingMode;
import bestBuyReceiptPrinter.addOns.Rebate;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestRebate {

	@Test
	public void testRebateConstruct()
	{
		Rebate newRebate=new Rebate();
		Rebate newRebateTwo=new Rebate();
		BigDecimal defaultRebateAmount=new BigDecimal(0);
		defaultRebateAmount=defaultRebateAmount.setScale(2, RoundingMode.CEILING);
		BigDecimal actualDefaultRebateAmount=newRebate.getAmountOff();
		double expectedRebateAmountAsDouble=defaultRebateAmount.doubleValue();
		double actualRebateAmountAsDouble=actualDefaultRebateAmount.doubleValue();		
		int actualRebateId=newRebate.getRebateId();
		int actualRebateIdTwo=newRebateTwo.getRebateId();
		Assertions.assertEquals(expectedRebateAmountAsDouble, 
				actualRebateAmountAsDouble);
		Assertions.assertTrue((actualRebateIdTwo == (actualRebateId + 1 )));
	}

	@Test
	public void testRebateParamConstruct()
	{
		BigDecimal rebateAmountParam=new BigDecimal(10.99);
		rebateAmountParam=rebateAmountParam.setScale(2, RoundingMode.CEILING);
		Rebate newRebate=new Rebate(rebateAmountParam);
		Rebate newRebateTwo=new Rebate(rebateAmountParam);
		double expectedRebateAmountAsDouble=rebateAmountParam.doubleValue();
		BigDecimal actualRebateAmount=newRebate.getAmountOff();
		double actualRebateAmountAsDouble=rebateAmountParam.doubleValue();
		int actualRebateId=newRebate.getRebateId();
		int actualRebateIdTwo=newRebateTwo.getRebateId();
		Assertions.assertEquals(expectedRebateAmountAsDouble, actualRebateAmountAsDouble);
		Assertions.assertTrue((actualRebateIdTwo == (actualRebateId +1)));
	}
	
	@Test
	public void testRebateSetter()
	{
		Rebate newRebate=new Rebate();
BigDecimal setRebate=new BigDecimal(20.99);
		setRebate.setScale(2, RoundingMode.CEILING);
		newRebate.setAmountOff(setRebate);
		BigDecimal actualRebate=newRebate.getAmountOff();
		double actualRebateAsDouble=actualRebate.doubleValue();
		double expectedRebateAsDouble=setRebate.doubleValue();
		Assertions.assertEquals(expectedRebateAsDouble, actualRebateAsDouble);
		
	}
	
	@Test
	public void testRebateGetters()
	{
		BigDecimal setRebate=new BigDecimal(20.99);
		setRebate.setScale(2, RoundingMode.CEILING);
		Rebate newRebate= new Rebate(setRebate);
		BigDecimal actualRebate=newRebate.getAmountOff();
		double actualRebateAsDouble=actualRebate.doubleValue();
		double expectedRebateAsDouble=setRebate.doubleValue();
		Assertions.assertEquals(expectedRebateAsDouble, actualRebateAsDouble);
	}
	
	@Test
	public void testRebateAppliesOneItem()
	{
		BigDecimal setRebate=new BigDecimal(20.99);
		setRebate.setScale(2, RoundingMode.CEILING);
		Rebate newRebate= new Rebate(setRebate);
		String storeItemDecription="Dell XPS 15";
		BigDecimal itemPrice=new BigDecimal(1899.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		StoreItem newItem=new StoreItem(storeItemDecription, itemPrice, newRebate.getRebateId());
		PurchasedItems newPurchaseList=new PurchasedItems();
		newPurchaseList.addItem(newItem);
		Assertions.assertTrue(newRebate.applies(newPurchaseList));
		
	}
	
	@Test
	public void testRebateAppliesSecondItems()
	{
		BigDecimal setRebate=new BigDecimal(20.99);
		setRebate.setScale(2, RoundingMode.CEILING);
		Rebate newRebate= new Rebate(setRebate);
		String storeItemDescription="MSI Prestige 15";
		BigDecimal itemPrice=new BigDecimal(1799.00);
		String storeItemDescriptionTwo="Dell XPS 15";
		BigDecimal itemPriceTwo=new BigDecimal(1899.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		itemPriceTwo=itemPriceTwo.setScale(2, RoundingMode.CEILING);
		StoreItem newItem=new StoreItem(storeItemDescription, itemPrice, -1);
		StoreItem newItemTwo=new StoreItem(storeItemDescriptionTwo, itemPriceTwo, newRebate.getRebateId());
		PurchasedItems newPurchaseList=new PurchasedItems();
		newPurchaseList.addItem(newItem);
		newPurchaseList.addItem(newItemTwo);
		Assertions.assertTrue(newRebate.applies(newPurchaseList));
	}
	
	@Test
	public void testRebateAppliesThirdItems()
	{
		BigDecimal setRebate=new BigDecimal(20.99);
		setRebate.setScale(2, RoundingMode.CEILING);
		Rebate newRebate= new Rebate(setRebate);
		String storeItemDescription="MSI Prestige 15";
		BigDecimal itemPrice=new BigDecimal(1799.00);
		String storeItemDescriptionTwo="Dell XPS 15";
		BigDecimal itemPriceTwo=new BigDecimal(1899.00);
		String storeItemDescriptionThree="Apple Macbook Pro 16";
		BigDecimal itemPriceThree=new BigDecimal(2399.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		itemPriceTwo=itemPriceTwo.setScale(2, RoundingMode.CEILING);
		itemPriceThree=itemPriceThree.setScale(2, RoundingMode.CEILING);
		StoreItem newItem=new StoreItem(storeItemDescription, itemPrice, -1);
		StoreItem newItemTwo=new StoreItem(storeItemDescriptionTwo, itemPriceTwo, -1);
		StoreItem newItemThree=new StoreItem(storeItemDescriptionThree, itemPriceThree, newRebate.getRebateId());
		PurchasedItems newPurchaseList=new PurchasedItems();
		newPurchaseList.addItem(newItem);
		newPurchaseList.addItem(newItemTwo);
		newPurchaseList.addItem(newItemThree);
		Assertions.assertTrue(newRebate.applies(newPurchaseList));

	}
	
	@Test
	public void testRebateDoesNotApplyThreeItems()
	{
		BigDecimal setRebate=new BigDecimal(20.99);
		setRebate.setScale(2, RoundingMode.CEILING);
		Rebate newRebate= new Rebate(setRebate);
		String storeItemDescription="MSI Prestige 15";
		BigDecimal itemPrice=new BigDecimal(1799.00);
		String storeItemDescriptionTwo="Dell XPS 15";
		BigDecimal itemPriceTwo=new BigDecimal(1899.00);
		String storeItemDescriptionThree="Apple Macbook Pro 16";
		BigDecimal itemPriceThree=new BigDecimal(2399.00);
		itemPrice=itemPrice.setScale(2, RoundingMode.CEILING);
		itemPriceTwo=itemPriceTwo.setScale(2, RoundingMode.CEILING);
		itemPriceThree=itemPriceThree.setScale(2, RoundingMode.CEILING);
		StoreItem newItem=new StoreItem(storeItemDescription, itemPrice, -1);
		StoreItem newItemTwo=new StoreItem(storeItemDescriptionTwo, itemPriceTwo, -1);
		StoreItem newItemThree=new StoreItem(storeItemDescriptionThree, itemPriceThree, -1);
		PurchasedItems newPurchaseList=new PurchasedItems();
		newPurchaseList.addItem(newItem);
		newPurchaseList.addItem(newItemTwo);
		newPurchaseList.addItem(newItemThree);
		Assertions.assertFalse(newRebate.applies(newPurchaseList));
	}
	
	@Test
	public void testRebateGetLines()
	{
		BigDecimal setRebate=new BigDecimal(20.99);
		setRebate.setScale(2, RoundingMode.CEILING);
		Rebate newRebate= new Rebate(setRebate);
		BigDecimal rebateAmountOff=newRebate.getAmountOff();
		int amountRebateId=newRebate.getRebateId();
		String expectedGetLines="$"+rebateAmountOff.toString()+" REBATE (#"+amountRebateId+")";
		String actualGetLines=newRebate.getLines();
		Assertions.assertEquals(expectedGetLines, actualGetLines);
	}
}

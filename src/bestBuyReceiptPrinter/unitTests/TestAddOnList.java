package bestBuyReceiptPrinter.unitTests;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import bestBuyReceiptPrinter.addOns.AddonList;
import bestBuyReceiptPrinter.addOns.AddonList.AddOnsIterator;
import bestBuyReceiptPrinter.addOns.Coupon;
import bestBuyReceiptPrinter.addOns.Rebate;
import bestBuyReceiptPrinter.addOns.SecondaryHeader;
import bestBuyReceiptPrinter.addOns.Addon;

public class TestAddOnList {

	private AddonList testAddonList;
	private int newExpirationMonth;
    private int newExpirationDate;
    private int newExpirationYear;
    private LocalDate newExpiration;
	
	@BeforeEach
	public void setup()
	{
		testAddonList=new AddonList();
	}
	
	
	public void setUpExpectedExpirationDates()
	{
		LocalDate today =LocalDate.now();
	    
	    if(today.getMonth() == Month.DECEMBER)
		{
			this.newExpirationYear=today.getYear()+1;
			this.newExpirationMonth=1;
			this.newExpirationDate=today.getDayOfMonth();
		}
		else if(today.getMonth() == Month.JANUARY && today.getDayOfMonth() >28)
		{
			this.newExpirationYear=today.getYear();
			this.newExpirationDate=27;
			this.newExpirationYear=today.getMonthValue()+1;
		}
		else
		{
			this.newExpirationYear=today.getYear();
			this.newExpirationMonth=today.getMonthValue()+1;
			if(this.newExpirationMonth == Month.MARCH.getValue() ||
				this.newExpirationMonth == Month.MAY.getValue() ||
				this.newExpirationMonth == Month.JULY.getValue() ||
				this.newExpirationMonth == Month.AUGUST.getValue() ||
				this.newExpirationMonth == Month.OCTOBER.getValue() ||
				this.newExpirationMonth == Month.DECEMBER.getValue())
			{
				this.newExpirationDate=today.getDayOfMonth();
			}
			else
			{
				this.newExpirationDate=today.getDayOfMonth()-1;
			}
		}
	    this.newExpiration=LocalDate.of(this.newExpirationYear,
	    		this.newExpirationMonth, this.newExpirationDate);
	}
	
	@Test
	public void testAddAnAddon()
	{
		Addon testAddon=new Coupon();
		this.testAddonList.addAddOn(testAddon);
		AddOnsIterator listIterator=this.testAddonList.getIterator();
		Coupon actualItem=(Coupon) listIterator.getCurrentItem();
		int expectedPercentageOff=0;
		String expectedDescription="";
		this.setUpExpectedExpirationDates();
		String actualGetLines=actualItem.getLines();
		String expectedGetLines=expectedDescription+" COUPON:"+expectedPercentageOff+
		" off next purchase.\n "+"Good until "+this.newExpiration.toString();
		Assertions.assertEquals(expectedGetLines, actualGetLines);
		
	}
	
	/**
	 * Test that AddonList is a collection
	 * of all addons not just a single addon
	 */
	@Test
	public void testAddAllTheAddons()
	{
		Addon testAddonOne=new Coupon();
		Addon testAddonTwo=new Rebate();
		Addon testAddonThree=new SecondaryHeader();
		this.testAddonList.addAddOn(testAddonOne);
		this.testAddonList.addAddOn(testAddonTwo);
		this.testAddonList.addAddOn(testAddonThree);
		AddOnsIterator listIterator=this.testAddonList.getIterator();
		int i=0;
		Addon currentItem;
		String actualItemGetLines;
		String expectedItemGetLines;
		try
		{
			currentItem=listIterator.getCurrentItem();
			String expectedDescription="";
			int expectedPercentageOff=0;
			this.setUpExpectedExpirationDates();
			expectedItemGetLines=expectedDescription+" COUPON:"+expectedPercentageOff+
					" off next purchase.\n "+"Good until "+this.newExpiration.toString();
			actualItemGetLines=currentItem.getLines();
			Assertions.assertEquals(expectedItemGetLines, actualItemGetLines);
			while(listIterator.hasNext())
			{
				if(i == 0)
				{
					listIterator.next();
					currentItem=listIterator.getCurrentItem();
					BigDecimal expectedRebateAmount=new BigDecimal(0);
					expectedRebateAmount=expectedRebateAmount.setScale(2, RoundingMode.CEILING);
					int expectedRebateId=1;
					expectedItemGetLines="$"+expectedRebateAmount.toString()+" REBATE (#"+expectedRebateId+")";
					actualItemGetLines=currentItem.getLines();
					Assertions.assertEquals(expectedItemGetLines, actualItemGetLines);
					Rebate castToRebate=(Rebate) currentItem;
					castToRebate.finalize();
				}
				else
				{
					listIterator.next();
					currentItem=listIterator.getCurrentItem();
					expectedItemGetLines="";
					actualItemGetLines=currentItem.getLines();
					Assertions.assertEquals(expectedItemGetLines, actualItemGetLines);
				}
				i=i+1;
			}
		}
		catch(IllegalStateException e)
		{
			Assertions.fail("A illegal state was reached in AddOnList");
		}
		
	}
	
	
	@Test
	public void testRemoveAnAddon()
	{
		Addon itemOne=new Coupon();
		Addon itemTwo=new Coupon();
		this.testAddonList.addAddOn(itemOne);
		this.testAddonList.addAddOn(itemTwo);
		this.testAddonList.removeAddon(itemTwo);
		int afterRemoveSize=this.testAddonList.getListSize();
		Assertions.assertTrue(afterRemoveSize == 1);
		//Test that it removed the correct item
		AddOnsIterator listIter=this.testAddonList.getIterator();
		try
		{
			Coupon actualCoupon=(Coupon)listIter.getCurrentItem();
			Coupon expectedCoupon=(Coupon) itemOne;
			Assertions.assertEquals(expectedCoupon.getCouponDescription(),
					actualCoupon.getCouponDescription());
		}
		catch(IllegalStateException e)
		{
			Assertions.fail("IllegalState was reached in Addon list");
		}
	}
	
	@Test
	public void testAddonIteratorConstructor()
	{
		Addon newItem=new Coupon();
		this.testAddonList.addAddOn(newItem);
		AddOnsIterator listIter=testAddonList.getIterator();
		Coupon actualAddon=(Coupon)listIter.getCurrentItem();
		String actualDescript=actualAddon.getCouponDescription();
		String expectedDescript="";
		Assertions.assertEquals(expectedDescript, actualDescript);
	}
	
	
	@Test
	public void testAddonIteratorConstructorNoItems()
	{
		AddOnsIterator listIter=testAddonList.getIterator();
		Assertions.assertThrows(IllegalStateException.class, () -> 
		{
			listIter.getCurrentItem();
		});
	}
	
	@Test
	public void testAddonIteratorHasNextTrue()
	{
		Addon newItem=new Coupon();
		Addon newItemTwo=new Coupon();
		this.testAddonList.addAddOn(newItem);
		this.testAddonList.addAddOn(newItemTwo);
		AddOnsIterator listIter=testAddonList.getIterator();
		Assertions.assertTrue(listIter.hasNext());
	}
	
	@Test
	public void testAddonIteratorHasNextFalse()
	{
		Addon newItem=new Coupon();
		this.testAddonList.addAddOn(newItem);
		AddOnsIterator listIter=testAddonList.getIterator();
		Assertions.assertFalse(listIter.hasNext());
	}
	
	@Test
	public void testAddonIteratorGetNext()
	{
		Addon newItem=new Rebate();
		Addon newItemTwo=new Rebate();
		this.testAddonList.addAddOn(newItem);
		this.testAddonList.addAddOn(newItemTwo);
	    AddOnsIterator listIter=testAddonList.getIterator();
	    listIter.next();
	    try
	    {
	    	Rebate expectedItem=(Rebate) listIter.getCurrentItem();
	    	int rebateId=expectedItem.getRebateId();
	    	Assertions.assertTrue(rebateId == 2);
	    	expectedItem.finalize();
	    	System.out.println("COUNTER: "+Rebate.getCounter());
	    	//finalize the remaining item
	    	expectedItem=(Rebate) newItem;
	    	expectedItem.finalize();
	    	System.out.println("COUNTER: "+Rebate.getCounter());
	    }
	    catch(IllegalStateException e)
	    {
	    	Rebate newItemDeconstruct=(Rebate) newItem;
	    	Rebate newItemTwoDeconstruct=(Rebate) newItemTwo;
	    	newItemDeconstruct.finalize();
	    	System.out.println("COUNTER: "+Rebate.getCounter());
	    	newItemTwoDeconstruct.finalize();
	    	System.out.println("COUNTER: "+Rebate.getCounter());
	    	Assertions.fail("Illegal state was reached in addon list.");
	    }
	 
	}
	
//	@Test
//	public void testRebateCounter()
//	{
//		Rebate newRebate=new Rebate();
//		System.out.println("COUNTER 1: "+Rebate.getCounter());
//		newRebate.finalize();
//		System.out.println("COUNTER 2: "+Rebate.getCounter());
//	}
	
	@Test
	public void testAddonIteratorGetNextNoNext()
	{
		Addon newItem=new Rebate();
		Addon newItemTwo=new Rebate();
		this.testAddonList.addAddOn(newItem);
	    AddOnsIterator listIter=testAddonList.getIterator();
	    Assertions.assertThrows(IllegalStateException.class, () -> {
	    	listIter.next();
	    	});
	    Rebate castToRebateOne=(Rebate) newItem;
	    Rebate castToRebateTwo=(Rebate) newItemTwo;
	    castToRebateOne.finalize();
	    System.out.println("COUNTER: "+Rebate.getCounter());
	    castToRebateTwo.finalize();
	    System.out.println("COUNTER: "+Rebate.getCounter());
	 }
}

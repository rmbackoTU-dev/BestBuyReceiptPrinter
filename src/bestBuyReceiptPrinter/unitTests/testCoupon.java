package bestBuyReceiptPrinter.unitTests;

import org.junit.jupiter.api.Test;

import bestBuyReceiptPrinter.addOns.Coupon;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;

import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class testCoupon {

	
	private static final String COUPON_DESCRIPTION_DEFAULT="";
	private static final int COUPON_PERCENTAGE_DEFAULT=0;
	private static final LocalDate COUPON_LOCAL_DATE_DEFAULT=LocalDate.of(2020, 11, 27);
	
	@Test
	public void testCouponDefaultConstructor()
	{
		Coupon testCoupon=new Coupon();
		BigDecimal qualifyingTotalDefault=new BigDecimal(0);
		qualifyingTotalDefault=qualifyingTotalDefault.setScale(2, RoundingMode.CEILING);
		int expectedYear=COUPON_LOCAL_DATE_DEFAULT.getYear();
		int expectedMonth=COUPON_LOCAL_DATE_DEFAULT.getMonthValue();
		int expectedDate=COUPON_LOCAL_DATE_DEFAULT.getDayOfMonth();
		double expectedQualifyingAsDouble=qualifyingTotalDefault.doubleValue();
		int actualPercentage = testCoupon.getPercentageOff();
		LocalDate actualExpiration=testCoupon.getExperationDate();
		String actualDescription=testCoupon.getCouponDescription();
		BigDecimal actualQualifying=testCoupon.getCouponQualifyingTotal();
		int actualMonth=actualExpiration.getMonthValue();
		int actualYear=actualExpiration.getYear();
		int actualDate=actualExpiration.getDayOfMonth();
		double actualQualifyingAsDouble=actualQualifying.doubleValue();
		Assertions.assertEquals(COUPON_DESCRIPTION_DEFAULT, actualDescription);
		Assertions.assertEquals(COUPON_PERCENTAGE_DEFAULT, actualPercentage);
		Assertions.assertEquals(actualMonth, expectedMonth);
		Assertions.assertEquals(actualYear, expectedYear);
		Assertions.assertEquals(actualDate, expectedDate);
		Assertions.assertEquals(actualQualifyingAsDouble, expectedQualifyingAsDouble);
	}
	
	@Test
	public void testCouponParameterConstructor()
	{  
		//expected params
		BigDecimal expectedQualifyingTotalDefault=new BigDecimal(20.99);
		expectedQualifyingTotalDefault=expectedQualifyingTotalDefault.setScale(2, RoundingMode.CEILING);
		int expectedPercentageParam=10;
		String expectedDescription="BEST BUY ";
		LocalDate couponExpirationDate=LocalDate.of(2021, 12, 28);
		int expectedYear=couponExpirationDate.getYear();
		int expectedMonth=couponExpirationDate.getMonthValue();
		int expectedDay=couponExpirationDate.getDayOfMonth();
		double expectedQualifyingTotalAsDouble=expectedQualifyingTotalDefault.doubleValue();
		//CONSTRUCTOR
		Coupon testCoupon=new Coupon(expectedQualifyingTotalDefault, couponExpirationDate,
			expectedPercentageParam, expectedDescription);
		//CONSTRUCTOR
		//Actual params
		BigDecimal actualQualifyingTotal=testCoupon.getCouponQualifyingTotal();
		int actualPercentageParam=testCoupon.getPercentageOff();
		String actualDescription=testCoupon.getCouponDescription();
		LocalDate actualExpirationDate=testCoupon.getExperationDate();
		int actualYear=actualExpirationDate.getYear();
		int actualMonth=actualExpirationDate.getMonthValue();
		int actualDate=actualExpirationDate.getDayOfMonth();
		double actualQualifyingTotalAsDouble=actualQualifyingTotal.doubleValue();
		//ASSERTIONS
		Assertions.assertEquals(expectedDescription, actualDescription);
		Assertions.assertEquals(expectedPercentageParam, actualPercentageParam);
		Assertions.assertEquals(expectedYear, actualYear);
		Assertions.assertEquals(expectedMonth, actualMonth);
		Assertions.assertEquals(expectedDay, actualDate);
		Assertions.assertEquals(expectedQualifyingTotalAsDouble, actualQualifyingTotalAsDouble);
		
	}
	
	@Test
	public void testCouponParameterConstructorRoundingTest()
	{
			//expected params
		BigDecimal inputQualifyingTotalDefault=new BigDecimal(20.997);
		inputQualifyingTotalDefault=inputQualifyingTotalDefault.setScale(2, RoundingMode.CEILING);
		int expectedPercentageParam=10;
		String expectedDescription="BEST BUY ";
		LocalDate couponExpirationDate=LocalDate.of(2021, 12, 28);
		double inputQualifyingTotalAsDouble=inputQualifyingTotalDefault.doubleValue();
		double expectedQualifyingTotalAsDouble=21.00;
		//CONSTRUCTOR
		Coupon testCoupon=new Coupon(inputQualifyingTotalDefault, couponExpirationDate,
			expectedPercentageParam, expectedDescription);
		//CONSTRUCTOR
		//Actual params
		BigDecimal actualQualifyingTotal=testCoupon.getCouponQualifyingTotal();
		double actualQualifyingTotalAsDouble=actualQualifyingTotal.doubleValue();
		//ASSERTIONS
		Assertions.assertEquals(actualQualifyingTotalAsDouble, expectedQualifyingTotalAsDouble);
	}
	
	@Test
	public void testAppliesOneItem()
	{
	   BigDecimal itemOnePrice=new BigDecimal(499.99);
	   itemOnePrice=itemOnePrice.setScale(2, RoundingMode.CEILING);
	   StoreItem itemOne=new StoreItem("Xbox Series X", itemOnePrice, -1);
	   PurchasedItems allItems=new PurchasedItems();
	   allItems.addItem(itemOne);
	   BigDecimal couponTotal=new BigDecimal(499.99);
	   couponTotal=couponTotal.setScale(2, RoundingMode.CEILING);
	   LocalDate couponExpiration=LocalDate.of(2021, 12, 31);
	   Coupon testCoupon=new Coupon(couponTotal, couponExpiration,
			   10, "BEST BUY");
	   Assertions.assertTrue(testCoupon.applies(allItems));
	}
	
	@Test
	public void testAppliesTwoItems()
	{
	   BigDecimal itemOnePrice=new BigDecimal(499.99);
	   BigDecimal itemTwoPrice=new BigDecimal(299.99);
	   StoreItem itemOne=new StoreItem("Xbox Series X", itemOnePrice, -1);
	   StoreItem itemTwo=new StoreItem("Xbox Series S", itemTwoPrice, -1);
	   PurchasedItems allItems=new PurchasedItems();
	   allItems.addItem(itemOne);
	   allItems.addItem(itemTwo);
	   BigDecimal couponTotal=new BigDecimal(799.98);
	   couponTotal=couponTotal.setScale(2, RoundingMode.CEILING);
	   LocalDate couponExpiration=LocalDate.of(2021, 12, 31);
	   Coupon testCoupon=new Coupon(couponTotal, couponExpiration,
			   10, "BEST BUY");
	   Assertions.assertTrue(testCoupon.applies(allItems));

	}
	
	@Test
	public void testAppliesThreeItems()
	{
	   BigDecimal itemOnePrice=new BigDecimal(499.99);
	   BigDecimal itemTwoPrice=new BigDecimal(299.99);
	   BigDecimal itemThreePrice=itemTwoPrice;
	   StoreItem itemOne=new StoreItem("Xbox Series X", itemOnePrice, -1);
	   StoreItem itemTwo=new StoreItem("Xbox Series S", itemTwoPrice, -1);
	   StoreItem itemThree=new StoreItem("Xbox One S", itemThreePrice, -1);
	   PurchasedItems allItems=new PurchasedItems();
	   allItems.addItem(itemOne);
	   allItems.addItem(itemTwo);
	   allItems.addItem(itemThree);
	   BigDecimal couponTotal=new BigDecimal(1099.97);
	   couponTotal=couponTotal.setScale(2, RoundingMode.CEILING);
	   LocalDate couponExpiration=LocalDate.of(2021, 12, 31);
	   Coupon testCoupon=new Coupon(couponTotal, couponExpiration,
			   10, "BEST BUY");
	   Assertions.assertTrue(testCoupon.applies(allItems));
	}
	
	@Test
	public void testNotAppliesThreeItems()
	{
	   BigDecimal itemOnePrice=new BigDecimal(499.99);
	   BigDecimal itemTwoPrice=new BigDecimal(299.99);
	   BigDecimal itemThreePrice=itemTwoPrice;
	   StoreItem itemOne=new StoreItem("Xbox Series X", itemOnePrice, -1);
	   StoreItem itemTwo=new StoreItem("Xbox Series S", itemTwoPrice, -1);
	   StoreItem itemThree=new StoreItem("Xbox One S", itemThreePrice, -1);
	   PurchasedItems allItems=new PurchasedItems();
	   allItems.addItem(itemOne);
	   allItems.addItem(itemTwo);
	   allItems.addItem(itemThree);
	   BigDecimal couponTotal=new BigDecimal(1500.00);
	   couponTotal=couponTotal.setScale(2, RoundingMode.CEILING);
	   LocalDate couponExpiration=LocalDate.of(2021, 12, 31);
	   Coupon testCoupon=new Coupon(couponTotal, couponExpiration,
			   10, "BEST BUY");
	   Assertions.assertFalse(testCoupon.applies(allItems));

	}
	
	@Test
	public void testGetLines()
	{
		Coupon newCoupon=new Coupon();
		String couponStringInput=this.COUPON_DESCRIPTION_DEFAULT+" COUPON:"+this.COUPON_PERCENTAGE_DEFAULT+
				" off next purchase.\n "+"Good until "+this.COUPON_LOCAL_DATE_DEFAULT.toString();
		Assertions.assertEquals(couponStringInput, newCoupon.getLines());
	}
}
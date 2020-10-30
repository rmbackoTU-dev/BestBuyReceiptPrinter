package bestBuyReceiptPrinter.addOns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.MonthDay;
import java.util.Iterator;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;

/**
 * Class provides a percentage that
 * can be computed on the base StoreItem price
 * to update the StoreItem price
 * @author Ryan Backof
 *
 */
public class Coupon implements Addon {

	private BigDecimal couponQualifyingTotal;
	private String description;
	private int percentageOff;
	private LocalDate experationDate;
	
	
	/**
	 * Default Constructor
	 */
	public Coupon()
	{
		couponQualifyingTotal = new BigDecimal(0);
		couponQualifyingTotal= 
				couponQualifyingTotal.setScale(2, RoundingMode.CEILING);
		LocalDate todaysDate=LocalDate.now();
		int addYear=0;
		int addMonth=0;
		int addDate=0;
		if(todaysDate.getMonth() == Month.DECEMBER)
		{
			addYear=1;
			addMonth=1;
			addDate=todaysDate.getDayOfMonth();
		}
		else if(todaysDate.getMonth() == Month.JANUARY && todaysDate.getDayOfMonth() >28)
		{
			addYear=todaysDate.getYear();
			addDate=27;
			addMonth=todaysDate.getMonthValue()+1;
		}
		{
			addYear=todaysDate.getYear();
			addMonth=todaysDate.getMonthValue()+1;
			if(addMonth == Month.MARCH.getValue() ||
				addMonth == Month.MAY.getValue() ||
				addMonth == Month.JULY.getValue() ||
				addMonth == Month.AUGUST.getValue() ||
				addMonth == Month.OCTOBER.getValue() ||
				addMonth == Month.DECEMBER.getValue())
			{
				addDate=todaysDate.getDayOfMonth();
			}
			else
			{
				addDate=todaysDate.getDayOfMonth()-1;
			}
		}
		this.experationDate=LocalDate.of(addYear, addMonth, addDate);
		this.description="";
		this.percentageOff=0;
	}
    
	/**
	 * Parameter Constructor
	 * @param totalQualifyingCost
	 * @param expiration
	 * @param percentage
	 * @param descript
	 */
	public Coupon(BigDecimal totalQualifyingCost, LocalDate expiration,
			int percentage, String descript)
	{
		this.couponQualifyingTotal=totalQualifyingCost;
		couponQualifyingTotal= 
				couponQualifyingTotal.setScale(2, RoundingMode.CEILING);
		this.experationDate=expiration;
		this.percentageOff=percentage;
		this.description=descript;
	}

	@Override
	public boolean applies(PurchasedItems items) {
		boolean applyAddon=false;
		Iterator<StoreItem> itemsIterator=items.getItemsIterator();
		BigDecimal totalPrice=new BigDecimal(0);
		totalPrice=totalPrice.setScale(2, RoundingMode.CEILING);
		BigDecimal currentItemPrice;
		StoreItem currentItem;
		if(itemsIterator.hasNext())
		{
			currentItem=(StoreItem) itemsIterator.next();
			currentItemPrice= currentItem.getPrice();
			totalPrice=totalPrice.add(currentItemPrice);
			while(itemsIterator.hasNext())
			{
				currentItem=(StoreItem) itemsIterator.next();
				currentItemPrice= currentItem.getPrice();
				totalPrice=totalPrice.add(currentItemPrice);
			}
		}
		if(totalPrice.compareTo(this.couponQualifyingTotal) >= 0)
		{
			applyAddon=true;
		}
		return applyAddon;
		
	}

	@Override
	public String getLines() {
		String couponString=this.description+" COUPON:"+this.percentageOff+
				" off next purchase.\n "+"Good until "+this.experationDate.toString();
		return couponString;
	}
	
	public int getPercentageOff() 
	{
	    return this.percentageOff;
	}
	
	public LocalDate getExperationDate()
	{
		return this.experationDate;
	}
	
	public String getCouponDescription()
	{
		return this.description;
	}
	
	public BigDecimal getCouponQualifyingTotal()
	{
		return this.couponQualifyingTotal;
	}
}

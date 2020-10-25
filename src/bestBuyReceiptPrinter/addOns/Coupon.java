package bestBuyReceiptPrinter.addOns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
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
	private double percentageOff;
	private Date experationDate;
	
	
	/**
	 * Default Constructor
	 */
	public Coupon()
	{
		couponQualifyingTotal = new BigDecimal(0);
		couponQualifyingTotal= 
				couponQualifyingTotal.setScale(2, RoundingMode.CEILING);
		long millis=System.currentTimeMillis();
		Date todaysDate=new Date(millis);
		int addYear=0;
		int addMonth=0;
		int addDate=0;
		if(todaysDate.getMonth() ==12)
		{
			addYear=1;
			addMonth=1;
			addDate=todaysDate.getDay();
		}
		else if(todaysDate.getMonth() == 2 && todaysDate.getDay()>28);
		{
			addYear=todaysDate.getYear();
			addDate=27;
			addMonth=todaysDate.getDate()+1;
		}
		long experation=Date.UTC(addYear, addMonth, addDate, 0, 0, 0);
		this.experationDate=new Date(experation);
		this.description="";
		this.percentageOff=0;
	}
	
	public Coupon(BigDecimal totalQualifyingCost, Date expiration,
			double percentage, String descript)
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
		// TODO Auto-generated method stub
		boolean applyAddon=false;
		Iterator<StoreItem> itemsIterator=items.getItemsIterator();
		BigDecimal totalPrice=new BigDecimal(0);
		totalPrice=totalPrice.setScale(2, RoundingMode.CEILING);
		BigDecimal currentItemPrice;
		StoreItem currentItem;
		if(itemsIterator.hasNext())
		{
			currentItem=(StoreItem) itemsIterator.next();
			
			while(itemsIterator.hasNext())
			{
				currentItemPrice= currentItem.getPrice();
				totalPrice.add(currentItemPrice);
			}
		}
		if(couponQualifyingTotal.compareTo(totalPrice) == 1)
		{
			applyAddon=true;
		}
		return applyAddon;
		
	}

	@Override
	public String getLines() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

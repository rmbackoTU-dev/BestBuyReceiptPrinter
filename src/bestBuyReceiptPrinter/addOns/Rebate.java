package bestBuyReceiptPrinter.addOns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;

/**
 * Add on provides a secondary amount that the 
 * user can receive back after redemption
 * @author Ryan Backof
 *
 */
public class Rebate implements Addon{

	private BigDecimal amountOff;
	private int rebateId;
	private static int rebateCounter=0;
	
	/**
	 * Default Constructor
	 */
	public Rebate()
	{
		rebateCounter=rebateCounter+1;
		this.rebateId=rebateCounter;
		this.amountOff=new BigDecimal(0);
		this.amountOff=amountOff.setScale(2,RoundingMode.CEILING);
	}
	
	/**
	 * Parameterize Constructor
	 * @param moneyBack
	 */
	public Rebate(BigDecimal moneyBack)
	{
		this.amountOff=moneyBack;
		this.amountOff=amountOff.setScale(2,RoundingMode.CEILING);
	    rebateCounter=rebateCounter+1;
		this.rebateId=rebateCounter;	
	}

	
	public static int getCounter()
	{
		return rebateCounter;
	}
	
	@Override
	public void finalize()
	{

	    try 
	    {
	    	rebateCounter=rebateCounter-1;
	    	super.finalize();
		}
	    catch (Throwable e)
	    {
	    	rebateCounter=rebateCounter-1;
			e.printStackTrace();
			System.err.println("There was an error with your rebate");
		}
	}
	
	public void setAmountOff(BigDecimal moneyBack)
	{
		this.amountOff=moneyBack;
	}
	
	public BigDecimal getAmountOff()
	{
		return this.amountOff;
	}
	
	/**
	 * Check if any item applies for this rebate
	 * if it does return true, if it does not
	 * return false
	 */
	public boolean applies(PurchasedItems items)
	{
		Iterator<StoreItem> itemsIterator=items.getItemsIterator();
		StoreItem currentItem;
		boolean applies=false;
		while(itemsIterator.hasNext())
		{
			currentItem=itemsIterator.next();
			if(currentItem.getRebateId()== this.rebateId)
			{
				applies=true;
			}
		}
		return applies;
	}
	
	public String getLines()
	{
		String rebateText="$"+this.amountOff.toString()+" REBATE (#"+this.rebateId+")";
		return rebateText;
	}
	
	public int getRebateId() 
	{
		return this.rebateId;
	}
}

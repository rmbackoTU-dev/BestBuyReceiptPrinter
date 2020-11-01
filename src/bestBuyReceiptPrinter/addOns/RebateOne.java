package bestBuyReceiptPrinter.addOns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems.PurchasedItemsIterator;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;

/**
 * Add on provides a secondary amount that the 
 * user can receive back after redemption
 * Rebate amount for $1.
 * @author Ryan Backof
 *
 */
public class RebateOne implements Rebate{

	private BigDecimal amountOff;
	ArrayList<String> qualifyingPurchasesNames;
	ArrayList<Integer> qualifyingPurchasesIds;
	
	/**
	 * Default Constructor
	 */
	public RebateOne()
	{
		this.amountOff=new BigDecimal(1.00);
		this.amountOff=amountOff.setScale(2,RoundingMode.CEILING);
		qualifyingPurchasesNames=new ArrayList<String>();
		qualifyingPurchasesIds=new ArrayList<Integer>();
	}
	
	public void addToQualifyingPurchases(StoreItem item)
	{
		qualifyingPurchasesNames.add(item.getDescription());
		qualifyingPurchasesIds.add(item.getInventoryNum());
	}

	@Override
	public String[]  getListOfQualifyingItems()
	{
		String[] qualifyingPurchasesArray=new String[this.qualifyingPurchasesNames.size()];
		Iterator itemsQualifyingIterator=this.qualifyingPurchasesNames.iterator();
		int i=0;
		while(itemsQualifyingIterator.hasNext() && i< this.qualifyingPurchasesNames.size())
		{
			qualifyingPurchasesArray[i]=(String) itemsQualifyingIterator.next();
			i=i+1;
		}
		return qualifyingPurchasesArray;
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
		//Iterator<StoreItem> itemsIterator=items.getItemsIterator();
		boolean applies=false;
		Integer currentId;
		Iterator<Integer> idIter=(Iterator<Integer>) this.qualifyingPurchasesIds.iterator();
		while(idIter.hasNext())
		{
			currentId=idIter.next();
			int unwrappedCurrentId=currentId.intValue();
			applies=items.containsItem(unwrappedCurrentId);
		}
		return applies;
	}
	
	public String getLines()
	{
		String rebateText="$"+this.amountOff.toString()+" REBATE (#1)";
		return rebateText;
	}
	
}

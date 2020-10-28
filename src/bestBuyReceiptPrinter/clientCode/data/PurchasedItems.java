package bestBuyReceiptPrinter.clientCode.data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * PurchasedItems class is a collection class
 * this collection aggregates 0 or more StoreItems
 * @author Ryan Backof
 *
 */
public class PurchasedItems {

	private ArrayList<StoreItem> items;
	
	public PurchasedItems()
	{
		items=new ArrayList<StoreItem>();
	}
	
	public Iterator<StoreItem> getItemsIterator()
	{
		Iterator<StoreItem> itemsIterator=items.iterator();
		return itemsIterator;
	}
	
	public void addItem(StoreItem item)
	{
		items.add(item);
	}
}

package bestBuyReceiptPrinter.clientCode.data;

import java.util.ArrayList;
import java.util.Iterator;

import bestBuyReceiptPrinter.addOns.Addon;
import bestBuyReceiptPrinter.addOns.AddonList;

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
	
	
	public PurchasedItemsIterator getPurchasedItemsIter()
	{
		PurchasedItemsIterator itemIterator=new PurchasedItemsIterator();
		return itemIterator;
	}
	
	public void addItem(StoreItem item)
	{
		items.add(item);
	}
	
	public class PurchasedItemsIterator
	{
		private StoreItem currentItem;
		private int currentIndex;
		
		public PurchasedItemsIterator()
		{
			currentIndex=0;
			if(!(PurchasedItems.this.items.isEmpty()))
			{
				this.currentItem=PurchasedItems.this.items.get(currentIndex);
			}
			else
			{
				currentItem=null;
			}
		}
		
		public void next()
		{
			if(this.hasNext())
			{
				this.currentIndex=currentIndex+1;
				this.currentItem=PurchasedItems.this.items.get(currentIndex);
			}
			else
			{
				throw new IllegalStateException("There is no more items in this list");
			}
		}
		
		public StoreItem getCurrentItem()
		{
			if(currentItem != null)
			{
				if(this.currentItem == PurchasedItems.this.items.get(this.currentIndex))
				{
					return this.currentItem;
				}
				else
				{
					this.currentItem=PurchasedItems.this.items.get(this.currentIndex);
					return this.currentItem;
				}
			}
			else
			{
				throw new IllegalStateException("current Item Is Not Set");
			}
		}
		
		public int getCurrentIndex()
		{
			return this.currentIndex;
		}
		
		public boolean hasNext()
		{
			return (currentIndex+1 < PurchasedItems.this.items.size());
		}
		
		public void resetIter()
		{
			this.currentIndex=0;
			if(!(PurchasedItems.this.items.isEmpty()))
			{
				currentItem=PurchasedItems.this.items.get(currentIndex);
			}
			
		}
	}
}

package bestBuyReceiptPrinter.clientCode.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	private PurchasedItemsIterator iter;
	
	public PurchasedItems()
	{
		items=new ArrayList<StoreItem>();
		iter=new PurchasedItemsIterator();
	}
	
	public PurchasedItemsIterator getPurchasedItemsIter()
	{
		return iter;
	}
	
	public void addItem(StoreItem item)
	{
		items.add(item);
		iter.updateIterIntialPointer();
	}

	public void removeItem(StoreItem item)
	{
		items.remove(item);
	}
	
	public BigDecimal getTotalPrice()
	{
		BigDecimal totalPrice=new BigDecimal(0);
		totalPrice=totalPrice.setScale(2, RoundingMode.CEILING);
		PurchasedItemsIterator itemsIterator=new PurchasedItemsIterator();
		return null;
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
		
		protected void updateIterIntialPointer()
		{
			if(currentItem == null)
			{
				if(!(PurchasedItems.this.items.isEmpty()))
				{
					this.currentItem=PurchasedItems.this.items.get(currentIndex);
				}
				else
				{
					currentItem=null;
				}
			}
		}
	}
}

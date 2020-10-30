package bestBuyReceiptPrinter.addOns;

import java.util.ArrayList;

/**
 * An aggregation of addons
 * @author Ryan
 *
 */
public class AddonList {
	private ArrayList<Addon> addons;
	
	public AddonList()
	{
		addons=new ArrayList<Addon>();
	}
	
	public void addAddOn(Addon addonToAdd)
	{
		addons.add(addonToAdd);
	}
	
	public void removeAddon(Addon addonToRemove)
	{
			addons.remove(addonToRemove);
	}
	
	public AddOnsIterator getIterator()
	{
		return new AddOnsIterator();
	}
	
	public int getListSize()
	{
		return this.addons.size();
	}
	
	
	public class AddOnsIterator
	{
		private Addon currentItem;
		private int currentIndex;
		
		public AddOnsIterator()
		{
			currentIndex=0;
			if(!(AddonList.this.addons.isEmpty()))
			{
				this.currentItem=AddonList.this.addons.get(currentIndex);
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
				this.currentItem=AddonList.this.addons.get(currentIndex);
			}
			else
			{
				throw new IllegalStateException("There is no more items in this list");
			}
		}
		
		public Addon getCurrentItem()
		{
			if(currentItem != null)
			{
				if(this.currentItem == AddonList.this.addons.get(this.currentIndex))
				{
					return this.currentItem;
				}
				else
				{
					this.currentItem=AddonList.this.addons.get(this.currentIndex);
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
			return (currentIndex+1 < AddonList.this.addons.size());
		}
		
		public void resetIter()
		{
			this.currentIndex=0;
			if(!(AddonList.this.addons.isEmpty()))
			{
				currentItem=AddonList.this.addons.get(currentIndex);
			}
			
		}
	}

}

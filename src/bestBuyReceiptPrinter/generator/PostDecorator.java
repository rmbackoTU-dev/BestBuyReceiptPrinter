package bestBuyReceiptPrinter.generator;

import java.math.BigDecimal;
import bestBuyReceiptPrinter.addOns.Addon;
import bestBuyReceiptPrinter.addOns.AddonList;
import bestBuyReceiptPrinter.addOns.AddonList.AddOnsIterator;
import bestBuyReceiptPrinter.addOns.Coupon;
import bestBuyReceiptPrinter.addOns.Rebate;

/**
* Applies any discount addOns to the purchaseItems
* @author Ryan Backof
*/
public class PostDecorator extends ReceiptDecorator {

	AddonList addons;
	
	public PostDecorator(Receipt receipt, Addon addon)
	{
		super(receipt, addon);
		addons=new AddonList();
		addons.addAddOn(addon);
	}
	
	public PostDecorator(Receipt receipt, AddonList addonList)
	{
		super(receipt, addonList);
		this.addons=addonList;
	}
	
	public void printReceipt()
	{
		AddOnsIterator addonIter=addons.getIterator();
		super.printReceipt();
		try
		{
			Addon currentAddon=addonIter.getCurrentItem();
			if(currentAddon instanceof Coupon || currentAddon instanceof Rebate)
			{
				System.out.println(currentAddon.getLines());
			}
		}
		catch(IllegalStateException e)
		{
			//Even if addons is empty we still have a Receipt to print
			System.err.println("Error while checking discounts ");
			e.printStackTrace();
		}
	}
	
	
	
}

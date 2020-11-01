package bestBuyReceiptPrinter.generator;

import bestBuyReceiptPrinter.addOns.Addon;
import bestBuyReceiptPrinter.addOns.SecondaryHeader;
import bestBuyReceiptPrinter.addOns.WinterHolidayHeader;

/**
 * Class adds necessary header addons to receipt
 * @author Ryan Backof
 *
 */
public class PreDecorator extends ReceiptDecorator {

	private SecondaryHeader headerAddOn;
	
	public PreDecorator(Receipt receipt, Addon addon)
	{
		super(receipt, addon);
		if(addon instanceof SecondaryHeader)
		{
			this.headerAddOn=(SecondaryHeader) addon;
		}
		else
		{
			this.headerAddOn=null;
		}
	}
	
	
	public void printReceipt()
	{
		if(this.headerAddOn == null)
		{
			throw new IllegalStateException("PreDecorator can only print addons that are secondary headers");
		}
		else
		{
			System.out.println(headerAddOn.getLines());
			super.printReceipt();
		}
	}
}

package bestBuyReceiptPrinter.addOns;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;

/**
 * Add-on adds a header for sales holidays
 * to the receipt
 * @author Ryan Backof
 *
 */
public class SecondaryHeader implements Addon {
	
	String headerText;
	
	public SecondaryHeader()
	{
		this.headerText="";
	}
	
	public void setHeaderText(String textForHeader)
	{
		this.headerText=textForHeader;
	}
	
	/**
	 * Always returns true, because if header is constructed
	 * it is needed
	 */
	@Override
	public boolean applies(PurchasedItems items)
	{
		return true;
	}
	
	@Override
	public String getLines()
	{
		return headerText;
	}
}

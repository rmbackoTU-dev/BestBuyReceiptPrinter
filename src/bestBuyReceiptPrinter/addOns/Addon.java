package bestBuyReceiptPrinter.addOns;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;

public interface Addon {

	/**
	 * Whether the addon is valid for
	 * a item, purchase amount or date
	 * MUST BE IMPLEMENTED FOR ADDON
	 * @return if the addon applies
	 */
	public boolean applies(PurchasedItems items);
	
	
	/**
	 * Formats strings to indicate that an addon 
	 * has been applied
	 * MUST BE IMPLEMENTED FOR ADDON
	 * @return relevant line
	 */
	public String getLines();


	
}

package bestBuyReceiptPrinter.clientCode;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.addOns.AddonList;
import bestBuyReceiptPrinter.generator.taxStrategies.TaxComputationMethod;
import bestBuyReceiptPrinter.generator.StoreHeader;
import bestBuyReceiptPrinter.generator.Receipt;
import bestBuyReceiptPrinter.addOns.TenPercentCoupon;
import java.util.Date;
import java.io.InputStreamReader;
import java.math.BigDecimal;
/**
 * A factory Method that creates a Receipt 
 * with the necessary add-ons and tax computations
 * @author Ryan Backof
 *
 */

public class ReceiptFactory {
	
		private AddonList addons;
		private TaxComputationMethod[] taxComputationObject;
		private StoreHeader header;
		private static InputStreamReader inputReader;
		
		public ReceiptFactory()
		{
			this.taxComputationObject=new TaxComputationMethod[4];
			this.addons=new AddonList();
			
		}

		public Receipt getReceipt(PurchasedItems itemsBought, Date today, TenPercentCoupon coupon)
		{
			//TODO: Use Data to decide which taxComputation applies
			Receipt newReceipt=null;
			return newReceipt;
		}
	
		
		public BigDecimal getCouponPriceAddon()
		{
			/*TODO: implement a way to add a coupon onto the base price
			* if an item has one
			*/
			return null;
		}
		
		public BigDecimal rebateIfAvailable()
		{
			/*TODO implement a way to return the price after rebate 
			 * if an item has one
			 */
			return null;
		}
}

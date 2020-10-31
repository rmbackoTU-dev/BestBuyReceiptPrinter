package bestBuyReceiptPrinter.addOns;
import java.time.LocalDate;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;

public interface SecondaryHeader extends Addon {

	public LocalDate getHolidayStartDate();
	
	public LocalDate getHolidayEndDate();

	
}

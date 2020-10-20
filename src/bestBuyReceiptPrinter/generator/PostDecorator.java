package bestBuyReceiptPrinter.generator;

import java.math.BigDecimal;
import bestBuyReceiptPrinter.addOns.Addon;

/**
* Applies any discount addOns to the purchaseItems
*/
public class PostDecorator extends ReceiptDecorator {

	private Addon discountAddon;
	private BigDecimal amountApplied;
}

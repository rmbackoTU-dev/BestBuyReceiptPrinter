package bestBuyReceiptPrinter.generator;

import java.math.BigDecimal;
import bestBuyReceiptPrinter.addOns.Addon;

public class PostDecorator extends ReceiptDecorator {

	private Addon discountAddon;
	private BigDecimal amountApplied;
}

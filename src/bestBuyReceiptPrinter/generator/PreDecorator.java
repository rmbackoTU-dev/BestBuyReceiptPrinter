package bestBuyReceiptPrinter.generator;

import bestBuyReceiptPrinter.addOns.SecondaryHeader;

/**
 * Class adds necessary header addons to receipt
 * @author Ryan
 *
 */
public class PreDecorator extends ReceiptDecorator {

	private SecondaryHeader currentHeader;
}

package bestBuyReceiptPrinter.generator;

import bestBuyReceiptPrinter.addOns.WinterHolidayHeader;

/**
 * Class adds necessary header addons to receipt
 * @author Ryan
 *
 */
public class PreDecorator extends ReceiptDecorator {

	private WinterHolidayHeader currentHeader;
}

package bestBuyReceiptPrinter.generator.taxStrategies;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.generator.Date;

public abstract class TaxComputationMethod {
    public abstract double computeTax(PurchasedItems items, Date date);
    public abstract boolean taxHoliday(Date date);
}
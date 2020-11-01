package bestBuyReceiptPrinter.generator.taxStrategies;

import bestBuyReceiptPrinter.generator.ReceiptDate;

public abstract class TaxComputationMethod {
    public abstract double computeTax(PurchasedItems items, ReceiptDate date);
    public abstract boolean taxHoliday();
}
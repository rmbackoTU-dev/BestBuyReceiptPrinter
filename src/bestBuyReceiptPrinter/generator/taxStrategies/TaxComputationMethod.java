package bestBuyReceiptPrinter.generator.taxStrategies;

public abstract class TaxComputationMethod {
    public abstract double computeTax(PurchasedItems items, ReceiptDate date);
    public abstract boolean taxHoliday();
}
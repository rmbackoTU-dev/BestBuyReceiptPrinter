package bestBuyReceiptPrinter.generator.taxStrategies;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.generator.ReceiptDate;

public abstract class TaxComputationMethod {

   protected ReceiptDate newDate;

    public TaxComputationMethod(ReceiptDate date){
        this.newDate = date;
    }

    public abstract double computeTax(PurchasedItems items, ReceiptDate date);
    public abstract boolean taxHoliday();
}
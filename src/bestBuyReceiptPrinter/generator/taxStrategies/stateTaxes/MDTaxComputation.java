package bestBuyReceiptPrinter.generator.taxStrategies.stateTaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems.PurchasedItemsIterator;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;
import bestBuyReceiptPrinter.generator.ReceiptDate;
import bestBuyReceiptPrinter.generator.taxStrategies.TaxComputationMethod;
import bestBuyReceiptPrinter.generator.ReceiptDate;

public class MDTaxComputation extends TaxComputationMethod {

    double taxPercent = 0.06;

    public MDTaxComputation(ReceiptDate date){
        super(date);
    }


    public double computeTax(PurchasedItems items, ReceiptDate date) {
// calls private method taxHoliday as part of this computation

        BigDecimal taxSum = new BigDecimal(0);
        taxSum = taxSum.setScale(0, RoundingMode.CEILING);
        StoreItem currentItem;
        if (taxHoliday()) {
            return 0;
        } else {
            PurchasedItemsIterator iterator = items.getPurchasedItemsIter();

            while (iterator.hasNext()) {
                iterator.next();
                currentItem = iterator.getCurrentItem();
                taxSum = taxSum.add(currentItem.getPrice());
            }

        }
        return taxSum.doubleValue()*taxPercent;
        //Going to be iterating through purchaseItems
    }


    /**
     * Returns true if date is between
     * August 9 and August 15
     */
    public boolean taxHoliday() {

        return false;
    }
}
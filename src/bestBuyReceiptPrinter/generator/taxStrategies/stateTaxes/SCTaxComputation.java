package bestBuyReceiptPrinter.generator.taxStrategies.stateTaxes;

import bestBuyReceiptPrinter.clientCode.data.StoreItem;
import bestBuyReceiptPrinter.generator.ReceiptDate;
import bestBuyReceiptPrinter.generator.taxStrategies.TaxComputationMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SCTaxComputation extends TaxComputationMethod {
    //South Carolina

    public double computeTax(PurchasedItems items, ReceiptDate date){
// calls private method taxHoliday as part of this computation

        BigDecimal taxSum = new BigDecimal(0);
        taxSum = taxSum.setScale(0, RoundingMode.CEILING);
        StoreItem currentItem;
        if (taxHoliday(date)) {
            return 0;
        } else {
            PurchasedItemsIterator iterator = items.getPurchasedItemsIter();

            while (iterator.hasNext()) {
                iterator.next();
                currentItem = iterator.getCurrentItem();
                taxSum = taxSum.add(currentItem.getPrice());
            }

        }
        return taxSum.doubleValue();
        //Going to be iterating through purchaseItems
    }

    public boolean taxHoliday(ReceiptDate date) {
    if(date.month==8){
        if(day=="Friday"|| day=="Saturday"||day=="Sunday"){
            if(date.date<9){
                return true;
            }
        }
        return false;
    }
}

// returns true if date of receipt within the state’s tax free holiday,
// else returns false. Supporting method of method computeTax.

// tax computation objects for other states are similar
}

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

public class SCTaxComputation extends TaxComputationMethod {
    //South Carolina

    double taxPercent = 0.06;

    public SCTaxComputation(ReceiptDate date){
        super(date);
    }

    public double computeTax(PurchasedItems items, ReceiptDate date){
// calls private method taxHoliday as part of this computation

        BigDecimal taxSum = new BigDecimal(0);
        taxSum = taxSum.setScale(0, RoundingMode.CEILING);
        StoreItem currentItem;
        if (taxHoliday()){
            return 0;
        } else {
            PurchasedItemsIterator iterator = items.getPurchasedItemsIter();
            iterator.resetIter();
            while (iterator.hasNext()) {
                iterator.next();
                currentItem = iterator.getCurrentItem();
                taxSum = taxSum.add(currentItem.getPrice());
            }

        }
        return taxSum.doubleValue();
        //Going to be iterating through purchaseItems
    }

    public boolean taxHoliday() {
        boolean isHoliday=false;
        ReceiptDate date = super.newDate;
        if (date.getMonth() == 8) {
            if (date.getDayOfWeek().equals("Friday") && date.getDate() == 1) {
                isHoliday=true;
            }  else if (date.getDate() < 10 && (date.getDayOfWeek().equals("Friday") || date.getDayOfWeek().equals("Saturday") || date.getDayOfWeek().equals("Sunday"))) {
                isHoliday=true;
            }

        }
        return isHoliday;
    }
}


// returns true if date of receipt within the state’s tax free holiday,
// else returns false. Supporting method of method computeTax.

// tax computation objects for other states are similar


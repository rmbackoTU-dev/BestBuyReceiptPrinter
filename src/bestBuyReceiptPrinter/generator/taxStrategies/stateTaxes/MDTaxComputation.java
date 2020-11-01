import java.util.*;
package bestBuyReceiptPrinter.generator.taxStrategies.stateTaxes;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.generator.taxStrategies.TaxComputationMethod;
import bestBuyReceiptPrinter.generator.Date;

public class MDTaxComputation extends TaxComputationMethod {


    public double computeTax(PurchasedItems items, Date date) {
// calls private method taxHoliday as part of this computation

        double taxSum = 0;

        if (taxHoliday(date)) {
            return 0;
        } else {
            ListIterator iterator = items.listIterator();

            while (iterator.hasNext()) {
                items i = iterator.next();
                taxSum = taxSum + i.price;
            }

        }
        return taxSum;
        //Going to be iterating through purchaseItems
    }



    public boolean taxHoliday(Date date) {

        return false;
    }
}

// returns true if date of receipt within the state’s tax free holiday,
// else returns false. Supporting method of method computeTax.

// tax computation objects for other states are similar
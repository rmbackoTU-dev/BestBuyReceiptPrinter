package bestBuyReceiptPrinter.generator.taxStrategies.stateTaxes;

import bestBuyReceiptPrinter.generator.taxStrategies.TaxComputationMethod;

public class MDTaxComputation extends TaxComputationMethod {


    public double computeTax(PurchasedItems items, ReceiptDate date) {
// calls private method taxHoliday as part of this computation

        if (taxHoliday(date)) {
            return 0;
        } else {
            return 0.06;

            //Going to be iterating through purchaseItems
        }
    }

    public boolean taxHoliday(ReceiptDate date) {

        return false;
    }
}

// returns true if date of receipt within the state’s tax free holiday,
// else returns false. Supporting method of method computeTax.

// tax computation objects for other states are similar
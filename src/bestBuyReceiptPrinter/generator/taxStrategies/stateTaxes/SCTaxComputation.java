package bestBuyReceiptPrinter.generator.taxStrategies.stateTaxes;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.generator.Date;

public class SCTaxComputation {
    //South Carolina

    public double computeTax(PurchasedItems items, Date date) {
// calls private method taxHoliday as part of this computation

        if (taxHoliday(date)) {
            return 0;
        } else {
            return 0.06;

            //Going to be iterating through purchaseItems
        }
    }

    public boolean taxHoliday(Date date) 
    {
    	boolean isHoliday=false;
    	if((date.getMonth() == 8 ) && ((date.getDate() >= 7) && date.getDate() <= 9))
    	{
    		isHoliday=true;
    	}
        return isHoliday;
    }

// returns true if date of receipt within the state’s tax free holiday,
// else returns false. Supporting method of method computeTax.

// tax computation objects for other states are similar
}

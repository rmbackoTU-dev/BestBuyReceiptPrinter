package bestBuyReceiptPrinter.generator;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.generator.taxStrategies.TaxComputationMethod;

public class BasicReceipt implements Receipt {

    private StoreHeader store_header; // street address, state code, phone number, store number
    private TaxComputationMethod tc;
    private ReceiptDate date; // may also be a String type
    private PurchasedItems items;

    public BasicReceipt(PurchasedItems items, ReceiptDate date) {
        // Date may also be a String type
        this.items = items;
        this.date = date;
    }

    public void setStoreHeader(StoreHeader h) {
        store_header = h;
    }

    public void setTaxComputationMethod(TaxComputationMethod tc) {
        this.tc = tc;
    }

    public void printReceipt() {
        //        Store Header (store street address, state code, zipcode, phone number, store number)
//        Date of Sale
//        Itemized Purchases
//        Total Sale (without sales tax)
//        Amount Due (with added sales tax)

        System.out.println(store_header.toString());
        System.out.println(date.toString());


    }
}

//The information for the basic receipt should be stored in a BasicReceipt object. A BasicReceipt
//should contain the store header information, date of sale, purchased items, the total sale (without tax,)
//and the amount due (with added tax). In addition, following the Strategy design pattern, there should be
//an instance variable of (interface) type TaxComputationMethod that can be assigned the appropriate t
//ax computation object for the state that the store resides in. (For tax purposes, everything purchased
//from Best Buy is in the category “computer or computer accessory.”)


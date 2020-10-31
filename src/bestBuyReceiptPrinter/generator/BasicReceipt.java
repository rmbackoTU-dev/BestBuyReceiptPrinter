package bestBuyReceiptPrinter.generator;

public class BasicReceipt implements Receipt {

		private StoreHeader store_header; // street address, state code, phone number, store number
		private TaxComputationMethod tc;
		private Date date; // may also be a String type
		private PurchasedItems items;

		public BasicReceipt(PurchasedItems items, Date date) {
			// Date may also be a String type
			this.items = items;
			this.date = date;
		}
		public void setStoreHeader(StoreHeader h) {
			store_header = h;}

		public void setTaxComputationMethod(TaxComputationMethod tc) {
			this.tc = tc; }

		public void printReceipt() {

		}
	}

//The information for the basic receipt should be stored in a BasicReceipt object (see below). A BasicReceipt
//should contain the store header information, date of sale, purchased items, the total sale (without tax,)
//and the amount due (with added tax). In addition, following the Strategy design pattern, there should be
//an instance variable of (interface) type TaxComputationMethod that can be assigned the appropriate t
//ax computation object for the state that the store resides in. (For tax purposes, everything purchased
//from Best Buy is in the category “computer or computer accessory.”)

//			// TODO Auto-generated method stub
//			String bestBuy = "BEST BUY";
//			String storeNumber = "Store # 2014";
//			String address = "123 Main St., SomeTown, MD 21455";
//			String phoneNumber = "410-704-5555";
//			String date = "4/16/18";
//			String time = "5:51pm";
//			String itemHeading = "ITEM # ";
//
//			System.out.printf("%-15s %32s %n", bestBuy, storeNumber);
//			System.out.printf("%-15s %15s %n", address, phoneNumber);
//			System.out.println(date + time);
//			System.out.println(itemHeading);


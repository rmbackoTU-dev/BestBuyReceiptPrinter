package bestBuyReceiptPrinter.generator;

public class BasicReceipt implements Receipt {
	
	public BasicReceipt()
	{
		
	}

	@Override
	public void printReceipt(){
		// TODO Auto-generated method stub
		String bestBuy = "BEST BUY";
		String storeNumber = "Store # 2014";
		String address = "123 Main St., SomeTown, MD 21455";
		String phoneNumber = "410-704-5555";
		String date = "4/16/18";
		String time = "5:51pm";
		String itemHeading = "ITEM # ";

		System.out.printf( "%-15s %15s %n", bestBuy, storeNumber);
		System.out.printf( "%-15s %15s %n", address, phoneNumber);
		System.out.println(date + time);
		System.out.println(itemHeading);
	}

}

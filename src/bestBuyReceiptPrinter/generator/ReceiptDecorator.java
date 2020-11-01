package bestBuyReceiptPrinter.generator;

public abstract class ReceiptDecorator implements Receipt {

	Receipt currentReceipt;
	
	@Override
	public void printReceipt() {
		// TODO Auto-generated method stub
		System.out.print("Receipt String goes here");
	}

}

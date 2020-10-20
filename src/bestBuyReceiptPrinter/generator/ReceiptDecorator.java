package bestBuyReceiptPrinter.generator;

public abstract class ReceiptDecorator implements Receipt {

	@Override
	public void printReceipt() {
		// TODO Auto-generated method stub
		System.out.print("Receipt String goes here");
	}

}

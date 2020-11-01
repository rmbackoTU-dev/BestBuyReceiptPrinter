package bestBuyReceiptPrinter.generator;

import bestBuyReceiptPrinter.addOns.Addon;
import bestBuyReceiptPrinter.addOns.AddonList;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;

public abstract class ReceiptDecorator implements Receipt {

	Receipt currentReceipt;
	AddonList addOnList;
	
	
	public ReceiptDecorator()
	{
		//default date constructor gets time and date for now
		ReceiptDate date=new  ReceiptDate();
		currentReceipt=new BasicReceipt(null, date);
	}
	
	
	public ReceiptDecorator(PurchasedItems items, ReceiptDate date)
	{
		currentReceipt=new BasicReceipt(items, date);
		addOnList=new AddonList();
	}
	
	public ReceiptDecorator(Receipt receipt, Addon addon)
	{
		currentReceipt=receipt;
		addOnList=new AddonList();
		addOnList.addAddOn(addon);
	}
	
	public ReceiptDecorator(Receipt receipt, AddonList addons)
	{
		currentReceipt=receipt;
		addOnList=addons;
	}
	
	@Override
	public void printReceipt() {
		this.currentReceipt.printReceipt();
	}
	
	protected void removeAddon(Addon a)
	{
		addOnList.removeAddon(a);
	}
	
	private void removeIfDoesNotApply()
	{
		
	}

}

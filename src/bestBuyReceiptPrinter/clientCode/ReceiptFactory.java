package bestBuyReceiptPrinter.clientCode;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.addOns.Addon;
import bestBuyReceiptPrinter.addOns.AddonList;
import bestBuyReceiptPrinter.addOns.AddonList.AddOnsIterator;
import bestBuyReceiptPrinter.addOns.SecondaryHeader;
import bestBuyReceiptPrinter.generator.taxStrategies.TaxComputationMethod;
import bestBuyReceiptPrinter.generator.taxStrategies.stateTaxes.DCTaxComputation;
import bestBuyReceiptPrinter.generator.taxStrategies.stateTaxes.MDTaxComputation;
import bestBuyReceiptPrinter.generator.taxStrategies.stateTaxes.SCTaxComputation;
import bestBuyReceiptPrinter.generator.StoreHeader;
import bestBuyReceiptPrinter.generator.BasicReceipt;
import bestBuyReceiptPrinter.generator.PostDecorator;
import bestBuyReceiptPrinter.generator.PreDecorator;
import bestBuyReceiptPrinter.generator.Receipt;
import bestBuyReceiptPrinter.generator.ReceiptDate;
import bestBuyReceiptPrinter.addOns.TenPercentCoupon;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * A factory Method that creates a Receipt 
 * with the necessary add-ons and tax computations
 * @author Ryan Backof
 *
 */

public class ReceiptFactory {
	
		private AddonList addons;
		private TaxComputationMethod[] taxComputationObject;
		private StoreHeader header;
		private String stateCode;
		private static BufferedReader input;

		
		
		public ReceiptFactory()
		{
			this.stateCode="";
			this.taxComputationObject=new TaxComputationMethod[4];
			this.addons=new AddonList();
			Path configFilePath=Paths.get("/resources/config_file.txt");
			String[] lines=new String[5];//Config file should only ever be 4 lines
			Path absoluteConfigFile=configFilePath.toAbsolutePath();
			try
			{
				//Add everything to an array of lines and parse in a later function
				BufferedReader reader=Files.newBufferedReader(absoluteConfigFile);
				String line=null;
				int i=0;
				while(((line = input.readLine()) != null) && i < lines.length)
				{
					lines[i]=line;
				}
				this.setHeader(lines);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		public ReceiptFactory(AddonList addonsToAdd)
		{
			this.addons=addonsToAdd;
			this.stateCode="";
			this.taxComputationObject=new TaxComputationMethod[4];
			this.addons=new AddonList();
			Path configFilePath=Paths.get("/resources/config_file.txt");
			String[] lines=new String[5];//Config file should only ever be 4 lines
			Path absoluteConfigFile=configFilePath.toAbsolutePath();
			try
			{
				//Add everything to an array of lines and parse in a later function
				BufferedReader reader=Files.newBufferedReader(absoluteConfigFile);
				String line=null;
				int i=0;
				while(((line = input.readLine()) != null) && i < lines.length)
				{
					lines[i]=line;
				}
				this.setHeader(lines);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
		
		/**
		 * Read the lines from the file read and 
		 * set the header and stateCode
		 * appropriately
		 * 
		 */
		public void setHeader(String[] lines)
		{
			String address="";
			String phoneNum="";
			String storeNum="";
			String zipCode="";
			for(int i=0; i< lines.length; i++)
			{
				String line=lines[i];
				String[] keyValue=new String[2];
				keyValue=line.split(":", 2);
				String key=keyValue[0];
				String value=keyValue[1];
				if(key == "Street Address")
				{
					address=value;
				}
				else if(key == "Zip Code")
				{
					zipCode=value;
				}
				else if(key == "State Code")
				{
					this.stateCode=value;
				}
				else if(key == "Phone Number")
				{
					phoneNum=value;
				}
				else if(key == "Store Number")
				{
					storeNum=value;
				}
			}	
			this.header=new StoreHeader(address, zipCode, this.stateCode, phoneNum, storeNum);
		}
		
		
		public void setAddons(AddonList addonsToAdd)
		{
			this.addons=addonsToAdd;
		}
		
		public Receipt getReceipt(PurchasedItems itemsBought, ReceiptDate today)
		{
			DCTaxComputation dcTax=new DCTaxComputation(today);
			MDTaxComputation mdTax=new MDTaxComputation(today);
			SCTaxComputation scTax=new SCTaxComputation(today);
			this.taxComputationObject=new TaxComputationMethod[4];
			this.taxComputationObject[0]=dcTax;
			this.taxComputationObject[1]=mdTax;
			this.taxComputationObject[2]=scTax;
			this.taxComputationObject[3]=null;
			//The fourth tax object repersents a state where no sales tax is present
			TaxComputationMethod taxStrat;
			if(this.stateCode == "MD")
			{
				taxStrat=taxComputationObject[1];
			}
			else if(this.stateCode == "SC")
			{
				taxStrat=taxComputationObject[2];
			}
			else if(this.stateCode == "DC")
			{
				taxStrat=taxComputationObject[0];
			}
			else
			{
				taxStrat=taxComputationObject[3];
			}
			BasicReceipt newReceipt=new BasicReceipt(itemsBought, today);
			newReceipt.setTaxComputationMethod(taxStrat);
			return newReceipt;
		}
	
		
		public Receipt getReceiptAddonIfApplies(Receipt receipt, PurchasedItems items)
		{
			Receipt newReceipt=receipt;
			AddOnsIterator addonIterator=this.addons.getIterator();
			Addon currentAddon;
			boolean applicableAddonHasSecondaryHeader=false;
			AddonList applicableAddons=new AddonList();
			try
			{
				currentAddon=addonIterator.getCurrentItem();
				if(currentAddon.applies(items))
				{
					if(currentAddon instanceof SecondaryHeader)
					{
						//make sure we only apply one Secondary Header addon
						if(!(applicableAddonHasSecondaryHeader))
						{
							applicableAddons.addAddOn(currentAddon);
							applicableAddonHasSecondaryHeader=true;
						}
					}
					else
					{
						applicableAddons.addAddOn(currentAddon);
					}
				}
				while(addonIterator.hasNext())
				{
					addonIterator.next();
					currentAddon=addonIterator.getCurrentItem();
					if(currentAddon.applies(items))
					{
						if(currentAddon instanceof SecondaryHeader)
						{
							if(!(applicableAddonHasSecondaryHeader))
							{
								applicableAddons.addAddOn(currentAddon);
							}
						}
						else
						{
							applicableAddons.addAddOn(currentAddon);
						}
					}	
				}
				
				//Iterate through the applicable Addons
				AddOnsIterator applicableAddonIter =applicableAddons.getIterator();
				Addon currentApplicableAddon=applicableAddonIter.getCurrentItem();
				//Differentiate between a preDecorator call and postDecorator call
				if(currentApplicableAddon instanceof SecondaryHeader)
				{
					newReceipt=new PreDecorator(newReceipt, currentApplicableAddon);
				}
				else
				{
					newReceipt=new PostDecorator(newReceipt, currentApplicableAddon);
				}
				while(applicableAddonIter.hasNext())
				{
					applicableAddonIter.next();
					currentApplicableAddon=applicableAddonIter.getCurrentItem();
					if(currentApplicableAddon instanceof SecondaryHeader)
					{
						newReceipt=new PreDecorator(newReceipt, currentApplicableAddon);
					}
					else
					{
						newReceipt=new PostDecorator(newReceipt, currentApplicableAddon);
					}
				}
			}
			catch(IllegalStateException e)
			{
				System.err.println("There are no Addons added");
			}
			return newReceipt;
		}
		
}

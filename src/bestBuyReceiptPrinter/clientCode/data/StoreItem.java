package bestBuyReceiptPrinter.clientCode.data;

/**
 * Store Item represents a single store item
 * store item is represented by its description
 * inventory number and price, additionally item has
 * a boolean which defines 
 * @author Ryan Backof
 *
 */
import java.math.BigDecimal;
import java.math.RoundingMode;

public class StoreItem {

	private String description;
	private BigDecimal price;
	private int inventoryNum;
	public static int currentInventoryCount=0;
	
	/**
	 * Default Constructor
	 */
	public StoreItem()
	{
		currentInventoryCount=currentInventoryCount+1;
		this.inventoryNum=currentInventoryCount;
		this.price=new BigDecimal(0);
		this.price=this.price.setScale(2, RoundingMode.CEILING);
		this.description="A new Item : "+this.inventoryNum;
	}
	
	/**
	 * Parameter Constructor
	 */
	public StoreItem(String descript,  BigDecimal itemPrice)
	{
		currentInventoryCount=currentInventoryCount+1;
		this.inventoryNum=currentInventoryCount;
		this.price=itemPrice;
		this.price=this.price.setScale(2, RoundingMode.CEILING);
		this.description=descript+" : "+this.inventoryNum;
	}
	
	
	@Override
	public void finalize()
	{
		try
		{
			currentInventoryCount=currentInventoryCount-1;
			super.finalize();
		}
		catch(Throwable e)
		{
			currentInventoryCount=currentInventoryCount-1;
			e.printStackTrace();
			System.err.println("There was an error with your rebate");
		}
	}
	
	public static int getItemCount()
	{
		return currentInventoryCount;
	}
	
	
	public int getInventoryNum()
	{
		return this.inventoryNum;
	}
	
	
	public String getDescription()
	{
		return this.description;
	}
	
	public BigDecimal getPrice()
	{
		return this.price;
	}


}
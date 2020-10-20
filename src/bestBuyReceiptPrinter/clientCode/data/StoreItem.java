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

public class StoreItem {
	
	private String description;
	private BigDecimal price;
	private int inventoryNum;
	private boolean rebateAvailable;

}

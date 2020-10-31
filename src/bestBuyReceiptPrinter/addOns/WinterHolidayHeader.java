package bestBuyReceiptPrinter.addOns;

import java.time.LocalDate;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;

/**
 * Add-on adds a header for sales holidays
 * to the receipt
 * @author Ryan Backof
 *
 */
public class WinterHolidayHeader implements SecondaryHeader {
	
	String headerText;
	LocalDate appliesDate;
	LocalDate endDate;
	
	/**
	 * Default constructor
	 */
	public WinterHolidayHeader()
	{
		this.headerText="Happy Holidays";
		LocalDate today=LocalDate.now();
		//Holidays begin in start of November
		this.appliesDate=LocalDate.of(today.getYear(), 11, 1);
		this.endDate=LocalDate.of(today.getYear()+1, 1 , 2);
	}
	
	@Override
	public LocalDate getHolidayStartDate()
	{
		return this.appliesDate;
	}
	
	@Override
	public LocalDate getHolidayEndDate()
	{
		return this.endDate;
	}
	
	/**
	 * Parameter constructor
	 * @param textForHeader
	 */
	public WinterHolidayHeader(String textForHeader)
	{
		this.headerText="Happy Holidays";
		setHeaderText(textForHeader);
		LocalDate today=LocalDate.now();
		//Holidays begin in start of November
		this.appliesDate=LocalDate.of(today.getYear(), 11, 1);
		this.endDate=LocalDate.of(today.getYear()+1, 1 , 2);
	}
	
	/**
	 * setter for the headerText
	 * @param textForHeader
	 */
	public void setHeaderText(String textForHeader)
	{
		this.headerText=this.headerText+textForHeader;
	}
	
	/**
	 * Always returns true, because if header is constructed
	 * it is needed
	 * Also hard to test date range without mocking LocalDate.now
	 */
	@Override
	public boolean applies(PurchasedItems items)
	{
	 
		return true;
	}
	
	@Override
	public String getLines()
	{
		return headerText;
	}
}

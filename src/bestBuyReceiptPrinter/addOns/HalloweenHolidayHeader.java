package bestBuyReceiptPrinter.addOns;

import java.time.LocalDate;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;

public class HalloweenHolidayHeader implements SecondaryHeader {
String headerText;
	LocalDate appliesDate;
	LocalDate endDate;
	
	/**
	 * Default constructor
	 */
	public HalloweenHolidayHeader()
	{
		this.headerText="Happy Halloween";
		LocalDate today=LocalDate.now();
		//Holidays begin in start of November
		this.appliesDate=LocalDate.of(today.getYear(), 10, 1);
		this.endDate=LocalDate.of(today.getYear(), 10 , 31);
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
	public HalloweenHolidayHeader(String textForHeader)
	{
		this.headerText="Happy Holidays";
		setHeaderText(textForHeader);
		LocalDate today=LocalDate.now();
		//Holidays begin in start of November
		this.appliesDate=LocalDate.of(today.getYear(), 10, 1);
		this.endDate=LocalDate.of(today.getYear(), 10 , 31);
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

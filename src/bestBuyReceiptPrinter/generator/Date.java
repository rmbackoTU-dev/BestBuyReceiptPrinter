package bestBuyReceiptPrinter.generator;

import java.time.LocalDate;
import java.time.LocalTime;

public class Date {
    private int month=0; //1-12
    private int date=0; // 1-31
    private String day= "Monday";
    private int year=01; //1-2 digit number
    private int hour=01; //1-12
    private int minute=11; //0-60
    private boolean am = true; //true am false pm

    private static  final String[] DAYS_OF_THE_WEEK=
    	{ "Monday", "Tuesday","Wednesday","Thursday", "Friday", "Saturday", "Sunday"};
    
    public Date()
    {
    	LocalDate today=LocalDate.now();
    	LocalTime timeRightNow=LocalTime.now();
    	this.month=today.getMonthValue();
    	this.date=today.getDayOfMonth();
    	this.year=today.getYear();
    	int dateInt=today.getDayOfWeek().getValue();
    	this.day=DAYS_OF_THE_WEEK[dateInt];
    	this.hour=timeRightNow.getHour();
    	this.minute=timeRightNow.getMinute();
    	if(this.hour > LocalTime.NOON.getHour())
    	{
    		this.am=false;
    	}
    	else
    	{
    		this.am=true;
    	}
    	
    	
    }
    
    public Date(int month, int date, String day, int year, int hour, int minute, boolean am) {
        this.month = month;
        this.day = day;
        this.date=date;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.am = am;
    }
    
    public  int getMonth()
    {
    	return this.month;
    }
    
    
    public int getDate()
    {
    	return this.date;
    }
    
    public int getYear()
    {
    	return this.year;
    }
    
    
    public int getHour()
    {
    	return this.hour;
    }
    
    
    public int getMinute()
    {
    	return this.minute;
    }
    
    public boolean isAM()
    {
    	return this.am;
    }
    
    public String getDayOfWeek()
    {
    	return this.day;
    }
}

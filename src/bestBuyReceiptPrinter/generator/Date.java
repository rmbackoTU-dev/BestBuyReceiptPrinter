package bestBuyReceiptPrinter.generator;

public class Date {
    int month=0; //1-12
    int day=0; // 1-31
    int year=01; //1-2 digit number
    int hour=01; //1-12
    int minute=11; //0-60
    boolean am = true; //true am false pm

    public Date(int month, int day, int year, int hour, int minute, boolean am) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.am = am;
    }
}

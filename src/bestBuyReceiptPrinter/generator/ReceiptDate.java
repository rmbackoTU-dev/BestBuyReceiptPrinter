package bestBuyReceiptPrinter.generator;

public class ReceiptDate {
    int month=0; //1-12
    int date=0; // 1-31
    String day= "Monday";
    int year=01; //1-2 digit number
    int hour=01; //1-12
    int minute=11; //0-60
    boolean am = true; //true am false pm

    public ReceiptDate(int month, int date, String day, int year, int hour, int minute, boolean am) {
        this.month = month;
        this.day = day;
        this.date=date;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.am = am;
    }

    @Override
    public String toString() {
        return "ReceiptDate{" +
                month +
                "/" + date +
                "/" + year +
                '}';
    }
}

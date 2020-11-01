package bestBuyReceiptPrinter.clientCode;

import bestBuyReceiptPrinter.addOns.Addon;
import bestBuyReceiptPrinter.addOns.AddonList;
import bestBuyReceiptPrinter.addOns.Coupon;
import bestBuyReceiptPrinter.addOns.HalloweenHolidayHeader;
import bestBuyReceiptPrinter.addOns.Rebate;
import bestBuyReceiptPrinter.addOns.RebateOne;
import bestBuyReceiptPrinter.addOns.RebateTwentyFive;
import bestBuyReceiptPrinter.addOns.SecondaryHeader;
import bestBuyReceiptPrinter.addOns.TenPercentCoupon;
import bestBuyReceiptPrinter.addOns.TwentyFivePercentCoupon;
import bestBuyReceiptPrinter.addOns.WinterHolidayHeader;
import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;
import bestBuyReceiptPrinter.generator.ReceiptDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Scanner;

public class client {

	private static Scanner scanner;
    public static void main(String[] args) {
// 1. Creates a Data object (either from Java API or date entered by user)
    	
        System.out.println("What is today's date?");
        scanner = new Scanner(System.in);
        System.out.println("Please enter in the month as a number (1 for January, etc)");
        int month = scanner.nextInt();
        System.out.println("Please enter in today's day. Monday for example.");
        String day = scanner.nextLine();
        System.out.println("Please enter in today's date. If it's the first day of the month, please enter 1.");
        int date = scanner.nextInt();
        System.out.println("What time is it? Please enter in the hour.");
        int hour = scanner.nextInt();
        System.out.println("Please enter in the minute");
        int minute = scanner.nextInt();
        System.out.println("Is it " + hour + ":" + minute + " am? y/n");
        String amQuestion = scanner.nextLine();
        boolean isAm = true;
        if (amQuestion.equals("n")) {
            isAm = false;
        }
        System.out.println("Please enter in the year");
        int year = scanner.nextInt();

        ReceiptDate clientDate = new ReceiptDate(month, date, day, year, hour, minute, isAm);


// 2. Creates a PurchasedItems object (selections made by user)
        System.out.println("What would you like to order today?");
        System.out.println("1. RyanCo. Gaming Laptop, $3100.99");
        System.out.println("2. RyanCo. Everyday Use Laptop, $750.99");
        System.out.println("3. Sashendo Stitch, $299.99");
        System.out.println("4. Sashendo GamePro Mastered, $59.99");
        System.out.println("5. RyanCo. All purpose charger, $19.99");
        System.out.println("6. Sashendo Legend of Java, $59.99");
        System.out.println("7. Sashendo The Nums, $59.99");

        String finished = "n";
        String itemNumber = "0";
        PurchasedItems items = new PurchasedItems();

        BigDecimal itemOnePrice=new BigDecimal(3200.99);
        BigDecimal itemTwoPrice=new BigDecimal(750.99);
        BigDecimal itemThreePrice=new BigDecimal(59.99);
        BigDecimal itemFourPrice=new BigDecimal(299.99);
        BigDecimal itemFivePrice=new BigDecimal(19.99);
        BigDecimal itemSixPrice=new BigDecimal(59.99);
        itemOnePrice=itemOnePrice.setScale(2, RoundingMode.CEILING);
        itemTwoPrice=itemTwoPrice.setScale(2, RoundingMode.CEILING);
        itemThreePrice=itemThreePrice.setScale(2, RoundingMode.CEILING);
        itemFourPrice=itemFourPrice.setScale(2, RoundingMode.CEILING);
        itemFivePrice=itemFivePrice.setScale(2, RoundingMode.CEILING);
        itemSixPrice=itemSixPrice.setScale(2, RoundingMode.CEILING);

        while (finished.equals("n")) {
            System.out.println("Please enter your order by indicating the item number");
            itemNumber = scanner.nextLine();
            if (itemNumber.equals("1")) {
                StoreItem item = new StoreItem("RyanCo. Gaming Laptop", itemOnePrice);
                items.addItem(item);
            } else if (itemNumber.equals("2")) {
                StoreItem item = new StoreItem("RyanCo. Everyday Use Laptop", itemTwoPrice);
                items.addItem(item);
            } else if (itemNumber.equals("3")) {
                StoreItem item = new StoreItem("Sashendo Stitch", itemThreePrice);
                items.addItem(item);
            } else if (itemNumber.equals("4")) {
                StoreItem item = new StoreItem("Sashendo GamePro Mastered", itemFourPrice);
                items.addItem(item);
            } else if (itemNumber.equals("5")) {
                StoreItem item = new StoreItem("RyanCo. All purpose charger", itemFivePrice);
                items.addItem(item);
            } else if (itemNumber.equals("6")) {
                StoreItem item = new StoreItem("Sashendo Legend of Java", itemSixPrice);
                items.addItem(item);
            } else if (itemNumber.equals("7")) {
            	StoreItem item= new StoreItem("Sashendo The Nums", itemSixPrice);
            	items.addItem(item);
            }
            System.out.println("Do you want to purchase something else? y/n");
            String purchaseAgain = "n";
            purchaseAgain = scanner.nextLine();
            if (purchaseAgain.equals("n")) {
                finished = "y";
            }
        }
    }
    
    
   
   BigDecimal qualifyingAmountOne=new BigDecimal(500.00);
   BigDecimal qualifyingAmountTwo=new BigDecimal(1000.00);
   
   LocalDate expirationDateOne=LocalDate.of(2021, 05, 21);
   LocalDate expiratationDateTwo=LocalDate.of(2020, 12, 14);
   
    
    AddonList listOfAddons=new AddonList();
    // 3. Constructs a ReceiptFactory object.
    //3.a construct addons
    //3.b pass Addons to ReceiptFactory
    SecondaryHeader winterHeader=new WinterHolidayHeader();
    SecondaryHeader halloweenHeader=new HalloweenHolidayHeader();
    Rebate firstRebate=new RebateOne();
    Rebate secondRebate=new RebateTwentyFive();
    Coupon firstCoupon=new TenPercentCoupon("Video Games Coupon", expiratationDateTwo, qualifyingAmountOne);
    Coupon secondCoupon=new TwentyFivePercentCoupon("Laptop Coupon", expirationDateOne, qualifyingAmountTwo);
    
    
    
    
}
    

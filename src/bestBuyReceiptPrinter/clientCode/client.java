package bestBuyReceiptPrinter.clientCode;

import bestBuyReceiptPrinter.clientCode.data.PurchasedItems;
import bestBuyReceiptPrinter.clientCode.data.StoreItem;
import bestBuyReceiptPrinter.generator.ReceiptDate;

import java.math.BigDecimal;
import java.util.Scanner;

public class client {

    public static void main(String[] args) {
// 1. Creates a Data object (either from Java API or date entered by user)
        System.out.println("What is today's date?");
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("5. Sashendo Legend of Java, $59.99");
        System.out.println("6. Sashendo The Nums, $59.99");

        String finished = "n";
        String itemNumber = "0";
        PurchasedItems items = new PurchasedItems();

        while (finished.equals("n")) {
            System.out.println("Please enter your order by indicating the item number");
            itemNumber = scanner.nextLine();
            if (itemNumber.equals("1")) {
                StoreItem item = new StoreItem("RyanCo. Gaming Laptop", 3200.99);
                items.addItem(item);
            } else if (itemNumber.equals("2")) {
                StoreItem item = new StoreItem("RyanCo. Everyday Use Laptop", 750.99);
                items.addItem(item);
            } else if (itemNumber.equals("3")) {
                StoreItem item = new StoreItem("Sashendo Stitch", 59.99);
                items.addItem(item);
            } else if (itemNumber.equals("4")) {
                StoreItem item = new StoreItem("Sashendo GamePro Mastered", 299.99);
                items.addItem(item);

            } else if (itemNumber.equals("5")) {
                StoreItem item = new StoreItem("RyanCo. All purpose charger", 19.99);
                items.addItem(item);
            } else if (itemNumber.equals("6")) {
                StoreItem item = new StoreItem("Sashendo Legend of Java", 59.99);
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
}



// 3. Constructs a ReceiptFactory object.
// 3. Prompts user for items to purchase, storing each in PurchasedItems.
// 4. Calls the getReceipt method of the factory to obtain constructed receipt.
// 5. Prints receipt by call to method prtReceipt.
// get receipt date
// (prompt user for current date)
// display all available store items to user
//                (to be implemented)
//// get all user selections
//                (to be implemented)
//                ReceiptFactory factory = new ReceiptFactory();
//                Receipt = factory.getReceipt(items, date);
//                receipt.prtReceipt();
//            }
//        }

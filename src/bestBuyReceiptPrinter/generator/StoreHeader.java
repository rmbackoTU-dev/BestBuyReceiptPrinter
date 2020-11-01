package bestBuyReceiptPrinter.generator;

public class StoreHeader {

    //street address, state code, zip code, phone number, and store number

    String streetAddress = "123 Maryland Ave.";
    String stateCode = "MD";
    String zipCode = "20715";
    String phoneNumber = "240-102-4567";
    int storeNumber = 123;

    public StoreHeader(String streetAddress, String stateCode, String zipCode, String phoneNumber, int storeNumber){
        this.streetAddress=streetAddress;
        this.stateCode=stateCode;
        this.zipCode=zipCode;
        this.phoneNumber=phoneNumber;
        this.storeNumber=storeNumber;
    }
}

package bestBuyReceiptPrinter.addOns;

import java.math.BigDecimal;

public interface Coupon extends Addon {

	public BigDecimal getQualifyingAmount();
}

package bestBuyReceiptPrinter.generator.taxStrategies;

import java.math.BigDecimal;

public interface TaxStrategy {

	
	/**
	 * All Tax Strategies must implement
	 * compute which is how they compute the tax
	 * amount
	 */
	public BigDecimal compute();
}

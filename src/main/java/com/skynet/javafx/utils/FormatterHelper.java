package com.skynet.javafx.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FormatterHelper {

	private static final String CURRENCY_DEFAULT_FORMAT = "0.00";

	public static String formatDefaultCurrency(Double value) {
		DecimalFormat df = new DecimalFormat(CURRENCY_DEFAULT_FORMAT);
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		df.setDecimalFormatSymbols(symbols);
		return df.format(value);
	}
}

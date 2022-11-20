package com.vn.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtil {
    public static String genMoney(Double money){
        Locale locale = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return  numberFormat.format(money);
    }
}

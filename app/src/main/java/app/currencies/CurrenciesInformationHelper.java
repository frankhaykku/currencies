package app.currencies;

import app.currencies.R;
import java.util.HashMap;
import java.util.Map;

public class CurrenciesInformationHelper {

    private static Map<String, String> mDescriptions = new HashMap<String, String>() {{
        put("AUD", "Australia Dollar");
        put("BGN", "Bulgaria Lev");
        put("BRL", "Brazil Real");
        put("CAD", "Canada Dollar");
        put("CHF", "Switzerland Franc");
        put("CNY", "China Yuan/Renminbi");
        put("CZK", "Czech Koruna");
        put("DKK", "Denmark Krone");
        put("EUR", "Euro");
        put("GBP", "Great Britain Pound");
        put("HKD", "Hong Kong Dollar");
        put("HRK", "Croatia Kuna");
        put("HUF", "Hungary Forint");
        put("IDR", "Indonesia Rupiah");
        put("ILS", "Israel New Shekel");
        put("INR", "India Rupee");
        put("ISK", "Iceland Krona");
        put("JPY", "Japan Yen");
        put("KRW", "South Korea Won");
        put("MXN", "Mexico Peso");
        put("MYR", "Malaysia Ringgit");
        put("NOK", "Norway Kroner");
        put("NZD", "New Zealand Dollar");
        put("PHP", "Philippines Peso");
        put("PLN", "Poland Zloty");
        put("RON", "Romania New Lei");
        put("RUB", "Russia Rouble");
        put("SEK", "Sweden Krona");
        put("SGD", "Singapore Dollar");
        put("THB", "Thailand Baht");
        put("TRY", "Turkish New Lira");
        put("USD", "USA Dollar");
        put("ZAR", "South Africa Rand");
    }};

    private static Map<String, Integer> mFlags = new HashMap<String, Integer>() {{
        put("AUD", R.drawable.aud);
        put("BGN", R.drawable.bgn);
        put("BRL", R.drawable.brl);
        put("CAD", R.drawable.cad);
        put("CHF", R.drawable.chf);
        put("CNY", R.drawable.cny);
        put("CZK", R.drawable.czk);
        put("DKK", R.drawable.dkk);
        put("EUR", R.drawable.eur);
        put("GBP", R.drawable.gbp);
        put("HKD", R.drawable.hkd);
        put("HRK", R.drawable.hrk);
        put("HUF", R.drawable.huf);
        put("IDR", R.drawable.idr);
        put("ILS", R.drawable.ils);
        put("INR", R.drawable.inr);
        put("ISK", R.drawable.isk);
        put("JPY", R.drawable.jpy);
        put("KRW", R.drawable.krw);
        put("MXN", R.drawable.mxn);
        put("MYR", R.drawable.myr);
        put("NOK", R.drawable.nok);
        put("NZD", R.drawable.nzd);
        put("PHP", R.drawable.php);
        put("PLN", R.drawable.pln);
        put("RON", R.drawable.ron);
        put("RUB", R.drawable.rub);
        put("SEK", R.drawable.sek);
        put("SGD", R.drawable.sgd);
        put("THB", R.drawable.thb);
        put("TRY", R.drawable.trl);
        put("USD", R.drawable.usd);
        put("ZAR", R.drawable.zar);
    }};

    public static String getDescByShortName(String shortName) {
        return mDescriptions.get(shortName) == null ? "Unknown" : mDescriptions.get(shortName);
    }

    public static int getFlagByShortName(String shortName) {
        return mFlags.get(shortName) <= 0 ? R.drawable.unk : mFlags.get(shortName);
    }
}

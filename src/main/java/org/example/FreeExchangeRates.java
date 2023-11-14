package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FreeExchangeRates {

    public static void main(String[] args) {
        try {
            String sourceCurrency = "USD";
            String targetCurrency = "EUR";

            double exchangeRate = getExchangeRate(sourceCurrency, targetCurrency);

            System.out.println("Exchange rate from " + sourceCurrency + " to " + targetCurrency + ": " + exchangeRate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getExchangeRate(String sourceCurrency, String targetCurrency) throws Exception {
        String apiUrl = "https://v6.exchangerate-api.com/v6/1284c43ce886ca484c6b2505/latest/" + sourceCurrency;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
//            System.out.println("string:" + jsonResponse);

            // Extract the exchange rate for the target currency
            return jsonResponse.getAsJsonObject("conversion_rates").get(targetCurrency).getAsDouble();
        }
    }
}

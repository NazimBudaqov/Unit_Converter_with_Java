package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Methods {
    // Placeholder lists for unit types
    public static final String[] units = {"Temperature", "Volume", "Mass", "Currency"};

    public static final String[] temperatureList = {"Celsius", "Fahrenheit", "Kelvin"};

    public static final String[] volumeList = {"Cubic Meter", "Liter", "Gallon", "Quart", "Pint"};

    public static final String[] massList = {"Kilogram", "Gram", "Pound", "Ounce", "Milligram"};

    public static final String[] currencyList = {"USD - US Dollar", "EUR - Euro", "JPY - Japanese Yen"};

    public static String extractNumericValue(String input) {
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }
    }

    // Extract non-numeric text using regex
    public static String extractNonNumericText(String input) {
        Pattern pattern = Pattern.compile("[^\\d\\s]+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group().trim();
        } else {
            return "";
        }

    }

    // Methods for unit conversion logic

    public static Double convertTemperatures(String input, String output_type){

        double input_value = Double.parseDouble(extractNumericValue(input));
        char input_type = input.charAt(input.length()-1);
        double result = 0;

        switch (output_type){
            case "K":
                switch (input_type){
                    case 'K' : result = input_value; break;
                    case 'C' : result = input_value + 273.15; break;
                    case 'F' : result = (input_value-32)*5/9+273.15; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + input);
                }
                return result;

            case "C":
                switch (input_type){
                    case 'C' : result = input_value; break;
                    case 'K' : result = input_value -  273.15; break;
                    case 'F' : result = input_value * 5/9 + 32; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + input);
                }
                return result;

            case "F":
                switch (input_type){
                    case 'F' : result = input_value; break;
                    case 'C' : result = (input_value -32) *5/9; break;
                    case 'K' : result = (input_value -32) *5/9 + 273.15; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + input);
                }
                return result;
        }
        return result;
    }

    public static double convertCurrencies(String input, String output_currency){
        double input_value = Double.parseDouble(extractNumericValue(input));
        char input_type = input.charAt(input.length()-1);
        double result = 0;
        return result;
    }


    public static void main(String[] args) {

        System.out.println(convertTemperatures("5 F", "K"));
        System.out.println();
    }

}


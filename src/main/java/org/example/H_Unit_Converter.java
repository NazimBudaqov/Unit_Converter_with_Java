package org.example;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import static org.example.Methods.extractNonNumericText;
import static org.example.Methods.extractNumericValue;

/*
 * Unit Converter (temp, currency, volume, mass and more) - Converts various units between one another.
 * The user enters the type of unit being entered, the type of unit they want to convert to and then the
 * value. The program will then make the conversion.
 * */

public class H_Unit_Converter {

    static JFrame f;
    static JTextField inputField, resultField;
    static String result;

    static double unit_a;
    static String type_a;

    public static void main(String[] args) {

        f = new JFrame("Unit Converter");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMinimumSize(new Dimension(440, 440));
        f.setResizable(false);
        f.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel conversionPanel = new JPanel();
        conversionPanel.setLayout(null);
        tabbedPane.addTab("Conversion", conversionPanel);

        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(null);
        tabbedPane.addTab("About", aboutPanel);

        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(null);
        tabbedPane.addTab("Help", helpPanel);

        tabbedPane.setBounds(0, 0, 400, 400);
        f.add(tabbedPane);

        inputField = new JTextField();
        inputField.setBounds(50, 50, 200, 30);
        conversionPanel.add(inputField);

        resultField = new JTextField();
        resultField.setBounds(50,200,200,30);
        conversionPanel.add(resultField);


        // buttons to add temperature definitions to text (one time)
        JButton b_celsius = new JButton("°C");
        b_celsius.setBounds(50, 100, 60, 20);
        conversionPanel.add(b_celsius);
        JButton b_fahrenheit = new JButton("°F");
        b_fahrenheit.setBounds(120, 100, 60, 20);
        conversionPanel.add(b_fahrenheit);
        JButton b_kelvin = new JButton("°K");
        b_kelvin.setBounds(190, 100, 60, 20);
        conversionPanel.add(b_kelvin);

        JButton b_getText = new JButton("Convert");
        b_getText.setBounds(270, 50, 100, 30);
        conversionPanel.add(b_getText);

        JButton b_currency = new JButton("Currency");
        b_currency.setBounds(50, 130, 100, 30);
        conversionPanel.add(b_currency);

        // button "Convert" - get text from input field,
        b_getText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String getValue = inputField.getText();
                System.out.println("text: " + getValue);

                String dubleStr = extractNumericValue(getValue);
                try {
                    double unit_a = Double.parseDouble(dubleStr);
                    System.out.println("Double value: " + dubleStr);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid double format: " + dubleStr);
                }

                String type_a = extractNonNumericText(getValue);
            }
        });

        // Create menu1 and set it to the default selection "Temperature"
        JComboBox<String> menu1 = new JComboBox<>(Methods.units);
        menu1.setSelectedItem("Temperature"); // Set the default selection
        menu1.setBounds(50, 5, 120, 30);
        conversionPanel.add(menu1);

// Create menu2 and set it to the default items for Temperature
        JComboBox<String> menu2 = new JComboBox<>(Methods.temperatureList);
        menu2.setBounds(190, 5, 60, 30);
        conversionPanel.add(menu2);

        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) menu1.getSelectedItem();

                // Clear the items in menu2
                menu2.removeAllItems();

                // Depending on the selected item in menu1, populate menu2 with appropriate items
                if ("Temperature".equals(selectedItem)) {
                    for (String item : Methods.temperatureList) {
                        menu2.addItem(item);
                    }
                } else if ("Volume".equals(selectedItem)) {
                    for (String item : Methods.volumeList) {
                        menu2.addItem(item);
                    }
                } else if ("Mass".equals(selectedItem)) {
                    for (String item : Methods.massList) {
                        menu2.addItem(item);
                    }
                } else if ("Currency".equals(selectedItem)) {
                    for (String item : Methods.currencyList) {
                        menu2.addItem(item);
                    }
                }

                // Clear and update the resultField with the selected item in menu2
                resultField.setText("");
                resultField.setText((String) menu2.getSelectedItem());

            }
        });

// Create resultField and set it to the default selected item in menu2
        resultField = new JTextField();
        resultField.setText((String) menu2.getSelectedItem());
        resultField.setBounds(50, 200, 200, 30);
        conversionPanel.add(resultField);

        // Repaint the panel to update menu2 and resultField
        conversionPanel.revalidate();
        conversionPanel.repaint();


        // Consume the newline character left in the buffer
//        sc.nextLine();

//        System.out.print("Type to convert (temp, currency, volume, mass, etc.): ");
//        String type_b;
//
//        switch (type_b) {
//            case "temp":
//                double convertedTemp = tempUnitConverter(unit_a, type_a);
//                System.out.println("Converted Temperature: " + convertedTemp);
//                break;
//
//            case "currency":
//                // Handle currency conversion
//                break;
//
//            case "volume":
//                // Handle volume conversion
//                break;
//
//            case "mass":
//                // Handle mass conversion
//                break;
//
//            default:
//                System.out.println("Invalid unit type.");
//                break;
//        }

//        sc.close();
//        System.exit(0);

        f.pack();
    }


}
package org.example;

import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import static org.example.Methods.*;

/*
 * Unit Converter (temp, currency, volume, mass and more) - Converts various units between one another.
 * The user enters the type of unit being entered, the type of unit they want to convert to and then the
 * value. The program will then make the conversion.
 * */

public class H_Unit_Converter {

    static JFrame f;
    static JTextField inputField, resultField;

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
        inputField.setBounds(50, 50, 140, 30);
        conversionPanel.add(inputField);

        resultField = new JTextField();
        resultField.setBounds(50,200,140,30);
        conversionPanel.add(resultField);

        // Create menu1 and set it to the default selection "Temperature"
        JComboBox<String> menu1 = new JComboBox<>(Methods.units);
        menu1.setSelectedItem("Temperature"); // Set the default selection
        menu1.setBounds(50, 5, 120, 30);
        conversionPanel.add(menu1);

        // Create menu2 and set it to the default items for Temperature
        JComboBox<String> menu2 = new JComboBox<>(Methods.temperatureList);
        menu2.setBounds(190, 50, 150, 29);
        conversionPanel.add(menu2);

        JComboBox<String> menu3 = new JComboBox<>(Methods.temperatureList);
        menu3.setBounds(190, 200, 150, 29);
        conversionPanel.add(menu3);

        menu1.addActionListener(e -> {
            String selectedItem = (String) menu1.getSelectedItem();

            // Clear the items in menu2
            menu2.removeAllItems();
            menu3.removeAllItems();

            // Depending on the selected item in menu1, populate menu2 with appropriate items
            if ("Temperature".equals(selectedItem)) {
                for (String item : Methods.temperatureList) {
                    menu2.addItem(item);
                    menu3.addItem(item);
                }
            } else if ("Volume".equals(selectedItem)) {
                for (String item : Methods.volumeList) {
                    menu2.addItem(item);
                    menu3.addItem(item);
                }
            } else if ("Mass".equals(selectedItem)) {
                for (String item : Methods.massList) {
                    menu2.addItem(item);
                    menu3.addItem(item);
                }
            } else if ("Currency".equals(selectedItem)) {
                for (String item : Methods.currencyList) {
                    menu2.addItem(item);
                    menu3.addItem(item);
                }
            }

        });

        // button "Convert" - get text from input field,
        JButton b_getText = new JButton("Convert");
        b_getText.setBounds(70, 125, 100, 30);

        b_getText.addActionListener(ae -> {
            String getValue = inputField.getText();
            double source_value = extractNumericValue(getValue);
            String source_type;
//           String source_type = extractNonNumericText(getValue);
            String target_type;

            if (String.valueOf(menu1.getSelectedItem()) == "Temperature"){
                source_type = String.valueOf(menu2.getSelectedItem()).substring(0,1);
                target_type = String.valueOf(menu3.getSelectedItem()).substring(0,1);
//                System.out.println(source_value+source_type + target_type);
                String result = String.valueOf(convertTemperatures(source_value+source_type,target_type));
                resultField.setText(result);
            }
            else if (String.valueOf(menu1.getSelectedItem()) == "Currency"){
                source_type = String.valueOf(menu2.getSelectedItem()).substring(0,3);
                target_type = String.valueOf(menu3.getSelectedItem()).substring(0,3);
                String result = String.valueOf(convertCurrencies(source_value+source_type,target_type));
                resultField.setText(result);
            }
            else{
                //temp
                source_type = String.valueOf(menu2.getSelectedItem());
            }

//            System.out.println(source_value + source_type);
        });
        conversionPanel.add(b_getText);

        // Repaint the panel to load/update all elements
        conversionPanel.revalidate();
        conversionPanel.repaint();

        f.pack();
    }
}
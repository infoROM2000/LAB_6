package com.company;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape
    JComboBox shapesCombo; // tipul de figura

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6); //default number of sides
        String[] colors = {"black", "random"};
        colorCombo = new JComboBox(colors);
        String[] shapes = {"polygon", "circle"};
        shapesCombo = new JComboBox(shapes);
        add(sidesLabel); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
        add(shapesCombo);
    }

    public int getSidesField() {
        return (int) sidesField.getValue();
    }

    public boolean getColor() {
        if (colorCombo.getSelectedItem().toString().equals("black"))
            return false;
        return true;
    }

    public boolean getShape() {
        if (shapesCombo.getSelectedItem().toString().equals("polygon"))
            return true;
        return false;
    }
}

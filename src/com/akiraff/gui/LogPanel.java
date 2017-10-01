package com.akiraff.gui;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel{

    private JTable table;
    private JPanel smallPanel = new JPanel(new GridBagLayout());

    private JLabel priceLabel = new JLabel("Total Price: " + 0);
    private JLabel lootLabel = new JLabel("Total Loot: " + 0);

    private JButton addButton = new JButton("Add Loot");

    private GridBagConstraints c = new GridBagConstraints();

    public LogPanel() {
        setLayout(new FlowLayout());
        addComponents();
    }

    private void addComponents() {
        String[] columnNames = {"Log Number", "Rewards", "Total price", "Date"};
        Object[][] data = {
                {"1", "barrows reward", 1000, "new date"},
                {"2", "barrows reward", 12030, "new date"},
                {"3", "barrows reward", 100210, "new date"}
        };

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(570, 200));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0, 10, 0, 0);
        smallPanel.add(priceLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        smallPanel.add(lootLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        smallPanel.add(addButton, c);

        add(smallPanel);
    }
}

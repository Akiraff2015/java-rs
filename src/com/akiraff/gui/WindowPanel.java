package com.akiraff.gui;

import com.akiraff.gui.barrows.*;
import com.akiraff.gui.components.SettingMenu;
import com.akiraff.user.Session;

import javax.swing.*;
import java.awt.*;

public class WindowPanel implements Runnable{
    private Session session = null;

    private JFrame frame = new JFrame("Barrows Loot Item Log");

    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints c = new GridBagConstraints();

    // Buttons
    private JButton addBtn = new JButton("Add");
    private JButton getDataBtn = new JButton("Retrieve Data");

    // Input
    private JTextField mindRuneInput = new JTextField("", 3);
    private JTextField chaosRuneInput = new JTextField("", 3);
    private JTextField deathRuneInput = new JTextField("", 3);
    private JTextField bloodRuneInput = new JTextField("", 3);

    // Labels for the form
    private JLabel mindRuneLabel = new JLabel("Mind Rune: ");
    private JLabel chaosRuneLabel = new JLabel("Chaos Rune: ");
    private JLabel deathRuneLabel = new JLabel("Death Rune: ");
    private JLabel bloodRuneLabel = new JLabel("Blood Rune: ");

    // Tabs
    private JTabbedPane tabPane = new JTabbedPane();

    private AhrimPanel ahrimPanel = AhrimPanel.getInstance();
    private AkrisaePanel akrisaePanel = AkrisaePanel.getInstance();
    private DharokPanel dharokPanel = DharokPanel.getInstance();
    private GuthanPanel guthanPanel = GuthanPanel.getInstance();
    private KarilPanel karilPanel = KarilPanel.getInstance();
    private LinzaPanel linzaPanel = LinzaPanel.getInstance();
    private ToragPanel toragPanel = ToragPanel.getInstance();
    private VeracPanel veracPanel = VeracPanel.getInstance();

    //Get text

    public WindowPanel(Session session) {
        this.session = session;
        run();
    }

    public void run() {
        if (session.getLogin()) {
            addBtn.addActionListener(e -> System.out.println("hello world"));

            getDataBtn.addActionListener(e ->  {
                BarrowsLog a = BarrowsLog.getInstance();
                a.getItem();
            });
            addComponents();
        } else {
            setWindow(false);
        }
    }

    private void addComponents() {
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 0, 0, 0);
        panel.add(mindRuneLabel, c);

        // Mind rune input
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 0, 0, 0);
        panel.add(mindRuneInput, c);

        // Chaos rune label
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(5, 0, 0, 0);
        panel.add(chaosRuneLabel, c);

        // Chaos rune input
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 0, 0, 0);
        panel.add(chaosRuneInput, c);

        // Death rune label
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5, 0, 0, 0);
        panel.add(deathRuneLabel, c);

        // Death rune input
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5, 0, 0, 0);
        panel.add(deathRuneInput, c);

        // Blood rune label
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(5, 0, 0, 0);
        panel.add(bloodRuneLabel, c);

        // Blood rune input
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(5, 0, 0, 0);
        panel.add(bloodRuneInput, c);

        // Add button
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(10, 10, 0, 0);
        panel.add(addBtn, c);

        // Get data button
        c.gridx = 1;
        c.gridy = 5;
        panel.add(getDataBtn, c);

        // Menu
        frame.setJMenuBar(new SettingMenu());

        // Tabs
        tabPane.addTab("Rune", panel);
        tabPane.addTab("Ahrim", ahrimPanel);
        tabPane.addTab("Akrisae", akrisaePanel);
        tabPane.addTab("Guthan", guthanPanel);
        tabPane.addTab("Karil",  karilPanel);
        tabPane.addTab("Torag", toragPanel);
        tabPane.addTab("Linza", linzaPanel);
        tabPane.addTab("Dharok", dharokPanel);
        tabPane.addTab("Verac", veracPanel);
        tabPane.addTab("Log", new LogPanel());

        frame.add(tabPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    private void setWindow(int width, int height, boolean p) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.add(panel);
        frame.setVisible(p);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void setWindow(boolean p) {
        frame.setVisible(p);
    }
}
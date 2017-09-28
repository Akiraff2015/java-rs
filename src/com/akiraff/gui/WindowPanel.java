package com.akiraff.gui;

import com.akiraff.gui.barrows.*;
import com.akiraff.gui.components.SettingMenu;
import com.akiraff.user.Session;
import com.akiraff.Contact;

import javax.swing.*;
import java.awt.*;

import java.sql.*;
import java.util.ArrayList;

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

    // Menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu settingsMenu = new JMenu("Settings");

    public WindowPanel(Session session) {
        this.session = session;
        run();
    }

    public void run() {
        if (session.getLogin()) {
            addBtn.addActionListener(e -> System.out.println("hello world"));

            getDataBtn.addActionListener(e -> selectAll());
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
        tabPane.addTab("Ahrim", new AhrimPanel());
        tabPane.addTab("Akrisae", new AkrisaePanel());
        tabPane.addTab("Guthan", new GuthanPanel());
        tabPane.addTab("Karil",  new KarilPanel());
        tabPane.addTab("Torag", new ToragPanel());
        tabPane.addTab("Linza", new LinzaPanel());
        tabPane.addTab("Dharok", new DharokPanel());
        tabPane.addTab("Verac", new VeracPanel());

        frame.add(tabPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    private void selectAll() {
        String sql = "SELECT * FROM contacts";
        ArrayList<Contact> listContacts = new ArrayList<>();

        try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listContacts.add(new Contact(rs.getString("name"), rs.getInt("phone"), rs.getString("email")));
            }

            for (Contact c : listContacts) {
                System.out.println("[CONSOLE]: " + c.getName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insert(String name, Integer phone, String email) {
        String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, phone);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("[CONSOLE]: Data inserted.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        String url = "jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\project\\test.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
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
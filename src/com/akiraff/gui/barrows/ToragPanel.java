package com.akiraff.gui.barrows;

import com.akiraff.api.Item;
import com.akiraff.gui.BarrowsLog;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

// Torag the corrupted
public class ToragPanel extends JPanel {

    private static ToragPanel ourInstance = new ToragPanel();

    private JLabel helmLabel = new JLabel("Torag's helm: ");
    private JLabel platebodyLabel = new JLabel("Torag's platebody: ");
    private JLabel platelegsLabel = new JLabel("Torag's platelegs: ");
    private JLabel hammerLabel = new JLabel("Torag's hammer: ");

    private JTextField helmInput = new JTextField("", 3);
    private JTextField platebodyInput = new JTextField("", 3);
    private JTextField platelegsInput = new JTextField("", 3);
    private JTextField hammerInput = new JTextField("", 3);

    private ImageIcon helmImage = new ImageIcon(getClass().getResource("../../img/torag/helm.png"));
    private ImageIcon platebodyImage = new ImageIcon(getClass().getResource("../../img/torag/platebody.png"));
    private ImageIcon platelegsImage = new ImageIcon(getClass().getResource("../../img/torag/platelegs.png"));
    private ImageIcon hammerImage = new ImageIcon(getClass().getResource("../../img/torag/hammer.png"));

    private JLabel helmImageLabel = new JLabel(helmImage);
    private JLabel platebodyImageLabel = new JLabel(platebodyImage);
    private JLabel platelegsImageLabel = new JLabel(platelegsImage);
    private JLabel hammerImageLabel = new JLabel(hammerImage);

    private int toragListId[] = {4745, 4749, 4751, 4747};
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    private ToragPanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    public static ToragPanel getInstance() {
        return ourInstance;
    }

    public void getTextInput() {
        BarrowsLog log = BarrowsLog.getInstance();

        if (!helmInput.getText().equals("")) {
            log.addItem(itemList.get(0), Integer.parseInt(helmInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(0).getName() + " " + helmInput.getText() + "x to loot.");
        }

        if (!platebodyInput.getText().equals("")) {
            log.addItem(itemList.get(1), Integer.parseInt(platebodyInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(1).getName() + " " + platebodyInput.getText() + "x to loot.");
        }

        if (!platelegsInput.getText().equals("")) {
            log.addItem(itemList.get(2), Integer.parseInt(platelegsInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(2).getName() + " " + platelegsInput.getText() + "x to loot.");
        }

        if (!hammerInput.getText().equals("")) {
            log.addItem(itemList.get(3), Integer.parseInt(hammerInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(3).getName() + " " + hammerInput.getText() + "x to loot.");

        } else {
            System.out.println("[CONSOLE]: Torag's loot is empty!");
        }
    }

    public void resetText() {
        helmInput.setText("");
        platebodyInput.setText("");
        platelegsInput.setText("");
        hammerInput.setText("");
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i : toragListId) {
                pstmt.setInt(1, i);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    itemList.add(new Item(rs.getInt("id"), rs.getDouble("price"), rs.getString("short_price"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addComponents() {
        //image helm
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 0, 0);
        c.anchor = GridBagConstraints.NORTHWEST;
        add(helmImageLabel, c);
        helmImageLabel.setToolTipText(itemList.get(0).getShortPrice());

        //label helm
        c.gridx = 1;
        c.gridy = 0;
        add(helmLabel, c);

        //input helm
        c.gridx = 2;
        c.gridy = 0;
        add(helmInput, c);

        //image platebody
        c.gridx = 0;
        c.gridy = 1;
        add(platebodyImageLabel, c);
        platebodyImageLabel.setToolTipText(itemList.get(1).getShortPrice());

        //label platebody
        c.gridx = 1;
        c.gridy = 1;
        add(platebodyLabel, c);

        //input platebody
        c.gridx = 2;
        c.gridy = 1;
        add(platebodyInput, c);

        //image platelegs
        c.gridx = 0;
        c.gridy = 2;
        add(platelegsImageLabel, c);
        platelegsImageLabel.setToolTipText(itemList.get(2).getShortPrice());

        //label platelegs
        c.gridx = 1;
        c.gridy = 2;
        add(platelegsLabel, c);

        //input platelegs
        c.gridx = 2;
        c.gridy = 2;
        add(platelegsInput, c);

        //image hammer
        c.gridx = 0;
        c.gridy = 3;
        add(hammerImageLabel, c);
        hammerImageLabel.setToolTipText(itemList.get(3).getShortPrice());

        //label hammer
        c.gridx = 1;
        c.gridy = 3;
        add(hammerLabel, c);

        //input hammer
        c.gridx = 2;
        c.gridy = 3;
        add(hammerInput, c);

        c.weightx = 1;
        c.weighty = 1;
        add(new JLabel(""), c);
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
}

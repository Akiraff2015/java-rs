package com.akiraff.gui.barrows;

import com.akiraff.api.Item;
import com.akiraff.gui.BarrowsLog;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

//Linza the disgraced
public class LinzaPanel extends JPanel{

    private static LinzaPanel ourInstance = new LinzaPanel();

    private JLabel helmLabel = new JLabel("Linza's helm: ");
    private JLabel cuirassLabel = new JLabel("Linza's cuirass: ");
    private JLabel greavesLabel = new JLabel("Linza's greaves: ");
    private JLabel hammerLabel = new JLabel("Linza's hammer: ");
    private JLabel shieldLabel = new JLabel("Linza's shield: ");

    private JTextField helmInput = new JTextField("", 3);
    private JTextField cuirassInput = new JTextField("", 3);
    private JTextField greavesInput = new JTextField("", 3);
    private JTextField hammerInput = new JTextField("", 3);
    private JTextField shieldInput = new JTextField("", 3);

    private ImageIcon helmImage = new ImageIcon(getClass().getResource("../../img/linza/helm.png"));
    private ImageIcon cuirassImage = new ImageIcon(getClass().getResource("../../img/linza/cuirass.png"));
    private ImageIcon greavesImage = new ImageIcon(getClass().getResource("../../img/linza/greaves.png"));
    private ImageIcon hammerImage = new ImageIcon(getClass().getResource("../../img/linza/hammer.png"));
    private ImageIcon shieldImage = new ImageIcon(getClass().getResource("../../img/linza/shield.png"));

    private JLabel helmImageLabel = new JLabel(helmImage);
    private JLabel cuirassImageLabel = new JLabel(cuirassImage);
    private JLabel greavesImageLabel = new JLabel(greavesImage);
    private JLabel hammerImageLabel = new JLabel(hammerImage);
    private JLabel shieldImageLabel = new JLabel(shieldImage);

    private int linzaListId[] = {37433, 37437, 37441, 37445, 37449};
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    public static LinzaPanel getInstance() {
        return ourInstance;
    }

    public void getTextInput() {
        BarrowsLog log = BarrowsLog.getInstance();

        if (!helmInput.getText().equals("")) {
            log.addItem(itemList.get(0), Integer.parseInt(helmInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(0).getName() + " " + helmInput.getText() + "x to loot.");
        }

        if (!cuirassInput.getText().equals("")) {
            log.addItem(itemList.get(1), Integer.parseInt(cuirassInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(1).getName() + " " + cuirassInput.getText() + "x to loot.");
        }

        if (!greavesInput.getText().equals("")) {
            log.addItem(itemList.get(2), Integer.parseInt(greavesInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(2).getName() + " " + greavesInput.getText() + "x to loot.");
        }

        if (!hammerInput.getText().equals("")) {
            log.addItem(itemList.get(3), Integer.parseInt(hammerInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(3).getName() + " " + hammerInput.getText() + "x to loot.");
        }

        if (!shieldInput.getText().equals("")) {
            log.addItem(itemList.get(4), Integer.parseInt(shieldInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(4).getName() + " " + shieldInput.getText() + "x to loot.");

        } else {
            System.out.println("[CONSOLE]: Linza's loot is empty!");
        }
    }

    public void resetText() {
        helmInput.setText("");
        cuirassInput.setText("");
        greavesInput.setText("");
        hammerInput.setText("");
        shieldInput.setText("");
    }

    private LinzaPanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i : linzaListId) {
                pstmt.setInt(1, i);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    itemList.add(new Item(rs.getInt("id"), rs.getDouble("price"), rs.getString("short_price"), rs.getString("name")));
                }
            }
        } catch(SQLException e) {
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

        //image cuirass
        c.gridx = 0;
        c.gridy = 1;
        add(cuirassImageLabel, c);
        cuirassImageLabel.setToolTipText(itemList.get(1).getShortPrice());

        //label cuirass
        c.gridx = 1;
        c.gridy = 1;
        add(cuirassLabel, c);

        //input cuirass
        c.gridx = 2;
        c.gridy = 1;
        add(cuirassInput, c);

        //image greaves
        c.gridx = 0;
        c.gridy = 2;
        add(greavesImageLabel, c);
        greavesImageLabel.setToolTipText(itemList.get(2).getShortPrice());

        //label greaves
        c.gridx = 1;
        c.gridy = 2;
        add(greavesLabel, c);

        //input greaves
        c.gridx = 2;
        c.gridy = 2;
        add(greavesInput, c);

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

        //image shield
        c.gridx = 0;
        c.gridy = 4;
        add(shieldImageLabel, c);

        //label shield
        c.gridx = 1;
        c.gridy = 4;
        add(shieldLabel, c);

        //input shield
        c.gridx = 2;
        c.gridy = 4;
        add(shieldInput, c);

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

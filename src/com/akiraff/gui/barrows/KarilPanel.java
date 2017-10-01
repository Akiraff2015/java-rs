package com.akiraff.gui.barrows;

import com.akiraff.api.Item;
import com.akiraff.gui.BarrowsLog;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

// Karil the tainted
public class KarilPanel extends JPanel {

    private static KarilPanel ourInstance = new KarilPanel();

    private JLabel coifLabel = new JLabel("Karil's coif: ");
    private JLabel topLabel = new JLabel("Karil's top: ");
    private JLabel skirtLabel = new JLabel("Karil's skirt: ");
    private JLabel crossbowLabel = new JLabel("Karil's crossbow: ");
    private JLabel pistolCrossbowLabel = new JLabel("Karil's pistol crossbow: ");
    private JLabel offPistolCrossBowLabel = new JLabel("Karil's off-hand pistol crossbow: ");

    private JTextField coifInput = new JTextField("", 3);
    private JTextField topInput = new JTextField("", 3);
    private JTextField skirtInput = new JTextField("", 3);
    private JTextField crossbowInput = new JTextField("", 3);
    private JTextField pistolCrossbowInput = new JTextField("", 3);
    private JTextField offPistolCrossbowInput = new JTextField("", 3);

    private ImageIcon coifImage = new ImageIcon(getClass().getResource("../../img/karil/coif.png"));
    private ImageIcon topImage = new ImageIcon(getClass().getResource("../../img/karil/top.png"));
    private ImageIcon skirtImage = new ImageIcon(getClass().getResource("../../img/karil/skirt.png"));
    private ImageIcon crossbowImage = new ImageIcon(getClass().getResource("../../img/karil/crossbow.png"));
    private ImageIcon pistolCrossbowImage = new ImageIcon(getClass().getResource("../../img/karil/pistolCrossbow.png"));
    private ImageIcon offPistolCrossbowImage = new ImageIcon(getClass().getResource("../../img/karil/offPistolCrossbow.png"));

    private JLabel coifImageLabel = new JLabel(coifImage);
    private JLabel topImageLabel = new JLabel(topImage);
    private JLabel skirtImageLabel = new JLabel(skirtImage);
    private JLabel crossbowImageLabel = new JLabel(crossbowImage);
    private JLabel pistolCrossbowImageLabel = new JLabel(pistolCrossbowImage);
    private JLabel offPistolCrossBowImageLabel = new JLabel(offPistolCrossbowImage);

    private int karilListId[] = {4732, 4736, 4738, 4734, 25918, 25895};
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    private KarilPanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    public void getTextInput() {
        BarrowsLog log = BarrowsLog.getInstance();

        if (!coifInput.getText().equals("")) {
            log.addItem(itemList.get(0), Integer.parseInt(coifInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(0).getName() + " " + coifInput.getText() + "x to loot.");
        }

        if (!topInput.getText().equals("")) {
            log.addItem(itemList.get(1), Integer.parseInt(topInput.getText()));
            System.out.println("[CONSOLE]: Added" + itemList.get(1).getName() + " " + topInput.getText() + "x to loot.");
        }

        if (!skirtInput.getText().equals("")) {
            log.addItem(itemList.get(2), Integer.parseInt(skirtInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(2).getName() + " " + skirtInput.getText() + "x to loot.");
        }

        if (!crossbowInput.getText().equals("")) {
            log.addItem(itemList.get(3), Integer.parseInt(crossbowInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(3).getName() + " " + crossbowInput.getText() + "x to loot.");
        }

        if (!pistolCrossbowInput.getText().equals("")) {
            log.addItem(itemList.get(4), Integer.parseInt(pistolCrossbowInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(4).getName() + " " + pistolCrossbowInput.getText() + "x to loot.");
        }

        if (!offPistolCrossbowInput.getText().equals("")) {
            log.addItem(itemList.get(5), Integer.parseInt(offPistolCrossbowInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(5).getName() + " " + offPistolCrossbowInput.getText() + "x to loot.");

        } else {
            System.out.println("[CONSOLE]: Karil's loot is empty!");
        }
    }

    public void resetText() {
        coifInput.setText("");
        topInput.setText("");
        skirtInput.setText("");
        crossbowInput.setText("");
        pistolCrossbowInput.setText("");
        offPistolCrossbowInput.setText("");
    }

    public static KarilPanel getInstance() {
        return ourInstance;
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i : karilListId) {
                pstmt.setInt(1, i);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    itemList.add(new Item(rs.getInt("id"), rs.getDouble("price"), rs.getString("short_price"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addComponents() {
        //image coif
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 0, 0);
        c.anchor = GridBagConstraints.NORTHWEST;
        add(coifImageLabel, c);
        coifImageLabel.setToolTipText(itemList.get(0).getShortPrice());

        //label coif
        c.gridx = 1;
        c.gridy = 0;
        add(coifLabel, c);

        //input coif
        c.gridx = 2;
        c.gridy = 0;
        add(coifInput, c);

        //image top
        c.gridx = 0;
        c.gridy = 1;
        add(topImageLabel, c);
        topImageLabel.setToolTipText(itemList.get(1).getShortPrice());

        //label top
        c.gridx = 1;
        c.gridy = 1;
        add(topLabel, c);

        //input top
        c.gridx = 2;
        c.gridy = 1;
        add(topInput, c);

        //image skirt
        c.gridx = 0;
        c.gridy = 2;
        add(skirtImageLabel, c);
        skirtImageLabel.setToolTipText(itemList.get(2).getShortPrice());

        //label skirt
        c.gridx = 1;
        c.gridy = 2;
        add(skirtLabel, c);

        //input skirt
        c.gridx = 2;
        c.gridy = 2;
        add(skirtInput, c);

        //image crossbow
        c.gridx = 0;
        c.gridy = 3;
        add(crossbowImageLabel, c);
        crossbowImageLabel.setToolTipText(itemList.get(3).getShortPrice());

        //label crossbow
        c.gridx = 1;
        c.gridy = 3;
        add(crossbowLabel, c);

        //input crossbow
        c.gridx = 2;
        c.gridy = 3;
        add(crossbowInput, c);

        //image pistol crossbow
        c.gridx = 0;
        c.gridy = 4;
        add(pistolCrossbowImageLabel, c);
        pistolCrossbowImageLabel.setToolTipText(itemList.get(4).getShortPrice());

        //label pistol crossbow
        c.gridx = 1;
        c.gridy = 4;
        add(pistolCrossbowLabel, c);

        //input pistol crossbow
        c.gridx = 2;
        c.gridy = 4;
        add(pistolCrossbowInput, c);

        //image off-hand pistol crossbow
        c.gridx = 0;
        c.gridy = 5;
        add(offPistolCrossBowImageLabel, c);
        offPistolCrossBowImageLabel.setToolTipText(itemList.get(5).getShortPrice());

        //label off-hand pistol crossbow
        c.gridx = 1;
        c.gridy = 5;
        add(offPistolCrossBowLabel, c);

        //input off-hand pistol crossbow
        c.gridx = 2;
        c.gridy = 5;
        add(offPistolCrossbowInput, c);

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

package com.akiraff.gui.barrows;

import com.akiraff.api.Item;
import com.akiraff.gui.BarrowsLog;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

// Guthan the infested
public class GuthanPanel extends JPanel {
    private static GuthanPanel ourInstance = new GuthanPanel();

    private JLabel helmLabel = new JLabel("Guthan's helm: ");
    private JLabel platebodyLabel = new JLabel("Guthan's platebody: ");
    private JLabel chainskirtLabel = new JLabel("Guthan's chainskirt: ");
    private JLabel warspearLabel = new JLabel("Guthan's warspear: ");

    private JTextField helmInput = new JTextField("", 3);
    private JTextField platebodyInput = new JTextField("", 3);
    private JTextField chainskirtInput = new JTextField("", 3);
    private JTextField warspearInput = new JTextField("", 3);

    private ImageIcon helmImage = new ImageIcon(getClass().getResource("../../img/guthan/helm.png"));
    private ImageIcon platebodyImage = new ImageIcon(getClass().getResource("../../img/guthan/platebody.png"));
    private ImageIcon chainskirtImage = new ImageIcon(getClass().getResource("../../img/guthan/chainskirt.png"));
    private ImageIcon warspearImage = new ImageIcon(getClass().getResource("../../img/guthan/warspear.png"));

    private JLabel helmImageLabel = new JLabel(helmImage);
    private JLabel platebodyImageLabel = new JLabel(platebodyImage);
    private JLabel chainskirtImageLabel = new JLabel(chainskirtImage);
    private JLabel warspearImageLabel = new JLabel(warspearImage);

    private int guthanListId[] = {4724, 4728, 4730, 4726};
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    private GuthanPanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    public static GuthanPanel getInstance() {
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

        if (!chainskirtInput.getText().equals("")) {
            log.addItem(itemList.get(2), Integer.parseInt(chainskirtInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(2).getName() + " " + chainskirtInput.getText() + "x to loot.");
        }

        if (!warspearInput.getText().equals("")) {
            log.addItem(itemList.get(3), Integer.parseInt(warspearInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(3).getName() + " " + warspearInput.getText() + "x to loot.");

        } else {
          System.out.println("[CONSOLE]: Guthan's loot is empty!");
        }
    }

    public void resetText() {
        helmInput.setText("");
        platebodyInput.setText("");
        chainskirtInput.setText("");
        warspearInput.setText("");
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i : guthanListId) {
                pstmt.setInt(1, i);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
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

        //image chainskirt
        c.gridx = 0;
        c.gridy = 2;
        add(chainskirtImageLabel, c);
        chainskirtImageLabel.setToolTipText(itemList.get(2).getShortPrice());

        //label chainskirt
        c.gridx = 1;
        c.gridy = 2;
        add(chainskirtLabel, c);

        //input chainskirt
        c.gridx = 2;
        c.gridy = 2;
        add(chainskirtInput, c);

        //image warspear
        c.gridx = 0;
        c.gridy = 3;
        add(warspearImageLabel, c);
        warspearImageLabel.setToolTipText(itemList.get(3).getShortPrice());

        //label warspear
        c.gridx = 1;
        c.gridy = 3;
        add(warspearLabel, c);

        //input warspear
        c.gridx = 2;
        c.gridy = 3;
        add(warspearInput, c);

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

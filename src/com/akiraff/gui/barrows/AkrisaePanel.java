package com.akiraff.gui.barrows;

import com.akiraff.api.Item;
import com.akiraff.gui.BarrowsLog;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

//Akrisae the Doomed
public class AkrisaePanel extends JPanel {

    private static AkrisaePanel ourInstance = new AkrisaePanel();

    private JLabel hoodLabel = new JLabel("Akrisae's hood: ");
    private JLabel robeTopLabel = new JLabel("Akrisae's robe top: ");
    private JLabel robeSkirtLabel = new JLabel("Akrisae's robe skirt: ");
    private JLabel warMaceLabel = new JLabel("Akrisae's war mace: ");

    private JTextField hoodInput = new JTextField("", 3);
    private JTextField robeTopInput = new JTextField("", 3);
    private  JTextField robeSkirtInput = new JTextField("", 3);
    private JTextField warMaceInput = new JTextField("", 3);

    private ImageIcon hoodImage = new ImageIcon(getClass().getResource("../../img/akrisae/hood.png"));
    private ImageIcon robeTopImage = new ImageIcon(getClass().getResource("../../img/akrisae/robeTop.png"));
    private ImageIcon robeSkirtImage = new ImageIcon(getClass().getResource("../../img/akrisae/robeSkirt.png"));
    private ImageIcon warMaceImage = new ImageIcon(getClass().getResource("../../img/akrisae/warMace.png"));

    private JLabel hoodImageLabel = new JLabel(hoodImage);
    private JLabel robeTopImageLabel = new JLabel(robeTopImage);
    private JLabel robeSkirtImageLabel = new JLabel(robeSkirtImage);
    private JLabel warMaceImageLabel = new JLabel(warMaceImage);

    private int akrisaeListId[] = {21736, 21752, 21760, 21744};
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    public static AkrisaePanel getInstance() {
        return ourInstance;
    }

    private AkrisaePanel() {
        setBorder(BorderFactory.createTitledBorder("Akrisae the Doomed"));
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    public void getTextInput() {
        BarrowsLog log = BarrowsLog.getInstance();

        if (!hoodInput.getText().equals("")) {
            log.addItem(itemList.get(0), Integer.parseInt(hoodInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(0).getName() + " " + hoodInput.getText() + "x to loot.");
        }

        if (!robeTopInput.getText().equals("")) {
            log.addItem(itemList.get(1), Integer.parseInt(robeTopInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(1).getName() + " " + robeTopInput.getText() + "x to loot.");
        }

        if (!robeSkirtInput.getText().equals("")) {
            log.addItem(itemList.get(2), Integer.parseInt(robeSkirtInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(2).getName() + " " + robeSkirtInput.getText() + "x to loot.");
        }

        if (!warMaceInput.getText().equals("")) {
            log.addItem(itemList.get(3), Integer.parseInt(warMaceInput.getText()));
            System.out.println("[CONSOLE]: Added " + itemList.get(3).getName() + " " + warMaceInput.getText() + "x to loot.");
        } else {
            System.out.println("[CONSOLE]: Akrisae's loot is empty!");
        }
    }

    public void resetText() {
        hoodInput.setText("");
        robeTopInput.setText("");
        robeSkirtInput.setText("");
        warMaceInput.setText("");
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try(Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i : akrisaeListId) {
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
        //image hood
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 0, 0);
        c.anchor = GridBagConstraints.NORTHWEST;
        add(hoodImageLabel, c);
        hoodImageLabel.setToolTipText(itemList.get(0).getShortPrice());

        //label hood
        c.gridx = 1;
        c.gridy = 0;
        add(hoodLabel, c);

        //input hood
        c.gridx = 2;
        c.gridy = 0;
        add(hoodInput, c);

        //robe top image
        c.gridx = 0;
        c.gridy = 1;
        add(robeTopImageLabel, c);
        robeTopImageLabel.setToolTipText(itemList.get(1).getShortPrice());

        //robe top label;
        c.gridx = 1;
        c.gridy = 1;
        add(robeTopLabel, c);

        //robe top input
        c.gridx = 2;
        c.gridy = 1;
        add(robeTopInput, c);

        //robe skirt image
        c.gridx = 0;
        c.gridy = 3;
        add(robeSkirtImageLabel, c);
        robeSkirtImageLabel.setToolTipText(itemList.get(2).getShortPrice());

        //robe skirt label
        c.gridx = 1;
        c.gridy = 3;
        add(robeSkirtLabel, c);

        //robe skirt input
        c.gridx = 2;
        c.gridy = 3;
        add(robeSkirtInput, c);

        //war mace image
        c.gridx = 0;
        c.gridy = 4;
        add(warMaceImageLabel, c);
        warMaceImageLabel.setToolTipText(itemList.get(3).getShortPrice());

        //war mace label
        c.gridx = 1;
        c.gridy = 4;
        add(warMaceLabel, c);

        //war mace input
        c.gridx = 2;
        c.gridy = 4;
        add(warMaceInput, c);

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

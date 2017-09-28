package com.akiraff.gui.barrows;

import com.akiraff.api.Item;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

//Akrisae the Doomed
public class AkrisaePanel extends JPanel {
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

    public AkrisaePanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try(Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < akrisaeListId.length; i++) {
                pstmt.setInt(1, akrisaeListId[i]);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString("name"));
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

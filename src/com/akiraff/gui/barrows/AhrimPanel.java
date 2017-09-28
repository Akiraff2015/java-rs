package com.akiraff.gui.barrows;

import com.akiraff.api.Item;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

// Ahrim the Blighted
public class AhrimPanel extends JPanel{

    // Labels
    private JLabel hoodLabel = new JLabel("Ahrim's hood: ");
    private JLabel robeLabel = new JLabel("Ahrim's robe top: ");
    private JLabel robeSkirtLabel = new JLabel("Ahrim's robe skirt: ");
    private JLabel wandLabel = new JLabel("Ahrim's wand: ");
    private JLabel staffLabel = new JLabel("Ahrim's staff: ");
    private JLabel bookOfMagicLabel = new JLabel("Ahrim's book of magic: ");

    // Textfield
    private JTextField hoodInput = new JTextField("", 3);
    private JTextField robeInput = new JTextField("", 3);
    private JTextField robeSkirtInput = new JTextField("", 3);
    private JTextField wandInput = new JTextField("", 3);
    private JTextField staffInput = new JTextField("", 3);
    private JTextField bookOfMagicInput = new JTextField("", 3);

    // Images + Image labels
    private ImageIcon hoodImage = new ImageIcon(getClass().getResource("../../img/ahrim/hood.png"));
    private ImageIcon robeTopImage = new ImageIcon(getClass().getResource("../../img/ahrim/robeTop.png"));
    private ImageIcon robeSkirtImage = new ImageIcon(getClass().getResource("../../img/ahrim/robeSkirt.png"));
    private ImageIcon wandImage = new ImageIcon(getClass().getResource("../../img/ahrim/wand.png"));
    private ImageIcon staffImage = new ImageIcon(getClass().getResource("../../img/ahrim/staff.png"));
    private ImageIcon bookOfMagicImage = new ImageIcon(getClass().getResource("../../img/ahrim/bookOfMagic.png"));

    private JLabel hoodImageLabel = new JLabel(hoodImage);
    private JLabel robeTopImageLabel = new JLabel(robeTopImage);
    private JLabel robeSkirtImageLabel = new JLabel(robeSkirtImage);
    private JLabel wandImageLabel = new JLabel(wandImage);
    private JLabel staffImageLabel = new JLabel(staffImage);
    private JLabel bookOfMagicImageLabel = new JLabel(bookOfMagicImage);

    private int ahrimListId[] = {4708, 4712, 4714, 25652, 4710, 25672};
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    public AhrimPanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i : ahrimListId) {
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

        //image robe
        c.gridx = 0;
        c.gridy = 1;
        add(robeTopImageLabel, c);
        robeTopImageLabel.setToolTipText(itemList.get(1).getShortPrice());

        //label robe
        c.gridx = 1;
        c.gridy = 1;
        add(robeLabel, c);

        //input robe
        c.gridx = 2;
        c.gridy = 1;
        add(robeInput, c);

        //robe skirt image
        c.gridx = 0;
        c.gridy = 2;
        add(robeSkirtImageLabel, c);
        robeSkirtImageLabel.setToolTipText(itemList.get(2).getShortPrice());

        //robe skirt label
        c.gridx = 1;
        c.gridy = 2;
        add(robeSkirtLabel, c);

        //robe skirt input
        c.gridx = 2;
        c.gridy = 2;
        add(robeSkirtInput, c);

        //wand image
        c.gridx = 0;
        c.gridy = 3;
        add(wandImageLabel, c);
        wandImageLabel.setToolTipText(itemList.get(3).getShortPrice());

        //wand label
        c.gridx = 1;
        c.gridy = 3;
        add(wandLabel, c);

        //wand input
        c.gridx = 2;
        c.gridy = 3;
        add(wandInput, c);

        //staff image
        c.gridx = 0;
        c.gridy = 4;
        add(staffImageLabel, c);
        staffImageLabel.setToolTipText(itemList.get(4).getShortPrice());

        //staff label
        c.gridx = 1;
        c.gridy = 4;
        add(staffLabel, c);

        //staff input
        c.gridx = 2;
        c.gridy = 4;
        add(staffInput, c);

        //book of magic image
        c.gridx = 0;
        c.gridy = 5;
        add(bookOfMagicImageLabel, c);

        //book of magic label
        c.gridx = 1;
        c.gridy = 5;
        add(bookOfMagicLabel, c);
        bookOfMagicImageLabel.setToolTipText(itemList.get(5).getShortPrice());

        //book of magic input
        c.gridx = 2;
        c.gridy = 5;
        add(bookOfMagicInput, c);

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

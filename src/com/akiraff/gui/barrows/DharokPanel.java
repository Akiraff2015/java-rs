package com.akiraff.gui.barrows;

import com.akiraff.api.Item;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

// Dharok the wretched
public class DharokPanel extends JPanel {
    private JLabel helmLabel = new JLabel("Dharok's helm: ");
    private JLabel platebodyLabel = new JLabel("Dharok's platebody: ");
    private JLabel platelegsLabel = new JLabel("Dharok's platelegs: ");
    private JLabel greataxeLabel = new JLabel("Dharok's greataxe: ");

    private JTextField helmInput = new JTextField("", 3);
    private JTextField platebodyInput = new JTextField("", 3);
    private JTextField platelegsInput = new JTextField("", 3);
    private JTextField greataxeInput = new JTextField("", 3);

    private ImageIcon helmImage = new ImageIcon(getClass().getResource("../../img/dharok/helm.png"));
    private ImageIcon platebodyImage = new ImageIcon(getClass().getResource("../../img/dharok/platebody.png"));
    private ImageIcon platelegsImage = new ImageIcon(getClass().getResource("../../img/dharok/platelegs.png"));
    private ImageIcon greataxeImage = new ImageIcon(getClass().getResource("../../img/dharok/greataxe.png"));

    private JLabel helmImageLabel = new JLabel(helmImage);
    private JLabel platebodyImageLabel = new JLabel(platebodyImage);
    private JLabel platelegsImageLabel = new JLabel(platelegsImage);
    private JLabel greataxeImageLabel = new JLabel(greataxeImage);

    private int dharokListId[] = {4716, 4720, 4722, 4718};
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    public DharokPanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i : dharokListId) {
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

        //label input
        c.gridx = 2;
        c.gridy = 0;
        add(helmInput, c);

        //platebody image
        c.gridx = 0;
        c.gridy = 1;
        add(platebodyImageLabel, c);
        platebodyImageLabel.setToolTipText(itemList.get(1).getShortPrice());

        //platebody label
        c.gridx = 1;
        c.gridy = 1;
        add(platebodyLabel, c);

        //platebody input
        c.gridx = 2;
        c.gridy = 1;
        add(platebodyInput, c);

        //platelegs image
        c.gridx = 0;
        c.gridy = 2;
        add(platelegsImageLabel, c);
        platelegsImageLabel.setToolTipText(itemList.get(2).getShortPrice());

        //platelgs label
        c.gridx = 1;
        c.gridy = 2;
        add(platelegsLabel, c);

        //platelegs input
        c.gridx = 2;
        c.gridy = 2;
        add(platelegsInput, c);

        //greataxe image
        c.gridx = 0;
        c.gridy = 3;
        add(greataxeImageLabel, c);
        greataxeImageLabel.setToolTipText(itemList.get(3).getShortPrice());

        //greataxe label
        c.gridx = 1;
        c.gridy = 3;
        add(greataxeLabel, c);

        //greataxe input
        c.gridx = 2;
        c.gridy = 3;
        add(greataxeInput, c);

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

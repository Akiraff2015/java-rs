package com.akiraff.gui.barrows;

import com.akiraff.api.Item;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

//Verac the defiled
public class VeracPanel extends JPanel{

    private JLabel helmLabel = new JLabel("Verac's helm: ");
    private JLabel brassardLabel = new JLabel("Verac's brassard: ");
    private JLabel plateskirtLabel = new JLabel("Verac's plateskirt: ");
    private JLabel flailLabel = new JLabel("Verac's flail: ");

    private JTextField helmInput = new JTextField("", 3);
    private JTextField brassardInput = new JTextField("", 3);
    private JTextField plateskirtInput = new JTextField("", 3);
    private JTextField flailInput = new JTextField("", 3);

    private ImageIcon helmImage = new ImageIcon(getClass().getResource("../../img/verac/helm.png"));
    private ImageIcon brassardImage = new ImageIcon(getClass().getResource("../../img/verac/brassard.png"));
    private ImageIcon plateskirtImage = new ImageIcon(getClass().getResource("../../img/verac/plateskirt.png"));
    private ImageIcon flailImage = new ImageIcon(getClass().getResource("../../img/verac/flail.png"));

    private JLabel helmImageLabel = new JLabel(helmImage);
    private JLabel brassardImageLabel = new JLabel(brassardImage);
    private JLabel plateskirtImageLabel = new JLabel(plateskirtImage);
    private JLabel flailImageLabel = new JLabel(flailImage);

    private int veractListId[] = {4753, 4757, 4759, 4755};
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    public VeracPanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i : veractListId) {
                pstmt.setInt(1, i);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString("name"));
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

        //image brassard
        c.gridx = 0;
        c.gridy = 1;
        add(brassardImageLabel, c);
        brassardImageLabel.setToolTipText(itemList.get(1).getShortPrice());

        //label brassard
        c.gridx = 1;
        c.gridy = 1;
        add(brassardLabel, c);

        //input brassard
        c.gridx = 2;
        c.gridy = 1;
        add(brassardInput, c);

        //image plateskirt
        c.gridx = 0;
        c.gridy = 2;
        add(plateskirtImageLabel, c);
        plateskirtImageLabel.setToolTipText(itemList.get(2).getShortPrice());

        //label plateskirt
        c.gridx = 1;
        c.gridy = 2;
        add(plateskirtLabel, c);

        //input plateskirt
        c.gridx = 2;
        c.gridy = 2;
        add(plateskirtInput, c);

        //image flail
        c.gridx = 0;
        c.gridy = 3;
        add(flailImageLabel, c);
        flailImageLabel.setToolTipText(itemList.get(3).getShortPrice());

        //label flail
        c.gridx = 1;
        c.gridy = 3;
        add(flailLabel, c);

        //input flail
        c.gridx = 2;
        c.gridy = 3;
        add(flailInput, c);

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

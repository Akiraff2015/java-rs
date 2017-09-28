package com.akiraff.gui.barrows;

import com.akiraff.api.Item;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

// Guthan the infested
public class GuthanPanel extends JPanel {
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

    private int guthanListId;
    private ArrayList<Item> itemList = new ArrayList<>();

    private GridBagConstraints c = new GridBagConstraints();

    public GuthanPanel() {
        setLayout(new GridBagLayout());
        getInfo();
        addComponents();
    }

    private void getInfo() {
        String sql = "SELECT * FROM BarrowsTable WHERE id = ?";
    }

    private void addComponents() {
        //image helm
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 0, 0);
        c.anchor = GridBagConstraints.NORTHWEST;
        add(helmImageLabel, c);

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

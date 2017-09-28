package com.akiraff.gui.barrows;

import javax.swing.*;
import java.awt.*;

//Linza the disgraced
public class LinzaPanel extends JPanel{

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

    private GridBagConstraints c = new GridBagConstraints();

    public LinzaPanel() {
        setLayout(new GridBagLayout());
        addComponents();
    }

    private void addComponents() {
        //image helm
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 0, 0);
        c.anchor = GridBagConstraints.LINE_END;
        add(helmImageLabel, c);

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
    }
}

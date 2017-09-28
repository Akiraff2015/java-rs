package com.akiraff.gui.barrows;

import javax.swing.*;
import java.awt.*;

// Karil the tainted
public class KarilPanel extends JPanel {

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

    private GridBagConstraints c = new GridBagConstraints();

    public KarilPanel() {
        setLayout(new GridBagLayout());
        addComponents();
    }

    private void addComponents() {
        //image coif
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 0, 0);
        c.anchor = GridBagConstraints.LINE_END;
        add(coifImageLabel, c);

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

        //label off-hand pistol crossbow
        c.gridx = 1;
        c.gridy = 5;
        add(offPistolCrossBowLabel, c);

        //input off-hand pistol crossbow
        c.gridx = 2;
        c.gridy = 5;
        add(offPistolCrossbowInput, c);


    }
}

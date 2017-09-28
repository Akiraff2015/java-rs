package com.akiraff.gui.barrows;

import javax.swing.*;
import java.awt.*;

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

    private GridBagConstraints c = new GridBagConstraints();

    public VeracPanel() {
        setLayout(new GridBagLayout());
        addComponents();
    }

    private void addComponents() {
        //image helm
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 0, 0);
        add(helmImageLabel, c);

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

        //label flail
        c.gridx = 1;
        c.gridy = 3;
        add(flailLabel, c);

        //input flail
        c.gridx = 2;
        c.gridy = 3;
        add(flailInput, c);
    }
}

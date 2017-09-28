package com.akiraff.gui.barrows;

import javax.swing.*;
import java.awt.*;

// Torag the corrupted
public class ToragPanel extends JPanel {
    private JLabel helmLabel = new JLabel("Torag's helm: ");
    private JLabel platebodyLabel = new JLabel("Torag's platebody: ");
    private JLabel platelegsLabel = new JLabel("Torag's platelegs: ");
    private JLabel hammerLabel = new JLabel("Torag's hammer: ");

    private JTextField helmInput = new JTextField("", 3);
    private JTextField platebodyInput = new JTextField("", 3);
    private JTextField platelegsInput = new JTextField("", 3);
    private JTextField hammerInput = new JTextField("", 3);

    private ImageIcon helmImage = new ImageIcon(getClass().getResource("../../img/torag/helm.png"));
    private ImageIcon platebodyImage = new ImageIcon(getClass().getResource("../../img/torag/platebody.png"));
    private ImageIcon platelegsImage = new ImageIcon(getClass().getResource("../../img/torag/platelegs.png"));
    private ImageIcon hammerImage = new ImageIcon(getClass().getResource("../../img/torag/hammer.png"));

    private JLabel helmImageLabel = new JLabel(helmImage);
    private JLabel platebodyImageLabel = new JLabel(platebodyImage);
    private JLabel platelegsImageLabel = new JLabel(platelegsImage);
    private JLabel hammerImageLabel = new JLabel(hammerImage);

    private GridBagConstraints c = new GridBagConstraints();

    public ToragPanel() {
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

        //image platelegs
        c.gridx = 0;
        c.gridy = 2;
        add(platelegsImageLabel, c);

        //label platelegs
        c.gridx = 1;
        c.gridy = 2;
        add(platelegsLabel, c);

        //input platelegs
        c.gridx = 2;
        c.gridy = 2;
        add(platelegsInput, c);

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
    }
}

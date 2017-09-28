package com.akiraff.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import com.akiraff.user.Username;

public class RegisterPanel implements Runnable{
    private JFrame frame = new JFrame("Register");
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints c = new GridBagConstraints();

    // Buttons
    private JButton registerBtn = new JButton("Register");
    private JButton clearBtn = new JButton("Clear");
    private JButton backBtn = new JButton("Back");

    // Input
    private JTextField usernameInput = new JTextField(10);
    private JPasswordField passwordInput = new JPasswordField(10);
    private JPasswordField confirmPasswordInput = new JPasswordField(10);

    // Labels
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JLabel passConfirmLabel = new JLabel("Confirm Password: ");

    public RegisterPanel() {
        run();
    }

    public void run() {
        addComponents();
        btnEventListener();
        setWindow(340, 210, true);
    }

    private void setWindow(int width, int height, boolean p) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.add(panel);
        frame.setVisible(p);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void setWindow(boolean p) {
        frame.setVisible(p);
    }

    private void addComponents() {
        // Username Label
        Insets insets5 = new Insets(5, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = insets5;
        panel.add(usernameLabel, c);

        // Username Input
        c.gridx = 1;
        c.gridy = 0;
        c.insets = insets5;
        panel.add(usernameInput, c);

        // Password Label
        c.gridx = 0;
        c.gridy = 1;
        c.insets = insets5;
        panel.add(passwordLabel, c);

        // Password Input
        c.gridx = 1;
        c.gridy = 1;
        c.insets = insets5;
        panel.add(passwordInput, c);

        // Password confirm;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = insets5;
        panel.add(passConfirmLabel, c);

        // Password confirm input
        c.gridx = 1;
        c.gridy = 2;
        c.insets = insets5;
        panel.add(confirmPasswordInput, c);

        // Back button
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 0, 0, 0);
        panel.add(backBtn, c);

        // Clear button
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10, 0, 0, 0);
        panel.add(clearBtn, c);

        // Register button
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(10, 0, 0, 0);
        panel.add(registerBtn, c);
    }

    private void btnEventListener() {
        registerBtn.addActionListener(e -> {
            //System.out.println(passwordInput.getPassword() != confirmPasswordInput.getPassword());

//             If password does not matches
            if (!Arrays.equals(passwordInput.getPassword(), confirmPasswordInput.getPassword())) {
                JOptionPane.showMessageDialog(frame, "Password does match!", "Error!", JOptionPane.PLAIN_MESSAGE);
            }

            // If username is blank
            else if (usernameInput.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "Username cannot be blank!", "Error!", JOptionPane.PLAIN_MESSAGE);
            }

            // If password left blank
            else if (passwordInput.getPassword().length == 0 && confirmPasswordInput.getPassword().length == 0) {
                JOptionPane.showMessageDialog(frame, "Password cannot be blank!", "Error!", JOptionPane.PLAIN_MESSAGE);
            }

            // If password matches
            else {
                Username user = new Username();
                user.registerUsername(new String(usernameInput.getText()), new String(passwordInput.getPassword()));
                setWindow(false);
                new LoginPanel();
            }
        });

        clearBtn.addActionListener(e -> {
            usernameInput.setText("");
            passwordInput.setText("");
            confirmPasswordInput.setText("");
        });

        backBtn.addActionListener(e -> {
            setPanelVisibility();
        });
    }

    private void setPanelVisibility() {
        setWindow(false);
        new LoginPanel();
    }
}
package com.akiraff.gui;

import javax.swing.*;
import java.awt.*;
import com.akiraff.user.Session;
import com.akiraff.user.Username;

public class LoginPanel implements Runnable {
    private Session session = null;

    private JFrame frame = new JFrame("Login");
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints c = new GridBagConstraints();

    // Buttons
    private JButton loginBtn = new JButton("Login");
    private JButton registerBtn = new JButton("Register");

    // Input
    private JTextField usernameInput = new JTextField(10);
    private JPasswordField passwordInput = new JPasswordField(10);

    // Labels
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");

    public LoginPanel() {
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
        Insets insets5 = new Insets(5, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = insets5;
        panel.add(usernameLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.insets = insets5;
        panel.add(usernameInput, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = insets5;
        panel.add(passwordLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.insets = insets5;
        panel.add(passwordInput, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = insets5;
        panel.add(loginBtn, c);

        c.gridx = 1;
        c.gridy = 2;
        c.insets = insets5;
        panel.add(registerBtn, c);
    }

    private void btnEventListener() {
        Username user = new Username();

        loginBtn.addActionListener(e -> {
            user.setUsername(usernameInput.getText());
            user.setPassword(new String(passwordInput.getPassword()));

            String username = user.getUsername();
            String password = user.getPassword();

            if (user.loginUsername(username, password)) {
                this.session = new Session(user);
                setPanelVisibility();
            }
        });

        registerBtn.addActionListener(e -> {
            setWindow(false);
            new RegisterPanel();
            System.out.println("register!");
        });
    }

    private void setPanelVisibility() {
        setWindow(false);
        new WindowPanel(this.session);
    }
}
package com.akiraff.gui.components;

import com.akiraff.api.Item;
import com.akiraff.gui.BarrowsLog;
import com.akiraff.gui.barrows.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class SettingMenu extends JMenuBar{
    private JMenu menu;
    private JMenu menuUpdatePrice;

    private JMenuItem menuAdd;
    private JMenuItem menuAllPrice;
    private JMenuItem menuLogout;
    private JMenuItem menuAhrim;
    private JMenuItem menuAkrisae;
    private JMenuItem menuDharok;
    private JMenuItem menuGuthan;
    private JMenuItem menuKaril;
    private JMenuItem menuLinza;
    private JMenuItem menuTorag;
    private JMenuItem menuVerac;

    private Item barrowsList[] = {
            new Item(4708), new Item(4710), new Item(4712), new Item(4714), new Item(25652), new Item(25672), //Ahrim
            new Item(21736), new Item(21744), new Item(21752), new Item(21760), //Akrisae
            new Item(4724), new Item(4726), new Item(4728), new Item(4730), //Guthan
            new Item(4732), new Item(4734), new Item(4736), new Item(4738), new Item(25895), new Item(25918), //Karil
            new Item(4745), new Item(4747), new Item(4749), new Item(4751), //Torag
            new Item(37433), new Item(37437), new Item(37441), new Item(37445), new Item(37449), //Linza
            new Item(4716), new Item(4718), new Item(4720), new Item(4722), //Dharok
            new Item(4753), new Item(4755), new Item(4757), new Item(4759) //Verac
    };

    private Item ahrimList[] = {new Item(4708), new Item(4710), new Item(4712), new Item(4714), new Item(25652), new Item(25672)};
    private Item akrisaeList[] = {new Item(21736), new Item(21744), new Item(21752), new Item(21760)};
    private Item guthanList[] = {new Item(4724), new Item(4726), new Item(4728), new Item(4730)};
    private Item karilList[] = {new Item(4732), new Item(4734), new Item(4736), new Item(4738), new Item(25895), new Item(25918)};
    private Item toragList[] = {new Item(4745), new Item(4747), new Item(4749), new Item(4751)};
    private Item linzaList[] = {new Item(37433), new Item(37437), new Item(37441), new Item(37445), new Item(37449)};
    private Item dharokList[] = {new Item(4716), new Item(4718), new Item(4720), new Item(4722)};
    private Item veracList[] = {new Item(4753), new Item(4755), new Item(4757), new Item(4759)};

    private AhrimPanel ahrimPanel = AhrimPanel.getInstance();
    private AkrisaePanel akrisaePanel = AkrisaePanel.getInstance();
    private DharokPanel dharokPanel = DharokPanel.getInstance();
    private GuthanPanel guthanPanel = GuthanPanel.getInstance();
    private KarilPanel karilPanel = KarilPanel.getInstance();
    private VeracPanel veracPanel = VeracPanel.getInstance();



    public SettingMenu() {
        menu = new JMenu("Settings");
        menuUpdatePrice = new JMenu("Update Price");

        menuLogout = new JMenuItem("Logout");
        menuAdd = new JMenuItem("Add Loot!");

        menuAllPrice = new JMenuItem("All Items");
        menuAhrim = new JMenuItem("Ahrim's Items");
        menuAkrisae = new JMenuItem("Akrisae's Items");
        menuDharok = new JMenuItem("Dharok's Items");
        menuGuthan = new JMenuItem("Guthan's Items");
        menuKaril = new JMenuItem("Karil's Items");
        menuLinza = new JMenuItem("Linza's Items");
        menuTorag = new JMenuItem("Torag's Items");
        menuVerac = new JMenuItem("Verac's Items");

        // Submenu price
        menuUpdatePrice.add(menuAllPrice);
        menuUpdatePrice.add(menuAhrim);
        menuUpdatePrice.add(menuAkrisae);
        menuUpdatePrice.add(menuDharok);
        menuUpdatePrice.add(menuKaril);
        menuUpdatePrice.add(menuLinza);
        menuUpdatePrice.add(menuTorag);
        menuUpdatePrice.add(menuVerac);

        //Main menu
        menu.add(menuAdd);
        menu.add(menuUpdatePrice);
        menu.add(menuLogout);
        add(menu);

        addEventListenerMethod();
    }

    // Event Listener
    private void addEventListenerMethod() {
        menuAllPrice.addActionListener(e -> updatePrice(barrowsList));
        menuAhrim.addActionListener(e -> updatePrice(ahrimList));
        menuAkrisae.addActionListener(e -> updatePrice(akrisaeList));
        menuDharok.addActionListener(e -> updatePrice(dharokList));
        menuGuthan.addActionListener(e -> updatePrice(guthanList));
        menuKaril.addActionListener(e -> updatePrice(karilList));
        menuLinza.addActionListener(e -> updatePrice(linzaList));
        menuTorag.addActionListener(e -> updatePrice(toragList));
        menuVerac.addActionListener(e -> updatePrice(veracList));

        menuLogout.addActionListener(e -> logoutMenu());

        menuAdd.addActionListener(e -> {
            BarrowsLog log = BarrowsLog.getInstance();
            //Getting input from all tabs
            ahrimPanel.getTextInput();
            akrisaePanel.getTextInput();
            dharokPanel.getTextInput();
            guthanPanel.getTextInput();
            karilPanel.getTextInput();
            veracPanel.getTextInput();

            //resetting input for all tabs
            ahrimPanel.resetText();
            akrisaePanel.resetText();
            dharokPanel.resetText();
            guthanPanel.resetText();
            karilPanel.resetText();
            veracPanel.resetText();

            log.getItem();
            log.emptyItem();
        });
    }

    private void logoutMenu() {
        System.out.println("Logout");
    }

    private void updatePrice(Item item[]) {
        String sql = "UPDATE BarrowsTable SET price = ?, short_price = ?, last_updated = ? WHERE id = ? ";

        try(Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Item i : item) {
                i.request();
                pstmt.setInt(1, (int) i.getPrice());
                pstmt.setString(2, i.getShortPrice());
                pstmt.setString(3, new Date().toString());
                pstmt.setInt(4, i.getId());
                pstmt.executeUpdate();

                System.out.println("[CONSOLE " + new Date() + "]: Updated for " + i.getName() + " price");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

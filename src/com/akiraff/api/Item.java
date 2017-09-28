package com.akiraff.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Item {
    private int id;
    private double price;
    private String shortPrice;
    private String name;

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, double price, String shortPrice, String name) {
        this.id = id;
        this.price = price;
        this.shortPrice = shortPrice;
        this.name = name;
    }

    public String getShortPrice() {
        return this.shortPrice;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void request() {
        String stringURL = "http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=" + this.id;

        try {
            URL url = new URL(stringURL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject();
            JsonObject obj = rootobj.get("item").getAsJsonObject();

            this.price = checkLastChar(obj.get("current").getAsJsonObject().get("price"));
            this.shortPrice = obj.get("current").getAsJsonObject().get("price").getAsString();
            this.name = obj.get("name").getAsString();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private double checkLastChar(JsonElement value) {
        String stringValue = value.getAsString();
        String lastChar = String.valueOf(stringValue.charAt(stringValue.length() - 1));
        stringValue = stringValue.substring(0, stringValue.length() - 1);

        if ("m".equals(lastChar)) {
            return Double.parseDouble(stringValue) * 1000000;
        }

        if ("k".equals(lastChar)) {
            return Double.parseDouble(stringValue) * 1000;
        }
        return -1;
    }
}

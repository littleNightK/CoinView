package com.example.mycrypto;

public class CryptoItem {
    private String symbol;
    private String name;
    private String price;
    private String change;
    private int iconResId; // Add this field for the icon resource ID

    public CryptoItem(String symbol, String name, String price, String change, int iconResId) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.change = change;
        this.iconResId = iconResId; // Set the icon resource ID
    }

    // Getter methods for the fields
    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getChange() {
        return change;
    }

    public int getIconResId() {
        return iconResId; // Getter for the icon resource ID
    }
}

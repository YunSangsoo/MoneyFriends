package com.example.moneyfriend;

public class Tax
{
    private String name;
    private double taxRate;

    public Tax() {}

    public Tax(String name, double taxRate)
    {
        this.name = name;
        this.taxRate = taxRate;
    }

    public String getName() {return name;}
    public double getTaxRate() {return taxRate;}
}

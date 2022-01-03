package com.example.demo;

public class Amenities {
    private String name;
    private int useCost;
    private int keepPrice;
    private int buyPrice;
    private int visits;
    private String type;
    private int level;
    private boolean working;

    public Amenities(String n, int b, int c, int p, String t, int l){
        name = n;
        useCost = c;
        keepPrice = p;
        visits = 0;
        type = t;
        buyPrice = b;
        level = l;
        working = true;
    }

    public int getPrice() {
        return keepPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getLevel() {
        return level;
    }

    public int getUseCost() {
        return useCost;
    }

    public boolean isWorking() {
        return working;
    }

    public int getVisits() {
        return visits;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }
}

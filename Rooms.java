package com.example.demo;

public class Rooms {
    private String type;
    private boolean occupied;
    private Customer owner;
    private int monthlyCost;
    private int buyCost;
    private int price;
    private int level;
    private double messy;
    private int num;

    public Rooms(String t, int p, int m, int b, int l){
        type = t;
        occupied = false;
        price = p;
        monthlyCost = m;
        buyCost = b;
        level = l;
        messy = 0;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        int num;
        if(price == 0){
            num = 6;
        } else if(price == 1){
            num = 30;
        } else {
            num = 90;
        }
        return num;
    }

    public int getBuyCost() {
        return buyCost;
    }

    public int getMonthlyCost() {
        return monthlyCost;
    }

    public double getMessy() {
        return messy;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Customer getOwner() {
        return owner;
    }

    public int getLevel() {
        return level;
    }

    public int getNum(){
        return num;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void moreMessy(){
        this.messy += 0.1;
        this.messy = roundToPlace(messy, 1);
    }

    public void setMessy(int m){
        this.messy = m;
    }

    public void setNum(int n){
        this.num = n;
    }

    public double roundToPlace(double num, int place) {
        num*=Math.pow(10, place);
        num = Math.round(num);
        num/=Math.pow(10, place);
        return num;
    }
}

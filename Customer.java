package com.example.demo;

import java.util.ArrayList;

public class Customer {
    private String name;
    private int happiness;
    private ArrayList<String> prefer = new ArrayList<>();
    private Rooms room;
    private int money;
    private int days;
    private ArrayList<String> opinion = new ArrayList<>();

    public Customer(String n, int d){
        name = n;
        happiness = 3;
        money = 0;
        days = d;
    }

    public String getName() {
        return name;
    }

    public Rooms getRoom() {
        return room;
    }

    public ArrayList<String> getPrefer() {
        return prefer;
    }

    public int getMoney() {
        return money;
    }

    public int getHappy() {
        return happiness;
    }

    public int getDays(){
        return days;
    }

    public ArrayList<String> getOpinion() {
        return opinion;
    }

    public void setPrefer(ArrayList<String> preferred) {
        this.prefer = preferred;
    }

    public void setHappy(int happiness) {
        this.happiness = happiness;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public void reduceDay(){
        this.days --;
    }

    public void setDays(int d){
        this.days = d;
    }

    public void setName(String name) {
        this.name = name;
    }
}

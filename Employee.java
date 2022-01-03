package com.example.demo;

public class Employee {
    private int wage;
    private String type;
    private String description;

    public Employee(String n, int w, String d){
        wage = w;
        type = n;
        description = d;
    }

    public int getWage() {
        return wage;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setDescription(String description) {
        this.description = description;
    }
}

package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.animation.AnimationTimer;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class HelloController{
    @FXML
    private Label lblDisplay, hotelName, moneyInfo, hotelInfo, build1, build2, build3, costInfo, nxtLvlInfo, nxtLvlMoney, nxtLvlRooms, nxtLvlRep, employeeInfo, errorText, num0, num1, num2, num3, wage0, wage1, wage2, wage3, salaryLbl, keepingLbl, serviceIncomeLbl, roomsIncomeLbl, monthlyLbl, eventInfo, repLbl;
    @FXML
    private TextField txtInput, nameInput;
    @FXML
    private AnchorPane startPane, mainPane, namePane, buildPane, starsPane, employeePane, reviewPane, statsPane, eventPane;
    @FXML
    private ImageView employeeIcon, buildIcon, infoIcon, times1, times2, times4, starImg, employeeImg;
    @FXML
    private ListView buildList, employeeList, reviewList;
    @FXML
    private Rectangle rect0, rect1, rect2;
    @FXML
    private Button buyBuild;
    @FXML
    private ProgressBar moneyProgress, roomsProgress, repProgress;
    @FXML
    LineChart lChart;
    @FXML
    double startTime;
    double resetTime;
    double popularity = .75;
    int funds = 30000;
    int lastMonth = 30000;
    int reputation = 500;
    double rate = 1.0;
    int month = 00;
    int date = 01;
    int year = 2022;
    int stars = 0;
    int whichButton;
    int[] dates = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int where;
    Employee chosen;
    String which;
    ArrayList<Amenities> newArray = new ArrayList<>();
    ArrayList<Integer> monthlyIncome = new ArrayList<>();

    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Rooms> hotelRooms = new ArrayList<>();
    ArrayList<Amenities> hotelAmenities = new ArrayList<>();
    ArrayList<Employee> hotelEmployee = new ArrayList<>();

    ArrayList<Amenities> allAmenities = new ArrayList<>();
    ArrayList<Rooms> allRooms = new ArrayList<>();
    ArrayList<Employee> allEmployees = new ArrayList<>();

    ArrayList<String> allNames = new ArrayList<>();
    ArrayList<String> amenityTypes = new ArrayList<>();
    int[] monthlyPayments = new int[4];
    double[][] nxtLvlRecs = {{10000.0, 20.0, 550}, {30000.0, 40.0, 600}, {50000.0, 60.0, 650}, {100000.0, 90.0, 700}, {200000.0, 150.0, 750}};

    public void switchPane(){
        namePane.setVisible(true);
    }

    @FXML
    protected void handleStart() {
        setAllAmenities();
        setAllRooms();
        setAllEmployees();
        startPane.setVisible(false);
        mainPane.setVisible(true);
        startTime = System.nanoTime();
        resetTime = System.nanoTime();
        try {
            FileReader reader = new FileReader("src/main/resources/names.txt");
            Scanner in = new Scanner(reader);

            while(in.hasNextLine()) {
                allNames.add(in.nextLine());
            }
        } catch (IOException ex){
            System.out.println("Something is very wrong");
        }
        for (int i = 0; i < 3; i++) {
            addRoom(0);
        }
        for (int i = 0; i < 3; i++) {
            addCustomer();
        }
        lblDisplay.setText((month+1) + "/" + date + "/" + year);
        hotelName.setText(nameInput.getText().toUpperCase(Locale.ROOT));
        hotelInfo.setText("Rooms: " + hotelRooms.size() + "\nAmenities: " + hotelAmenities.size() + "\nCustomers: " + customers.size() + "\nEmployees: " + hotelEmployee.size());
        namePane.setVisible(false);
        moneyInfo.setText("$" + funds);
        start();
    }

    public void setAllAmenities(){
        allAmenities.add(new Amenities("Laundry Room", 2500,30, 85, "Services", 1));
        allAmenities.add(new Amenities("Cafe", 3500,40, 125, "Food", 1));
        allAmenities.add(new Amenities("Internet Cafe", 5500,135, 250, "Services", 2));
        allAmenities.add(new Amenities("Gym", 8500,210, 370, "Health", 2));
        allAmenities.add(new Amenities("Arcade", 15000,270, 450, "Entertainment", 3));
        allAmenities.add(new Amenities("Drugstore", 15000,100, 350, "Services", 3));
        allAmenities.add(new Amenities("Salon", 25000, 540, 850, "Health", 4));
        allAmenities.add(new Amenities("Lounge", 35000, 900, 750, "Food", 4));
        allAmenities.add(new Amenities("Pool", 45000, 1080, 210, "Health", 4));
        allAmenities.add(new Amenities("Cinema", 50000, 1200, 2300, "Entertainment", 5));
        allAmenities.add(new Amenities("Restaurant", 85000, 540, 3700, "Food", 5));
        allAmenities.add(new Amenities("Casino", 150000, 5000, 1500, "Entertainment", 5));
    }

    public void setAllRooms(){
        String[][] names = {{"Standard", "600", "0", "30"}, {"Suite", "2600", "3", "200"}, {"Presidential Suite", "7600", "5", "400"}};
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            allRooms.add(new Rooms(names[i][0], i, Integer.valueOf(names[i][3]), Integer.valueOf(names[i][1]), Integer.valueOf(names[i][2])));
            System.out.println("Price; " + allRooms.get(i).getPrice());
        }
    }

    public void setAllEmployees(){
        allEmployees.add(new Employee( "Maid", 200,"Keeps Hotel Clean"));
        allEmployees.add(new Employee("Waiter", 300, "Serves at Cafes and Restaurants"));
        allEmployees.add(new Employee("Engineer", 500, "Fixes Broken Facilites"));
        allEmployees.add(new Employee("Security", 5000, "Increases Safety of Hotel"));
    }

    public void setTypes(){
        amenityTypes.clear();
        for (Amenities a: allAmenities) {
            if(a.getLevel() <= stars){
                amenityTypes.add(a.getName());
            }
        }
    }

    @FXML
    public void start() {

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(startTime>0){
                    double x = (double)(Math.floor(resetTime/1000000000));
                    double y  = (double)(now/1000000000);
                    double timeElapsed = y-x;
                    if(y-x>1 * rate){
                        lblDisplay.setText(updateDate());
                        //checking if the number of customers is less than the popularity of the hotel
                        if(customers.size() < (hotelRooms.size() * popularity)){
                            //finding the number of customers needed to reach popularity
                            int num = (int)Math.round((hotelRooms.size() * popularity) - customers.size());
                            for (int i = 0; i < num; i++) {
                                addCustomer();
                            }
                        }
                        earnMoney();
                        workingPlace();
                        employeeWork();
                        checkHappiness();
                        moneyInfo.setText("$" + funds);
                        hotelInfo.setText("Rooms: " + hotelRooms.size() + "\nAmenities: " + hotelAmenities.size() + "\nCustomers: " + customers.size() + "\nEmployees: " + hotelEmployee.size());
                        resetTime = System.nanoTime();
                        checkProgress();
                        removeCustomer();
                        randomEvent();
                    }
                }
            }
        }.start();
    }

    //increases the rate at which time is passing
    public void increaseSpeed(MouseEvent t){
//        System.out.println(t.getPickResult().getIntersectedNode().getId());
        int num = Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(5));
        rate = 1.0/num;
    }

    public void addCustomer(){
        customers.add(new Customer(allNames.get(randomNumber(0, allNames.size()-1)), randomNumber(1, 10)));

        int i = customers.size() - 1;
        System.out.println();

        System.out.println();
        System.out.println("Add Customer Function: ");
        for (int x = 0; x < 3; x++) {
            System.out.println("Room Number: " + hotelRooms.get(x).getNum());
        }

        for (int j = 0; j < 3; j++) {
//            customers.get(i).setRoom(new Rooms("trash", false, 0, 0, 0, 10));
            String s;

            //checking if the available amenities array has 0 elements
            if(amenityTypes.size() == 0){
                s = "N/A";
            } else {
                s = amenityTypes.get(randomNumber(0, amenityTypes.size() - 1));
            }

            //making sure there are no repeats of favorite amenities
            while(customers.get(i).getPrefer().contains(s) && !s.equals("N/A")){
                //if the available list of amenities is less than 3 and all available amenities
                // are already in the preference array then it is going to push n/a
                s = amenityTypes.get(randomNumber(0, amenityTypes.size()-1));
                if(amenityTypes.size() < 3 && customers.get(i).getPrefer().size() == amenityTypes.size()){
                    s = "N/A";
                }
            }

            customers.get(i).getPrefer().add(s);
        }
        System.out.println(customers.get(i).getPrefer());
//        This allows a customer to occupy a room
        boolean roomed = false;
        for (Rooms a: hotelRooms) {
            if(!a.isOccupied() && !roomed && a.getMessy() <= 1){
                customers.get(i).setRoom(a);
                a.setOccupied(true);
                a.setOwner(customers.get(i));
                roomed = true;
                System.out.println("Setting owner: " + customers.get(i).getName());
            }
        }
        if(!roomed){
            errorText.setText("*Hotel is no longer getting customers due to extreme uncleanliness.");
            errorText.setVisible(true);
            reviewList.getItems().add("-3 STAR- " + customers.get(i).getName() + ": No Rooms Available!!!");
            customers.remove(customers.get(i));
            reputation -=3;
            repLbl.setText("Reputation:\n" + reputation);
        }
    }

    public void addRoom(int i){
        String[][] names = {{"Standard", "600", "0", "30"}, {"Suite", "2600", "3", "200"}, {"Presidential Suite", "7600", "5", "400"}};
        hotelRooms.add(new Rooms(names[i][0], i, Integer.valueOf(names[i][3]), Integer.valueOf(names[i][1]), Integer.valueOf(names[i][2])));
//        System.out.println("Price; " + allRooms.get(i).getPrice());
//        System.out.println(hotelRooms.size());
        hotelRooms.get(hotelRooms.size()-1).setNum(hotelRooms.size());
        funds -= allRooms.get(i).getBuyCost();
        moneyInfo.setText("$" + funds);
        hotelInfo.setText("Rooms: " + hotelRooms.size() + "\nAmenities: " + hotelAmenities.size() + "\nCustomers: " + customers.size() + "\nEmployees: " + hotelEmployee.size());
    }

    public void addAmenity(ArrayList<Amenities> n){
        Amenities newAmenity = new Amenities(n.get(whichButton).getName(), n.get(whichButton).getBuyPrice(), n.get(whichButton).getUseCost(), n.get(whichButton).getPrice(), n.get(whichButton).getType(), n.get(whichButton).getLevel());
        hotelAmenities.add(newAmenity);
    }
    
    public void removeCustomer(){
        //reduces the customers days left in hotel and removes customer from list if zero days left
        ArrayList<Customer> removedCustomers = new ArrayList<>();

        for (Customer a: customers) {
            a.reduceDay();
            if(a.getDays() <= 0){
                removedCustomers.add(a);
                System.out.println(a.getOpinion().size());
            }
        }
        for (Customer b: removedCustomers) {
            if(customers.contains(b)){
                customers.remove(b);
                complaint(b.getOpinion(), b);
                reputation += b.getHappy();
                repLbl.setText("Reputation:\n" + reputation);
                System.out.println("reputation: " + reputation);
                b.getRoom().setOccupied(false);
                b.getRoom().setOwner(null);
                System.out.println(b.getName() + " left like actually gone");
            }
        }
    }

    public void complaint(ArrayList<String> x, Customer y){
        System.out.println(x);
        String say = "";
        //0: more maids, 1: more engineers, 2: more amenities
        int[] count = new int[3];
        String[] types = {"moreMaids", "moreEngineers", "moreAmenities"};

        if(x.size() <= 1){
            say =  y.getHappy() + " STAR- " + y.getName() + ": Nice hotel! Great rooms and staff!";
        } else {
            //counting which string occurs the most in the opinion array for customers
            for (String a: x) {
                for (int i = 0; i < types.length; i++) {
                    if(a.equals(types[i])){
                        count[i] ++;
                    }
                }
            }

            //finding the max out of 3
            int max = 0;
            for (int i = 1; i < count.length; i++) {
                if(count[i] > count[max]){
                    max = i;
                }
            }

            String[][] reviews = {{"Nice hotel but needs more maids.", "Absolutely horrible! Dirtiest place I have ever been!"}, {"Love the serveice but some facilities need some fixing.", "Nothing even works here. Have you guys heard of engineers?!"}, {"Like the place but I would recommend a ", "Never coming back again! This place is dissappointing and doesn't even have a "}};
            if(max == 2){
                if(y.getHappy() < 0){
                    say = y.getHappy() + " STAR- " + y.getName() + ": " + reviews[max][1] + y.getPrefer().get(randomNumber(0, y.getPrefer().size()-1));
                } else {
                    say = y.getHappy() + " STAR - " + y.getName() + ": " + reviews[max][0] + y.getPrefer().get(randomNumber(0, y.getPrefer().size()-1));
                }
            } else {
                if(y.getHappy() < 0){
                    say = y.getHappy() + " STAR - " + y.getName() + ": " + reviews[max][1];
                } else {
                    say = y.getHappy() + " STAR - " + y.getName() + ": " + reviews[max][0];
                }
            }
        }

        System.out.println(say);
        reviewList.getItems().add(say);
    }

    public void workingPlace(){
        //increases the messiness a little bit for each room and if it gets above a certain decimal amount for one room then
        //the customer is unhappy
        int count = 0;
        for (Rooms a: hotelRooms) {
            count ++;
            a.moreMessy();
            System.out.println(a.getMessy());
            if(a.getMessy() >= .4 && a.isOccupied()){
                System.out.println("Rooms " + count + ": " + a.getOwner().getName() + " is " + a.getOwner().getHappy());
                a.getOwner().setHappy(a.getOwner().getHappy()-1);
                a.getOwner().getOpinion().add("moreMaids");
//                System.out.println("dirty room");
            }
        }
        for (Amenities b: hotelAmenities) {
            if(randomNumber(1, 10) == 1){
                b.setWorking(false);
            }
            if(b.getType().equals("Services") && randomNumber(1, 3) == 1){
                b.setWorking(false);
            }
        }
    }

    public void employeeWork(){
        for (Employee a: hotelEmployee) {
            //The maid cleans 5 random messy rooms each night
            if(a.getType().equals("Maid")){
                int cleaned = 0;
                boolean allClean = false;
                while(cleaned < 5 && !allClean) {
                    Rooms x = hotelRooms.get(randomNumber(0, hotelRooms.size()-1));
                    if (x.getMessy() != 0) {
                        x.setMessy(0);
                        cleaned++;
                    }
                    allClean = true;
                    for (Rooms y: hotelRooms) {
                        if(y.getMessy() > 0){
                            allClean = false;
                        }
                    }
                }
                //The engineer fixes 2 random amenities every night
            } else if (a.getType().equals("Engineer")){
                int fixed = 0;
                boolean allFixed = false;
                while(fixed < 2 && !allFixed){
                    Amenities y = hotelAmenities.get(randomNumber(0, hotelAmenities.size()-1));
                    if(!y.isWorking()){
                        y.setWorking(true);
                        fixed ++;
                    }
                    allFixed = true;
                    for (Amenities z: hotelAmenities) {
                        if(!z.isWorking()){
                            allFixed = false;
                        }
                    }
                }
            }
        }
    }

    public void checkHappiness(){
        //makes sure that the happiness of 0 is not possible
        for (Customer a: customers) {
            System.out.println(a.getName() + " is " + a.getHappy());
            if(a.getHappy() == 0){
                a.setHappy(a.getHappy()-1);
            } else if(a.getHappy() <= -3){
                System.out.println(a.getName() + " is in the negatives");
                a.setDays(0);
                a.setHappy(-3);
            }
        }
    }

    public void earnMoney(){
        int daily = 0;
        for (Customer a : customers) {
            if (hotelAmenities.size() > 0) {
                //determines the chance of a customer visiting an amenity
                int chance = 5;
                for (String b : a.getPrefer()) {
                    for (String c : amenityTypes) {
                        if (b.equals(c)) {
                            chance = 2;
                        }
                    }
                }
                if(chance == 5){
                    a.getOpinion().add("moreAmenities");
                    a.setHappy(a.getHappy()-1);
                }
                //gets a random hotel amenity and the price
                if (randomNumber(1, chance) == 1) {
                    Amenities d = hotelAmenities.get(randomNumber(0, hotelAmenities.size()-1));
                    if(d.isWorking()){
                        if(d.getType().equals("Food")){
                            if(enoughWaiters()){
                                a.setMoney(a.getMoney() + d.getUseCost());
                                daily += d.getUseCost();
                                monthlyPayments[2] += d.getUseCost();
                            } else {
                                errorText.setText("One of your food amenities doesn't have a waiter!");
                            }
                        } else {
                            a.setMoney(a.getMoney() + d.getUseCost());
                            daily += d.getUseCost();
                            monthlyPayments[2] += d.getUseCost();
                        }
                    } else {
                        a.setHappy(a.getHappy() - 1);
                        a.getOpinion().add("moreEngineers");
                    }
                }
            }

            daily += a.getRoom().getPrice();
            monthlyPayments[3] += a.getRoom().getPrice();
//            System.out.println(a.getRoom().getPrice());
//            System.out.println(daily);
//            System.out.println("Room Price: " + a.getRoom());
            a.setMoney(a.getMoney() + a.getRoom().getPrice());
        }
        funds+=daily;
//        System.out.println("Funds: " + daily);
    }

    public boolean enoughWaiters(){
        int num = 0;
        for (Amenities x: hotelAmenities) {
            if(x.getType().equals("Food")){
                num ++;
            }
        }

        int num2 = 0;
        for(Employee y: hotelEmployee){
            if (y.getType().equals("Waiter")){
                num2 ++;
            }
        }

        if(num2 < num) {
            return false;
        } else {
            return true;
        }
    }

    //increases the date my one day
    public String updateDate(){
        if(dates[month] == date){
            if(month == 11){
                month = 00;
                year ++;
            } else {
                month++;
                monthlyFees();
            }
            date = 01;
        } else {
            date ++;
        }

        return (month+1) + "/" + date + "/" + year;
    }

    //paying the monthly fees of each amenity and hotel room
    public void monthlyFees(){
        int fees = 0;
        for (Amenities a: hotelAmenities) {
            fees += a.getPrice();
            monthlyPayments[1] += a.getPrice();
        }
        for (Rooms b: hotelRooms){
            fees += b.getMonthlyCost();
            monthlyPayments[1] += b.getMonthlyCost();
        }
        for(Employee c: hotelEmployee){
            fees += c.getWage();
            monthlyPayments[0] += c.getWage();
        }

        setBudget();

        for (int x: monthlyPayments) {
            x = 0;
        }

        System.out.println("Fees being paid cost: " + fees);
        funds -= fees;

        monthlyIncome.add(funds - lastMonth);
        lastMonth = funds;
    }

    public void openBuild(){
//        setAllAmenities();
//        setAllRooms();
        String[] types = {"Rooms", "Services", "Food", "Health", "Entertainment"};
        buildPane.setVisible(true);
        starsPane.setVisible(false);
        reviewPane.setVisible(false);
        employeePane.setVisible(false);
        buildList.getItems().clear();
        for (String a: types) {
            buildList.getItems().add(a);
        }
    }

    public void buildClicked(){
        where = buildList.getSelectionModel().getSelectedIndex();
        which = buildList.getSelectionModel().getSelectedItem().toString();
        costInfo.setText("");
        buyBuild.setVisible(false);
        //Calls function based on what user clicks
        if(where == 0){
            displayRooms();
        } else{
            displayAmenities(searchAllAmenities(which));
        }
    }

    //Goes through the entire amenities arrat and sorts it into a new array based on the type
    public ArrayList<Amenities> searchAllAmenities(String a){
        newArray.clear();
        for (Amenities x: allAmenities) {
            if(x.getType().equals(a)){
                newArray.add(x);
            }
        }
        return newArray;
    }

    //shows the name of the amenities on the buttons
    public void displayAmenities(ArrayList<Amenities> n){
        Label[] boi = {build1, build2, build3};
        for (int i = 0; i < 3; i++) {
            if(n.get(i).getLevel() <= stars) {
                boi[i].setText(n.get(i).getName());
            }else{
                boi[i].setText("Locked");
            }
        }
    }

    //seperate fundtion to do the same thing for buttons because it is a differnt class with different methods.
    public void displayRooms(){
        Label[] boi = {build1, build2, build3};

        for (int i = 0; i < 3; i++) {
            if(allRooms.get(i).getLevel() <= stars) {
                boi[i].setText(allRooms.get(i).getType());
                System.out.println(allRooms.get(i).getType());
            }else{
                boi[i].setText("Locked");
            }
        }
    }

    //shows cost of room and amenities when clicked
    public void showPrice(MouseEvent t){
        System.out.println(t.getPickResult().getIntersectedNode().getId());
        whichButton = Integer.parseInt(t.getPickResult().getIntersectedNode().getId().substring(4));
        if(where == 0){
            if(allRooms.get(whichButton).getLevel() <= stars){
                costInfo.setText("Build Cost: $" + allRooms.get(whichButton).getBuyCost() + "\nKeeping Expenses: $" + allRooms.get(whichButton).getMonthlyCost());
                buyBuild.setVisible(true);
            } else {
                costInfo.setText("Unlock This Room By Earning More Stars");
                buyBuild.setVisible(false);
            }
        } else {
            if(newArray.get(whichButton).getLevel() <= stars){
                costInfo.setText("Build Cost: $" + newArray.get(whichButton).getBuyPrice() + "\nKeeping Expenses: $" + newArray.get(whichButton).getPrice());
                buyBuild.setVisible(true);
            } else {
                costInfo.setText("Unlock This Amenity By Earning More Stars");
                buyBuild.setVisible(false);
            }
        }
    }

    public void buyPlace(){
        if(where == 0){
            addRoom(whichButton);
        } else {
            addAmenity(searchAllAmenities(which));
        }
    }

    public void openCertification(){
        starsPane.setVisible(true);
        reviewPane.setVisible(false);
        statsPane.setVisible(false);
        buildPane.setVisible(false);
        eventPane.setVisible(false);
        employeePane.setVisible(false);
        updateProgress();
    }

    public void updateProgress(){
        nxtLvlInfo.setText("Requirements to Certify to " + (stars+1) + " Star");
        nxtLvlMoney.setText("Money Required: $" + (int)(nxtLvlRecs[stars][0]));
        nxtLvlRooms.setText("Rooms Required: " + (int)(nxtLvlRecs[stars][1]));
        nxtLvlRep.setText("Reputation Required: " + (int)(nxtLvlRecs[stars][2]));
        moneyProgress.setProgress(funds/nxtLvlRecs[stars][0]);
        roomsProgress.setProgress(hotelRooms.size()/nxtLvlRecs[stars][1]);
        repProgress.setProgress(reputation/nxtLvlRecs[stars][2]);
    }

    public void checkProgress(){
        if(funds/nxtLvlRecs[stars][0] >= 1 && hotelRooms.size()/nxtLvlRecs[stars][1] >= 1 && reputation/nxtLvlRecs[stars][2] >= 1){
            stars ++;
            setTypes();
            try {
                FileInputStream input = new FileInputStream("src/main/resources/" + stars + "stars.png");
                starImg.setImage(new Image(input));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void openEmployees(){
        errorText.setText("");
        buildPane.setVisible(false);
        starsPane.setVisible(false);
        reviewPane.setVisible(false);
        statsPane.setVisible(false);
        eventPane.setVisible(false);
        employeePane.setVisible(true);
        employeeList.getItems().clear();
        for (Employee a: allEmployees) {
            employeeList.getItems().add(a.getType());
        }
    }

    public void employeeClicked(){
        which = employeeList.getSelectionModel().getSelectedItem().toString();
        //sets image to icon of employee clicked
        try {
            FileInputStream input = new FileInputStream("src/main/resources/" + which + ".png");
            employeeImg.setImage(new Image(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        chosen = null;
        for (Employee a: allEmployees) {
            if(a.getType().equals(which)){
                chosen = a;
            }
        }
        employeeInfo.setText("Monthly Wage: $" + chosen.getWage() + "\nJob Description: " + chosen.getDescription());
    }

    public void hireEmployee(){
        hotelEmployee.add(new Employee(chosen.getType(), chosen.getWage(), chosen.getDescription()));
        hotelInfo.setText("Rooms: " + hotelRooms.size() + "\nAmenities: " + hotelAmenities.size() + "\nCustomers: " + customers.size() + "\nEmployees: " + hotelEmployee.size());
        errorText.setText("");
    }

    public void openReview(){
        reviewPane.setVisible(true);
        buildPane.setVisible(false);
        starsPane.setVisible(false);
        employeePane.setVisible(false);
        eventPane.setVisible(false);
        statsPane.setVisible(false);
    }

    public void openStats(){
        reviewPane.setVisible(false);
        buildPane.setVisible(false);
        starsPane.setVisible(false);
        employeePane.setVisible(false);
        eventPane.setVisible(false);
        statsPane.setVisible(true);

        //creating the line graph
        lChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName("Monthly Income");
        series.getData().clear();
        for (int i = 0; i < monthlyIncome.size(); i++) {
            series.getData().add(new XYChart.Data(Integer.toString(i+1), monthlyIncome.get(i)));
        }
        lChart.getData().add(series);

        //setting the staff information
        Label[][] staffInfo = {{num0, wage0}, {num1, wage1}, {num2, wage2}, {num3, wage3}};
        int maid[] = new int[2];
        int waiter[] = new int[2];
        int engineer[] = new int[2];
        int security[] = new int[2];
        for (Employee a: hotelEmployee) {
            if(a.getType().equals("Maid")){
                maid[0] ++;
                maid[1] += a.getWage();
            } else if(a.getType().equals("Waiter")){
                waiter[0] ++;
                waiter[1] += a.getWage();
            } else if(a.getType().equals("Engineer")){
                engineer[0] ++;
                engineer[1] += a.getWage();
            } else if(a.getType().equals("Security")){
                security[0] ++;
                security[1] += a.getWage();
            }
        }
        staffInfo[0][0].setText("#" + maid[0]);
        staffInfo[1][0].setText("#" + waiter[0]);
        staffInfo[2][0].setText("#" + engineer[0]);
        staffInfo[3][0].setText("#" + security[0]);
        staffInfo[0][1].setText("$" + Integer.toString(maid[1]));
        staffInfo[1][1].setText("$" + Integer.toString(waiter[1]));
        staffInfo[2][1].setText("$" + Integer.toString(engineer[1]));
        staffInfo[3][1].setText("$" + Integer.toString(security[1]));

//        info.add(new Employee("Maid", maid[0], "$" + Integer.toString(maid[1])));
//        info.add(new Employee("Waiter", waiter[0], "$" + Integer.toString(waiter[1])));
//        info.add(new Employee("Engineer", engineer[0], "$" + Integer.toString(engineer[1])));
//        info.add(new Employee("Security", security[0], "$" + Integer.toString(security[1])));

    }

    public void setBudget(){
        salaryLbl.setText("Staff Salary: -" + monthlyPayments[0]);
        keepingLbl.setText("Rooms Keeping: -" + monthlyPayments[1]);
        serviceIncomeLbl.setText("Service Income: +" + monthlyPayments[2]);
        roomsIncomeLbl.setText("Rooms Income: +" + monthlyPayments[3]);

        monthlyLbl.setText("PROFIT THIS MONTH: " + (monthlyPayments[2] + monthlyPayments[3] - monthlyPayments[0] - monthlyPayments[1]));
    }

    public void exitPanes(){
        System.out.println("TRYING TO EXIT?!?!?!?!?!");
        buildPane.setVisible(false);
        starsPane.setVisible(false);
        employeePane.setVisible(false);
        reviewPane.setVisible(false);
        statsPane.setVisible(false);
        eventPane.setVisible(false);
    }

    public void randomEvent(){
        if(randomNumber(1, 20) == 1){
            String event = "";
            int chance = randomNumber(1, 10);
            if(chance == 1){
                event = "All of your maids went on strike!!!";
                for (Employee a: hotelEmployee) {
                    if(a.getType().equals("Maid")){
                        hotelEmployee.remove(a);
                    }
                }
            } else if(chance <= 3){
                boolean noGuards = true;
                for (Employee a: hotelEmployee) {
                    if(a.getType().equals("Security")){
                        noGuards = false;
                    }
                }
                if(noGuards){
                    event = "The hotel has no security so ";
                    int which = randomNumber(1,3);
                    if(which == 1){
                        event = event + "spies broke into the safe and stole $10000";
                        funds -= 10000;
                    } else if(which == 2){
                        event = event + "a fight broke out between boxers and decreased popularity";
                        popularity -= .05;
                    } else {
                        event = event + "one of your rooms got torn down by crazy ninjas";
                        funds -= 1000;
                    }
                } else {
                    event = "You have security so nothing happened.";
                }
            } else if (chance == 4){
                event = "The hotel was late on paying their taxes.";
                funds = funds/2;
            } else if (chance == 5){
                event = "There is a big event being held at your hotel, increasing the popularity.";
                popularity += .05;
            } else if (chance == 6){
                event = "A large company decided to fund your hotel $10000";
                funds += 10000;
            } else if (chance == 7){
                event = "The employees have been working very hard, increasing the good ratings.";
                reputation += 30;
                repLbl.setText("Reputation:\n" + reputation);
            } else if (chance == 8){
                event = "The hotel invested in a business that is going well. The investment was doubled.";
                funds += 10000;
            } else if (chance == 9){
                event = "A secretive businessman who liked the hotel gifted you a security team";
                chosen = allEmployees.get(3);
                hireEmployee();
                funds += 5000;
            } else if (chance == 10){
                event = "Nobody liked your hotel because it is so boring. Your popularity is decreasing.";
                popularity -= .1;
            }
            System.out.println(event);
            eventInfo.setText(event);
            eventPane.setVisible(true);
        }
    }

    public int randomNumber(int a, int b) {
        double x = Math.floor(Math.random() * (b - a + 1) + a);
        return (int) x;
    }

    public double roundToPlace(double num, int place) {
        num*=Math.pow(10, place);
        num = Math.round(num);
        num/=Math.pow(10, place);
        return num;
    }
}
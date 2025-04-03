package org.example.aivaje2.VAO;

public class User {
    String ime;
    String email;
    double balance;
    String carType;

    public User(String ime, String email, double balance, String carType) {
        this.ime = ime;
        this.email = email;
        this.balance = balance;
        this.carType = carType;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}

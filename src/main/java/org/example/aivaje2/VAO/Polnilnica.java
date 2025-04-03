package org.example.aivaje2.VAO;

import org.example.aivaje2.Opazovalci.PolnilnicaObserver;

import java.util.ArrayList;
import java.util.List;

public class Polnilnica {
    private int id;
    private String lokacija;
    private String naziv;
    private Ponudnik ponudnik;
    private int hitrost;
    private boolean isActive;
    private String regija;
    private List<PolnilnicaObserver> observers = new ArrayList<>();

    public Polnilnica(int id, String lokacija, String naziv, Ponudnik ponudnik, boolean isActive, int hitrost, String regija) {
        this.id = id;
        this.lokacija = lokacija;
        this.naziv = naziv;
        this.ponudnik = ponudnik;
        this.isActive = isActive;
        this.hitrost = hitrost;
        this.regija = regija;
    }

    public void addObserver(PolnilnicaObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PolnilnicaObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (PolnilnicaObserver observer : observers) {
            observer.update();
        }
    }

    public Polnilnica() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getPonudnikId() {
        return ponudnik.getId();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getRegija() {
        return regija;
    }

    public void setRegija(String regija) {
        this.regija = regija;
    }

    public int getHitrost() {
        return hitrost;
    }

    public void setHitrost(int hitrost) {
        this.hitrost = hitrost;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Ponudnik getPonudnik() {
        return ponudnik;
    }

    public void setPonudnik(Ponudnik ponudnik) {
        this.ponudnik = ponudnik;
    }

    @Override
    public String toString() {
        return "Polnilnica{" +
                "id=" + id +
                ", lokacija='" + lokacija + '\'' +
                ", ponudnikId=" + ponudnik.getId() +
                '}';
    }
}

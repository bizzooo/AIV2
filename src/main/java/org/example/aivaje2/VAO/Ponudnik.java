package org.example.aivaje2.VAO;

import java.util.ArrayList;

public class Ponudnik {
    private int id;
    private String naziv;
    private ArrayList<Polnilnica> postaje;

    public Ponudnik(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
        this.postaje = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ArrayList<Polnilnica> getPostaje() {
        return postaje;
    }

    public void setPostaje(ArrayList<Polnilnica> polnilnice) {
        this.postaje = polnilnice;
    }

    @Override
    public String toString() {
        return "Ponudnik{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", polnilnice=" + postaje.size() +
                '}';
    }
}

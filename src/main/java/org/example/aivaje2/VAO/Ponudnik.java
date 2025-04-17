package org.example.aivaje2.VAO;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ponudnik")
public class Ponudnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String naziv;

    @OneToMany(mappedBy = "ponudnik", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Polnilnica> postaje;

    public Ponudnik(String naziv) {
        this.naziv = naziv;
        this.postaje = new ArrayList<>();
    }

    public Ponudnik() {
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

    public List<Polnilnica> getPostaje() {
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

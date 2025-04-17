package org.example.aivaje2.MrBean;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.DependsOn;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.example.aivaje2.DAO.*;
import org.example.aivaje2.Service.PolnilnicaService;
import org.example.aivaje2.Service.PonudnikService;
import org.example.aivaje2.Service.UserService;
import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;
import org.example.aivaje2.VAO.User;

import java.io.Serializable;

@Data
@Named("vstaviBean")
@ViewScoped
public class VstaviPolnilnicoBean implements Serializable {


    private String ime;
    private String email;
    private double balance;
    private String carType;

    private int postajaID;
    private String lokacija;
    private String naziv;
    private int hitrost;
    private boolean active;
    private String regija;

    private int ponudnikID;

    @EJB
    private iPonudnikDAO ponudnikDAO;
    @EJB
    private iPolnilnicaDAO polnilnicaDAO;
    @EJB
    private iUserDAO userDAO;


    public void addPolnilnica(){
        polnilnicaDAO.dodajPostajo(new Polnilnica(lokacija,naziv,ponudnikDAO.pridobiPonudnika(ponudnikID), active,hitrost,regija));
        System.out.println("Added postaja " + naziv);
    }

    public void addUser(){
        userDAO.insertUser(new User(ime,email,balance,carType));
        System.out.println("Added user " + ime);
    }

    public void printData(){
        System.out.println("Postaje:");
        for(Polnilnica p : polnilnicaDAO.vrniVsePostaje()){
            System.out.println(p.getNaziv());
        }
        System.out.println("Ponudniki:");
        for(Ponudnik p : ponudnikDAO.vrniVsePonudnike()){
            System.out.println(p.getNaziv());
        }
    }
}

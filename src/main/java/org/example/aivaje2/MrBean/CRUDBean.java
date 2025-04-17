package org.example.aivaje2.MrBean;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.example.aivaje2.DAO.iPolnilnicaDAO;
import org.example.aivaje2.DAO.iPonudnikDAO;
import org.example.aivaje2.DAO.iUserDAO;
import org.example.aivaje2.Service.PolnilnicaService;
import org.example.aivaje2.Service.PonudnikService;
import org.example.aivaje2.Service.UserService;
import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;
import org.example.aivaje2.VAO.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Named("crudBean")
@SessionScoped
public class CRUDBean implements Serializable {

    private int test;

    @EJB
    private iPonudnikDAO ponudnikDAO;
    @EJB
    private iPolnilnicaDAO polnilnicaDAO;
    @EJB
    private iUserDAO userDAO;

    private User izbranUser;
    private boolean newUser;

    private Polnilnica izbranaPolnilnica;
    private int izbranaPolnilnicaId;
    private String izbranaPolnilnicaNaziv;
    private String izbranaPolnilnicaLokacija;
    private int izbranaPolnilnicaHitrost;
    private String izbranaPolnilnicaRegija;
    private boolean izbranaPolnilnicaActive;
    private int izbranaPolnilnicaPonudnikId;

    private Ponudnik izbranPonudnik;
    private boolean newPonudnik;
    private String ponudnikNaziv;
    private int ponudnikID;

    private List<Polnilnica> polnilnice;
    private ArrayList<Ponudnik> ponudniki;
    private List<User> users;

    @PostConstruct
    public void init() {
        if (izbranaPolnilnica == null) {
            izbranaPolnilnica = new Polnilnica();
        }
        if (izbranUser == null) {
            izbranUser = new User();
        }
        polnilnice = polnilnicaDAO.vrniVsePostaje();
        ponudniki = new ArrayList<>(ponudnikDAO.vrniVsePonudnike());
        users = userDAO.getAllUsers();
    }

    public String addPolnilnica() {
        polnilnicaDAO.dodajPostajo(new Polnilnica(izbranaPolnilnicaLokacija, izbranaPolnilnicaNaziv, ponudnikDAO.pridobiPonudnika(izbranaPolnilnicaPonudnikId), izbranaPolnilnicaActive, izbranaPolnilnicaHitrost, izbranaPolnilnicaRegija));
        polnilnice = polnilnicaDAO.vrniVsePostaje();
        System.out.println("this shit gay");
        return "polnilnice.xhtml?faces-redirect=true";
    }

    public void data() {
        System.out.println("Izbrana polnilnica:");
        System.out.println("ID: " + izbranaPolnilnica.getId());
        System.out.println("Naziv: " + izbranaPolnilnica.getNaziv());
        System.out.println("Lokacija: " + izbranaPolnilnica.getLokacija());
        System.out.println("Hitrost: " + izbranaPolnilnica.getHitrost());
        System.out.println("Regija: " + izbranaPolnilnica.getRegija());
        System.out.println("Ponudnik: " + izbranaPolnilnica.getPonudnik());
        System.out.println("Aktivna: " + izbranaPolnilnica.isActive());
        System.out.println("test" + test);
    }
    public void urediPolnilnica(Polnilnica polnilnica) {
        this.izbranaPolnilnica = polnilnica;
        System.out.println("I AM UPDATED JEBES TA SHIT");
    }

    public void savePolnilnica() {
        if (izbranaPolnilnica.getId() == 0) {
            polnilnicaDAO.dodajPostajo(izbranaPolnilnica);
        } else {
            polnilnicaDAO.posodobiPostajo(izbranaPolnilnica);
        }
        polnilnice = polnilnicaDAO.vrniVsePostaje();
    }

    public void deletePolnilnica(Polnilnica polnilnica) {
        polnilnicaDAO.odstraniPostajo(polnilnica.getId());
        polnilnice = polnilnicaDAO.vrniVsePostaje();
    }

    public Ponudnik ponudnikIzbrane(){
        if(izbranaPolnilnica.getPonudnik() != null){
            return izbranaPolnilnica.getPonudnik();
        } else {
            return ponudnikDAO.pridobiPonudnika(1);
        }
    }

    public String addUser() {
        izbranUser = new User();
        newUser = true;
        return "dodajUser.xhtml?faces-redirect=true";
    }

    public String editUser() {
        newUser = false;
        return "dodajUser.xhtml?faces-redirect=true";
    }

    public String saveUser() {
        if (newUser) {
            userDAO.insertUser(izbranUser);
        } else {
            userDAO.updateUser(izbranUser);
        }
        users = userDAO.getAllUsers();
        return "users.xhtml?faces-redirect=true";
    }

    public String deleteUser() {
        userDAO.deleteUser(izbranUser);
        users = userDAO.getAllUsers();
        return null;
    }

    public String addPonudnik() {
        izbranPonudnik = new Ponudnik(ponudnikNaziv);
        newPonudnik = true;
        return "dodajPonudnika.xhtml?faces-redirect=true";
    }

    // Called from the "Edit" link via f:setPropertyActionListener.
    public String editPonudnik(Ponudnik ponudnik) {
        newPonudnik = false;
        return "dodajPonudnika.xhtml?faces-redirect=true";
    }

    // Called when saving the provider in the form.
    public String savePonudnik() {
        if (newPonudnik) {
            ponudnikDAO.dodajPonudnika(izbranPonudnik);
        } else {
            ponudnikDAO.posodobiPonudnika(new Ponudnik(ponudnikNaziv));
        }
        ponudniki = ponudnikDAO.vrniVsePonudnike();
        return "ponudniki.xhtml?faces-redirect=true";
    }

    public String deletePonudnik() {
        ponudnikDAO.odstraniPonudnika(izbranPonudnik.getId());
        ponudniki = ponudnikDAO.vrniVsePonudnike();
        return null;
    }
}

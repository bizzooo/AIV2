package org.example.aivaje2.Service;

import jakarta.ejb.EJB;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import org.example.aivaje2.COR.AliImaUporabnikSredstva;
import org.example.aivaje2.COR.PolnilnicaNaVoljo;
import org.example.aivaje2.COR.aliJeKompatibilna;
import org.example.aivaje2.COR.iPolnilnicaHandler;
import org.example.aivaje2.DAO.PolnilnicaDAO;
import org.example.aivaje2.DAO.iPolnilnicaDAO;
import org.example.aivaje2.DAO.iPonudnikDAO;
import org.example.aivaje2.DAO.iUserDAO;
import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;
import org.example.aivaje2.VAO.User;
import java.util.List;

@Stateless
public class PolnilnicaService implements RemotePService {

    @EJB
    private iPolnilnicaDAO polnilnicaDAO;

    @EJB
    private iUserDAO userDAO;

    private iPolnilnicaHandler chargingHandlerChain;

    private iPolnilnicaHandler getChargingHandlerChain() {
        if (chargingHandlerChain == null) {
            // Instantiate each check handler
            iPolnilnicaHandler handler1 = new AliImaUporabnikSredstva();
            iPolnilnicaHandler handler2 = new aliJeKompatibilna();
            iPolnilnicaHandler handler3 = new PolnilnicaNaVoljo();

            // Chain them together: handler1 -> handler2 -> handler3
            handler1.setNextHandler(handler2);
            handler2.setNextHandler(handler3);

            chargingHandlerChain = handler1;
        }
        return chargingHandlerChain;
    }


    public void dodajPostajo(String lokacija, String naziv, Ponudnik ponudnik, boolean isActive, int hitrost, String regija) {
        if (lokacija == null || naziv == null || ponudnik == null || regija == null) {
            throw new IllegalArgumentException("Napaka pri dodajanju polnilnice");
        }
        polnilnicaDAO.dodajPostajo(new Polnilnica(lokacija, naziv, ponudnik, isActive, hitrost, regija));
    }


    public void dodajPostajo(Polnilnica postaja) {
        if (postaja == null || postaja.getId() < 0) {
            throw new IllegalArgumentException("Napaka pri dodajanju polnilnice");
        }
        polnilnicaDAO.dodajPostajo(postaja);
    }

    public void posodobiPostajo(String lokacija, String naziv, Ponudnik ponudnik, boolean isActive, int hitrost, String regija) {
        if (lokacija == null || naziv == null || ponudnik == null || regija == null) {
            throw new IllegalArgumentException("Napaka pri posodabljanju polnilnice");
        }
        polnilnicaDAO.dodajPostajo(new Polnilnica(lokacija, naziv, ponudnik, isActive, hitrost, regija));
    }

    public void posodobiPostajo(Polnilnica p) {
        if (p.getId() < 0 || p.getLokacija() == null || p.getNaziv() == null || p.getPonudnik() == null || p.getRegija() == null) {
            throw new IllegalArgumentException("Napaka pri posodabljanju polnilnice");
        }
        polnilnicaDAO.dodajPostajo(p);
    }

    public void odstraniPostajo(Polnilnica postaja) {
        if (postaja == null) {
            throw new IllegalArgumentException("Napaka pri odstranjevanju polnilnice");
        }
        polnilnicaDAO.odstraniPostajo(postaja.getId());
    }

    public Polnilnica pridobiPostajo(Polnilnica postaja) {
        if (postaja == null) {
            throw new IllegalArgumentException("Napaka pri pridobivanju postaje");
        }
        return polnilnicaDAO.pridobiPostajo(postaja.getId());
    }

    public List<Polnilnica> vrniVsePostaje() {
        if (polnilnicaDAO.vrniVsePostaje().isEmpty()) {
            throw new IllegalArgumentException("Ni polnilnic");
        }
        return polnilnicaDAO.vrniVsePostaje();
    }

    public List<Polnilnica> vrniPostajePoPonudniku(Ponudnik ponudnik) {
        if (ponudnik == null) {
            throw new IllegalArgumentException("Napaka ponudnik ne obstaja");
        }
        return polnilnicaDAO.vrniPostajePoPonudniku(ponudnik.getId());
    }

    @Override
    public boolean startCharging(String ime, String email, double balance, String carType, String lokacija, String naziv, boolean isActive, int hitrost, String regija) {
        iPolnilnicaHandler handlerChain = getChargingHandlerChain();

        User user = new User(ime,email,balance,carType);
        Polnilnica polnilnica = new Polnilnica(lokacija, naziv, isActive, hitrost, regija);

        if (polnilnicaDAO.pridobiPostajo(polnilnica.getId()) == null) {
            polnilnicaDAO.dodajPostajo(polnilnica);
        }
        if(userDAO.getUser(user) == null){
            userDAO.insertUser(user);
        }


        boolean p = handlerChain.handleReq(user, polnilnica);

        return p;
    }
}



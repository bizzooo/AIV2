package org.example.aivaje2.Service;

import org.example.aivaje2.COR.AliImaUporabnikSredstva;
import org.example.aivaje2.COR.PolnilnicaNaVoljo;
import org.example.aivaje2.COR.aliJeKompatibilna;
import org.example.aivaje2.COR.iPolnilnicaHandler;
import org.example.aivaje2.DAO.PolnilnicaDAO;
import org.example.aivaje2.DAO.iPolnilnicaDAO;
import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;
import org.example.aivaje2.VAO.User;

import java.util.List;

public class PolnilnicaService {
    private final iPolnilnicaDAO polnilnicaDAO = PolnilnicaDAO.getInstance();

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

    public void startCharging(User user, Polnilnica polnilnica) {

        iPolnilnicaHandler handlerChain = getChargingHandlerChain();

        handlerChain.handleReq(user, polnilnica);


        if (!polnilnica.isActive()) {
            System.out.println("Charging started for user " + user.getEmail());

            polnilnica.setActive(true);
        } else {
            System.out.println("Charging station is already in use.");
        }
    }

    public void dodajPostajo(int id, String lokacija, String naziv, Ponudnik ponudnik, boolean isActive, int hitrost, String regija){
        if(id < 0 || lokacija == null || naziv == null || ponudnik == null || regija == null){
            throw new IllegalArgumentException("Napaka pri dodajanju polnilnice");
        }
        polnilnicaDAO.dodajPostajo(new Polnilnica(id, lokacija, naziv, ponudnik, isActive, hitrost, regija));
    }


    public void dodajPostajo(Polnilnica postaja){
        if(postaja == null || postaja.getId() < 0){
            throw new IllegalArgumentException("Napaka pri dodajanju polnilnice");
        }
        polnilnicaDAO.dodajPostajo(postaja);
    }

    public void posodobiPostajo(int id, String lokacija, String naziv, Ponudnik ponudnik, boolean isActive, int hitrost, String regija){
        if(id < 0 || lokacija == null || naziv == null || ponudnik == null || regija == null){
            throw new IllegalArgumentException("Napaka pri posodabljanju polnilnice");
        }
        polnilnicaDAO.dodajPostajo(new Polnilnica(id, lokacija, naziv, ponudnik, isActive, hitrost, regija));
    }

    public void odstraniPostajo(Polnilnica postaja){
        if(postaja == null){
            throw new IllegalArgumentException("Napaka pri odstranjevanju polnilnice");
        }
        polnilnicaDAO.odstraniPostajo(postaja.getId());
    }

    public Polnilnica pridobiPostajo(Polnilnica postaja){
        if(postaja == null){
            throw new IllegalArgumentException("Napaka pri pridobivanju postaje");
        }
        return polnilnicaDAO.pridobiPostajo(postaja.getId());
    }

    public List<Polnilnica> vrniVsePostaje(){
        if(polnilnicaDAO.vrniVsePostaje().isEmpty()) {
            throw new IllegalArgumentException("Ni polnilnic");
        }
        return polnilnicaDAO.vrniVsePostaje();
    }

    public List<Polnilnica> vrniPostajePoPonudniku(Ponudnik ponudnik){
        if(ponudnik == null){
            throw new IllegalArgumentException("Napaka ponudnik ne obstaja");
        }
        return polnilnicaDAO.vrniPostajePoPonudniku(ponudnik.getId());
    }
}

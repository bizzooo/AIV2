package org.example.aivaje2.Service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.aivaje2.DAO.PonudnikDAO;
import org.example.aivaje2.DAO.iPonudnikDAO;
import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;

import java.util.ArrayList;

@Stateless
public class PonudnikService {

    @EJB
    private iPonudnikDAO ponudnikDAO;

    public void dodajPonudnika(int id, String naziv){
        if(id < 0 || naziv == null){
            throw new IllegalArgumentException("Napaka pri dodajanju ponudnika");
        }
        ponudnikDAO.dodajPonudnika(new Ponudnik(naziv));
    }

    public void dodajPonudnika(Ponudnik ponudnik){
        if(ponudnik == null || ponudnik.getId() < 0){
            throw new IllegalArgumentException("Napaka pri dodajanju ponudnika");
        }
        ponudnikDAO.dodajPonudnika(ponudnik);
    }

    public void posodobiPonudnika(Ponudnik ponudnik){
        if(ponudnik == null){
            throw new IllegalArgumentException("Napaka pri posodablanju ponudnika");
        }
        ponudnikDAO.posodobiPonudnika(ponudnik);
    }

    public void odstraniPonudnika(Ponudnik ponudnik){
        if(ponudnik == null || ponudnik.getId() < 0){
            throw new IllegalArgumentException("Napaka pri odstranjevanju ponudnika");
        }
        ponudnikDAO.odstraniPonudnika(ponudnik.getId());
    }

    public void dodajPostajo(Ponudnik ponudnik, Polnilnica postaja){
        if(ponudnik == null || ponudnik.getId() < 0 || postaja == null || postaja.getId() < 0){
            throw new IllegalArgumentException("Napaka pri dodajanju Postaje");
        }
        ponudnikDAO.dodajPostajo(ponudnik,postaja);
    }

    public void posodobiPostajo(Ponudnik ponudnik, Polnilnica postaja){
        if(ponudnik == null || ponudnik.getId() < 0 || postaja == null || postaja.getId() < 0){
            throw new IllegalArgumentException("Napaka pri posodabljanju Postaje");
        }
        ponudnikDAO.posodobiPostajo(ponudnik,postaja);
    }

    public void odstraniPostajo(Ponudnik ponudnik, Polnilnica postaja){
        if(ponudnik == null || ponudnik.getId() < 0 || postaja == null || postaja.getId() < 0){
            throw new IllegalArgumentException("Napaka pri posodabljanju Postaje");
        }
        ponudnikDAO.odstraniPostajo(ponudnik,postaja.getId());
    }

    public Ponudnik pridobiPonudnika(Ponudnik ponudnik){
        if(ponudnik == null || ponudnik.getId() < 0){
            throw new IllegalArgumentException("Napaka pri pridobivanju ponudnika");
        }
        return ponudnikDAO.pridobiPonudnika(ponudnik.getId());
    }

    public Ponudnik pridobiPonudnika(int id){
        return ponudnikDAO.pridobiPonudnika(id);
    }

    public ArrayList<Ponudnik> vrniVsePonudnike(){
        if(ponudnikDAO.vrniVsePonudnike().isEmpty()){
            throw new IllegalArgumentException("List je prazen");
        }
        return ponudnikDAO.vrniVsePonudnike();
    }
}

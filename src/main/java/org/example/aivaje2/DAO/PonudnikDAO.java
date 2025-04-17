package org.example.aivaje2.DAO;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;

import java.util.ArrayList;


@Stateless
@Local(iPonudnikDAO.class)
public class PonudnikDAO implements iPonudnikDAO{

    @PersistenceContext
    private EntityManager em;

    /*
    private static volatile PonudnikDAO instance;

    private PonudnikDAO(){};

    public static PonudnikDAO getInstance(){
        if(instance == null){
            synchronized (PonudnikDAO.class){
                if(instance == null){
                    instance = new PonudnikDAO();
                }
            }
        }
        return instance;
    }
    */


    @Override
    public void dodajPonudnika(Ponudnik ponudnik) {
        em.persist(ponudnik);
    }


    @Override
    public void posodobiPonudnika(Ponudnik ponudnik) {
        em.merge(ponudnik);
    }

    @Override
    public void odstraniPonudnika(int id) {
        Ponudnik ponudnik = em.find(Ponudnik.class, id);
        if (ponudnik != null) {
            em.remove(ponudnik);
        }
    }

    @Override
    public void dodajPostajo(Ponudnik ponudnik, Polnilnica postaja) {
        Ponudnik p = em.find(Ponudnik.class, ponudnik.getId());
        if (p != null) {
            boolean exists = p.getPostaje().stream().anyMatch(po -> po.getId() == postaja.getId());
            if (!exists) {
                postaja.setPonudnik(p);
                p.getPostaje().add(postaja);
                em.persist(postaja);
            }
        }
    }

    @Override
    public void posodobiPostajo(Ponudnik ponudnik, Polnilnica postaja) {
        // Update the post (this assumes that postaja is detached and should be merged)
        em.merge(postaja);
    }

    @Override
    public void odstraniPostajo(Ponudnik ponudnik, int postajaId) {
        Ponudnik p = em.find(Ponudnik.class, ponudnik.getId());
        if (p != null) {
            Polnilnica pol = p.getPostaje().stream()
                    .filter(po -> po.getId() == postajaId)
                    .findFirst()
                    .orElse(null);
            if (pol != null) {
                p.getPostaje().remove(pol);
                if (em.contains(pol)) {
                    em.remove(pol);
                } else {
                    em.remove(em.merge(pol));
                }
            }
        }
    }

    @Override
    public Ponudnik pridobiPonudnika(int id) {
        return em.find(Ponudnik.class, id);
    }

    @Override
    public ArrayList<Ponudnik> vrniVsePonudnike() {
        return (ArrayList<Ponudnik>) em.createQuery("SELECT p FROM Ponudnik p", Ponudnik.class)
                .getResultList();
    }
}

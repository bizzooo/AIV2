package org.example.aivaje2.DAO;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.aivaje2.VAO.Polnilnica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
@Local(iPolnilnicaDAO.class)
public class PolnilnicaDAO implements iPolnilnicaDAO{

    @PersistenceContext
    private EntityManager em;

    /*
    private static volatile PolnilnicaDAO instance;

    private PolnilnicaDAO(){}

    public static PolnilnicaDAO getInstance(){
        if(instance==null){
            synchronized (PolnilnicaDAO.class){
                if(instance==null){
                    instance = new PolnilnicaDAO();
                }
            }
        }
        return instance;
    }
    */
    @Override
    public void dodajPostajo(Polnilnica postaja) {
        em.persist(postaja);
    }

    @Override
    public void posodobiPostajo(Polnilnica postaja) {
        em.merge(postaja);
    }

    @Override
    public void odstraniPostajo(int id) {
        Polnilnica postaja = em.find(Polnilnica.class, id);
        if (postaja != null) {
            em.remove(postaja);
        }
    }

    @Override
    public Polnilnica pridobiPostajo(int id) {
        return em.find(Polnilnica.class, id);
    }

    @Override
    public List<Polnilnica> vrniVsePostaje() {
        return em.createQuery("SELECT p FROM Polnilnica p", Polnilnica.class)
                .getResultList();
    }

    @Override
    public List<Polnilnica> vrniPostajePoPonudniku(int ponudnikId) {
        return em.createQuery("SELECT p FROM Polnilnica p WHERE p.ponudnik.id = :ponudnikId", Polnilnica.class)
                .setParameter("ponudnikId", ponudnikId)
                .getResultList();
    }
}

package org.example.aivaje2.DAO;

import org.example.aivaje2.VAO.Polnilnica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PolnilnicaDAO implements iPolnilnicaDAO{

    private static volatile PolnilnicaDAO instance;
    private final List<Polnilnica> polnilnice = Collections.synchronizedList(new ArrayList<>());

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

    @Override
    public void dodajPostajo(Polnilnica postaja) {
        synchronized (polnilnice) {
            if (polnilnice.stream().noneMatch(p -> p.getId() == postaja.getId())) {
                polnilnice.add(postaja);
            }
        }
    }

    @Override
    public void posodobiPostajo(Polnilnica postaja) {
        synchronized (polnilnice) {
            polnilnice.stream()
                    .filter(p -> p.getId() == postaja.getId())
                    .findFirst()
                    .ifPresent(p -> polnilnice.set(polnilnice.indexOf(p), postaja));
        }
    }

    @Override
    public void odstraniPostajo(int id) {
        synchronized (polnilnice) {
            polnilnice.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .ifPresent(p -> polnilnice.remove(polnilnice.indexOf(p)));
        }
    }

    @Override
    public Polnilnica pridobiPostajo(int id) {
        synchronized (polnilnice) {
            return polnilnice.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
        }
    }

    @Override
    public List<Polnilnica> vrniVsePostaje() {
        synchronized (polnilnice) {
            return new ArrayList<>(polnilnice);
        }
    }

    @Override
    public List<Polnilnica> vrniPostajePoPonudniku(int ponudnikId) {
        synchronized (polnilnice) {
            return polnilnice.stream()
                    .filter(p -> p.getPonudnikId() == ponudnikId)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        //collect() za arugmente rabi Collectors class ki ma neke staticne metode (no instance needed)
        //toCollection vrze vse iteme ki so preko streama prisli do nje in jih vrze v nek Collection
        //ArrayList in this case new ArrayList cuz i just return it lahko bi pa tudi nardil
        //nek prazen ArrayList<Polnilnica> foo = new ArrayList in passal toCollection(foo) in pac returnal foo
        //v glavnem looks disguisting
    }
}

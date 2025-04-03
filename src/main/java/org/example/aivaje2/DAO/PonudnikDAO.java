package org.example.aivaje2.DAO;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PonudnikDAO implements iPonudnikDAO{
    private final List<Ponudnik> ponudniki = Collections.synchronizedList(new ArrayList<>());
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

    @Override
    public void dodajPonudnika(Ponudnik ponudnik) {
        synchronized (ponudniki) {
            if (ponudniki.stream().noneMatch(p -> p.getId() == ponudnik.getId())) {
                ponudniki.add(ponudnik);
            }
        }
        //Disgusting javascript vibes
        //stream() creates a ¿? stream ¿?
        //noneMatch for each {if(all false)}
    }

    @Override
    public void posodobiPonudnika(Ponudnik ponudnik) {
        synchronized (ponudniki) {
            ponudniki.stream()
                    .filter(p -> p.getId() == ponudnik.getId())
                    .findFirst()
                    .ifPresent(p -> {
                        ponudniki.set(ponudniki.indexOf(p), ponudnik);
                    });
        }
        //.filter() == for if replacement
        //.findFirst() return replacement ko je filter bool true
        //.ifPresent() Ne rabim implementirat ce nic ne najde?
        // set prepise shit shranjeno na memory address of index p z drugim argumentom in this case ponudnik
    }

    @Override
    public void odstraniPonudnika(int id) {
        synchronized (ponudniki) {
            ponudniki.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .ifPresent(p -> ponudniki.remove(ponudniki.indexOf(p)));
        }
        // remove ostrani tisto kar je shranjeno na indexu provided
    }

    @Override
    public void dodajPostajo(Ponudnik ponudnik,Polnilnica postaja) {
        synchronized (ponudniki) {
            ponudniki.stream()
                    .filter(p -> p.getId() == ponudnik.getId())
                    .findFirst()
                    .ifPresent(p -> {
                        if (p.getPostaje().stream().noneMatch(po -> po.getId() == postaja.getId())) {
                            p.getPostaje().add(postaja);
                        }
                    });
        }
    }

    @Override
    public void posodobiPostajo(Ponudnik ponudnik,Polnilnica postaja) {
        synchronized (ponudniki) {
            ponudniki.stream()
                    .filter(p -> p.getId() == ponudnik.getId())
                    .findFirst()
                    .ifPresent(p -> {
                        p.getPostaje().stream()
                                .filter(po -> po.getId() == postaja.getId())
                                .findFirst()
                                .ifPresent(po -> p.getPostaje().set(p.getPostaje().indexOf(po), postaja));
                    });
        }
        //p.postaje() == referenca na ponudnik postaje
        //CANCER surely loops are better?
    }

    @Override
    public void odstraniPostajo(Ponudnik ponudnik,int postajaId) {
        synchronized (ponudniki) {
            ponudniki.stream()
                    .filter(p -> p.getId() == ponudnik.getId())
                    .findFirst()
                    .ifPresent(p -> {
                        p.getPostaje().stream()
                                .filter(po -> po.getId() == postajaId)
                                .findFirst()
                                .ifPresent(po -> p.getPostaje().remove(p.getPostaje().indexOf(po)));
                    });
        }
    }

    @Override
    public Ponudnik pridobiPonudnika(int id) {
        synchronized (ponudniki) {
            return ponudniki.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
        }
         //returnamo instanco ki jo (prvo) najdemo z filtrom
         //else return null
    }

    @Override
    public ArrayList<Ponudnik> vrniVsePonudnike() {
        synchronized (ponudniki) {
            return new ArrayList<>(ponudniki);
        }
    }
}

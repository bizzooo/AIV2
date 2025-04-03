package org.example.aivaje2.Opazovalci;

import org.example.aivaje2.VAO.Polnilnica;

import java.util.ArrayList;
import java.util.List;

public class PostajeDisplay implements PolnilnicaObserver{
    private List<Polnilnica> postaje;

    public PostajeDisplay(List<Polnilnica> postaje){
        this.postaje = postaje;
    }

    @Override
    public void update() {
        List<String> prostePostaje = new ArrayList<>();
        List<String> zasedenePostaje = new ArrayList<>();

        for (Polnilnica postaja : postaje) {
            if (!postaja.isActive()) {
                prostePostaje.add(postaja.getNaziv());
            } else if (postaja.isActive()) {
                zasedenePostaje.add(postaja.getNaziv());
            }
        }

        System.out.println("[Zaslon polnilne postaje] Trenutno stanje polnilnic:");
        System.out.println("Proste polnilnice: " + String.join(", ", prostePostaje));
        System.out.println("Zasedene polnilnice: " + String.join(", ", zasedenePostaje));
    }
}

package org.example.aivaje2.Opazovalci;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;

public class PonudnikNotifier implements PolnilnicaObserver{
    private Polnilnica postaja;
    private Ponudnik ponudnik;

    public PonudnikNotifier(Polnilnica station, Ponudnik provider) {
        this.postaja = station;
        this.ponudnik = provider;
    }

    @Override
    public void update() {
        String status = postaja.isActive() ? "zasedeno" : "prosto";

        System.out.println("Ponudnik "+ ponudnik.getNaziv() +" obveščen: Polnilnica " + postaja.getNaziv() +
                " pri ponudniku " + postaja.getPonudnik().getNaziv() + " je zdaj " + status + ".");
    }
}

package org.example.aivaje2.Opazovalci;

import org.example.aivaje2.VAO.Polnilnica;

public class UserNotifier implements PolnilnicaObserver{
    private Polnilnica postaja;

    public UserNotifier(Polnilnica postaja){
        this.postaja = postaja;
    }

    @Override
    public void update() {
        String status = postaja.isActive() ? "zasedeno" : "prosto";

        if("zasedeno".equals(status)){
            System.out.println("Uporabnik obveščen: Polnilnica " + postaja.getNaziv() + " je zdaj " + status + ".");
        } else if ("prosto".equals(status)) {
            System.out.println("Uporabnik obveščen: Polnilnica " + postaja.getNaziv() + " je zdaj " + status + ". Prosim pridite na polnilnico.");
        }
    }
}

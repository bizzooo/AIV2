package org.example.aivaje2.COR;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.User;

public class aliJeKompatibilna implements iPolnilnicaHandler {
    private iPolnilnicaHandler next;

    @Override
    public void setNextHandler(iPolnilnicaHandler nextHandler) {
        this.next = nextHandler;
    }

    @Override
    public boolean handleReq(User user, Polnilnica polnilnica) {
        if (!user.getCarType().equalsIgnoreCase("electric")) {
            System.out.println("Uporabnik ima naroben avto");
            return false;
        }
        if (next != null) {
            return next.handleReq(user, polnilnica);
        }
        return true;
    }
}

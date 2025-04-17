package org.example.aivaje2.COR;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.User;

public class AliImaUporabnikSredstva implements iPolnilnicaHandler {
    private iPolnilnicaHandler next;

    @Override
    public void setNextHandler(iPolnilnicaHandler nextHandler) {
        this.next = nextHandler;
    }

    @Override
    public boolean handleReq(User user, Polnilnica polnilnica) {
        if (user.getBalance() < 0) {
            System.out.println("Uporabnik nima dovolj sredstev");
            return false;
        }
        if (next != null) {
            return next.handleReq(user, polnilnica);
        }
        return true;
    }
}
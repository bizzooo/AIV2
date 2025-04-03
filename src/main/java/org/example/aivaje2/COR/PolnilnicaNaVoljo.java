package org.example.aivaje2.COR;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.User;

public class PolnilnicaNaVoljo implements iPolnilnicaHandler{
    private iPolnilnicaHandler next;

    @Override
    public void setNextHandler(iPolnilnicaHandler nextHandler) {
        this.next = nextHandler;
    }

    @Override
    public void handleReq(User user, Polnilnica polnilnica) {
        if(polnilnica.isActive()){
            System.out.println("Email to " + user.getEmail() + "polnlilnica" + polnilnica.getNaziv() + "is already charging");
            return;
        }
        if(next != null){
            next.handleReq(user, polnilnica);
        }
    }
}

package org.example.aivaje2.COR;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.User;

public interface iPolnilnicaHandler {
    void setNextHandler(iPolnilnicaHandler nextHandler);
    void handleReq(User user, Polnilnica polnilnica);
}

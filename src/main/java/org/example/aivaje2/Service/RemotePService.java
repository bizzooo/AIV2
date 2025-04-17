package org.example.aivaje2.Service;

import jakarta.ejb.Remote;


//CHANGE IN OUTSIDE PROJECT
@Remote
public interface RemotePService {
    public boolean startCharging(String ime, String email, double balance, String carType, String lokacija, String naziv, boolean isActive, int hitrost, String regija);
}

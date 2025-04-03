package org.example.aivaje2.DAO;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;

import java.util.ArrayList;

public interface iPonudnikDAO {
    void dodajPonudnika(Ponudnik ponudnik);
    void posodobiPonudnika(Ponudnik ponudnik);
    void odstraniPonudnika(int id);
    void dodajPostajo(Ponudnik ponudnik,Polnilnica postaja);
    void posodobiPostajo(Ponudnik ponudnik,Polnilnica postaja);
    void odstraniPostajo(Ponudnik ponudnik,int postajaId);
    Ponudnik pridobiPonudnika(int id);
    ArrayList<Ponudnik> vrniVsePonudnike();
}

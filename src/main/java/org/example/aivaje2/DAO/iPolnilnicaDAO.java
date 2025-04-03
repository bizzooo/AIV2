package org.example.aivaje2.DAO;

import org.example.aivaje2.VAO.Polnilnica;

import java.util.List;

public interface iPolnilnicaDAO {
    void dodajPostajo(Polnilnica postaja);
    void posodobiPostajo(Polnilnica postaja);
    void odstraniPostajo(int id);
    Polnilnica pridobiPostajo(int id);
    List<Polnilnica> vrniVsePostaje();
    List<Polnilnica> vrniPostajePoPonudniku(int ponudnikId);
}

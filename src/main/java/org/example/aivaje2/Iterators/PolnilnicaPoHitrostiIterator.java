package org.example.aivaje2.Iterators;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolnilnicaPoHitrostiIterator implements Iterator {
    private final Iterator<Polnilnica> iterator;

    public PolnilnicaPoHitrostiIterator(int hitrost, Ponudnik ponudnik){
        List<Polnilnica> postajePoHitrosti = new ArrayList<Polnilnica>();

        for (Polnilnica p : ponudnik.getPostaje()){
            if(p.getHitrost() > hitrost){
                postajePoHitrosti.add(p);
            }
        }

        this.iterator = postajePoHitrosti.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Object next() {
        return iterator.next();
    }
}

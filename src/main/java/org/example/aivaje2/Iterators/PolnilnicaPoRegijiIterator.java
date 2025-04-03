package org.example.aivaje2.Iterators;

import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolnilnicaPoRegijiIterator implements Iterator {
    private final Iterator<Polnilnica> iterator;

    PolnilnicaPoRegijiIterator(String regija, Ponudnik ponudnik){
        List<Polnilnica> postajePoRegiji = new ArrayList<Polnilnica>();

        for(Polnilnica p : ponudnik.getPostaje()){
            if(p.getRegija().equals(regija)){
                postajePoRegiji.add(p);
            }
        }
        this.iterator = postajePoRegiji.iterator();
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

package org.example.aivaje2.Iterators;

import org.example.aivaje2.VAO.Polnilnica;

import java.util.*;

public class VsepolnilnicePoAbecediIterator implements Iterator {
    private final Iterator<Polnilnica> iterator;

    public VsepolnilnicePoAbecediIterator(List<Polnilnica> postaje){
        List<Polnilnica> postajePoAbecedi = new ArrayList<Polnilnica>();

        for(Polnilnica p : postaje){
            postajePoAbecedi.add(p);
        }

        Collections.sort(postajePoAbecedi, new Comparator<Polnilnica>() {
            @Override
            public int compare(Polnilnica o1, Polnilnica o2) {
                return o1.getNaziv().compareTo(o2.getNaziv());
            }
        });

        iterator = postajePoAbecedi.iterator();
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

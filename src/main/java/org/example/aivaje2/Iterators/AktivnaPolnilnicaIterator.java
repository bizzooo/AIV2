package org.example.aivaje2.Iterators;

import org.example.aivaje2.VAO.Polnilnica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class AktivnaPolnilnicaIterator implements Iterator<Polnilnica>{
    private final Iterator<Polnilnica> iterator;

    public AktivnaPolnilnicaIterator(List<Polnilnica> postaje) {
        List<Polnilnica> aktivnePolnilnice = new ArrayList<>();

        for(Polnilnica p : postaje) {
            if(p.isActive()){
                aktivnePolnilnice.add(p);
            }
        }

        this.iterator = aktivnePolnilnice.iterator();
    }



    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Polnilnica next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super Polnilnica> action) {
        Iterator.super.forEachRemaining(action);
    }
}

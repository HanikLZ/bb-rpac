package org.mdvsc.rapc.ext;

import java.util.Enumeration;

class CollectionEnumeration<E> implements Enumeration<E> {
    private Collection<E> collection;
    CollectionEnumeration(Collection<E> collection) {
        this.collection = collection;
    }
    private final Iterator<E> i = collection.iterator();
    public boolean hasMoreElements() {
        return i.hasNext();
    }
    public E nextElement() {
        return i.next();
    }
}

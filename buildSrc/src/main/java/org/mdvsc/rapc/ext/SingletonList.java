package org.mdvsc.rapc.ext;

/**
 * @serial include
 */
class SingletonList<E> extends AbstractList<E> implements RandomAccess  {

    @SuppressWarnings("serial") // Conditionally serializable
    private final E element;

    SingletonList(E obj)                {element = obj;}

    public int size()                   {return 1;}

    public boolean contains(Object obj) {return Collections.eq(obj, element);}

    public E get(int index) {
        if (index != 0)
            throw new java.lang.IndexOutOfBoundsException(index);
        return element;
    }

    @Override
    public void sort(Comparator<? super E> c) {
    }

    @Override
    public int hashCode() {
        return 31 + (element != null ? element.hashCode() : 0);
    }
}
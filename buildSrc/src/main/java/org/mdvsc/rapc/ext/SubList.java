package org.mdvsc.rapc.ext;

import java.util.NoSuchElementException;

class SubList<E> extends AbstractList<E> {
    private final AbstractList<E> root;
    private final SubList<E> parent;
    private final int offset;
    protected int size;
    /**
     * Constructs a sublist of an arbitrary AbstractList, which is
     * not a SubList itself.
     */
    public SubList(AbstractList<E> root, int fromIndex, int toIndex) {
        this.root = root;
        this.parent = null;
        this.offset = fromIndex;
        this.size = toIndex - fromIndex;
        this.modCount = root.modCount;
    }

    /**
     * Constructs a sublist of another SubList.
     */
    protected SubList(SubList<E> parent, int fromIndex, int toIndex) {
        this.root = parent.root;
        this.parent = parent;
        this.offset = parent.offset + fromIndex;
        this.size = toIndex - fromIndex;
        this.modCount = root.modCount;
    }

    public E set(int index, E element) {
        Collections.checkIndex(index, size);
        checkForComodification();
        return root.set(offset + index, element);
    }

    public E get(int index) {
        Collections.checkIndex(index, size);
        checkForComodification();
        return root.get(offset + index);
    }

    public int size() {
        checkForComodification();
        return size;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);
        checkForComodification();
        root.add(offset + index, element);
        updateSizeAndModCount(1);
    }

    public E remove(int index) {
        Collections.checkIndex(index, size);
        checkForComodification();
        E result = root.remove(offset + index);
        updateSizeAndModCount(-1);
        return result;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        checkForComodification();
        root.removeRange(offset + fromIndex, offset + toIndex);
        updateSizeAndModCount(fromIndex - toIndex);
    }

    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize==0)
            return false;
        checkForComodification();
        root.addAll(offset + index, c);
        updateSizeAndModCount(cSize);
        return true;
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    public ListIterator<E> listIterator(int index) {
        checkForComodification();
        rangeCheckForAdd(index);

        return new ListIterator<E>() {
            private final ListIterator<E> i =
                    root.listIterator(offset + index);

            public boolean hasNext() {
                return nextIndex() < size;
            }

            public E next() {
                if (hasNext())
                    return i.next();
                else
                    throw new NoSuchElementException();
            }

            public boolean hasPrevious() {
                return previousIndex() >= 0;
            }

            public E previous() {
                if (hasPrevious())
                    return i.previous();
                else
                    throw new NoSuchElementException();
            }

            public int nextIndex() {
                return i.nextIndex() - offset;
            }

            public int previousIndex() {
                return i.previousIndex() - offset;
            }

            public void remove() {
                i.remove();
                updateSizeAndModCount(-1);
            }

            public void set(E e) {
                i.set(e);
            }

            public void add(E e) {
                i.add(e);
                updateSizeAndModCount(1);
            }
        };
    }

    public List<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubList<>(this, fromIndex, toIndex);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index);
    }

    private void checkForComodification() {
        if (root.modCount != this.modCount)
            throw new ConcurrentModificationException();
    }

    private void updateSizeAndModCount(int sizeChange) {
        SubList<E> slist = this;
        do {
            slist.size += sizeChange;
            slist.modCount = root.modCount;
            slist = slist.parent;
        } while (slist != null);
    }
}

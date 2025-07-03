package org.mdvsc.rapc.ext;

import java.util.NoSuchElementException;

class SubArrayList<E> extends AbstractList<E> implements RandomAccess {

    private final ArrayList<E> root;
    private final SubArrayList<E> parent;
    private final int offset;
    private int size;

    /**
     * Constructs a sublist of an arbitrary ArrayList.
     */
    public SubArrayList(ArrayList<E> root, int fromIndex, int toIndex) {
        this.root = root;
        this.parent = null;
        this.offset = fromIndex;
        this.size = toIndex - fromIndex;
        this.modCount = root.modCount;
    }

    /**
     * Constructs a sublist of another SubList.
     */
    private SubArrayList(SubArrayList<E> parent, int fromIndex, int toIndex) {
        this.root = parent.root;
        this.parent = parent;
        this.offset = parent.offset + fromIndex;
        this.size = toIndex - fromIndex;
        this.modCount = parent.modCount;
    }

    public E set(int index, E element) {
        checkIndex(index, size);
        checkForComodification();
        E oldValue = root.elementData(offset + index);
        root.elementData[offset + index] = element;
        return oldValue;
    }

    public E get(int index) {
        checkIndex(index, size);
        checkForComodification();
        return root.elementData(offset + index);
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
        checkIndex(index, size);
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
        return addAll(this.size, c);
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

    public boolean removeAll(Collection<?> c) {
        return batchRemove(c, false);
    }

    public boolean retainAll(Collection<?> c) {
        return batchRemove(c, true);
    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        checkForComodification();
        int oldSize = root.size();
        boolean modified =
                root.batchRemove(c, complement, offset, offset + size);
        if (modified)
            updateSizeAndModCount(root.size() - oldSize);
        return modified;
    }

    public Object[] toArray() {
        checkForComodification();
        return Arrays.copyOfRange(root.elementData, offset, offset + size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        checkForComodification();
        if (a.length < size) return (T[]) Arrays.copyOfRange(root.elementData, offset, offset + size);
        System.arraycopy(root.elementData, offset, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof List)) {
            return false;
        }

        boolean equal = root.equalsRange((List<?>)o, offset, offset + size);
        checkForComodification();
        return equal;
    }

    public int hashCode() {
        int hash = root.hashCodeRange(offset, offset + size);
        checkForComodification();
        return hash;
    }

    public int indexOf(Object o) {
        int index = root.indexOfRange(o, offset, offset + size);
        checkForComodification();
        return index >= 0 ? index - offset : -1;
    }

    public int lastIndexOf(Object o) {
        int index = root.lastIndexOfRange(o, offset, offset + size);
        checkForComodification();
        return index >= 0 ? index - offset : -1;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    public ListIterator<E> listIterator(int index) {
        checkForComodification();
        rangeCheckForAdd(index);

        return new ListIterator<E>() {
            int cursor = index;
            int lastRet = -1;
            int expectedModCount = SubArrayList.this.modCount;

            public boolean hasNext() {
                return cursor != SubArrayList.this.size;
            }

            @SuppressWarnings("unchecked")
            public E next() {
                checkForComodification();
                int i = cursor;
                if (i >= SubArrayList.this.size)
                    throw new NoSuchElementException();
                Object[] elementData = root.elementData;
                if (offset + i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i + 1;
                return (E) elementData[offset + (lastRet = i)];
            }

            public boolean hasPrevious() {
                return cursor != 0;
            }

            @SuppressWarnings("unchecked")
            public E previous() {
                checkForComodification();
                int i = cursor - 1;
                if (i < 0)
                    throw new NoSuchElementException();
                Object[] elementData = root.elementData;
                if (offset + i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i;
                return (E) elementData[offset + (lastRet = i)];
            }

            public int nextIndex() {
                return cursor;
            }

            public int previousIndex() {
                return cursor - 1;
            }

            public void remove() {
                if (lastRet < 0)
                    throw new IllegalStateException();
                checkForComodification();

                try {
                    SubArrayList.this.remove(lastRet);
                    cursor = lastRet;
                    lastRet = -1;
                    expectedModCount = SubArrayList.this.modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }

            public void set(E e) {
                if (lastRet < 0)
                    throw new IllegalStateException();
                checkForComodification();

                try {
                    root.set(offset + lastRet, e);
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }

            public void add(E e) {
                checkForComodification();

                try {
                    int i = cursor;
                    SubArrayList.this.add(i, e);
                    cursor = i + 1;
                    lastRet = -1;
                    expectedModCount = SubArrayList.this.modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }

            final void checkForComodification() {
                if (root.modCount != expectedModCount)
                    throw new ConcurrentModificationException();
            }
        };
    }

    public List<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubArrayList<>(this, fromIndex, toIndex);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException(index);
    }

    private void checkForComodification() {
        if (root.modCount != modCount)
            throw new ConcurrentModificationException();
    }

    private void updateSizeAndModCount(int sizeChange) {
        SubArrayList<E> slist = this;
        do {
            slist.size += sizeChange;
            slist.modCount = root.modCount;
            slist = slist.parent;
        } while (slist != null);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        checkForComodification();
        root.sortRange(c, offset, offset + size);
        updateSizeAndModCount(0);
    }
}

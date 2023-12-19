package org.example;

import java.util.Arrays;
import java.util.Iterator;

public class CustomArrayList<T> implements Iterable<T> {

    private Object[] elements;
    public int size;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private void removeAt(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, 2 * size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return (T) elements[currentIndex++];
            }
        };
    }
    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(elements[i]);
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}

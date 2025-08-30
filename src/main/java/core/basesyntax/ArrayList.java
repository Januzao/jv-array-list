package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size >= dataArray.length) {
            grow();
        }

        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size >= dataArray.length) {
            grow();
        }

        for (int i = size; i > index; i--) {
            dataArray[i] = dataArray[i - 1];
        }

        dataArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > dataArray.length) {
            grow();
        }

        for (int i = 0; i < list.size(); i++) {
            dataArray[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        final T removed = (T) dataArray[index];

        for (int i = index; i < size - 1; i++) {
            dataArray[i] = dataArray[i + 1];
        }

        dataArray[--size] = null;

        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(dataArray[i], element)) {
                final T removed = (T) dataArray[i];

                for (int j = i; j < size - 1; j++) {
                    dataArray[j] = dataArray[j + 1];
                }

                dataArray[--size] = null;

                return removed;
            }
        }

        throw new NoSuchElementException("No element to remove");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int oldSize = dataArray.length;
        int newSize = oldSize + (oldSize << 1);
        dataArray = Arrays.copyOf(dataArray, newSize);
    }
}

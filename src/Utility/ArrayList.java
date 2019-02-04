package Utility;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements Iterable<T> {

    private static final int INITIAL_SIZE = 10;
    private static final double MULTIPLIER = 1.5;

    private T[] array = (T[]) new Object[INITIAL_SIZE];
    int arrSize = 0;

    public int size(){
        return arrSize;
    }

    public void add(T element) {
        if (arrSize == array.length){
            array = Arrays.copyOf(array, (int) (arrSize*MULTIPLIER));
        }
        array[arrSize++] = element;
    }

    public void set(int index, T element) throws IndexOutOfBoundsException{
        if (index < 0 || index > arrSize) throw new IndexOutOfBoundsException();
        array[index] = element;
    }

    public T get(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > arrSize) throw new IndexOutOfBoundsException();
        return array[index];
    }

    public void remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > arrSize) throw new IndexOutOfBoundsException();
        if (index == arrSize - 1){
            array[--arrSize] = null;
        } else {
            System.arraycopy(array, index+1, array, index, --arrSize - index);
        }
    }

    public T[] getArray(){
        return Arrays.copyOf(array, arrSize);
    }

    public boolean isEmpty(){
        return arrSize == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(this);
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "array=" + Arrays.toString(getArray()) +
                '}';
    }

    private class ArrayListIterator<E> implements Iterator<E> {
        private int position = 0;
        private ArrayList<E> array;

        private ArrayListIterator(ArrayList<E> array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return position < array.arrSize;
        }

        @Override
        public E next() {
            if (this.hasNext()) return array.get(position++);
            return null;
        }
    }

}

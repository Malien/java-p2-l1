package Utility;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Home-made implementation of ArrayList
 * @param <T> the type stored inside of array
 * @author Yaroslav Petryk
 */
public class ArrayList<T> implements Iterable<T> {

    private static final int INITIAL_SIZE = 10;
    private static final double MULTIPLIER = 1.5;

    private T[] array = (T[]) new Object[INITIAL_SIZE];
    private int arrSize = 0;

    /**
     * Get the size of array
     * @return the current size of array
     */
    public int size(){
        return arrSize;
    }

    /**
     * Adds element to the end of array
     * @param element the alement to be added
     */
    public void add(T element) {
        if (arrSize == array.length){
            array = Arrays.copyOf(array, (int) (arrSize*MULTIPLIER));
        }
        array[arrSize++] = element;
    }

    /**
     * Extend array with another array
     * @param arrayList that will provide data from which it should be extended
     */
    public void extend(ArrayList<T> arrayList){
        if (array.length < arrayList.size() + arrSize){
            array = Arrays.copyOf(array, arrayList.size() + arrSize);
        }
        for (T entry : arrayList){
            add(entry);
        }
    }

    /**
     * Set the element to a new one
     * @param index position where to set the element
     * @param element element to be set
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, T element) throws IndexOutOfBoundsException{
        if (index < 0 || index > arrSize) throw new IndexOutOfBoundsException();
        array[index] = element;
    }

    /**
     * Gets the element at index
     * @param index place of an element
     * @return element in the list
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > arrSize) throw new IndexOutOfBoundsException();
        return array[index];
    }

    /**
     * Removes the element from the list
     * Note: The list is reorganized such that there will be not gaps
     * @param index place of an element to be deleted
     * @throws IndexOutOfBoundsException
     */
    public void remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > arrSize) throw new IndexOutOfBoundsException();
        if (index == arrSize - 1){
            array[--arrSize] = null;
        } else {
            System.arraycopy(array, index+1, array, index, --arrSize - index);
        }
    }

    /**
     * Get standart array of elements
     * @return array containing all elements
     */
    public T[] getArray(){
        return Arrays.copyOf(array, arrSize);
    }

    /**
     * Get whether or not array is empty
     * @return if empty true else false
     */
    public boolean isEmpty(){
        return arrSize == 0;
    }

    /**
     * Get the iterator of the ArrayList
     * @return iterator of type same to one stored in ArrayList
     */
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

    public String toPlainString(){
        return Arrays.toString(getArray());
    }

    /**
     * implementation of sublist
     * @param from
     * @param to
     * @return
     * @author Rozhko Andrew
     */
    public ArrayList subList(int from, int to){
        ArrayList res = new ArrayList();
        for (int i = from; i < to; i++){
            res.add(this.get(i));
        }
        return res;
    }

    public ArrayList addAll(ArrayList list){
        ArrayList res = this;
        for (Object t : list){
            res.add(t);
        }
        return res;
    }

    /**
     * Imoplementation of the ArrayList iterator
     * @param <E> the type of the elements stored
     * @author Yaroslav Petryk
     */
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

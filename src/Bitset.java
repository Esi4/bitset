import java.util.*;

public class Bitset<T> {
    public Object[] bitset;
    private final int size;

    public Bitset(int size) {
        this.size = size;
        this.bitset = new Object[size];
        element(size);
    }

    public void element(int size) { //заполняет весь сет null
        for (int i = 0; i < size; i++) {
            bitset[i] = null;
        }
    }

    public void add(T x) { //добавление элемента в сет
        full();
        if(x == null) throw new IllegalArgumentException("Input is null");
        else {
            for (int i = 0; i < size; i++) {
                if (bitset[i] == null) {
                    bitset[i] = x;
                    break;
                }
            }
        }
    }

    public T get(int x) { //возвращает элемент
        return (T) bitset[x];
    }

    public Object[] getBitSet() { //возвращает сам сет
        return bitset;
    }

    public int getSize() { //возвращает размер сета
        return size;
    }

    public void add(Object[] objects) { //объединение двух сетов
        full();
        for (Object object : objects) {
            for (int j = 0; j < size; j++) {
                if (object != null) {
                    if (bitset[j] == null) {
                        bitset[j] = object;
                        break;
                    }
                }
            }
        }
    }

    public boolean full() { //проверка на полностью заполненный сет
        int n = 0;
        for (int i = 0; i < size; i++) {
            if (bitset[i] == null) {
                n++;
            }
        }
        return n != 0;
    }

    public Bitset<T> crossing(Bitset twoSet) { //пересечение
        int min = Math.min(twoSet.size, size);
        Bitset<T> intersectSet = new Bitset<T>(min);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < twoSet.size; j++) {
                if(bitset[i].equals(twoSet.bitset[j])) {
                    intersectSet.add((T) bitset[i]);
                }
            }
        }
        return intersectSet;
    }

    public Bitset<T> addition(Bitset<T> twoSet) { //дополнение
        int min = Math.min(twoSet.size, size);
        Bitset<T> addSet = new Bitset<T>(min);

        for (int i = 0; i < size; i++) {
            int n = 0;
            int g = 0;
            for (int j = 0; j < twoSet.size; j++) {
                if(bitset[i].equals(twoSet.bitset[j])) {
                    n++;
                }
                if(twoSet.bitset[i].equals(bitset[j])) {
                    g++;
                }
            }
            if(n == 0) {
                addSet.add((T) bitset[i]);
            }
            if(g == 0) {
                addSet.add((T) twoSet.bitset[i]);
            }
        }
        return addSet;
    }

    public void empty() { //проверка на полностью пустой сет
        int n = 0;
        for (int i = 0; i < size; i++) {
            if (bitset[i] == null) {
                n++;
            }
        }
        if(n == size) throw new IllegalArgumentException("Bitset is empty");
    }

    public void remove(T x) { //удаляет конкретный объект
        empty();
        for (int i = 0; i < size; i++) {
            if(bitset[i] == x) {
                bitset[i] = null;
            }
        }
    }

    public void remove(int x){ //удаляет объект из сета по индексу
        empty();
        bitset[x] = null;
    }

    public void remove(Object[] o) { //удаляет сразу несколько объектов из сета
        empty();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < o.length; j++) {
                if(bitset[i] != null && o[j] != null && bitset[i] == o[j]) {
                    remove((T) bitset[i]);
                }
            }
        }
    }

    public void removeAllSet(Bitset<T> bi3) { //полностью очищает сет
        for (int i = 0; i < bi3.size; i++) {
            bi3.bitset[i] = null;
        }
    }

    public boolean contains(T x) { //проверка на содержание одного объекта в сете
        empty();
        for (int i = 0; i < size; i++) {
            if(bitset[i] == x) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object[] o) { //проверка на содержание нескольких объектов в сете
        int n = 0;
        for (int i = 0; i < o.length; i++) {
            if(contains((T) o[i])) {
                n++;
            }
        }
        if(n == o.length) {
            return true;
        } else return false;
    }


    public Iterator<Integer> iterator() { //итератор
        return new BitsetIterator();
    }

    private class BitsetIterator implements Iterator<Integer> {
        int n = 0;

        @Override
        public boolean hasNext() {
            return n < bitset.length;
        }

        @Override
        public Integer next() {
            if(!hasNext()) {
                throw new NoSuchElementException("No more elements in BitSet");
            }
            return (Integer) bitset[n++];
        }
    }
}

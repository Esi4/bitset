import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class Test {
    Bitset<String> bi1;
    Bitset<String> bi2;
    Bitset<Integer> bi3;

    void newSet() { //готовый заполненный сет
        bi1 = new Bitset<String>(4);
        bi1.add("x");
        bi1.add("25");
        bi1.add("hello");
        bi1.add("veins");
    }

    @org.junit.jupiter.api.Test
    void add() {
        newSet();
        assertFalse(bi1.full()); //тест на полностью заполненный сет
        assertEquals("25", bi1.get(1)); //тест на возвращение элемента по индексу
        bi1.removeAllSet(bi1);


        bi2 = new Bitset<String>(4);
        bi2.add("x");
        bi2.add("Halestorm");
        Object obj = new Object[]{"ssc", "42"};
        bi2.add((Object[]) obj);
        assertArrayEquals(new Object[]{"x", "Halestorm", "ssc", "42"}, bi2 .getBitSet()); //тест на добавления к сету несколько объектов
    }

    @org.junit.jupiter.api.Test
    void crossingTest() {
        newSet();
        bi2 = new Bitset<String>(4);
        bi2.add("x");
        bi2.add("Halestorm");
        bi2.add("veins");
        bi2.add("a");

        assertArrayEquals(new Object[]{"x", "veins", null, null}, bi1.crossing(bi2).getBitSet());
        bi1.removeAllSet(bi1);
    }

    @org.junit.jupiter.api.Test
    void additionTest() {
        newSet();
        bi2 = new Bitset<String>(4);
        bi2.add("x");
        bi2.add("Halestorm");
        bi2.add("veins");
        bi2.add("a");

        assertArrayEquals(new Object[]{"25","Halestorm", "hello", "a" }, bi1.addition(bi2).getBitSet());
        bi1.removeAllSet(bi1);
    }

    @org.junit.jupiter.api.Test
    void remove() {
        newSet();  //тест на удаления элемента из сета
        bi1.remove("veins");
        assertArrayEquals(new Object[]{"x", "25", "hello", null}, bi1.getBitSet());

        bi1.remove(1); //тест на удаления элемента по индексу
        assertArrayEquals(new Object[]{"x", null, "hello", null}, bi1.getBitSet());
        bi1.removeAllSet(bi1);

        newSet(); //тест на удаление из сета несколько объектов
        bi1.remove(new Object[]{"25", "hello"});
        assertArrayEquals(new Object[]{"x", null, null, "veins"}, bi1.getBitSet());
        bi1.removeAllSet(bi1);

    }

    @org.junit.jupiter.api.Test
    void contains() {
        newSet();
        assertTrue(bi1.contains("x")); //тест на соержание в массиве объекта по самому объекту
        assertFalse(bi1.contains("glob"));
        assertTrue(bi1.contains(new Object[] {"x", "hello"})); //тест на содержание нескольких объектов в сете
        bi1.removeAllSet(bi1);

    }

    @org.junit.jupiter.api.Test
    void iterator() {
        bi3 = new Bitset<Integer>(4);
        bi3.add(1);
        bi3.add(2);
        bi3.add(3);
        bi3.add(4);
        Iterator it = bi3.iterator();

        int n = 1;

        while(it.hasNext()) {
            assertEquals(n, it.next());
            n++;
        }

    }


}

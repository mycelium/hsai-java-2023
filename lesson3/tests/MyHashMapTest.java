import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void putWithSameKeys() {
        var test = new MyHashMap();

        test.put("one", new Point(1, 1));
        test.put("one", new Point(3, 3));

        assertEquals(test.getNumberOfElements(), 1);
    }

    @Test
    void checkFunctionsAfterRemoval() {
        var test = new MyHashMap();

        test.put("one", new Point(1, 1));
        test.put("three", new Point(2, 2));

        test.remove("one");

        assertAll(
                () -> assertNull(test.get("one")),
                () -> assertFalse(test.contains("one")),
                () -> assertEquals(test.getSafe("one"), Optional.empty()),
                () -> assertEquals(test.getOrElse("one", new Point(10, 10)), new Point(10, 10))
        );

    }

    @Test
    void getSimple() {

        var test = new MyHashMap();

        test.put("one", new Point(1, 1));
        test.put("three", new Point(2, 2));

        assertAll(
                () -> assertNotNull(test.get("one")),
                () -> assertNull(test.get("eleven")),
                () -> assertNotNull(test.get("three")),
                () -> assertEquals(test.get("one"), test.get("one")),
                () -> assertNotEquals(test.get("one"), test.get("three"))
        );
    }

    @Test
    void getSafeSimple() {
        var test = new MyHashMap();

        test.put("one", new Point(1, 1));
        test.put("three", new Point(2, 2));

        assertAll(
                () -> assertNotNull(test.get("one")),
                () -> assertNull(test.get("eleven")),
                () -> assertNotNull(test.get("three")),
                () -> assertEquals(test.get("one"), test.get("one")),
                () -> assertNotEquals(test.get("one"), test.get("three"))
        );
    }

    @Test
    void getOrElseSimple() {
        var test = new MyHashMap();

        test.put("one", new Point(-1, 1));
        test.put("three", new Point(2, 2));
        test.put("low", new Point(-100, 100));

        Point elsePoint = new Point(10, 10);

        assertAll(
                () -> assertNotNull(test.getOrElse("one", elsePoint)),
                () -> assertEquals(test.getOrElse("eleven", elsePoint), elsePoint),
                () -> assertEquals(test.getOrElse("three", elsePoint), new Point(2, 2)),
                () -> assertNotEquals(test.getOrElse("one", elsePoint), test.getOrElse("low", elsePoint))
        );
    }

    @Test
    void containsOrNotContains() {
        var test = new MyHashMap();

        test.put("one", new Point(-1, 1));

        assertTrue(test.contains("one"));
        assertFalse(test.contains("eleven"));
    }

    @Test
    void remove() {
        var test = new MyHashMap();

        String[] keys = {"one","three"};

        test.put(keys[0], new Point(-1, 1));
        test.put(keys[1], new Point(2, 2));

        test.remove("one");
        test.remove("three");

        assertTrue(test.hashTable[(keys[0].hashCode() & 0x7fffffff) % test.getTableSize()].isTombstone());
        assertTrue(test.hashTable[(keys[1].hashCode() & 0x7fffffff) % test.getTableSize()].isTombstone());

        assertEquals(test.remove("one"),Optional.empty());
        assertEquals(test.remove("love"),Optional.empty());

    }

    @Test
    void removeAndResize(){
        var test = new MyHashMap();

        String[] keys = {"one","eleven","one hundred and one","one thousand one hundred and one","peace","live","AllAroundTheWorld"};
        Point[] values = {new Point(1,1), new Point(11,11),new Point(111,111),new Point(1111,1111),new Point(20,22),new Point(20,14),new Point(-1,-1)};

        for(int i = 0; i<3;i++){
            test.put(keys[i],values[i]);
        }

        assertAll(
                ()-> assertEquals(test.remove(keys[2]),Optional.of(values[2])),
                ()-> assertEquals(test.remove(keys[0]),Optional.of(values[0])),
                ()-> assertEquals(test.remove(keys[1]),Optional.of(values[1]))
        );

        test.setSearchTechnique(SearchTechniques.LINEAR);
        test.setStep(11);

        for(int i = 0;i<3;i++) {
            assertNull(test.get(keys[i]));
        }

        for(int i = 0; i<7;i++){
            test.put(keys[i],values[i]);
        }
        assertEquals(test.getNumberOfElements(),7);
        assertEquals(test.getNumberOfTombStones(),0);

        for(int i = 0; i<7;i++){
            assertEquals(test.get(keys[i]),values[i]);
        }

    }

    @Test
    void checkGetForDifferentTechniques(){
        var test = new MyHashMap();
        test.setSearchTechnique(SearchTechniques.QUADRATIC);

        test.put("one",new Point(1,1));
        test.put("cat",new Point(3,3));
        test.put("dog",new Point(9,9));

        assertEquals(test.get("one"),new Point(1,1));
        assertNull(test.get("oneOneONE"));
        assertEquals(test.get("dog"),new Point(9,9));

        test.setSearchTechnique(SearchTechniques.LINEAR);

        assertDoesNotThrow(()->test.setStep(11));

        assertEquals(test.get("cat"),new Point(3,3));
        assertNull(test.get("catCatCAT"));
        assertEquals(test.get("dog"),new Point(9,9));

    }
    @Test
    void checkSetStep(){
        var test = new MyHashMap();
        assertAll(
                ()-> assertThrows(IllegalArgumentException.class,()->test.setStep(test.getTableSize()*2)),
                ()-> assertThrows(IllegalArgumentException.class,()->test.setStep(test.getTableSize()*-1))
        );
        assertDoesNotThrow(()->test.setStep(test.getTableSize()-1));
    }
}
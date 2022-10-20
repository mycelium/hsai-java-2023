package main;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @org.junit.jupiter.api.Test
    void get() {
        MyHashMap map = new MyHashMap();
        map.put("One ring to rule them all",new Point(654,432));
        assertEquals(map.get("One ring to rule them all"),new Point(654, 432));
        assertEquals(map.get("The ring of power"),null);
    }

    @org.junit.jupiter.api.Test
    void put(){
        MyHashMap map = new MyHashMap();
        String str1="Hello,world", str2="aaa", str3="aaaa";
        map.put(str1,new Point(0,1));
        assertEquals(map.get(str1),new Point(0,1));
        map.put(str2,new Point(6,1));
        map.put(str3,new Point(7,1));
        assertAll(
                ()->assertNotEquals(str2,str3),
                ()->assertNotEquals(str1,str3),
                ()->assertNotEquals(str1,str2)
        );
    }

    @org.junit.jupiter.api.Test
    void getOrElse() {
        MyHashMap map = new MyHashMap();
        map.put("elf", new Point(-1, 1));
        map.put("dwarf", new Point(2, 2));
        map.put("Man", new Point(-100, 100));

        Point elsePoint = new Point(10, 10);

        assertAll(
                () -> assertNotNull(map.getOrElse("elf", elsePoint)),
                () -> assertEquals(map.getOrElse("Kingdom", elsePoint), elsePoint),
                () -> assertEquals(map.getOrElse("dwarf", elsePoint), new Point(2, 2)),
                () -> assertNotEquals(map.getOrElse("elf", elsePoint), map.getOrElse("Man", elsePoint))
        );
    }

    @org.junit.jupiter.api.Test
    void getSafe() {
        MyHashMap map = new MyHashMap();
        map.put("Hello,world", new Point(11,11));
        map.put("userdefault", new Point(13,13));
        assertAll(
                ()->assertNull(map.get("USERDEFAULT")),
                ()->assertNull(map.get("elf")),
                ()->assertNotNull(map.get("Hello,world")),
                ()->assertNotNull(map.get("userdefault"))
        );
    }

    @org.junit.jupiter.api.Test
    void remove() {
        MyHashMap map = new MyHashMap();
        map.put("smth",new Point(1,2));
        map.put("smbd",new Point(3,2));
        assertTrue(map.contains("smth"));
        assertTrue(map.contains("smbd"));
        map.remove("smth");
        assertTrue(map.contains("smbd"));
        map.remove("smbd");
        assertFalse(map.contains("smth"));
        assertFalse(map.contains("smbd"));
    }

    @org.junit.jupiter.api.Test
    void contains() {
        MyHashMap map = new MyHashMap();
        map.put("1",new Point(1,1));
        map.put("2",new Point(2,2));
        map.put("11",new Point(11,11));
        assertAll(
                ()->assertTrue(map.contains("1")),
                ()->assertTrue(map.contains("2")),
                ()->assertTrue(map.contains("11")),
                ()->assertFalse(map.contains("13")),
                ()->assertFalse(map.contains("testcase"))
        );
    }

    @org.junit.jupiter.api.Test
    void setStep() {
        MyHashMap map = new MyHashMap();
        map.setStep(10);
        assertEquals(map.getStep(),10);
        map.setStep(-2);
        assertNotEquals(map.getStep(),-2);
        map.setStep(0);
        assertNotEquals(map.getStep(),0);
    }

    @org.junit.jupiter.api.Test
    void setLookupStrategy() {
        MyHashMap map = new MyHashMap();
        map.setLookupStrategy("linear");
        assertEquals(map.getLookupStrategy(),"linear");
        map.setLookupStrategy("quadr");
        assertEquals(map.getLookupStrategy(),"quadr");
        map.setLookupStrategy("enum");
        assertEquals(map.getLookupStrategy(),"enum");
        map.setLookupStrategy("smthWrong");
        assertNotEquals(map.getLookupStrategy(),"smthWrong");
    }
    @org.junit.jupiter.api.Test
    void getLookupStrategy(){
        MyHashMap map = new MyHashMap();
        map.setLookupStrategy("linear");
        assertEquals(map.getLookupStrategy(),"linear");
        map.setLookupStrategy("quadr");
        assertEquals(map.getLookupStrategy(),"quadr");
        map.setLookupStrategy("enum");
        assertEquals(map.getLookupStrategy(),"enum");
        map.setLookupStrategy("smthWrong");
        assertNotEquals(map.getLookupStrategy(),"smthWrong");
    }
    @org.junit.jupiter.api.Test
    void getStep(){
        MyHashMap map = new MyHashMap();
        map.setStep(5);
        assertEquals(map.getStep(),5);
        map.setStep(-1);
        assertNotEquals(map.getStep(),-1);
        map.setStep(0);
        assertNotEquals(map.getStep(),0);
    }
}
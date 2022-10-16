public class Main {

    public static void main(String[] args) {

        OrdinaryInterface table1 = new MyHashMap();
        table1.setSearchTechnique(SearchTechniques.QUADRATIC);
        //table1.setStep(100);

        LinearInterface table2 = new MyHashMap();
        table1.setSearchTechnique(SearchTechniques.LINEAR);
        table2.setStep(11);

        table1.put("Homework", new Point(19, 19));
        table2.put("HYUKOH", new Point(0, 0));

        boolean check = table1.contains("Homework");
        check = table2.contains("HYUKOH");

        table1.remove("Homework");
        table2.remove("HYUKOH");

        check = table1.contains("Homework");
        check = table2.contains("HYUKOH");

    }
}
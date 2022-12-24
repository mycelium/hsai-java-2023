package lesson8.toml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class TOMLParser {
    private final Map<String, TOMLElement> table;
    private final BufferedReader bufferedReader;
    private String currentLine;

    public TOMLParser(String fileName) throws FileNotFoundException {

        this.table = new LinkedHashMap<>();
        FileReader fileReader = new FileReader(fileName);
        this.bufferedReader = new BufferedReader(fileReader);
        this.currentLine = "";
    }
    private TOMLElement parseTOMLElement(TOMLElementType currentType) throws IOException {

        TOMLElement element = null;

        switch (currentType) {
            case KEY_VALUE -> element = parseKeyValue();
            case ARRAY_OF_TABLES -> element = parseArrayOfTables();
            case TABLE -> element = parseTable();
            default -> System.out.println("Something went wrong!");
        }
        return element;
    }
    public void parse() throws IOException {

        while(bufferedReader.ready()&&!currentLine.isEmpty()){

            currentLine = bufferedReader.readLine();

            TOMLStringBreaker stringBreaker = new TOMLStringBreaker(currentLine);

            TOMLElementType currentType = stringBreaker.checkType();

            if(currentType!= TOMLElementType.EMPTY){
                TOMLElement element = parseTOMLElement(currentType);
                table.put(element.getKey(), element);
            }
        }
    }
    private TOMLElement parseTable() throws IOException {

        TOMLElement element = new TOMLElement(lesson8.toml.TOMLElementType.TABLE);

        Map<String, Object> map = new LinkedHashMap<>();

        String s = currentLine.replaceAll("\\[","").replaceAll("]","");

        currentLine = bufferedReader.readLine();

        while(currentLine!=null&&!currentLine.isEmpty()){

              TOMLStringBreaker stringBreaker = new TOMLStringBreaker(currentLine);

              TOMLElementType currentType = stringBreaker.checkType();

              TOMLElement e = parseTOMLElement(currentType);

              map.put(e.getKey(),e);

              currentLine = bufferedReader.readLine();
        }
        element.setValue(s,map);
        return element;
    }
    private TOMLElement parseKeyValue(){

        TOMLElement element = new TOMLElement(lesson8.toml.TOMLElementType.KEY_VALUE);

        String[] s = currentLine.split("=",2);

        TOMLStringBreaker stringBreaker = new TOMLStringBreaker(s[1]);

        Object tt = stringBreaker.returnValue();

        element.setValue(s[0].replaceAll("\\s",""),tt);

        return element;
    }

    private TOMLElement parseArrayOfTables() throws IOException {

        TOMLElement element = new TOMLElement(lesson8.toml.TOMLElementType.ARRAY_OF_TABLES);

        List<Map<String,TOMLElement>> array = new ArrayList<>();

        String s = currentLine.replaceAll("\\[","").replaceAll("]","");

        currentLine = bufferedReader.readLine();

        while(currentLine!=null&&!currentLine.isEmpty()){
            TOMLStringBreaker stringBreaker = new TOMLStringBreaker(currentLine);

            TOMLElementType currentType = stringBreaker.checkType();

            TOMLElement e = parseTOMLElement(currentType);

            Map<String,TOMLElement> hm = new LinkedHashMap<>();

            hm.put(e.getKey(),e);

            array.add(hm);

            currentLine = bufferedReader.readLine();
        }
        element.setValue(s,array);
        return element;
    }
    public void putIntoJSON(String fileName){
    }
}

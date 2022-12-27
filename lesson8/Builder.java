package lesson8;

import lesson8.json.JSONParser;
import lesson8.toml.TOMLParser;

import java.io.*;

public class  Builder {
    public static void builder(String fileName1, String file1,String fileName2,String file2) throws IOException {

        FileInputStream iFile = new FileInputStream(fileName1);
        FileOutputStream oFile = new FileOutputStream(fileName2);

        if(file1.equals(file2)){
            int c;
            while((c = iFile.read())!=-1){
                oFile.write(c);
            }
            iFile.close();
            oFile.close();
        }
        else {
            switch (file1) {
                case "TOML" -> {
                    TOMLParser tomlParser = new TOMLParser(fileName1);
                    tomlParser.parse();
                    tomlParser.putIntoJSON(fileName2);
                }
                case "JSON" -> {
                    JSONParser jsonParser = new JSONParser(fileName1);
                    jsonParser.parse();
                    jsonParser.putIntoTOML(fileName2);
                }
                default -> System.out.println("Something went wrong!");
            }
        }

    }
}


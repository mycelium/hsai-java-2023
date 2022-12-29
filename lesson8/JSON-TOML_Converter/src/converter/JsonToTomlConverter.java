package converter;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonToTomlConverter {

    private static StringBuilder sbToml = new StringBuilder();
    public static String convert(String json) {
        StringBuilder sb = new StringBuilder(json.substring(1,json.length()-1));
        int nesting = 0;
        for (int i = 0; i< sb.length(); i++) {
            if (sb.charAt(i) =='[' || sb.charAt(i)=='{')
                nesting++;
            if (sb.charAt(i) ==']' || sb.charAt(i)=='}')
                nesting--;
            if (nesting != 0 && sb.charAt(i) == ',')
                sb.setCharAt(i, ';');
        }
        String[] objects = sb.toString().trim().split(",");
        for (String s: objects) {
            String key = s.split(":")[0].trim();
            String value = s.split(":")[1].trim();
            switch (value.charAt(0)) {
                case ('['): {
                    sbToml.append('[' + key + "] = ");
                    convert(value);
                    break;
                }
                default:{
                    sbToml.append(key.substring(1, key.length() - 1) + " = " + value + '\n');
                    break;
                }
            }
        }
        return sbToml.toString();
    }

}

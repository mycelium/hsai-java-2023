package lesson8.homework.parsers.JSONParser;

import java.util.HashMap;

public class JSONObject {
    private final static char specialChar;
    private final static char commaChar;
    private HashMap<String, String> objects;

    static {
        specialChar = String.valueOf(CONSTANTS.SPECIAL).toCharArray()[0];
        commaChar = String.valueOf(CONSTANTS.COMMA).toCharArray()[0];
    }

    public JSONObject(String arg) {
        getJSONObjects(arg);
    }

    public void getJSONObjects(String arg) {
        objects = new HashMap<String, String>();
        if (arg.startsWith(String.valueOf(CONSTANTS.CURLY_OPEN_BRACKETS))
                && arg.endsWith(String.valueOf(CONSTANTS.CURLY_CLOSE_BRACKETS))) {

            StringBuilder builder = new StringBuilder(arg);
            builder.deleteCharAt(0);
            builder.deleteCharAt(builder.length() - 1);
            replaceCOMMA(builder);

            for (String objects : builder.toString().split(String.valueOf(CONSTANTS.COMMA))) {

                String[] objectValue = objects.split(String.valueOf(CONSTANTS.COLON), 2);

                if (objectValue.length == 2)
                    this.objects.put(objectValue[0].replace("\"", ""),
                            objectValue[1].replace("\"", ""));
            }
        }
    }

    private StringBuilder replaceCOMMA(StringBuilder arg) {
        boolean isJsonArray = false;
        for (int i = 0; i < arg.length(); i++) {
            char a = arg.charAt(i);
            if (isJsonArray) {
                if (String.valueOf(a).compareTo(String.valueOf(CONSTANTS.COMMA)) == 0) {
                    arg.setCharAt(i, specialChar);
                }
            }
            if (String.valueOf(a).compareTo(String.valueOf(CONSTANTS.SQUARE_OPEN_BRACKETS)) == 0)
                isJsonArray = true;
            if (String.valueOf(a).compareTo(String.valueOf(CONSTANTS.SQUARE_CLOSE_BRACKETS)) == 0)
                isJsonArray = false;
        }
        return arg;
    }

    public boolean checkIfJASONArray(String str) {
        if (str.indexOf(String.valueOf(CONSTANTS.SQUARE_OPEN_BRACKETS)) != -1
                && str.indexOf(String.valueOf(CONSTANTS.SQUARE_CLOSE_BRACKETS)) != -1) {
            return true;
        } else return false;
    }

    public JSONArray getJSONArray(String key) {
        if (objects != null)
            return new JSONArray(objects.get(key).replace('|', ','));
        return null;
    }

    public HashMap<String, String> getHashMap() {
        return objects;
    }
}

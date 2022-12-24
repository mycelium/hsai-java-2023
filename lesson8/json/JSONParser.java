package lesson8.json;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser{
    private final List<JSONElement> elements;
    private final JSONTokenizer tokenizer;
    public JSONParser(String fileName) throws IOException {
        tokenizer = new JSONTokenizer(fileName);
        elements = new ArrayList<>();
    }

    public void parse() {
        while (tokenizer.hasMoreTokens()) {
                Token token = tokenizer.getToken();
                switch (token.type()) {
                    case CURLY_OPEN -> {
                        JSONElement parsedObject = parseObject();
                        elements.add(parsedObject);
                    }
                    case ARRAY_OPEN -> {
                        JSONElement parsedList = parseList();
                        elements.add(parsedList);
                    }
                    case STRING -> elements.add(parseString(token.value()));
                    case NUMBER -> elements.add(parseNumber(token.value()));
                    case BOOLEAN -> elements.add(parseBoolean(token.value()));
                    default -> System.out.println("Something went wrong!");
                }
        }
    }
    private JSONElement parseObject(){
        JSONElement element = new JSONElement(Type.OBJECT);
        Map<String, JSONElement> value = new HashMap<>();
        boolean hasCompleted = false;
        while (!hasCompleted) {
            if (tokenizer.hasMoreTokens()) {
                Token nextToken = tokenizer.getToken();
                String key = nextToken.value();
                tokenizer.getToken();
                nextToken = tokenizer.getToken();
                switch (nextToken.type()) {
                    case STRING -> value.put(key, parseString(nextToken.value()));
                    case ARRAY_OPEN -> value.put(key, parseList());
                    case NUMBER -> value.put(key, parseNumber(nextToken.value()));
                    case CURLY_OPEN -> value.put(key, parseObject());
                    case BOOLEAN -> value.put(key, parseBoolean(nextToken.value()));
                    case NULL_TYPE -> value.put(key, parseNull());
                    default -> System.out.println("Something went wrong!");
                }
                nextToken = tokenizer.getToken();
                if (nextToken.type() == TokenType.CURLY_CLOSE) {
                    hasCompleted = true;
                }

            } else {
                System.out.println("No more tokens!");
            }
        }
        element.setValue(value);
        return element;
    }
    private JSONElement parseString(String value) {
        JSONElement element = new JSONElement(Type.STRING);
        element.setValue(value);
        return element;
    }
    private JSONElement parseNumber(String value) {
        JSONElement element = new JSONElement(Type.NUMBER);
        element.setValue(Integer.parseInt(value));
        return element;
    }

    private JSONElement parseList() {
        JSONElement element = new JSONElement(Type.LIST);
        List<JSONElement> currentList = new ArrayList<>();
        boolean hasCompleted = false;
        while (!hasCompleted) {
            if (!tokenizer.hasMoreTokens()) {
                System.out.println("No more tokens!");
            } else {
                Token nextToken = tokenizer.getToken();
                JSONElement node = null;
                switch (nextToken.type()) {
                    case ARRAY_OPEN -> node = parseList();
                    case CURLY_OPEN -> node = parseObject();
                    case STRING -> node = parseString(nextToken.value());
                    case NUMBER -> node = parseNumber(nextToken.value());
                    case BOOLEAN -> node = parseBoolean(nextToken.value());
                    case NULL_TYPE -> node = parseNull();
                    default -> System.out.println("Something went wrong!");
                }
                currentList.add(node);
                nextToken = tokenizer.getToken();
                if (nextToken.type() == TokenType.ARRAY_CLOSE) {
                    hasCompleted = true;
                }
            }
        }
        element.setValue(currentList);

        return element;
    }
    private JSONElement parseBoolean(String value){
        JSONElement element = new JSONElement(Type.BOOLEAN);
        element.setValue(value.equals("True"));
        return element;
    }
    private JSONElement parseNull(){
        JSONElement element = new JSONElement(Type.NULL_TYPE);
        element.setValue("null");
        return element;
    }

    public void putIntoTOML(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();

        new FileWriter(fileName, false).close();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        for(JSONElement value: elements){
            result.append(value.toTOMLString());
        }

        bufferedWriter.write(result.toString());
        bufferedWriter.close();
    }
}

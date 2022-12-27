package lesson8.json;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
enum Type { OBJECT, LIST, STRING, NUMBER, BOOLEAN, NULL_TYPE}
public class JSONElement {
    Boolean bValue;

    Integer iValue;

    String sValue;

    String nValue;

    List<JSONElement> lValue;

    Map<String, JSONElement> oValue;

    public Type getType() {
        return type;
    }
    Type type;
    JSONElement(Type type){
        this.type = type;
    }
    void setValue(Boolean b){
        this.bValue = b;
    }
    void setValue(Integer i){
        this.iValue = i;
    }
    void setValue(String s){
        if(type==Type.STRING) {
            this.sValue = s;
        }else{
            this.nValue = "null";
        }
    }
    void setValue(List<JSONElement> l){
        this.lValue = l;
    }
    void setValue(Map<String, JSONElement> o){
        this.oValue = o;
    }

    public String toString(int level){

        char[] str = new char[level];
        Arrays.fill(str,' ');

        String spaceString = new String(str);

        StringBuilder outputString = new StringBuilder();

        switch (type) {
            case STRING -> outputString.append(spaceString).append("\"").append(sValue).append("\"");
            case NUMBER -> outputString.append(spaceString).append(iValue);
            case BOOLEAN -> outputString.append(spaceString).append(bValue ? "true" : "false");
            case NULL_TYPE -> outputString.append(spaceString).append(nValue);
            case LIST -> {
                outputString.append(spaceString).append("[\n");
                int index = 0;
                for (var v : lValue){
                    outputString.append(v.toString(level + 1));
                    if (index < lValue.size() - 1){
                        outputString.append(",\n");
                    }
                    index++;
                }
                outputString.append("\n").append(spaceString).append("]");
            }
            case OBJECT -> {
                outputString.append(spaceString).append("{\n");
                int counter = 0;
                for(Map.Entry<String, JSONElement> entry : oValue.entrySet()) {
                    String key = entry.getKey();
                    outputString.append(spaceString).append(" ").append("\"").append(key).append("\"").append(": ");
                    JSONElement value = entry.getValue();
                    outputString.append(value.toString(0));
                    counter++;
                    if(counter != oValue.size()) {
                        outputString.append(",\n");
                    }else{
                        outputString.append(spaceString).append("\n");
                    }
                }
                outputString.append(spaceString).append("}");
            }
        }
        return outputString.toString();
    }

    private boolean checkIfAllOL(){
        if(type==Type.LIST){
            for(JSONElement value : lValue){
                if(value.getType()!=Type.OBJECT&&value.getType()!=Type.LIST)
                    return false;
            }
        }else return false;

        return true;
    }
    public String toTOMLString(){
        StringBuilder outputString = new StringBuilder();

        switch (type) {
            case STRING -> outputString.append("\"").append(sValue).append("\"");
            case NUMBER -> outputString.append(iValue);
            case BOOLEAN -> outputString.append(bValue ? "true" : "false");
            case NULL_TYPE -> outputString.append("\"" + "null" + "\"");
            case LIST -> {
                if(!checkIfAllOL()) {
                    int counter = 0;
                    outputString.append("[ ");
                    for (var v : lValue) {
                        counter++;
                        if(v.getType()==Type.OBJECT) outputString.append("{ ");
                        outputString.append(v.toTOMLString());
                        if(v.getType()==Type.OBJECT) {
                            outputString = new StringBuilder(outputString.substring(0, outputString.length() - 2));
                            outputString.append(" }");
                        }
                        if(counter<lValue.size())
                            outputString.append(" , ");
                    }
                    outputString.append(" ]\n");
                }else{
                    for (var v : lValue) {
                        outputString.append(v.toTOMLString());
                    }
                }
            }
            case OBJECT -> {
                for(Map.Entry<String, JSONElement> entry : oValue.entrySet()) {
                    String key = entry.getKey();

                    JSONElement value = entry.getValue();
                    switch(value.getType()){
                        case LIST -> {
                            if(value.checkIfAllOL()){
                                outputString.append("[[").append(key).append("]]\n");
                            }else{
                                outputString.append(key).append(" = ");
                            }
                            outputString.append(value.toTOMLString());
                        }
                        case OBJECT -> {
                            outputString.append("[").append(key).append("]\n");
                            outputString.append(value.toTOMLString());
                        }
                        default -> outputString.append(key).append(" = ").append(value.toTOMLString());
                    }
                    outputString.append("\n");
                }
                outputString.append("\n");
            }
        }
        return outputString.toString();
    }
}

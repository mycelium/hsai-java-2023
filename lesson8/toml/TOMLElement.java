package lesson8.toml;

enum TOMLElementType { TABLE, ARRAY_OF_TABLES, KEY_VALUE,EMPTY}

public class TOMLElement {

    public String getKey() {
        return key;
    }
    String key;
    Object value;
    TOMLElementType type;

    public TOMLElement(TOMLElementType type){
        this.type = type;
    }

    public void setValue(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}

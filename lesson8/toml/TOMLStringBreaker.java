package lesson8.toml;

import java.util.*;

public class TOMLStringBreaker {
    String buf;

    public TOMLStringBreaker(String s) {
         this.buf = s;
         buf = buf.replaceAll("\\s","");
    }

    TOMLElementType checkType(){

        if (buf.isEmpty()) {
            System.out.println("The Line is Empty!");
            return TOMLElementType.EMPTY;
        }

        char c = buf.charAt(0);

        if(buf.charAt(0)=='[') {
               if(buf.charAt(1)=='[') {
                   return TOMLElementType.ARRAY_OF_TABLES;
               }
               else return TOMLElementType.TABLE;
        }
            else if((c >= '0' && c <= '9')||(c>='a'&&c<='z')||c=='"') {
                return TOMLElementType.KEY_VALUE;
            }
        return TOMLElementType.EMPTY;
    }

    Object returnValue(){
        if(buf.isEmpty()){
            System.out.println("Something is wrong!");
            return Optional.empty();
        }
        else if(buf.charAt(0)=='"'){
            return buf.replace("\"","");
        }
        else if(buf.equals("true")||buf.equals("false")){
             return buf.equals("true");
        }
        else if((buf.charAt(0) >= '0' && buf.charAt(0) <= '9')||buf.charAt(0)=='-'){
            return Integer.parseInt(buf);
        }
        else if(buf.charAt(0)=='['){
            buf = buf.substring(1,buf.length()-1);
            return getArray();
        }
        else if(buf.charAt(0)=='{'){
            buf = buf.substring(1);
            return getInlineTable();
        }
        else {
            System.out.println("Something went wrong!");
            return Optional.empty();
        }
    }

    private Map<String,Object> getInlineTable(){
        Map<String,Object> map = new HashMap<>();
        buf = buf.replace("}","");
        String[] ss;
        if(buf.contains(",")) {
            ss = buf.split(",");
        }else{
            ss = new String[1];
            ss[0] = buf;
        }
        for(var o : ss){
            String[] oo = o.split("=");
            TOMLStringBreaker tokenizer = new TOMLStringBreaker(oo[1]);
            map.put(oo[0],tokenizer.returnValue());
        }
        return map;
    }

    private List<Object> getArray(){

        List<Object> list = new ArrayList<>();

        if(!buf.contains("{")) {
            String[] s = buf.split(",");
            for (String value : s) {
                TOMLStringBreaker token = new TOMLStringBreaker(value);
                list.add(token.returnValue());
            }
        }else{
            String[] s = buf.split("\\{");
            for (String value : s) {
                if (value.contains("}")) {
                    list.add(new TOMLStringBreaker(value).getInlineTable());
                } else {
                    String[] ss = value.split(",");
                    for (var o : ss) {
                        TOMLStringBreaker tokenizer = new TOMLStringBreaker(o);
                        list.add(tokenizer.returnValue());
                    }
                }
            }
        }
        return list;
    }
}

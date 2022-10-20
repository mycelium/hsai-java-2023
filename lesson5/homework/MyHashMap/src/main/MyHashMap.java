package main;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class MyHashMap {
    private int step;
    private ArrayList<HashItem> hashMap;//Dynamic array for hashMap values
    String searchType;
    public MyHashMap(){
        hashMap = new ArrayList<>();
        step = 1;
        searchType = "linear";
    }
    //generates hash code by input string
    //using first, last and middle symbs of string
    private int hashFunction(String str){
        int res;
        if(str == null)
            return -1;
        res = str.charAt(0)*str.charAt(str.length()-1)*str.charAt(str.length()/2);
        return res;
    }
    public Point get(String str){
        switch(searchType){
            case("linear"):return linearGet(str);
            case("quadr"):return quadrGet(str);
            case("enum"):return enumGet(str);
            default:;
        }
        return null;
    }
    //insert a pair of String and Point
    //if there is no such pair (nothing otherwise)
    public void put(String str, Point pt){
        int hashValue = hashFunction(str);
        boolean notInserted = true;
        while(hashValue>=hashMap.size()){
            hashMap.add(new HashItem(null,null));
        }
        while(notInserted){
            if(hashValue>=hashMap.size()){
                hashMap.add(new HashItem(str,pt));
                return;
            }
            if((hashMap.get(hashValue).str()==null)){
                hashMap.set(hashValue,new HashItem(str,pt));
                notInserted=false;
                break;
            }
            else if(Objects.equals(hashMap.get(hashValue).val(),pt)){
                notInserted = false;
                return;
            }
            hashValue++;
        }
        return;
    }
    public Point getOrElse(String str, Point pt){
        if(contains(str)) {
            return get(str);
        }
        return pt;
    }
    Optional<Point> getSafe(String str){
        return Optional.ofNullable(get(str));
    }
    Optional<Point>remove(String str){
        if(getSafe(str).isEmpty())
            return Optional.empty();
        int hashValue = hashFunction(str);
        if(hashValue>=hashMap.size())
            return Optional.empty();
        while(!Objects.equals(hashMap.get(hashValue),null)) {
            if (Objects.equals(hashMap.get(hashValue).str(), str)) {
                hashMap.set(hashValue,new HashItem(null,null));
                break;
            }
            hashValue++;
            if(hashValue>=hashMap.size())
                return Optional.empty();
        }
        if(hashValue+1>=hashMap.size())
            return Optional.empty();
        while(hashFunction(str)==hashFunction(hashMap.get(hashValue+1).str())){
            System.out.println(str+"   "+hashMap.get(hashValue+1).str());
            hashMap.set(hashValue,new HashItem(hashMap.get(hashValue+1).str(),
                    hashMap.get(hashValue+1).val()));
            hashValue++;
            if(hashValue+1>=hashMap.size())
                return Optional.empty();
            if(hashMap.get(hashValue+1).str()==null)
                return Optional.empty();
        }
        hashMap.set(hashValue,new HashItem(null,null));
        return Optional.empty();
    }
    boolean contains(String str){
        if(get(str)!=null)
            return true;
        return false;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int val){
        if(val>=1)
            step = val;
        return;
    }
    public void setLookupStrategy(String str){
        switch(str){
            case("linear"):searchType="linear";break;
            case("quadr"):searchType="quadr";break;
            case("enum"):searchType="enum";break;
            default:;
        }
    };
    private Point linearGet(String str){
        int hashValue = hashFunction(str);
        if(hashValue>=hashMap.size())
            return null;
        while(!Objects.equals(hashMap.get(hashValue),null)) {
            if (Objects.equals(hashMap.get(hashValue).str(), str)) {
                return hashMap.get(hashValue).val();
            }
            hashValue+=step;
            if(hashValue>=hashMap.size())
                return null;
        }
        return null;
    }
    private Point quadrGet(String str){
        int q = 1;
        int hashValue = hashFunction(str);
        if(hashValue>=hashMap.size())
            return null;
        while(!Objects.equals(hashMap.get(hashValue),null)) {
            if (Objects.equals(hashMap.get(hashValue).str(), str)) {
                return hashMap.get(hashValue).val();
            }
            q++;
            hashValue+=(q*q);
            if(hashValue>=hashMap.size())
                return null;
        }
        return null;
    }
    private Point enumGet(String str){
        int hashValue = hashFunction(str);
        if(hashValue>=hashMap.size())
            return null;
        while(!Objects.equals(hashMap.get(hashValue),null)) {
            if (Objects.equals(hashMap.get(hashValue).str(), str)) {
                return hashMap.get(hashValue).val();
            }
            hashValue++;
            if(hashValue>=hashMap.size())
                return null;
        }
        return null;
    }

    public String getLookupStrategy() {
        return searchType;
    }
}


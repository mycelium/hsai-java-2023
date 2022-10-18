package com.hsai.myfileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hsai.mypair.*;
import com.hsai.mypoint.*;

public class MyFileInput {
    /**
     * Method for extracting data from a txt file in {Key, Value}
     * format line by line.
     * 
     * @param file  name of txt file
     * @param regex regular expression
     * @param list  list of pairs of {Key, Value} to contain results
     * @return number of matched results
     */
    public static int readFile(String file, String regex, ArrayList<MyPair<String, MyPoint>> list) {
        int count = 0;
        try {
            FileReader ifstream = new FileReader(file);
            BufferedReader reader = new BufferedReader(ifstream);
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher match = Pattern.compile(regex).matcher(line);
                if (match.find()) {
                    list.add(new MyPair<String, MyPoint>(match.group(1),
                            new MyPoint(Integer.parseInt(match.group(2)), Integer.parseInt(match.group(2)))));
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
        return count;
    }
}

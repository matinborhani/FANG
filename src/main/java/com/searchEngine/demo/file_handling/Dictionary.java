package com.searchEngine.demo.file_handling;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    final static int MIN_LENGTH = 3 ;
    /*
Remove all words's length is 3 or less
 */
    public static List<String> removeBadWords(List<String> listStr){
        List<String> listStrFinal = new ArrayList<>();
        for (int i = 0 ; i<listStr.size();i++) {
            String strTemp = listStr.get(i);


            // ************************* convert arabic ک to persian
            if (strTemp.contains(String.valueOf((char) 1603)))
                strTemp = strTemp.replace((char) 1603, (char) 1705);
            if (strTemp.contains(String.valueOf((char) 1595)))
                strTemp = strTemp.replace((char) 1595, (char) 1705);

            // ************************* convert arabic ی to persian
            if (strTemp.contains(String.valueOf((char) 1610)))
                strTemp = strTemp.replace((char) 1610, (char) 1740);
            if (strTemp.contains(String.valueOf((char) 1609)))
                strTemp = strTemp.replace((char) 1609, (char) 1740);
            if (strTemp.contains(String.valueOf((char) 1597)))
                strTemp = strTemp.replace((char) 1597, (char) 1740);

            // ************************ check Length
            if (strTemp.length() >= MIN_LENGTH)
                listStrFinal.add(strTemp);


        }
        return listStrFinal;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\MatinB\\Desktop\\University\\Term 7\\Search Engine\\Project Shariat\\textonly002.txt");
        try {
            FileReader fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> stringList = Files.readLines(file, Charsets.UTF_8);
        String s = stringList.get(0);
        List<String> listWords = removeBadWords(Splitter.on(" ").trimResults().omitEmptyStrings().splitToList(s));
        for (String s1:listWords)
            System.out.println(s1);
    }
}

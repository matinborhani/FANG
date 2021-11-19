package com.searchEngine.demo.file_handling;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Peta {
    List<String> Dictionary_for_spellcheker = new ArrayList<>();
    final int MIN_LENGTH = 3;
    HashMap<String,List<Properties>> hashWords = new HashMap<>();
    int countAllDoc = 0;
    List<String> listWords = new ArrayList<>();
    final double RATIO_TITLE = 37;
    /*
    Remove all words's length is 3 or less
     */
    public List<String> removeBadWords(List<String> listStr){
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


    public HashMap<String,List<Properties>> countFrequeny(List<String> listStr, String URL
            , String docID, List<String> title , String strTitle,String body) {
        // for Body
        for (String word:listStr){
            if (hashWords.containsKey(word)) {
                List<Properties> propertiesList = hashWords.get(word);
                // if exist with this doc id
                if (propertiesList.get(propertiesList.size() - 1).docID.equals(docID))
                    propertiesList.get(propertiesList.size()-1).count++ ;
                else{
                    Properties newProperties = new Properties(URL,docID,1,strTitle,body);
                    propertiesList.add(newProperties);
                }
            }
            else {
                List<Properties> properties = new ArrayList<>();
                properties.add(new Properties(URL,docID,1,strTitle,body));
                hashWords.put(word, properties);
                listWords.add(word);
            }
        }

        // for Title
        for (String wordTitle : title) {
            if (hashWords.containsKey(wordTitle)) {
                List<Properties> propertiesList = hashWords.get(wordTitle);
                for (int i = 0; i < propertiesList.size(); i++) {
                    if (propertiesList.get(i).docID.equals(docID))
                        propertiesList.get(i).count += RATIO_TITLE;
                }
                hashWords.put(wordTitle, propertiesList);
                System.out.println();
            }
        }
        return hashWords;

    }

    public void setTFIDF() {
        for (int i = 0; i < listWords.size(); i++) {
            String word = listWords.get(i);
            List<Properties> propertiesWord = hashWords.get(word);
            int countDoc = propertiesWord.size();  // number of document that word appear them
            for (int j = 0; j < propertiesWord.size(); j++) {
                int countWord = propertiesWord.get(j).count; // number of word that exist in this document
                propertiesWord.get(j).TFIDF = calculateTFIDF(countDoc, countWord);
            }
            hashWords.put(word, propertiesWord);
        }
    }

    public double calculateTFIDF(int countDocWithWord, int countWord) {
        double result = (1 + Math.log10(countWord)) * Math.log10((double) this.countAllDoc / countDocWithWord);
        return result;
    }
    public HashMap<String,List<Properties>> getWords() throws IOException {
        File file = new File("C:\\Users\\MatinB\\Desktop\\University\\Term 7\\Search Engine\\Spring framework\\demo2\\demo\\textdocs0022.txt");
        try {
            FileReader fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> stringList = Files.readLines(file, Charsets.UTF_8);
        for (int i = 0 ; i < stringList.size() ; i++){
            // if i == 0 DOCID
            String docID = stringList.get(i);
            // if i == 1 URL
            i++ ;
            String URL = stringList.get(i);
            // if i == 2 Title
            i++;
            String title = stringList.get(i);
            List<String> listWordsTitle = removeBadWords(Splitter.on(" ").trimResults().omitEmptyStrings().splitToList(title));
            getDictionary_for_spellcheker().addAll(listWordsTitle);
            // if i ==3 Body
            i++ ;
            String body = stringList.get(i);
            List<String> listWordsBody = removeBadWords(Splitter.on(" ").trimResults().omitEmptyStrings().splitToList(body));

            countFrequeny(listWordsBody, URL, docID, listWordsTitle,title,body);

            // counter Document
            countAllDoc++;

            // add to properties
//            Properties properties = new Properties(URL,docID);
        }

        setTFIDF();


        return hashWords;
    }
    public List<String> getDictionary_for_spellcheker() {
        return Dictionary_for_spellcheker;
    }
}

package com.searchEngine.demo.file_handling;


import java.util.*;
import java.util.stream.Collectors;

public class SearchProcess {
    String inputStr;
    HashMap<String, List<Properties>> hashWords;
    Hashtable<String, List<Properties>> dictionaryResult;
    List<Properties> showList = null ;
    final int RATIO = 1;

    // ********** print a list of Properties
    public void print(List<Properties> propertiesList) {
        for (int i = 0; i < propertiesList.size(); i++) {
            System.out.println(propertiesList.get(i).getDocID());
            System.out.println(propertiesList.get(i).getUrl());
        }
    }
    // ************* adjust body section
    public List<Properties> printBody(List<Properties > propertiesSharing) {
        String suggestion = "   ...    ";
        String[] s = inputStr.split(" ");
        for (int i = 0 ; i<propertiesSharing.size() ; i++) {
            String bodyText = propertiesSharing.get(i).getBody();
            int index = bodyText.indexOf(inputStr);
            if (index < 0)
                index = bodyText.indexOf(s[0]);
            int startIdx = index - 40 ;
            int endIdx = index + 40 ;
            if (startIdx < 0) // if index go to 0 or negative
                startIdx = 0;
            if (endIdx > bodyText.length()) // if index go to size of text body or greater
                endIdx = bodyText.length();
            String substring = bodyText.substring(startIdx,endIdx);
            substring = suggestion.concat(substring);
            substring = substring.concat(suggestion);
            propertiesSharing.get(i).setBody(substring);
        }
        return propertiesSharing;
    }

    public SearchProcess(HashMap<String, List<Properties>> hashWords) {
        this.hashWords = hashWords;
    }

    // Old Version
    public void getData() {
        Scanner scanner = new Scanner(System.in);
        String inputSearch = scanner.nextLine();
        this.inputStr = inputSearch;
        search();
    }

    // New Version
    public void getData(String str){
        this.inputStr = str;
        search();
    }

    public List<Properties> getResult(){
        return showList;
    }


    public List<Properties> findSharing(String[] s) {
        int sizeDictionary = dictionaryResult.size();
        List<Properties> sharingDocs = null;
        if (sizeDictionary <= 0)
            print(dictionaryResult.get(inputStr));
        else {
            sharingDocs = dictionaryResult.get(s[0]);
            for (int i = 1 ; i < s.length; i++) {
                int j = i ;
                sharingDocs = sharingDocs.stream().filter(properties -> properties.contain(
                        dictionaryResult.get(s[j]))).collect(Collectors.toList());
            }
        }
        calculateTFIDF(sharingDocs, dictionaryResult, s);
        return sharingDocs;
    }

    public void calculateTFIDF(List<Properties> propertiesListSharing, Hashtable<String, List<Properties>> dictionaryResult, String[] stringsInput) {
        for (int i = 0; i < propertiesListSharing.size(); i++) {
            Properties properties = propertiesListSharing.get(i);
            properties.TFIDF = properties.getTFIDF() * RATIO;
            for (int j = 1; j < stringsInput.length; j++) { // start of 1 because we have properties of str[0] in dictionarySharing
                List<Properties> propertiesListFromWord = dictionaryResult.get(stringsInput[j]);
                int idx = properties.existINList(propertiesListFromWord);
                propertiesListSharing.get(i).TFIDF += propertiesListFromWord.get(idx).getTFIDF(); // TFIDF of sharing List can add of other TFIDF that subScribe in other List
            }
        }
        Collections.sort(propertiesListSharing, Collections.reverseOrder());
//        print(propertiesListSharing);
        showList = printBody(propertiesListSharing);
    }

    // ***************
    public void search() {
        String[] s = inputStr.split(" ");
        dictionaryResult = new Hashtable<>(s.length + 1);
        for (String str : s) {
            List<Properties> propertiesList = hashWords.get(str);
            if (propertiesList == null) // means not found
                return;
            Collections.sort(propertiesList, Collections.reverseOrder());
            dictionaryResult.put(str, propertiesList);
        }
        findSharing(s);

    }
}

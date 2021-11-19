package com.searchEngine.demo.file_handling;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SaveLoad {
    File fileWords ;
    Peta jensen = new Peta();
    HashMap<String,List<Properties>> indexingTable;
    final String fileNameDictionary = "Dictionary.txt";
    String fileName = "hashList.txt";
    List<String> Dictionary_for_spellcheker = new ArrayList<>();

    public List<String> getDictionary_for_spellcheker() {
        return Dictionary_for_spellcheker;
    }

    public void getWords(){
        try {
             indexingTable = jensen.getWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWords() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(indexingTable);
        fileOutputStream.close();
        objectOutputStream.close();

    }

    public HashMap<String, List<Properties>> loadWords() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            HashMap<String,List<Properties>> hashMap = (HashMap) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return hashMap;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        objectInputStream.close();
        fileInputStream.close();
        return null;
    }


    public void saveDictionary(List<String> dic) throws FileNotFoundException, IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileNameDictionary));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(dic);
        fileOutputStream.close();
        objectOutputStream.close();
    }

    public List<String> loadDictionary() throws FileNotFoundException, IOException{
        FileInputStream fileInputStream = new FileInputStream(new File(fileNameDictionary));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            List<String> dic2 = (List) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return dic2;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        objectInputStream.close();
        fileInputStream.close();
        return null;
    }
}

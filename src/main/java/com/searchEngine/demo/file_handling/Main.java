package com.searchEngine.demo.file_handling;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class Main {
    Spell_cheker spell_cheker = null;
    public  String spellCkeker = "";

    public static List<String> dictionaryForSpellcheker =new  ArrayList<String>();
    HashMap<String, List<Properties>> hashWords = null;
    SearchProcess searchProcess;
    final int NUMBER_ITEM_SHOW = 20;

    String input_Previous = ""; // for paging
    int round=0;
    public int sizeResult ;

    public void preProcess() throws IOException {
        SaveLoad saveLoad = new SaveLoad();


        // Load
        try {
            hashWords = saveLoad.loadWords();
            dictionaryForSpellcheker.addAll(saveLoad.loadDictionary());////اضافه کردن دیکشنری تمامی کلمات فارسی همان که دانلود شد.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> hash_Set = new HashSet<String>();
        hash_Set.addAll(saveLoad.getDictionary_for_spellcheker());//حذف کردن کلمات تکراری تایتل ها
        dictionaryForSpellcheker.addAll(hash_Set);///اضافه شدن دیکشنری تایتل ها

    }
    public boolean newSearch(String input){
        if (input.equals(input_Previous)) { // hamoon ghablias
            return false;
        }
        else{
            input_Previous = input;
            return true;
        }
    }
    public void spellChekerFunction(String input){
        String[] s = input.split(" ");
        String s1 = "";
        for (int i = 0 ;i < s.length; i++) {
            spell_cheker = new Spell_cheker(dictionaryForSpellcheker,s[i]);
            List<String> stringList = spell_cheker.getResult();
            for (String str:stringList) {
                s1 = s1.concat(str).concat("  ,  ");
            }
            s1 = s1.concat("\n");

        }
        spellCkeker = s1;

    }
    public List<Properties> searching(String input,int page){
        spellChekerFunction(input);
        round = page - 1; // becasue we start with 1
        if (newSearch(input)) {
            searchProcess = new SearchProcess(hashWords);
            searchProcess.getData(input);

        }
        List<Properties> result = searchProcess.getResult();
        if (result == null) // means not found
            return null;
        // calculate number of all page
        sizeResult = result.size()/NUMBER_ITEM_SHOW;
        if (result.size()%NUMBER_ITEM_SHOW > 0) // add remain page
            sizeResult++;


        List<Properties> preparedToShow = preparedToShow(result); // filter only 20 result
        return preparedToShow;

    }
    // *************************** filter only 20 result for showing
    public List<Properties> preparedToShow(List<Properties> list){
        List<Properties> listToShow= new ArrayList<>(NUMBER_ITEM_SHOW);
        for (int counter = 0; counter < NUMBER_ITEM_SHOW ; counter++) {
            int idx = round * NUMBER_ITEM_SHOW + counter;
            if (idx == list.size())
                return listToShow;
            else
                listToShow.add(list.get(idx));
        }
        return listToShow;
    }
}

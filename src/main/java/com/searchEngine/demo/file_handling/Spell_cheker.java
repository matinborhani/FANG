/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchEngine.demo.file_handling;

import java.util.Dictionary;
import java.util.*;

/**
 *
 * @author mshar
 */
public class Spell_cheker {
    final static int MIN_LENGTH = 3 ;
    private List<String> dictionary = new ArrayList<>();
    private ArrayList<String> result=new ArrayList<>();
    private String query;
    public static ArrayList<String> words=new ArrayList<String>();
    public Spell_cheker(List<String> dic, String str) {
        this.dictionary=dic;
        this.query=str;
        start();
    }
    
    
    public void start(){
        String []str;
         Dictionary geek = new Hashtable();
           
                    words=bigram(query);
                    geek=search(words);
                   result=probably(geek,query);
             }
    
    
    
    
    
    public ArrayList<String> getResult() {
        return result;
    }
    
        public  ArrayList<String> bigram(String str){
        
       // System.out.println(str.substring(2, 4).toString());
        ArrayList<String> currentword=new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            if(i+2<=str.length())
            currentword.add(str.substring(i, i+2));
            
            if(i+2==str.length())
                break;
            
        }
        
        for (int i = 0; i < currentword.size(); i++) {
            //System.out.println(words.get(i));
            
        }
        return currentword;
    }
    public  ArrayList<String> findmax(HashMap<String,Double> a){
        double max = 0;
        String str;
        HashMap<String,Double> resultMap=new HashMap<String, Double>();
//        Enumeration ka = a.keys();
            ArrayList<String> result_ArrayList=new ArrayList<>();
            
            for (int i = 0; i < 5; i++) {
            Map.Entry<String,Double> maxEntry = null;
            for (Map.Entry<String,Double> entry : a.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
        }
            resultMap.put(maxEntry.getKey(), maxEntry.getValue());
            a.remove(maxEntry.getKey(),maxEntry.getValue());
           
            }

for (Map.Entry<String,Double> entry : resultMap.entrySet()){
    result_ArrayList.add(entry.getKey());
}
       return result_ArrayList;

        
    }
    
    public  ArrayList<String> probably(Dictionary geek,String str){

         HashMap<String,Double> newDictionarywithprob = new HashMap<String,Double>();
        ArrayList<String> processword=new ArrayList<String>();
        ArrayList<String> nextword=new ArrayList<String>();
        ArrayList<String> subscribe=new ArrayList<String>();
        ArrayList<String> atleast_word_with_mostprob=new ArrayList<String>();
        String completestring="";
          for (Enumeration i = geek.elements(); i.hasMoreElements();) {

      completestring+=i.nextElement().toString();

    }
          processword=ProcessString(completestring);
          for (int j = 0; j < processword.size(); j++) {
                   nextword=bigram(processword.get(j));
                   subscribe=subscribe(words, nextword);
                   newDictionarywithprob.put(processword.get(j), compute(words.size(), nextword.size(), subscribe.size()));
              }
          atleast_word_with_mostprob=findmax(newDictionarywithprob);
        
        return atleast_word_with_mostprob;
    }
    
    
    public  double compute(int a,int b,int c){
        ///a is firstword.size    &     b is secondword.size    &   c is size of subscribe of a&b formula is |a∩b|/(|a|+|b|-|a+b|)
        return (double)c/(a+b-c);
    }
    
    
    public  Dictionary search(ArrayList<String> bigram){
        
        ArrayList<String> dic=new ArrayList<String>();
        ArrayList<String> copydic=new ArrayList<String>();
        Dictionary geek = new Hashtable();
        
        
      //  words.add(0, "سلام");
      int index;
        //words.subList(0, 0).add("سلام");
        for (int i = 0; i < bigram.size(); i++) {
            dic.removeAll(dic);
            for (int j = 0; j < dictionary.size(); j++) {
                index=dictionary.get(j).indexOf(bigram.get(i).toString());
                if(index!=-1){
                    dic.add(dictionary.get(j));
                    copydic=(ArrayList<String>) dic.clone();
                    geek.put(bigram.get(i),copydic);
                }
            }
            
            
            
        }
        return geek; 
    }
    
    public  ArrayList<String> ProcessString(String str){
        ArrayList<String> newdic=new ArrayList<String>();
        String copystr ="";
        String []splitstr;
        for (int i = 0; i < str.length(); i++) {
            
            if(i+1<str.length()&&(str.charAt(i)==']'&&str.charAt(i+1)=='['))
                    copystr+=" ";
            if(str.charAt(i)!='['&&str.charAt(i)!=']'&&str.charAt(i)!=','){
                copystr+=str.charAt(i)+"";
            }
        }
        splitstr=copystr.split(" ");
        for (int i = 0; i < splitstr.length; i++) {
            if(!newdic.contains(splitstr[i]))
                newdic.add(splitstr[i]);
            
        }
        return newdic;
    }
    
    
    public  ArrayList<String> subscribe(ArrayList<String> A,ArrayList<String> B){
        //A is word of query    B is on de dictionary
        ArrayList<String> subscribe=new ArrayList<String>();
        String swaped = null;
        String replaced=null;
        
        for (int i = 0; i <A.size() ; i++) {
            for (int j = 0; j < B.size(); j++) {
//                if(B.get(j)=="گیاه")
//                {
                if(A.get(i).contains(B.get(j)))
                    subscribe.add(A.get(i));
                else{/////jabeja kardan yek harf
                    swaped=swap(A.get(i).toString());
                    if(swaped.contains(B.get(j))&&!subscribe.contains(swaped)){
                        subscribe.add(swaped);
                    }
                    else{///avaz kardan harf haye nazdik be ham
                        replaced=replace(A.get(i).toString());
                        if(replaced.contains(B.get(j))&&!subscribe.contains(swaped)){
                            subscribe.add(replaced);
                        }
                    }
                }
            }
            //}
            
        }
        return subscribe;
    }

    public  String swap(String str) {
        char x,y;
        String newstr;
        x=str.charAt(0);
        y=str.charAt(1);
        return newstr=""+y+x;
        
    }

    private  String replace(String str) {
       if(str.contains("ک")){
           str=str.replace('ک', 'گ');
          }
       else if(str.contains("گ")){
           str=str.replace('گ', 'ک');
          }
        else if(str.contains("گ")){
           str=str.replace('گ', 'ک');
          }
       else if(str.contains("ز")){
           str=str.replace('ز', 'ژ');
          }
       else if(str.contains("ژ")){
           str=str.replace('ژ', 'ز');
          }
       else if(str.contains("ر")){
           str=str.replace('ر', 'ز');
          }
       else if(str.contains("ز")){
           str=str.replace('ز', 'ر');
          }
       else if(str.contains("پ")){
           str=str.replace('پ', 'چ');
          }
       else if(str.contains("چ")){
           str=str.replace('چ', 'پ');
          }
       else if(str.contains("ف")){
           str=str.replace('ف', 'ق');
          }
       else if(str.contains("ق")){
           str=str.replace('ق', 'ف');
          }
       else if(str.contains("د")){
           str=str.replace('د', 'ذ');
          }
       else if(str.contains("ذ")){
           str=str.replace('ذ', 'د');
          }
       else if(str.contains("س")){
           str=str.replace('س', 'ش');
          }
       else if(str.contains("ش")){
           str=str.replace('ش', 'س');
          }
       else if(str.contains("ح")){
           str=str.replace('ح', 'خ');
          }
       else if(str.contains("خ")){
           str=str.replace('خ', 'ح');
          }
      
       
   return str;
    }
    
        public  List<String> removeBadWords(List<String> listStr){
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
    }

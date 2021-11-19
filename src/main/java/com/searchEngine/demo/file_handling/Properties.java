package com.searchEngine.demo.file_handling;

import java.io.Serializable;
import java.util.List;

public class Properties implements Serializable, Comparable {
    public String url;
    public String docID;
    public int count;
    public String title;
    public double TFIDF;
    int round = 0;
    String body;

    // ************************ Constructor

    public Properties(String url, String docID, int count , String title,String body) {
        this.url = url;
        this.docID = docID;
        this.count = count;
        this.title = title;
        this.body = body;
    }

    public Properties(String url, String docID) {
        this.url = url;
        this.docID = docID;
    }

    public Properties() {
    }

    // ************************** Getter and Setter


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getTFIDF() {
        return TFIDF;
    }

    public void setTFIDF(double TFIDF) {
        this.TFIDF = TFIDF;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // *************************** Methods

    public int existINList(List<Properties> propertiesList) {
        for (int i = 0; i < propertiesList.size(); i++) {
            if (propertiesList.get(i).equals(this))
                return i;
        }
        return -1;
    }

    public boolean contain(List<Properties> listproperties) {
        if (listproperties.contains(this)) {
            return true;
        } else
            return false;
    }
    @Override
    public String toString() {
        String str = url + "," + docID + "," + count;
        return str;
    }

    @Override
    public int compareTo(Object o) {
        Properties p1 = (Properties) o;
        return this.getTFIDF().compareTo((p1.getTFIDF()));
    }

    @Override
    public boolean equals(Object obj) {
        Properties p1 = (Properties) obj;
        return this.getDocID().equals(p1.getDocID());
    }

    //    public void TFIDF(){
//        // count
//        // formol
//    }
}


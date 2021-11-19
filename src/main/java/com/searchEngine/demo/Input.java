package com.searchEngine.demo;

public class Input {
    String strInput;
    int numPage = 1;
    int numberAllPage;
    String spellChecker;

    // *************** Constructor

    public Input(String strInput) {
        this.strInput = strInput;
    }

    public Input() {
    }

    // ***************** Getter & Setter


    public String getStrInput() {
        return strInput;
    }

    public void setStrInput(String strInput) {
        this.strInput = strInput;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }

    public int getNumberAllPage() {
        return numberAllPage;
    }

    public void setNumberAllPage(int numberPage) {
        this.numberAllPage = numberPage;
    }

    public String getSpellChecker() {
        return spellChecker;
    }

    public void setSpellChecker(String spellChecker) {
        this.spellChecker = spellChecker;
    }
}

package com.searchEngine.demo.controller;
import com.searchEngine.demo.Input;
import com.searchEngine.demo.file_handling.Main;
import com.searchEngine.demo.file_handling.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@ComponentScan({"com.searchEngine.demo.file_handling"})
@org.springframework.stereotype.Controller
public class SearchEngine {



    @Autowired
    Main main;

    @GetMapping("/home")
    public String StringhomePage(Model model){
        try {
            main.preProcess();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("input",new Input());

        return "home";
    }

    @PostMapping("/search")
    public String search_Process(@ModelAttribute Input input , Model model){
        List<Properties> resultList = main.searching(input.getStrInput(),input.getNumPage());
        if (resultList == null) { // Not found 404
            input.setSpellChecker(main.spellCkeker);
            model.addAttribute("input", input);
            return "error";
        }
        model.addAttribute("listProperties",resultList);
        input.setNumberAllPage(main.sizeResult);
        model.addAttribute("input",input);
        System.out.println(main.spellCkeker);


        return "result";
    }

}

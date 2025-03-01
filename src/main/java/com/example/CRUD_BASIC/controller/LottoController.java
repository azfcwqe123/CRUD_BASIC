package com.example.CRUD_BASIC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LottoController {

    @GetMapping("/home/lotto")
    public String getLotto(Model model) {
        int[] numbers = new int[6];
        for(int i=0; i<6; i++) {
            numbers[i] = (int) (Math.random() * 45) + 1;
        }
        model.addAttribute("numbers",numbers);
        return "lotto/lotto";
    }


}
package com.msa.app.ui;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "smartmenu";
    }

}
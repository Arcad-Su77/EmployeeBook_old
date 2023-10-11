package com.arcad.employeebook.service.view;

import org.springframework.stereotype.Service;

@Service
public class ViewService {

    public String viewOut(String result) {
        String header = "<h1> Calculator Result </h1><br>==================================<br>";
        String footer = "<br>==================================<br><a href=\"/\">HOME</a>";
        return header + result + footer;
    }
}

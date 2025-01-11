package org.example;

import org.springframework.stereotype.Component;

@Component("comp")
public class Desktop implements Computer{

    public Desktop(){
        System.out.println("Desktop object created");
    }
    @Override
    public void code(){
        System.out.println("Desktop class");
    }

   
}

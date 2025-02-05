package org.example;

import org.springframework.stereotype.Component;

@Component()
public class Laptop implements Computer{

    public Laptop()
    {
        System.out.println("Laptop object created");
    }
    @Override
    public void code(){
        System.out.println("Laptop class");
    }
}

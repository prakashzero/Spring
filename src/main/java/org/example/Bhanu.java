package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;
@Component
public class Bhanu {
    @Autowired
    @Qualifier("comp")
    private Computer comp;

    @Value("24")
    private int age;
    public Bhanu(){
        System.out.println("Bhanu constructor called since object created");
    }

    public Computer getComp() {
        return comp;
    }

    public void setComp(Computer comp) {
        this.comp = comp;
    }

//    @ConstructorProperties({"age","comp"})
//    public Bhanu(int age, Computer comp) {
//        this.age = age;
//        this.comp=comp;
//    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Setter is called");
        this.age = age;
    }

    public void code(){

       comp.code();

    }


}

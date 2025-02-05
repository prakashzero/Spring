package org.example.config;

import org.example.Bhanu;
import org.example.Computer;
import org.example.Desktop;
import org.example.Laptop;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@ComponentScan("org.example")
@Configuration
public class AppConfig {
//
//    @Bean
//    public Bhanu bhanu(Computer comp){
//        Bhanu b=new Bhanu();
//        b.setAge(25);
//        b.setComp(comp);
//        return b;
//
//
//    }
//
//    @Bean
//    //@Scope(value = "prototype")
//    public Desktop desktop(){
//        return new Desktop();
//    }
//
//    @Bean
//    @Primary
//    public Laptop laptop(){
//        return  new Laptop();
//    }

}

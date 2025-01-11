package org.example;

import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        Desktop dt=context.getBean(Desktop.class);
        dt.code();

//        Desktop dt1=context.getBean(Desktop.class);
//        dt1.code();

        Bhanu b1 = (Bhanu) context.getBean(Bhanu.class);
        System.out.println(b1.getAge());
        b1.code();


//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//
//        Bhanu b1 = (Bhanu) context.getBean("Bhanu");
//
//        System.out.println(b1.getAge());
//        b1.code();
//        b1.age=20;
//        System.out.println(b1.age);
//        Bhanu b2= (Bhanu) context.getBean("Bhanu1");
//        System.out.println(b2.age);
//        Prakash p= (Prakash) context.getBean("Prakash");
//        b1.code();
//
//        p.code();

        //Desktop d= context.getBean("lap1",Desktop.class);


    }
}
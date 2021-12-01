package com.delsystem.instamart.workapp;


import com.delsystem.instamart.util.JSONFileParser;
import com.delsystem.instamart.workapp.model.Outlets;
import com.delsystem.instamart.workapp.model.TradePoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Main.java
 * Date/time: 15 сентябрь 2021 in 14:25
 */

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext("com.delsystem.instamart");
ApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext.xml"
);
        JSONFileParser parser = context.getBean(JSONFileParser.class);

        Outlets outlets = context.getBean(Outlets.class);
        outlets.setContext(context);
        
        TradePoint tradePoint = outlets.getTradePoint("1706");
        tradePoint.refreshOrders(parser.getCurrentOrdersMap());

        Outlets outletsTest = Outlets.getInstance();
        Outlets outlets2 = context.getBean(Outlets.class);
        System.out.println(outlets == outlets2);
        System.out.println(outletsTest == outlets2);
        System.out.println(outlets == outletsTest);
        System.out.println(Outlets.getInstance() == outlets);
        System.out.println(Outlets.getInstance() == outlets2);
        System.out.println(Outlets.getInstance() == outletsTest);

//        tradePoint.getOrders().forEach((key, order) -> {
//            System.out.println("Информация объекта " + key + "\n\n");
//            System.out.println(order);
//            System.out.println("\n\n");
//            System.out.println("--------------------------------------------------");
//            System.out.println("\n\n");
//        });

    }

}

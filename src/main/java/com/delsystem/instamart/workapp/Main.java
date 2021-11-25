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
    private final static String filePath = "/home/andreigp/Documents/Sber/query_result_2021-10-10T15 50 25.45162Z.json";
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.delsystem.instamart");

        JSONFileParser parser = context.getBean(JSONFileParser.class);
        parser.setFilePath(filePath);
        Outlets outlets = context.getBean(Outlets.class);
        TradePoint tradePoint = outlets.getTradePoint("1706");
        tradePoint.refreshOrders(parser.getCurrentOrdersMap());

        tradePoint.getOrders().forEach((key, order) -> {
            System.out.println("Информация объекта " + key + "\n\n");
            System.out.println(order);
            System.out.println("\n\n");
            System.out.println("--------------------------------------------------");
            System.out.println("\n\n");
        });

    }

}

package com.delsystem.instamart.workapp;


import com.delsystem.instamart.util.JSONFileParser;
import com.delsystem.instamart.workapp.model.Outlets;
import com.delsystem.instamart.workapp.model.TradePoint;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Main.java
 * Date/time: 15 сентябрь 2021 in 14:25
 */

public class Main {
    private final static String filePath = "/home/andreigp/Documents/Sber/query_result_2021-10-10T15 50 25.45162Z.json";
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        JSONFileParser parser = context.getBean("xmlParser", JSONFileParser.class);
        parser.setFilePath(filePath);
        Outlets outlets = context.getBean("outlets", Outlets.class);
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

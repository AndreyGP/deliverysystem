package com.delsystem.instamart.workapp;


import com.delsystem.instamart.springjavaconfigs.MainConfiguration;
import com.delsystem.instamart.util.JSONFileParser;
import com.delsystem.instamart.workapp.model.Outlets;
import com.delsystem.instamart.workapp.model.TradePoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Main.java
 * Date/time: 15 сентябрь 2021 in 14:25
 * Я просто импровизирую))
 */

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(MainConfiguration.class);

        JSONFileParser parser = context.getBean(JSONFileParser.class);

        Outlets outlets = context.getBean(Outlets.class);
        outlets.setContext(context);

        TradePoint tradePoint1706 = outlets.getTradePoint("1706");
        tradePoint1706.refreshOrders(parser.getCurrentOrdersMap());

        outlets = context.getBean(Outlets.class);
        TradePoint tradePoint1707 = outlets.getTradePoint("1707");
        tradePoint1707.refreshOrders(parser.getCurrentOrdersMap());

        outlets = context.getBean(Outlets.class);
        TradePoint tradePoint1708 = outlets.getTradePoint("1708");
        tradePoint1708.refreshOrders(parser.getCurrentOrdersMap());

        int currentAmountTradePoint = outlets.getAmountTradePoint();
        System.out.println(currentAmountTradePoint);


//        tradePoint1706.getOrders().forEach((key, order) -> {
//            System.out.println("Информация объекта " + key + "\n\n");
//            System.out.println(order);
//            System.out.println("\n\n");
//            System.out.println("--------------------------------------------------");
//            System.out.println("\n\n");
//        });

    }

}

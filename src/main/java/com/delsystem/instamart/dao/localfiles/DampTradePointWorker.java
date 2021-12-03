package com.delsystem.instamart.dao.localfiles;

import com.delsystem.instamart.bean.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: DampTradePointWorker.java
 * Date/time: 18 ноябрь 2021 in 21:24
 */

public class DampTradePointWorker implements Runnable {
    private String dumpPath;
    private Map<String, Order> orders;


    public void setDumpPath(String dumpPath) {
        this.dumpPath = dumpPath;
    }

    public void setOrders(Map<String, Order> orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dumpPath))) {
            String firstLine = "Номер доставки|" +
                    "Адрес клиента|" +
                    "Текущий статус доставки|" +
                    "Время слота по мест. вр.|" +
                    "Имя сборщика|" +
                    "Имя курьера|" +
                    "Время взятия в сборку|" +
                    "Время окончания сборки|" +
                    "Время начала доставки (СберМ)|" +
                    "Время доставки|" +
                    "Отзыв клиента|" +
                    "Оценка|" +
                    "Число позиций|";
            writer.write(firstLine);
            writer.newLine();

            orders.forEach((delNumber, order) -> {
                StringBuilder nextLine = new StringBuilder()
                        .append(delNumber).append("|")
                        .append(order.getAddress()).append("|")
                        .append(order.getCurrentStatus()).append("|")
                        .append(order.getDeliveryInterval()).append("|")
                        .append(order.getOrderPickerName()).append("|")
                        .append(order.getOrderCourierName()).append("|")
                        .append(order.getPickUpTime()).append("|")
                        .append(order.getEndPickingTime()).append("|")
                        .append(order.getStartDeliveryTime()).append("|")
                        .append(order.getEndDeliveryTime()).append("|")
                        .append(order.getComment()).append("|")
                        .append(order.getGrade()).append("|")
                        .append(order.getQuantityOfPositions()).append("|");

                try {
                    writer.write(nextLine.toString());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package com.delsystem.instamart.dao.localfiles;

import com.delsystem.instamart.bean.Order;

import java.io.BufferedWriter;
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
            StringBuilder firstLine = new StringBuilder()
                    .append("Номер доставки|")
                    .append("Адрес клиента|")
                    .append("Текущий статус доставки|")
                    .append("Время слота по мест. вр.|")
                    .append("Имя сборщика|")
                    .append("Имя курьера|")
                    .append("Время взятия в сборку|")
                    .append("Время окончания сборки|")
                    .append("Время начала доставки (СберМ)|")
                    .append("Время доставки|")
                    .append("Отзыв клиента|")
                    .append("Оценка|")
                    .append("Число позиций|");
            writer.write(firstLine.toString());
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

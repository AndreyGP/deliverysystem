package com.delsystem.instamart.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Order.java
 * Date/time: 11 октябрь 2021 in 23:58
 * Description: Typical order class.
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */

public class Order {
    private String deliveryNumber;
    private String address;
    private String currentStatus;
    private String deliveryInterval;
    private String orderPickerName;
    private String orderCourierName;
    private String pickUpTime;
    private String endPickingTime;
    private String startDeliveryTime;
    private String endDeliveryTime;
    private String comment;
    private int grade;
    private int quantityOfPositions;
    private boolean delayedDelivery;
    private boolean courierAppointed;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Order() {

    }

    public Order initOrder(final Map<String, String> orderMap) {
        deliveryNumber = orderMap.get("Номер доставки");
        address = getFormatAddress(orderMap.get("Адрес клиента"));
        currentStatus = Status.getStatusByKey(orderMap.get("Текущий статус доставки"));
        deliveryInterval = orderMap.get("Время слота по мест. вр.").substring(11);
        orderPickerName = orderMap.get("Имя сборщика").equals("null") ? "Не назначен" : orderMap.get("Имя сборщика");
        orderCourierName = orderMap.get("Имя курьера").equals("null") ? "Не назначен" : orderMap.get("Имя курьера");
        pickUpTime = orderMap.get("Время взятия в сборку").equals("null") ? "Не взят" : orderMap.get("Время взятия в сборку");
        endPickingTime = orderMap.get("Время окончания сборки").equals("null") ? "Не собран" : orderMap.get("Время окончания сборки");
        startDeliveryTime = orderMap.get("Время начала доставки (СберМ)").equals("null") ? "Не в рейсе" : orderMap.get("Время начала доставки (СберМ)");
        endDeliveryTime = orderMap.get("Время доставки").equals("null") ? "Не доставлен" : orderMap.get("Время доставки");
        comment = orderMap.get("Оценка").equals("null")  ? "Без комментария" : orderMap.get("Отзыв клиента");
        grade = orderMap.get("Оценка").equals("null") ? 0 : Integer.parseInt(orderMap.get("Оценка"));
        quantityOfPositions = Integer.parseInt(orderMap.get("Число позиций"));
        delayedDelivery = lateDelivery();// No realisation
        courierAppointed =inRay();// No realisation
        return this;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public String getAddress() {
        readLock.lock();
        try {
            return address;
        } finally {
            readLock.unlock();
        }
    }

    public void setAddress(final String address) {
        writeLock.lock();
        try {
            this.address = address;
        }finally {
            writeLock.unlock();
        }
    }

    public String getCurrentStatus() {
        readLock.lock();
        try {
            return currentStatus;
        } finally {
            readLock.unlock();
        }
    }

    public void setCurrentStatus(final String currentStatus) {
        writeLock.lock();
        try {
            this.currentStatus = Status.getStatusByKey(currentStatus);
        }finally {
            writeLock.unlock();
        }
    }

    public String getDeliveryInterval() {
        readLock.lock();
        try {
            return deliveryInterval;
        }finally {
            readLock.unlock();
        }
    }

    public void setDeliveryInterval(final String deliveryInterval) {
        writeLock.lock();
        try {
            this.deliveryInterval = deliveryInterval;
        }finally {
            writeLock.unlock();
        }
    }

    public String getOrderPickerName() {
        readLock.lock();
        try {
            return orderPickerName;
        }finally {
            readLock.unlock();
        }
    }

    public void setOrderPickerName(final String orderPickerName) {
        writeLock.lock();
        try {
            this.orderPickerName = orderPickerName;
        }finally {
            writeLock.unlock();
        }
    }

    public String getOrderCourierName() {
        readLock.lock();
        try {
            return orderCourierName;
        }finally {
            readLock.unlock();
        }
    }

    public void setOrderCourierName(final String orderCourierName) {
        writeLock.lock();
        try {
            this.orderCourierName = orderCourierName;
        }finally {
            writeLock.unlock();
        }
    }

    public String getPickUpTime() {
        readLock.lock();
        try {
            return null;
        }finally {
            readLock.unlock();
        }
    }

    public void setPickUpTime(final String pickUpTime) {
        writeLock.lock();
        try {
            this.pickUpTime = pickUpTime;
        }finally {
            writeLock.unlock();
        }
    }

    public String getEndPickingTime() {
        readLock.lock();
        try {
            return null;
        }finally {
            readLock.unlock();
        }
    }

    public void setEndPickingTime(final String endPickingTime) {
        writeLock.lock();
        try {
            this.endPickingTime = endPickingTime;
        }finally {
            writeLock.unlock();
        }
    }

    public String getStartDeliveryTime() {
        readLock.lock();
        try {
            return null;
        }finally {
            readLock.unlock();
        }
    }

    public void setStartDeliveryTime(final String startDeliveryTime) {
        writeLock.lock();
        try {
            this.startDeliveryTime = startDeliveryTime;
        }finally {
            writeLock.unlock();
        }
    }

    public String getEndDeliveryTime() {
        readLock.lock();
        try {
            return null;
        }finally {
            readLock.unlock();
        }
    }

    public void setEndDeliveryTime(final String endDeliveryTime) {
        writeLock.lock();
        try {
            this.endDeliveryTime = endDeliveryTime;
        }finally {
            writeLock.unlock();
        }
    }

    public int getQuantityOfPositions() {
        readLock.lock();
        try {
            return this.quantityOfPositions;
        }finally {
            readLock.unlock();
        }
    }

    public void setQuantityOfPositions(final int quantityOfPositions) {
        writeLock.lock();
        try {
            this.quantityOfPositions = quantityOfPositions;
        }finally {
            writeLock.unlock();
        }
    }

    public boolean isDelayedDelivery() {
        readLock.lock();
        try {
            return delayedDelivery;
        }finally {
            readLock.unlock();
        }
    }

    public void setDelayedDelivery(final boolean delayedDelivery) {
        writeLock.lock();
        try {
            this.delayedDelivery = delayedDelivery;
        }finally {
            writeLock.unlock();
        }
    }

    public boolean isCourierAppointed() {
        readLock.lock();
        try {
            return this.courierAppointed;
        }finally {
            readLock.unlock();
        }
    }

    public void setCourierAppointed(final boolean courierAppointed) {
        writeLock.lock();
        try {
            this.courierAppointed = courierAppointed;
        }finally {
            writeLock.lock();
        }
    }

    public String getComment() {
        readLock.lock();
        try {
            return comment;
        }finally {
            readLock.unlock();
        }
    }

    public void setComment(final String comment) {
        writeLock.lock();
        try {
            this.comment = comment;
        }finally {
            writeLock.unlock();
        }
    }

    public int getGrade() {
        readLock.lock();
        try {
            return grade;
        }finally {
            readLock.unlock();
        }
    }

    public void setGrade(final int grade) {
        writeLock.lock();
        try {
            this.grade = grade;
        }finally {
            writeLock.unlock();
        }
    }

    private String getFormatAddress(final String address) {
        readLock.lock();
        try {
            String[] splitAddress = address.split(",");
            int lgt = splitAddress.length;
            return (splitAddress[lgt - 3].replaceAll("Москва||МО||Московская область||Россия", "").trim() +
                    splitAddress[lgt - 2] +
                    "," +
                    splitAddress[lgt - 1])
                    .replaceAll("\\s*?улица\\s*?", "")
                    .replaceAll("\\s*?шоссе\\s*?", "")
                    .replaceAll("\\s*?бульвар\\s*?", "")
                    .replaceAll("\\s*?проспект\\s*?", "")
                    .replaceAll("\\s*?проезд\\s*?", "").trim();
        }finally {
            readLock.unlock();
        }
    }
/*
Test
 */
    private boolean lateDelivery() {
        readLock.lock();
        try {
            if (currentStatus.equals("Доставлен")) {
                int lastIntervalHour = Integer.parseInt(deliveryInterval.split(" - ")[1].split(":")[0]);
                int lastIntervalMinutes = Integer.parseInt(deliveryInterval.split(" - ")[1].split(":")[1]);
                int realIntervalHour = Integer.parseInt(endDeliveryTime.split(":")[0]);
                int realIntervalMinutes = Integer.parseInt(endDeliveryTime.split(":")[1]);
                if (realIntervalHour > lastIntervalHour) return true;
                if (realIntervalHour < lastIntervalHour) return false;
                return lastIntervalMinutes > realIntervalMinutes;
            }
            return false;
        }finally {
            readLock.unlock();
        }
    }

    private boolean inRay() {
        readLock.lock();
        try {
            return !startDeliveryTime.equals("Не в рейсе");
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public String toString() {
        readLock.lock();
        try {
            StringBuilder orderInfo = new StringBuilder()
                    .append("№ заказа: ").append(deliveryNumber).append("\n")
                    .append("Адрес доставки: ").append(address).append("\n")
                    .append("Интервал доставки: ").append(deliveryInterval).append("\n")
                    .append("Статус заказа: ").append(currentStatus).append("\n")
                    .append("Позиций: ").append(quantityOfPositions).append("\n");

            if (!orderPickerName.equals("Не назначен")) {
                orderInfo.append("Сборщик: ").append(orderPickerName).append("\n");
            }
            if (!pickUpTime.equals("Не взят")) {
                orderInfo.append("Взят в сборку в ").append(pickUpTime).append("\n");
            }
            if (!endPickingTime.equals("Не собран")) {
                orderInfo.append("Сборка завершена в ").append(endPickingTime).append("\n");
            }
            if (!orderCourierName.equals("Не назначен")) {
                orderInfo.append("Курьер: ").append(orderCourierName).append("\n");
            }
            if (startDeliveryTime.equals("Не в рейсе") && !endDeliveryTime.equals("Не в рейсе") && currentStatus.equals("Доставлен")) {
                orderInfo.append("Был взят без рейса").append("\n");
            }
            if (!startDeliveryTime.equals("Не в рейсе")) {
                orderInfo.append("Рейс начат в ").append(startDeliveryTime).append("\n");
            }
            if (!endDeliveryTime.equals("Не доставлен")) {
                orderInfo.append("Доставлен ");
                if (delayedDelivery) {
                    orderInfo.append("с опозданием ");
                }
                orderInfo.append("в ").append(endDeliveryTime).append("\n");
            }

            return orderInfo.toString();
        }finally {
            readLock.unlock();
        }
    }

    static class Status {
        private final static Map<String, String> statuses = new HashMap<>();

        static {
            statuses.put("canceled","Отменён");
            statuses.put("shipped","Доставлен");
            statuses.put("ready_to_ship","На базе");
            statuses.put("shipping","В доставке");
            statuses.put("packaging","Упаковка");
            statuses.put("on_approval","Согласование");
            statuses.put("paused","Приостановлен");
            statuses.put("on_cash_desk","На кассе");
            statuses.put("collecting","В сборке");
            statuses.put("ready","Новый");
            statuses.put("suspended","Возможно");
        }

        static String getStatusByKey(final String key) {
            return statuses.get(key);
        }

    }
}

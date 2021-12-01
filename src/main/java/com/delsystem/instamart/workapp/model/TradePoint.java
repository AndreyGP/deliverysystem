package com.delsystem.instamart.workapp.model;

import com.delsystem.instamart.bean.Order;
import com.delsystem.instamart.bean.PartnerBase;
import com.delsystem.instamart.dao.localfiles.DampTradePointWorker;
import com.delsystem.instamart.util.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: TradePoint.java
 * Date/time: 12 октябрь 2021 in 15:17
 *
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */
@Component
@Scope("prototype")
public class TradePoint {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();
    private final Set<PartnerBase> pickers = new HashSet<>();
    private final Set<PartnerBase> couriers = new HashSet<>();
    private String tradePoint;
    private String dumpPath;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void setTradePointNumber(final String tradePointNumber) {
        this.tradePoint = tradePointNumber;
        initDumpPathForCurrentTradePoint();
    }

    public void setDumpPath(final String dumpPath) {
        this.dumpPath = dumpPath;
        if (tradePoint != null)
            initDumpPathForCurrentTradePoint();
    }

    private void initDumpPathForCurrentTradePoint() {
        this.dumpPath = dumpPath + tradePoint + ".csv";
    }

    public String getTradePointNumber() {
        readLock.lock();
        try {
            return tradePoint;
        } finally {
            readLock.unlock();
        }
    }

    public Map<String, Order> getOrders() {
        readLock.lock();
        try {
            return orders;
        } finally {
            readLock.unlock();
        }
    }


    public void refreshOrders(final List<Map<String, String>> listOrdersMap) {
        writeLock.lock();
        try {
            orders.clear();
            listOrdersMap.parallelStream()
                    .forEachOrdered(orderMap -> orders.put(orderMap.get("Номер доставки"), new Order().initOrder(orderMap)));
        } finally {
            writeLock.unlock();
            dampTradePoint();
        }
    }

    public Map<String, Order> getOrdersByName(final String fullName, final String role) {
        readLock.lock();
        try {
            return role.equals(Role.PICKER.getRole()) ? getOrdersByPickerName(fullName) : getOrdersByCourierName(fullName);
        } finally {
            readLock.unlock();
        }
    }

    private Map<String, Order> getOrdersByPickerName(final String fullName) {
        Map<String, Order> pickerOrders = new ConcurrentHashMap<>();
        List<Order> orderList = orders.values().stream()
                .filter(o -> o.getOrderPickerName().equals(fullName))
                .collect(Collectors.toList());
        orderList.forEach(order -> pickerOrders.put(order.getDeliveryNumber(), order));
        return pickerOrders;
    }

    private Map<String, Order> getOrdersByCourierName(final String fullName) {
        Map<String, Order> courierOrders = new ConcurrentHashMap<>();
        List<Order> orderList = orders.values().stream()
                .filter(o -> o.getOrderCourierName().equals(fullName))
                .collect(Collectors.toList());
        orderList.forEach(order -> courierOrders.put(order.getDeliveryNumber(), order));
        return courierOrders;
    }

    private void dampTradePoint() {
        readLock.lock();
        try {
            DampTradePointWorker dampTradePointWorker = new DampTradePointWorker();
            dampTradePointWorker.setDumpPath(dumpPath);
            dampTradePointWorker.setOrders(orders);
            Thread damp = new Thread(dampTradePointWorker);
            damp.start();
        } finally {
            readLock.unlock();
        }
    }

}

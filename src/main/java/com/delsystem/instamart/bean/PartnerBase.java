package com.delsystem.instamart.bean;

import com.delsystem.instamart.workapp.model.Outlets;

import java.util.Map;
import java.util.OptionalDouble;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: PartnerBase.java
 * Date/time: 18 октябрь 2021 in 11:11
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */

abstract public class PartnerBase implements Employee {
    protected String role;
    protected String fullName;
    protected String name;
    protected String baseTradePoint;
    protected String currentTradePoint;
    protected Map<String, Order> orders = new ConcurrentHashMap<>();
    protected double gradeOfTheDay;
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    protected final Lock readLock = lock.readLock();
    protected final Lock writeLock = lock.writeLock();

    abstract public String getPartnerInfo();
    abstract public void dumpPartnerInfo();
    abstract public void backupPartnerInfo();

    public void refreshOrders() {
        writeLock.lock();
        try {
            orders.clear();
            orders = Outlets.getInstance()
                    .getTradePoint(currentTradePoint)
                    .getOrdersByName(fullName, role);
        }finally {
            writeLock.unlock();
        }
    }

    public void refreshGradeOfTheDay() {
        writeLock.lock();
        try {
            OptionalDouble gradeSumOptionalDouble = orders.values().stream()
                    .distinct()
                    .filter(o -> o.getGrade() != 0)
                    .mapToInt(Order::getGrade)
                    .average();
            gradeOfTheDay = gradeSumOptionalDouble.isPresent() ? gradeSumOptionalDouble.getAsDouble() : 0;
        }finally {
            writeLock.unlock();
        }
    }

    public String getRole() {
        readLock.lock();
        try {
            return role;
        }finally {
            readLock.unlock();
        }
    }

    public void setRole(final String role) {
        writeLock.lock();
        try {
            this.role = role;
        }finally {
            writeLock.unlock();
        }
    }

    public String getFullName() {
        readLock.lock();
        try {
            return fullName;
        }finally {
            readLock.unlock();
        }
    }

    public void setFullName(final String fullName) {
        writeLock.lock();
        try {
            this.fullName = fullName;
        }finally {
            writeLock.unlock();
        }
    }

    public String getName() {
        readLock.lock();
        try {
            return name;
        }finally {
            readLock.unlock();
        }
    }

    public void setName(final String name) {
        writeLock.lock();
        try {
            this.name = name;
        }finally {
            writeLock.unlock();
        }
    }

    public String getBaseTradePoint() {
        readLock.lock();
        try {
            return baseTradePoint;
        }finally {
            readLock.unlock();
        }
    }

    public void setBaseTradePoint(final String baseTradePoint) {
        writeLock.lock();
        try {
            this.baseTradePoint = baseTradePoint;
        }finally {
            writeLock.unlock();
        }
    }

    public String getCurrentTradePoint() {
        readLock.lock();
        try {
            return currentTradePoint;
        }finally {
            readLock.unlock();
        }
    }

    public void setCurrentTradePoint(final String currentTradePoint) {
        writeLock.lock();
        try {
            this.currentTradePoint = currentTradePoint;
        }finally {
            writeLock.unlock();
        }
    }

    public Map<String, Order> getOrders() {
        readLock.lock();
        try {
            return orders;
        }finally {
            readLock.unlock();
        }
    }

    public void setOrders(final Map<String, Order> orders) {
        writeLock.lock();
        try {
            this.orders = orders;
        }finally {
            writeLock.unlock();
        }
    }

    public double getGradeOfTheDay() {
        readLock.lock();
        try {
            return gradeOfTheDay;
        }finally {
            readLock.unlock();
        }
    }

    public void setGradeOfTheDay(final float gradeOfTheDay) {
        writeLock.lock();
        try {
            this.gradeOfTheDay = gradeOfTheDay;
        }finally {
            writeLock.unlock();
        }
    }
}

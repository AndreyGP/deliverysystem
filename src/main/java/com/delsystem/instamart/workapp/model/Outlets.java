package com.delsystem.instamart.workapp.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Outlets.java
 * Date/time: 17 октябрь 2021 in 12:01
 *
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */
@Component
@Scope("singleton")
public class Outlets {
    private final Map<String, TradePoint> outlets = new ConcurrentHashMap<>();
    private static ApplicationContext context;
    private static Outlets instance;
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Lock readLock = lock.readLock();
    private static final Lock writeLock = lock.writeLock();

    private Outlets() {
        instance = this;
    }

    public void setContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    /**
     * @return singleton instance
     */
    public static Outlets getInstance() {
        try {
            if (instance == null) {
                writeLock.lock();
                if (instance == null) {
                    instance = new Outlets();
                }
                writeLock.unlock();
            }
            readLock.lock();
            return instance;
        } finally {
            readLock.unlock();
        }
    }

    public int getAmountTradePoint() {
        return outlets.size();
    }

    public TradePoint getTradePoint(final String tradePointNumber) {
        try {
            if (outlets.get(tradePointNumber) == null) {
                initTradePoint(tradePointNumber);
            }
            readLock.lock();
            return outlets.get(tradePointNumber);
        } finally {
            if (readLock.tryLock())
                readLock.unlock();
        }
    }

    private void initTradePoint(final String tradePointNumber) {
        try {
            if (outlets.get(tradePointNumber) == null) {
                readLock.lock();
                TradePoint newTradePoint = context.getBean(TradePoint.class);
                newTradePoint.setTradePointNumber(tradePointNumber);
                outlets.put(tradePointNumber, newTradePoint);
            }
        } finally {
            if (readLock.tryLock())
                readLock.unlock();
        }
    }

}

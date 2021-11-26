package com.delsystem.instamart.workapp.model;

import org.springframework.context.annotation.Bean;
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
    private static Outlets instance = new Outlets();
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Lock readLock = lock.readLock();
    private static final Lock writeLock = lock.writeLock();

    /**
     * @return singleton instance
     */
    public static Outlets getInstance() {
        try {
            if (instance == null) {
                writeLock.lock();
                if (instance == null) instance = new Outlets();
                writeLock.unlock();
            }
            readLock.lock();
            return instance;
        } finally {
            readLock.unlock();
        }
    }

    public TradePoint getTradePoint(final String tradePoint) {
        try {
            if (outlets.get(tradePoint) == null) {
                initTradePoint(tradePoint);
            }
            readLock.lock();
            return outlets.get(tradePoint);
        } finally {
            readLock.unlock();
        }
    }

    private void initTradePoint(final String tradePointNumber) {
        writeLock.lock();
        try {
            if (outlets.get(tradePointNumber) == null) {
                TradePoint tradePoint = new TradePoint();
                tradePoint.initTradePoint(tradePointNumber);
                outlets.put(tradePointNumber,tradePoint);
            }
        } finally {
            writeLock.unlock();
        }
    }

}

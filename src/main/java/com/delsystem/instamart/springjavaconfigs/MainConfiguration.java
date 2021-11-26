package com.delsystem.instamart.springjavaconfigs;

import com.delsystem.instamart.bean.Order;
import com.delsystem.instamart.workapp.model.TradePoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * deliverysystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: MainConfiguration.java
 * Date/time: 26 ноябрь 2021 in 17:42
 */
@Configuration
@ComponentScan("com.delsystem.instamart")
public class MainConfiguration {

    @Bean
    @Scope("prototype")
    public TradePoint getTradePoint() {
        return new TradePoint();
    }

    @Bean
    @Scope("prototype")
    public Order getOrder() {
        return new Order();
    }
}

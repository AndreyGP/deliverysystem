package com.delsystem.instamart.springjavaconfigs;

import org.springframework.context.annotation.*;


/**
 * deliverysystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: MainConfiguration.java
 * Date/time: 26 ноябрь 2021 in 17:42
 */
@Configuration
@ComponentScan("com.delsystem.instamart")
@PropertySources({
        @PropertySource("classpath:base.properties")
})
public class MainConfiguration {


}

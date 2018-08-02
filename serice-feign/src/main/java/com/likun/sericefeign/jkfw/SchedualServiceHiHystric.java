package com.likun.sericefeign.jkfw;

import org.springframework.stereotype.Component;
/**
 * 只需要在FeignClient的SchedualServiceHi接口的注解中加上fallback的指定类就行了
 * SchedualServiceHiHystric需要实现SchedualServiceHi 接口，并注入到Ioc容器中
 *
 * */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi{
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}

package com.likun.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;
    /**
     * 通过之前注入ioc容器的restTemplate来消费service-hi服务的“/hi”接口，
     * 在这里我们直接用的程序名替代了具体的url地址，
     * 在ribbon中它会根据服务名来选择具体的服务实例，
     * 根据服务实例在请求的时候会用具体的url替换掉服务名
     * */

    /**
     * 一个服务注册中心，eureka server,端口为8761
     * service-hi工程跑了两个实例，端口分别为8762,8763，分别向服务注册中心注册
     * sercvice-ribbon端口为8764,向服务注册中心注
     * 当sercvice-ribbon通过restTemplate调用service-hi的hi接口时，因为用ribbon进行了负载均衡，
     * 会轮流的调用service-hi：8762和8763 两个端口的hi接口；
     * */
    /**
     * 在hiService方法上加上@HystrixCommand注解。该注解对该方法创建了熔断器的功能，
     * 并指定了fallbackMethod熔断方法，熔断方法直接返回了一个字符串，字符串为”hi,”+name+”,sorry,error!”，
     * */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://eureka-server2/hi?name="+name,String.class);
    }
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}

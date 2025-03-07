//package shopping.orderserver.service;
//
//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//@Slf4j
//@Component
//public class OrderDiscoverClient {
//    @Autowired
//    public OrderDiscoverClient(EurekaClient eurekaClient) {
//        this.eurekaClient = eurekaClient;
//    }
//    public String getSomeCategory(){
//        InstanceInfo instance = eurekaClient.getNextServerFromEureka("ProductService", false);
//        log.info(instance.getHomePageUrl());
//        RestTemplate restTemplate = new RestTemplate();
//        String serviceUri = String.format("%s/api/v1/categories/1", instance.getHomePageUrl());
//        return restTemplate.getForObject(serviceUri, String.class);
//    }
//}

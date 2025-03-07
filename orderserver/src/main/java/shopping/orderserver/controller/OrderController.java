//package shopping.orderserver.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import shopping.orderserver.service.OrderDiscoverClient;
//
//@RestController
//@RequestMapping("api/v1/orders")
//public class OrderController {
//    private final OrderDiscoverClient orderDiscoverClient;
//    @Autowired
//    public OrderController(OrderDiscoverClient orderDiscoverClient) {
//        this.orderDiscoverClient = orderDiscoverClient;
//    }
//    @GetMapping
//    public String getSomething(){
//        return orderDiscoverClient.getSomeCategory();
//    }
//}

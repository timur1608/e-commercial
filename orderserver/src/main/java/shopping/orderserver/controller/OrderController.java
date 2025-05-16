package shopping.orderserver.controller;


import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.orderserver.service.OrderBuilder;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderBuilder orderBuilder;
    private final Tracer tracer;
    @Autowired
    public OrderController(OrderBuilder orderBuilder, Tracer tracer) {
        this.orderBuilder = orderBuilder;
        this.tracer = tracer;
    }

    @PostMapping
    public void newOrder() {
//        tracer.
        log.info("New order");
        orderBuilder.createOrder();
    }
}

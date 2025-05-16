//package shopping.gatewayserver.filters;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Order(1)
//@Component
//public class TrackingFilter implements GlobalFilter {
//    private final FilterUtils filterUtils;
//    @Autowired
//    public TrackingFilter(FilterUtils filterUtils) {
//        this.filterUtils = filterUtils;
//    }
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        if (filterUtils.getCorrelationId(exchange.getRequest().getHeaders()) != null){
//            log.info(" foundCorrelation Id: {}", filterUtils.getCorrelationId(exchange.getRequest().getHeaders()));
//        }
//        else{
//            String correlationId = generateCorrelationId();
//            exchange = filterUtils.setCorrelationId(exchange, correlationId);
//            log.info(" add Correlation Id: {}", correlationId);
//        }
//        return chain.filter(exchange);
//    }
//    public String generateCorrelationId() {
//        return java.util.UUID.randomUUID().toString();
//    }
//}

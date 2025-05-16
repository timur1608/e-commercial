//package shopping.gatewayserver.filters;
//
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.server.ServerWebExchange;
//
//import java.util.List;
//
//@Component
//public class FilterUtils {
//    public static final String CORRELATION_ID = "tmx-correlation-id";
//
//    public String getCorrelationId(HttpHeaders headers) {
//        if (headers.get(CORRELATION_ID) != null) {
//            List<String> values = headers.get(CORRELATION_ID);
//            assert values != null;
//            return values.getFirst();
//        }
//        return null;
//    }
//
//    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
//        return exchange.mutate().request(
//                exchange.getRequest().mutate().header(CORRELATION_ID, correlationId).build()
//        ).build();
//    }
//
//}

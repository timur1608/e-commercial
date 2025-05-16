package shopping.deliveryserver.consumer;

//import brave.Span;
//import brave.Tracer;
import io.micrometer.tracing.ScopedSpan;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import shopping.deliveryserver.model.Cart;
import shopping.deliveryserver.service.CartHandler;

import java.time.Instant;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

@Slf4j
@Service
public class CartConsumer {
    private final CartHandler cartHandler;
    private final Random random;
    private final TaskScheduler taskScheduler;
    private final Tracer tracer;
    @Autowired
    public CartConsumer(CartHandler cartHandler, @Qualifier("taskScheduler") TaskScheduler taskScheduler, Tracer tracer) {
        this.cartHandler = cartHandler;
        this.taskScheduler = taskScheduler;
        this.random = new Random();
        this.tracer = tracer;
    }
    @Bean
    public Consumer<Message<Cart>> getUserCart(){
        return message -> {
            var map = message.getPayload();
            Span span = tracer.currentSpan();
            Instant when = Instant.now().plusSeconds(random.nextLong(10L,20L));
            taskScheduler.schedule(() -> {
                Span child = Objects.requireNonNull(tracer.nextSpan(span)).name("DelayedCartProcessing")
                        .start();
                try (Tracer.SpanInScope ws = tracer.withSpan(child)) {
                    cartHandler.processCart(map);
                } finally {
                    child.end();
                }
            }, when);
        };
    }
}

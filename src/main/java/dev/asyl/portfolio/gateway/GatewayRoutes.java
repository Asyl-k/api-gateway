package dev.asyl.portfolio.gateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {
    @Bean
    RouteLocator routes(RouteLocatorBuilder builder,
            @Value("${services.orders-url:http://localhost:8081}") String orders,
            @Value("${services.inventory-url:http://localhost:8082}") String inventory,
            @Value("${services.booking-url:http://localhost:8083}") String booking){
        return builder.routes()
                .route("orders",r->r.path("/api/orders/**")
                        .filters(f->f.circuitBreaker(c->c.setName("orders").setFallbackUri("forward:/fallback/orders")))
                        .uri(orders))
                .route("inventory",r->r.path("/api/inventory/**")
                        .filters(f->f.circuitBreaker(c->c.setName("inventory").setFallbackUri("forward:/fallback/inventory")))
                        .uri(inventory))
                .route("bookings",r->r.path("/api/bookings/**")
                        .filters(f->f.circuitBreaker(c->c.setName("bookings").setFallbackUri("forward:/fallback/bookings")))
                        .uri(booking))
                .build();
    }
}

package dev.asyl.portfolio.gateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(FallbackController.class)
class FallbackControllerTest {
    @Autowired WebTestClient client;
    @Test void fallbackReturnsRetryableServiceUnavailable(){
        client.get().uri("/fallback/orders").exchange()
                .expectStatus().isEqualTo(503)
                .expectBody(FallbackController.GatewayError.class)
                .value(error->{
                    assertThat(error.code()).isEqualTo("UPSTREAM_UNAVAILABLE");
                    assertThat(error.message()).contains("orders");
                    assertThat(error.timestamp()).isNotNull();
                });
    }
}

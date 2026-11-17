package dev.asyl.portfolio.gateway;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

@RestController
public class FallbackController {
    @GetMapping("/fallback/{service}")
    public ResponseEntity<GatewayError> fallback(@PathVariable String service){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new GatewayError("UPSTREAM_UNAVAILABLE","Service '%s' is temporarily unavailable".formatted(service),Instant.now()));
    }
    public record GatewayError(String code,String message,Instant timestamp){}
}

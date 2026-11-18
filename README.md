# API Gateway

> **Lab / not maintained.** Это ранний учебный проект: репозиторий заархивирован и оставлен для истории. Актуальные проекты — в [профиле](https://github.com/Asyl-k).
>
> **Lab / not maintained.** This is an early learning project. The repository is archived and kept for history; see [my profile](https://github.com/Asyl-k) for maintained projects.

Единая точка входа для сервисов портфолио на Spring Cloud Gateway. Маршрутизирует запросы к orders, inventory и booking; для каждого upstream настроен отдельный Resilience4j circuit breaker и контролируемый fallback `503`.

## Маршруты
- `/api/orders/**` → `ORDERS_SERVICE_URL`
- `/api/inventory/**` → `INVENTORY_SERVICE_URL`
- `/api/bookings/**` → `BOOKING_SERVICE_URL`
- `/actuator/health` — readiness/liveness health endpoint

Адреса сервисов настраиваются переменными окружения. Локально gateway ожидает сервисы на портах 8081–8083.

## Запуск и тесты
`mvn spring-boot:run` · `mvn verify`

Тест проверяет контракт fallback-ответа. Java 21 · Spring Cloud Gateway · Resilience4j · WebFlux · Actuator.

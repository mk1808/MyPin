package com.mypin.apiGateway.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/USERS/**")
						.filters(f -> f.rewritePath("/USERS/(?<segment>.*)", "/${segment}")
								.circuitBreaker(config -> config
										.setName("usersCircuitBreaker")
										.setFallbackUri("forward:/contactSupport"))
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(keyResolver())))
						.uri("lb://USERS"))
				
				.route(p -> p.path("/MAPS/**")
						.filters(f -> f.rewritePath("/MAPS/(?<segment>.*)", "/${segment}")
								.circuitBreaker(config -> config
										.setName("mapsCircuitBreaker")
										.setFallbackUri("forward:/contactSupport"))
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(keyResolver())))
						.uri("lb://MAPS"))
				
				.route(p -> p.path("/PINLISTS/**")
						.filters(f -> f.rewritePath("/PINLISTS/(?<segment>.*)", "/${segment}")
								.circuitBreaker(config -> config
										.setName("pinListsCircuitBreaker")
										.setFallbackUri("forward:/contactSupport"))
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(keyResolver())))
						.uri("lb://PINLISTS"))
				
				.route(p -> p.path("/SYNCHRONIZATION/**")
						.filters(f -> f.rewritePath("/SYNCHRONIZATION/(?<segment>.*)", "/${segment}")
								.circuitBreaker(config -> config
										.setName("synchronizationCircuitBreaker")
										.setFallbackUri("forward:/contactSupport"))
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(keyResolver())))
						.uri("lb://SYNCHRONIZATION"))
				
				.route(p -> p.path("/NOTIFICATIONS/**")
						.filters(f -> f.rewritePath("/NOTIFICATIONS/(?<segment>.*)", "/${segment}")
								.circuitBreaker(config -> config
										.setName("notificationsCircuitBreaker")
										.setFallbackUri("forward:/contactSupport"))
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(keyResolver())))
						.uri("lb://NOTIFICATIONS"))			
				
				.build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory
				.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(10)).build())
				.build());
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(10, 20, 1);
	}

	@Bean
	KeyResolver keyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()).defaultIfEmpty("anonymous");
	}

}

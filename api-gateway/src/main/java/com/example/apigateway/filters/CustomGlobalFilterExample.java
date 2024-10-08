package com.example.apigateway.filters;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomGlobalFilterExample implements GlobalFilter, Ordered{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("** Pase por el gateway **");

		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 1;
	}

}

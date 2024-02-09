package com.example.appid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.TokenRelayFilterFunctions.tokenRelay;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class GatewayConfiguration {
    @Bean
    public RouterFunction<ServerResponse> getRoute(@Value("${resource-server-api-uri}") String resourceServerUri) {

        /**
         * the programmatic route definition is needed because of a bug in gateway mvc not parting the token reelay correctly
         * https://github.com/spring-cloud/spring-cloud-gateway/issues/3176
         * **/
        return route("resource_server")
                .route(path("/api/**"), http(resourceServerUri))
                .filter(tokenRelay())
                .build();
    }
}

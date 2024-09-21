package com.microservice.api_gateway.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class UserHeaderFilter {

    public Function<ServerRequest, ServerRequest> addUserHeader() {
        return request -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
                String email = jwt.getClaimAsString("email");

                Consumer<HttpHeaders> headers = httpHeaders -> {
                    httpHeaders.add("email", email);
                };

                return ServerRequest.from(request)
                        .headers(headers)
                        .build();
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }
        };
    }
}

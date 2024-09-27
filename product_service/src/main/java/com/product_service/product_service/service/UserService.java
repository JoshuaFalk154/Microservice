package com.product_service.product_service.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Value("${keycloak.fetch-realm}")
    private String REALM_NAME;
    private final String CIRCUIT_BREAKER_NAME = "keycloakCircuitBreaker";
    private final Keycloak keycloak;

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "userExistsFallback")
    public boolean userExists(String userId) {
        UserRepresentation user = keycloak.realm(REALM_NAME)
                .users()
                .get(userId)
                .toRepresentation();

        boolean result = user != null;
        log.info("user with id {} does exist: {}", userId, result);
        return result;
    }

    public void checkUserExistence(String userId) {
        if (!userExists(userId)) {
            throw new RuntimeException("User with id " + userId + " does not exist");
        }
    }

    public boolean userExistsFallback(String userId, Throwable t) {
        log.error("Keycloak service unavailable. Fallback method triggered for userExists with id: {}", userId, t);
        return false;
    }

}

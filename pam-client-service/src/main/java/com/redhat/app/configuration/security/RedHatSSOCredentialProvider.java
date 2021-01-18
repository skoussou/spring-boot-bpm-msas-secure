package com.redhat.app.configuration.security;

import org.kie.server.client.CredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class RedHatSSOCredentialProvider implements CredentialsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedHatSSOCredentialProvider.class);

    private final Supplier<String> bearerTokenProvider;

    public RedHatSSOCredentialProvider(final Supplier<String> bearerTokenProvider) {
        this.bearerTokenProvider = bearerTokenProvider;
    }

    @Override
    public String getHeaderName() {
        //return AUTHORIZATION;
        return "Authorization";
    }

    @Override
    public String getAuthorization() {
      LOGGER.info("Get user authorization using KeyCloakTokenCredentialsProvider");
      //LOGGER.debug("Get user authorization using KeyCloakTokenCredentialsProvider");

        String bearerToken = bearerTokenProvider.get();
        LOGGER.info("adding token: {}", bearerToken);
        return TOKEN_AUTH_PREFIX + bearerToken;
    }
}

/**
 * public class KeyCloakTokenCredentialsProvider implements CredentialsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyCloakTokenCredentialsProvider.class);

    @Override
    public String getHeaderName() {
        return "Authorization";
    }

    @Override
    public String getAuthorization() {
        LOGGER.debug("Get user authorization using KeyCloakTokenCredentialsProvider");
        HttpServletRequest request = SecurityIntegrationFilter.getRequest();

        Principal principal = request.getUserPrincipal();
        if (principal != null && principal instanceof KeycloakPrincipal) {
            try {
                KeycloakPrincipal kc = (KeycloakPrincipal) principal;
                return CredentialsProvider.TOKEN_AUTH_PREFIX + kc.getKeycloakSecurityContext().getTokenString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}



public class EnteredTokenCredentialsProvider implements CredentialsProvider {

    private String token;

    public EnteredTokenCredentialsProvider(String token) {
        this.token = token;
    }

    @Override
    public String getHeaderName() {
        return AUTHORIZATION;
    }

    @Override
    public String getAuthorization() {
        return TOKEN_AUTH_PREFIX + token;
    }
}
~      


 */
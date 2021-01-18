/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.service.configuration.security.jwt;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Converts a Keycloak JWT token to {@link AbstractAuthenticationToken}.
 */
public class KeycloakAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakAuthenticationConverter.class);

    private final JwtAuthenticationConverter delegate;

    public KeycloakAuthenticationConverter() {
        this.delegate = new JwtAuthenticationConverter();
        this.delegate.setJwtGrantedAuthoritiesConverter(jwt -> convertRoles(extractRoles(jwt)));

        // Spring 2.3.x does not offer the following method. Following Links for possible definition of principal ClaimName definition
        // How to set user authorities from user claims return by an oauth server in spring security: https://stackoverflow.com/a/56259665/11662915
        // Spring Security 5 with oauth2 causing 'principalName cannot be empty' error: https://stackoverflow.com/questions/63352692/spring-security-5-with-oauth2-causing-principalname-cannot-be-empty-error
        // Improve handling of non-String principal claim values : https://www.gitmemory.com/issue/spring-projects/spring-security/9212/732283811
        // Extract Currently Logged in User information from JWT token using Spring Security: https://stackoverflow.com/questions/49127791/extract-currently-logged-in-user-information-from-jwt-token-using-spring-securit
        // JWT Authentication With Spring Boot’s Inbuilt OAuth2 Resource Server: https://medium.com/swlh/stateless-jwt-authentication-with-spring-boot-a-better-approach-1f5dbae6c30f
        this.delegate.setPrincipalClaimName("preferred_username");
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        return delegate.convert(jwt);
    }

    private List<GrantedAuthority> convertRoles(List<String> keycloakRoles) {
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        LOGGER.debug("#############################");
        LOGGER.debug("KeycloakRoles Roles in Token: {}", keycloakRoles);
        LOGGER.debug("#############################");
        for (String role : keycloakRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return grantedAuthorities;
    }

    @SuppressWarnings("unchecked")
    private List<String> extractRoles(Jwt jwt) {
        // Using other than realm roles would require something like https://stackoverflow.com/questions/58205510/spring-security-mapping-oauth2-claims-with-roles-to-secure-resource-server-endp
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        LOGGER.debug("#############################");
        LOGGER.debug("JWT Token Claims {}", jwt.getClaims());
        LOGGER.debug("#############################");

        return (List<String>) realmAccess.get("roles");
    }

}

package com.redhat.app.configuration.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import com.redhat.app.configuration.security.jwt.JwtProperties;
import com.redhat.app.configuration.security.jwt.KeycloakAuthenticationConverter;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;


//@Component
@Configuration
@EnableWebSecurity(debug=true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("****** SecurityConfiguration.configure - ACTIVE ******");
        //        http
        //                .csrf().disable()
        //                .authorizeRequests()
        //                .anyRequest().hasRole("kie-server")
        //                .and().headers().frameOptions().disable();
        http.authorizeRequests(authorize -> authorize.anyRequest().hasRole("kie-server"))
                .oauth2ResourceServer(configurer -> configurer.jwt(customizer -> customizer
                                 // Setup a custom Keycloak role converter
                                .jwtAuthenticationConverter(new KeycloakAuthenticationConverter())
                              // Setup a JWT decoder that uses public key to verify it
                              // Alternatively Spring Security could verify the token by contacting Keycloak server
                              .decoder(NimbusJwtDecoder.withPublicKey(getJwtPublicKey()).build())
//                .exceptionHandling(configurer -> configurer
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                );
                ));
    }

//    @Bean
//    public PrincipalExtractor githubPrincipalExtractor() {
//        return new GithubPrincipalExtractor();
//    }

    private RSAPublicKey getJwtPublicKey() {
        try {
            byte[] encoded = Base64.decodeBase64(jwtProperties.getPublicKey());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}

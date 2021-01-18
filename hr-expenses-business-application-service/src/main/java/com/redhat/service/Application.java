package com.redhat.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Warning. For demonstration purposes only.
        // A locally running Keycloak instance normally does not have a valid SSL certificate.
        // Therefore, we need to override a default SSL context to ignore a self-signed certificate.
        //initDefaultContext();

        SpringApplication.run(Application.class, args);
    }

//    private static void initDefaultContext() {
//        try {
//            SSLContext sslContext = SSLContexts.custom()
//                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
//                    .build();
//            SSLContext.setDefault(sslContext);
//        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
//            throw new RuntimeException(e);
//        }
//        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
//    }

}
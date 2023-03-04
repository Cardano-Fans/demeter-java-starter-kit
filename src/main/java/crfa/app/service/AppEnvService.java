//package org.cardanofoundation.service;
//
//import org.cardanofoundation.infrastructure.AppEnv;
//import io.micronaut.context.annotation.Value;
//import jakarta.inject.Singleton;
//
//@Singleton
//public class AppEnvService {
//
//    private String env;
//
//    public AppEnvService(@Value("${env:preprod}") String env) {
//        this.env = env;
//    }
//
//    public AppEnv appEnv() {
//        return AppEnv.valueOf(env.toUpperCase());
//    }
//
//}

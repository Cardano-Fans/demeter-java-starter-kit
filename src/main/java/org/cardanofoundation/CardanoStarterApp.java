package org.cardanofoundation;

import io.blockfrost.sdk.api.util.NetworkHelper;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.ApplicationShutdownEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class CardanoStarterApp {

    public static void main(String[] args) {
        Micronaut.build(args)
                .eagerInitSingletons(true)
                .mainClass(CardanoStarterApp.class)
                .start();
    }

    @EventListener
    public void onStartup(ServerStartupEvent event) {
        log.info("Starting CardanoStarterApp...");
    }

    @EventListener
    public void stop(final ApplicationShutdownEvent event) {
        log.info("Stopping CardanoStarterApp...");
        NetworkHelper.getInstance().shutdown();
        log.info("Stopped.");
    }

}

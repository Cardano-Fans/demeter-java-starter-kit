package org.cardanofoundation.service;

import io.micronaut.context.annotation.Value;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.cardanofoundation.repository.BlockfrostApi;

@Singleton
@Slf4j
public class CardanoTokenSender {

    private final String projectId;

    @Inject
    private BlockfrostApi blockfrostApi;

    public CardanoTokenSender(WalletPassReader walletPassReader,
                              @Value("${projectId}") String projectId,
                              @Value("${walletIndex:0}") int walletIndex) {
        this.projectId = projectId;

        var pass = walletPassReader.readWalletPass();
    }

    @EventListener
    public void onStartUp(ServerStartupEvent event) {
        System.out.println(blockfrostApi.getLatestBlock());
    }

}

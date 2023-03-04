//package org.cardanofoundation.repository;
//
//import io.blockfrost.sdk.api.BlockService;
//import io.blockfrost.sdk.api.exception.APIException;
//import io.blockfrost.sdk.api.model.Block;
//import io.blockfrost.sdk.impl.BlockServiceImpl;
//import io.micronaut.context.annotation.Value;
//import jakarta.inject.Singleton;
//import lombok.extern.slf4j.Slf4j;
//import org.cardanofoundation.service.AppEnvService;
//
//import static io.blockfrost.sdk.api.util.Constants.BLOCKFROST_MAINNET_URL;
//import static io.blockfrost.sdk.api.util.Constants.BLOCKFROST_PREPOD_URL;
//import static org.cardanofoundation.infrastructure.AppEnv.MAINNET;
//import static org.cardanofoundation.infrastructure.AppEnv.PREPROD;
//
//@Singleton
//@Slf4j
//public class BlockfrostApi {
//
//    private final BlockService blockService;
//
//    public BlockfrostApi(AppEnvService appEnvService,
//                         @Value("${projectId}") String blockFrostProjectId) {
//        if (appEnvService.appEnv() == MAINNET) {
//            log.info("Blockfrost API in mainnet mode, projectId:{}", blockFrostProjectId);
//            this.blockService = new BlockServiceImpl(BLOCKFROST_MAINNET_URL, blockFrostProjectId);
//        } else if (appEnvService.appEnv() == PREPROD) {
//            log.info("Blockfrost API in testnet mode, projectId:{}", blockFrostProjectId);
//            this.blockService = new BlockServiceImpl(BLOCKFROST_PREPOD_URL, blockFrostProjectId);
//        } else {
//            throw new RuntimeException("unknown network");
//        }
//    }
//
//    public Block getLatestBlock() {
//        try {
//            return blockService.getLatestBlock();
//        } catch (APIException e) {
//            log.error("Blockfrost error", e);
//            throw new RuntimeException("blockfrost error", e);
//        }
//    }
//
//}

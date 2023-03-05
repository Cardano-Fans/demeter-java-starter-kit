package crfa.app.commands;

import com.bloxbean.cardano.client.account.Account;
import com.bloxbean.cardano.client.api.exception.ApiException;
import com.bloxbean.cardano.client.api.model.Result;
import com.bloxbean.cardano.client.backend.api.DefaultProtocolParamsSupplier;
import com.bloxbean.cardano.client.backend.api.DefaultUtxoSupplier;
import com.bloxbean.cardano.client.backend.api.TransactionService;
import com.bloxbean.cardano.client.exception.CborSerializationException;
import com.bloxbean.cardano.client.function.Output;
import com.bloxbean.cardano.client.function.TxBuilderContext;
import com.bloxbean.cardano.client.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import static com.bloxbean.cardano.client.common.ADAConversionUtil.adaToLovelace;
import static com.bloxbean.cardano.client.common.CardanoConstants.LOVELACE;
import static com.bloxbean.cardano.client.function.helper.BalanceTxBuilders.balanceTx;
import static com.bloxbean.cardano.client.function.helper.InputBuilders.createFromSender;
import static com.bloxbean.cardano.client.function.helper.SignerProviders.signerFrom;
import static crfa.app.util.ConsoleWriter.success;

@ShellComponent
@Slf4j
public class TransactionCommands {

    @Autowired
    private ProviderCommands providerCommands;

    @Autowired
    private AccountCommands accountCommands;

    @ShellMethod(value = "Current active network", key = "send-ada")
    public void sendAda(@ShellOption(value = {"-f"}, help = "from (address or account name)") String from,
                        @ShellOption(value = {"-t"}, help = "to (address or account name)") String to,
                        @ShellOption(value = {"-a"}, help = "ADA amount") int adaAmount) throws CborSerializationException, ApiException {
        val senderAccount = getAccount(from);
        val receiverAccount = getAccount(to);

        val senderAddress = senderAccount.baseAddress();
        val receiverAddress = receiverAccount.baseAddress();

        val backendService = providerCommands.getActiveProvider().orElseThrow().backendService();
        val transactionService = backendService.getTransactionService();

        val output = Output.builder()
                .address(receiverAddress)
                .assetName(LOVELACE)
                .qty(adaToLovelace(adaAmount))
                .build();

        val txBuilder = output.outputBuilder()
                .buildInputs(createFromSender(senderAddress, senderAddress))
                .andThen(balanceTx(senderAddress, 1));

        val utxoSupplier = new DefaultUtxoSupplier(backendService.getUtxoService());
        val protocolParamsSupplier = new DefaultProtocolParamsSupplier(backendService.getEpochService());

        val signedTransaction = TxBuilderContext.init(utxoSupplier, protocolParamsSupplier)
                .buildAndSign(txBuilder, signerFrom(senderAccount));

        val result = backendService.getTransactionService().submitTransaction(signedTransaction.serialize());
        waitForTransaction(transactionService, result);

        System.out.println(success("Transaction success, result: %s", result.getValue()));
    }

    private Account getAccount(String accName) {
        return accountCommands.findAccountByName(accName).orElseThrow(()  -> new RuntimeException(String.format("%s does not represent valid account", accName)));
    }

    private void waitForTransaction(TransactionService transactionService, Result<String> result) {
        try {
            if (result.isSuccessful()) {
                int count = 0;
                while (count < 180) {
                    val txnResult = transactionService.getTransaction(result.getValue());
                    if (txnResult.isSuccessful()) {
                        System.out.println(JsonUtil.getPrettyJson(txnResult.getValue()));
                        break;
                    } else {
                        System.out.println("Waiting for transaction to be processed ....");
                    }

                    count++;
                    Thread.sleep(2000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

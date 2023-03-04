package crfa.app.commands;

import com.bloxbean.cardano.client.account.Account;
import com.bloxbean.cardano.client.common.model.Networks;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import static crfa.app.util.ConsoleWriter.strLn;

@ShellComponent
@Slf4j
public class AddressCommands {

    enum Network {
        MAINNET,
        TESTNET,
        PREPROD,
        PREVIEW
    }

    @ShellMethod(value = "Generate new address", key = "generate-address")
    public void generateNew(@ShellOption(value = {"--network"}, help = "Provide a known network (mainnet, testnet, preprod, preview)") String network) {
        val n = Network.valueOf(network.toUpperCase());

        val nn = switch (n) {
            case MAINNET -> Networks.mainnet();
            case PREPROD -> Networks.preprod();
            case TESTNET -> Networks.testnet();
            case PREVIEW -> Networks.preview();
        };

        val account = new Account(nn);

        val sb = new StringBuilder();
        sb.append(strLn("Address   : %s", account.baseAddress()));
        sb.append(strLn("Mnemonics : %s", account.mnemonic()));

        System.out.println(sb.toString());
    }

}
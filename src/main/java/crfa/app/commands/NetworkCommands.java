package crfa.app.commands;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

import static crfa.app.util.ConsoleWriter.strLn;

@ShellComponent
@Slf4j
public class NetworkCommands {

    private Optional<Network> network = Optional.of(Network.PREVIEW);

    public enum Network {
        MAINNET,
        TESTNET,
        PREPROD,
        PREVIEW
    }

    public Optional<Network> getNetwork() {
        return network;
    }

    @ShellMethod(value = "Current active network", key = "current-network")
    public void currentNetwork() {
        if (network.isPresent()) {
            printNetwork(network.orElseThrow());
            return;
        }

        val sb = new StringBuilder();

        sb.append(strLn("No network selected yet, please use: switch-network command."));

        System.out.println(sb);
    }

    @ShellMethod(value = "Switches active network", key = "switch-network")
    public void switchNetwork(@ShellOption(value = {"-n"}, defaultValue = "testnet", help = "Provide a known network (mainnet, testnet, preprod, preview)") String network) {
        val n = Network.valueOf(network.toUpperCase());
        this.network = Optional.of(n);

        printNetwork(n);
    }

    private static void printNetwork(Network network) {
        val sb = new StringBuilder();

        sb.append(strLn("Name: %s", network.name()));

        System.out.println(sb);
    }

}

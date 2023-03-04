package crfa.app.commands;

import com.bloxbean.cardano.client.account.Account;
import com.bloxbean.cardano.client.common.model.Networks;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static crfa.app.util.ConsoleWriter.strLn;

@ShellComponent
@Slf4j
public class AddressCommands {

    private Map<String, Account> accounts = new LinkedHashMap<>();

    enum Network {
        MAINNET,
        TESTNET,
        PREPROD,
        PREVIEW
    }

    public Optional<Account> findAccountByName(String name) {
        return Optional.ofNullable(accounts.get(name));
    }

    @ShellMethod(value = "Generate new account", key = "gen-new-account")
    public void generateNewAccount(@ShellOption(value = {"-n"}, defaultValue = "testnet", help = "Provide a known network (mainnet, testnet, preprod, preview)") String network) {
        val faker = new Faker();
        val funnyName = faker.funnyName();
        val name = funnyName.name().replace(" ", "_");

        val account = createAcc(Network.valueOf(network.toUpperCase()));

        accounts.put(name, account);

        printAcc(name, account);
    }

    @ShellMethod(value = "Account by name", key = "account-by-name")
    public void accountByName(@ShellOption(value = {"-n"}, help = "Provide account name") String name) {
        val acc = findAccountByName(name).orElseThrow(() -> new RuntimeException("Account by name not found, name:" + name));
        printAcc(name, acc);
    }

    @ShellMethod(value = "List accounts", key = "list-accounts")
    public void listAccounts() {
        accounts.forEach(AddressCommands::printAcc);
    }

    @ShellMethod(value = "Clears saved account", key = "clear-accounts")
    public void clearAccounts() {
        this.accounts.clear();
    }

    private static void printAcc(String name, Account account) {
        val sb = new StringBuilder();

        sb.append(strLn("Name      : %s", name));
        sb.append(strLn("Address   : %s", account.baseAddress()));
        sb.append(strLn("Mnemonics : %s", account.mnemonic()));

        System.out.println(sb);
    }

    private static Account createAcc(Network network) {
        val n = network;

        val nn = switch (n) {
            case MAINNET -> Networks.mainnet();
            case PREPROD -> Networks.preprod();
            case TESTNET -> Networks.testnet();
            case PREVIEW -> Networks.preview();
        };

        return new Account(nn);
    }

}

package crfa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@SpringBootApplication
@ShellComponent
public class CardanoStarterApp {

    @ShellMethod
    public String hi() {
        return "hi";
    }

    public static void main(String[] args) {
        SpringApplication.run(CardanoStarterApp.class, args);
    }

}

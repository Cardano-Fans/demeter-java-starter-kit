package crfa.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.shell.standard.ShellComponent;

@SpringBootApplication
@ShellComponent
public class CardanoStarterApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CardanoStarterApp.class)
                .logStartupInfo(false)
                .run(args);
    }

}

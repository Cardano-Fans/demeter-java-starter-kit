package crfa.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.shell.standard.ShellComponent;

@SpringBootApplication
@ShellComponent
public class CardanoJavaStarterKit {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CardanoJavaStarterKit.class)
                .logStartupInfo(false)
                .run(args);
    }

}

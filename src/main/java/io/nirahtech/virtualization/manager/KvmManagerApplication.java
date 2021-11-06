package io.nirahtech.virtualization.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class that represents the entry point of the application.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
@SpringBootApplication
public class KvmManagerApplication {
    
    /**
     * Start the application.
     * @throws Exception
     */
    private final void start(final String[] arguments) {
        SpringApplication.run(KvmManagerApplication.class, arguments);
    }
    
    /**
     * Entry point of the program.
     * 
     * @param args Command line arguments.
     */
    public static void main(final String[] arguments) {
        KvmManagerApplication application = new KvmManagerApplication();
        try {
            application.start(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

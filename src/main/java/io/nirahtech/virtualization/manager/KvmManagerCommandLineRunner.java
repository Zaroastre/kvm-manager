package io.nirahtech.virtualization.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

/**
 * Class that represents a command line runner after Spring context initialization.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public class KvmManagerCommandLineRunner implements CommandLineRunner {
   
        @Autowired
        private ApplicationContext applicationContext;
    
        @Override
        public void run(String... args) throws Exception {
            // TODO Auto-generated method stub
            
        }
}
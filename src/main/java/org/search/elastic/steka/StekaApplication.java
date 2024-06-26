package org.search.elastic.steka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StekaApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(StekaApplication.class, args);
    }
}
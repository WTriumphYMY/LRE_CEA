package cn.edu.nwpu.lre_cea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class LreCeaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LreCeaApplication.class, args);
    }

}

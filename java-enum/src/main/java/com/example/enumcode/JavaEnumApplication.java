package com.example.enumcode;

import com.example.enumcode.advance.CardType;
import com.example.enumcode.advance.EnumMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaEnumApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEnumApplication.class, args);
    }


    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("CardType", CardType.class);

        return enumMapper;
    }
}

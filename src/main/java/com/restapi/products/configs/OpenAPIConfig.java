package com.restapi.products.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {


    @Bean
    public OpenAPI openAPI(){
        Contact contact = new Contact();
        contact.setName("Don");

        Info info = new Info()
                .title("Product Management Application")
                .version("1.0.0")
                .contact(contact)
                .description("This APIS exposes endpoints to manage products.");

        return new OpenAPI().info(info);
    }
}

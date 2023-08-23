package ru.gtatarnikov.skyeng.testtask.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${skyeng.test-task.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("gerka0604@gmail.com");
        contact.setName("gtatarnikov");
        contact.setUrl("https://github.com/GermanTatarnikov");

        Info info = new Info()
                .title("Почта")
                .version("1.0")
                .contact(contact)
                .description("Это API предоставляет конечные точки для управления почтовыми отправлениями.")
                .termsOfService("https://www.bezkoder.com/terms");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}

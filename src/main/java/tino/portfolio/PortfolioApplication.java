package tino.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PortfolioApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PortfolioApplication.class);
        Map<String, Object> props = new HashMap<>();
        props.put("server.port", System.getenv().getOrDefault("PORT", "8080"));
        app.setDefaultProperties(props);
        app.run(args);
    }
}

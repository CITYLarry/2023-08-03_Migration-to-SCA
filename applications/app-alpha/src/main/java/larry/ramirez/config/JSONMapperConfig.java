package larry.ramirez.config;

import larry.ramirez.serializer.JSONMapper;
import larry.ramirez.serializer.JSONMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JSONMapperConfig {

    @Bean
    public JSONMapper jsonMapper() {
        return new JSONMapperImpl();
    }
}

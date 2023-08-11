package larry.ramirez.config;

import larry.ramirez.usecase.beta.ViewUpdater;
import larry.ramirez.usecase.generic.gateways.DomainViewRepository;
import larry.ramirez.usecase.generic.gateways.ViewBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "larry.ramirez.usecase.beta",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

        @Bean
        public ViewUpdater viewUpdaterUseCase(DomainViewRepository repository, ViewBus bus) {
                return new ViewUpdater(repository, bus);
        }
}

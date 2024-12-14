package es.jmpg.developer.runeterra.stream.loader.app.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private LoaderConfig loader;
    private DataConfig data;

    @Data
    public static class LoaderConfig {
        private ApiConfig api;
    }

    @Data
    public static class ApiConfig {
        private String latest;
        private String version;
    }

    @Data
    public static class DataConfig {
        private String output;
    }

}

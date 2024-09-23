package superioraccountingsoftware.com.Accounting;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow requests to all endpoints
                .allowedOrigins("http://localhost:3000") // Frontend app origin (React app running locally)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // HTTP methods allowed
                .allowedHeaders("*") // Allow any headers
                .allowCredentials(true); // Allow credentials (if needed)
    }
}

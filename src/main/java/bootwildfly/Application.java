package bootwildfly;



import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.h2.server.web.WebServlet;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
    
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("dirdlididi-api")
            .select() //Selection by RequestHandler
              .paths(paths()) // and by paths
              .build()
            .apiInfo(apiInfo());
    }
    
    private Predicate<String> paths() {
        return or(
                regex("/problema.*"),
                regex("/estatistica.*"),
                regex("/usuario.*"),
                regex("/admin.*"),
                regex("/springsRestController.*"),
                regex("/test.*"));
    }
    
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Rest api Dirdlididi")
                .description("")
                .termsOfServiceUrl("dirlididibldr-benonisilva.rhcloud.com")
                .contact("Benoni Silva")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/benonisilva/dirlididi-api")
                .version("0.1")
                .build();
    }
}


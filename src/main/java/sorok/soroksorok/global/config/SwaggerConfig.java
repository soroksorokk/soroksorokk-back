package sorok.soroksorok.global.config;

import com.fasterxml.classmate.TypeResolver;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api(TypeResolver typeResolver) {
    return new Docket(DocumentationType.OAS_30)
        .securityContexts(Collections.singletonList(securityContext())) // 추가
        .securitySchemes(List.of(apiKey(), apiKey2())) // 추가
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("sorok.soroksorok"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  // 추가
  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .build();
  }

  // 추가
  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("Authorization", authorizationScopes), new SecurityReference("Authorization-refresh", authorizationScopes));
  }

  // 추가
  private ApiKey apiKey() {
    return new ApiKey("Authorization", "Authorization", "header");
  }

  // 추가
  private ApiKey apiKey2() {
    return new ApiKey("Authorization-refresh", "Authorization-refresh", "header");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("소록소록 - Backend API")
        .description("소록소록 - Backend API 문서")
        .version("1.0")
        .build();
  }

}
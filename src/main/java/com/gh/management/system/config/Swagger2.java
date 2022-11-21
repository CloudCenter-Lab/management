package com.gh.management.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class Swagger2 {

    //请求地址为:http://localhost:9000/doc.html
    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)          //指定Api类型为Swagger2
                .apiInfo(apiInfo())                             //指定文档汇总信息
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.gh.management.system.controller")) //指定controller包路径
                .paths(PathSelectors.any())                     //指定展示所有controller
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());

    }

    private ApiInfo apiInfo(){
        //返回一个apiinfo
        return new ApiInfoBuilder()
                .title("api接口文档")                                       //文档页标题
                .contact(
                        new Contact(
                                "YJL",
                                "",
                                "228662572@qq.com")
                )                                                           // 联系人信息
                .description("api文档")                                       // 详细信息
                .version("1.0.1")                                           // 文档版本号
                .termsOfServiceUrl("https://www.baidu.com")                  //网站地址
                .build();
    }


    private List<ApiKey> securitySchemes(){
        //设置请求头信息
        List<ApiKey> result= new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization","Authorization","Header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts(){
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }

}

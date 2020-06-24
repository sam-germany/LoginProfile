package com.gts.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	
	Contact contact3 = new Contact("abc Name" , "www.google.in ", "https://ggtech.co.in/");
	
	List<VendorExtension> vendorExtensions = new ArrayList<>();
	
	ApiInfo apiInfo3 = new ApiInfo(
			                       "Job Searching app documentation",
			                       "This pages documents RESTful Web Service endpoints",
			                       "5.5",
			                       "https://ggtech.co.in/",
			                       contact3,
			                       "Mysql 8.0",
			                       "https://ggtech.co.in/",
			                       vendorExtensions);
			
	
	@Bean
    public Docket apiDocket() { 
        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
          .protocols(new HashSet<>(Arrays.asList("HTTP" , "HTTPs")))
          .apiInfo(apiInfo3)
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.gts.users"))              
          .paths(PathSelectors.any())                          
          .build();          
        
        return docket;
    }
}
	
	


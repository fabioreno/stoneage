package com.stoneage.microservices.rettiwtservice;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration 
{
	public static final Contact DEFAULT_CONTACT = 
		new Contact(
				"Fabio Souza", 
				"https://github.com/fabioreno/", 
				"f.souza@gmail.com");
		
	public static final ApiInfo DEFAULT_API_INFO = 
			new ApiInfo(
					"Rettiwt", 
					"Rettiwt Social Media Service", "1.0", 
					"urn:tos", DEFAULT_CONTACT, 
					"Apache 2.0", 
					"http://www.apache.org/licenses/LICENSE-2.0", emptyList());
	
	public static final Set<String> CONTENT_NEGOTIATION = 
			new HashSet<String>(asList("application/json", "application/xml"));
		
	@Bean
	public Docket api()
	{
		return new Docket(SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.consumes(CONTENT_NEGOTIATION)
				.produces(CONTENT_NEGOTIATION)
				.select()
				.apis(RequestHandlerSelectors.any())              
                .paths(PathSelectors.any())
                .build();
	}
}

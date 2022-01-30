package com.alura.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alura.model.domain.Usuario;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {  
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.alura"))              
          .paths(PathSelectors.ant("/**"))                          
          .build()
          .ignoredParameterTypes(Usuario.class) // ignorando urls que utilizam esta classe.
          .globalOperationParameters(Arrays.asList( // Para adicionarmos parâmetros em todos endpoints do swagger.
        	new ParameterBuilder()
        	.name("Authorization")
        	.description("Header para token JWT")
        	.modelRef(new ModelRef("string"))
        	.parameterType("header")
        	.required(false)
        	.build()
          ));
    }
    
}

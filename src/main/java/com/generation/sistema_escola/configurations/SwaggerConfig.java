package com.generation.sistema_escola.configurations;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springSistemaEscolaOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("CRUD SistemaEscola") 
                .description("API para gerenciamento de alunos - Sistema Escola") 
                .version("v0.0.1") 
                .license(new License() 
                    .name("Open Source License") 
                    .url("https://opensource.org/licenses/MIT")) //link genérico
                .contact(new Contact() 
                    .name("Ana Paula Moura / Jonathan Cavalcanti de Paula / Michelle da Silva Madeira")
                    .url("https://github.com/Grupo8Gen/EscolaGeneration") 
                    .email("grupo8awsgen@gmail.com"))) 
            .externalDocs(new ExternalDocumentation()
                .description("Github") 
                .url("https://github.com/Grupo8Gen")); 
    }

    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();
                apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
                apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
                apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
                apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
                apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
                apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
                apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
            }));
        };
    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }
}

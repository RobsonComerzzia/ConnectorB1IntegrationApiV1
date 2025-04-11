package com.seidor.comerzzia.connector.core.springdoc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.seidor.comerzzia.connector.api.exceptionhandler.Problem;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration

/*
@SecurityScheme(name = "security_auth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
                scopes = {
                        @OAuthScope(name = "READ", description = "read scope"),
                        @OAuthScope(name = "WRITE", description = "write scope")
                }
        )))
*/


@SecurityScheme(name = "security_auth", type = SecuritySchemeType.OAUTH2,
flows = @OAuthFlows(clientCredentials  = @OAuthFlow(
		tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
		scopes = {
		        @OAuthScope(name = "READ", description = "read scope"),
		        @OAuthScope(name = "WRITE", description = "write scope")
		}
)))

public class SpringDocConfig {

	
    private static final String badRequestResponse = "BadRequestResponse";
    private static final String notFoundResponse = "NotFoundResponse";
    private static final String notAcceptableResponse = "NotAcceptableResponse";
    private static final String internalServerErrorResponse = "InternalServerErrorResponse";

    @Bean
    public OpenAPI openAPI() {
    	
    	Server server = new Server().url("/");
        return new OpenAPI()
        		.servers(List.of(server))
    			.info(new Info()
                        .title("Comerzzia Brasil - Master Intregation API")
                        .version("v1")
                        .description("REST API de Integração de dados da Comerzzia Brasil")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("Comerzzia Brasil")
                        .url("https://comerzziabrasil.com.br")
                ).tags(Arrays.asList(
                		new Tag().name("ERP to Comerzzia - Famílias").description("Exporta dados de famílias de produto do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Categorias").description("Exporta dados de categorias de produto do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Seções").description("Exporta dados de seções de produto do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Marcas").description("Exporta dados de marca de produto do ERP para o Comerzzia"),
                		
                		new Tag().name("ERP to Comerzzia - Tratamento de imposto").description("Exporta dados de tratamento de imposto do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Tipo de Imposto").description("Exporta dados de tipo de imposto do ERP para o Comerzzia"),
                		
                		new Tag().name("ERP to Comerzzia - Unidades de Medida").description("Exporta dados de unidades de medida do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Unidades de Medida Etiqueta").description("Exporta dados de unidades de medida por etiqueta do ERP para o Comerzzia"),
                		
                		new Tag().name("ERP to Comerzzia - Itens").description("Exporta dados de itens(produtos) do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Código de barras").description("Exporta código de barras de produtos do ERP para o Comerzzia"),
                		
                		new Tag().name("ERP to Comerzzia - Tipo de efeito do meio de pagamento").description("Exporta dados do tipo de efeito do meio de pagamento do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Meios de Pagamento").description("Exporta dados de meios de pagamento do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Vencimentos do Meio de Pagamento").description("Exporta dados de vencimentos do meio de pagamento do ERP para o Comerzzia"),
                		new Tag().name("ERP to Comerzzia - Fornecedores").description("Exporta dados do fornecedoror do produto do ERP para o Comerzzia")
                  	                		
                		//new Tag().name("Grupos").description("Gerencia os grupos"),
                        //new Tag().name("Usuários").description("Gerencia os usuários"),
                        //new Tag().name("Permissões").description("Gerencia as permissões")
                )).components(new Components()
                        .schemas(generateSchemas())
                        .responses(generateResponses())
                );
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach((httpMethod, operation) -> {
                                ApiResponses responses = operation.getResponses();
                                switch (httpMethod) {
                                    case GET:
                                        responses.addApiResponse("406", new ApiResponse().$ref(notAcceptableResponse));
                                        responses.addApiResponse("500", new ApiResponse().$ref(internalServerErrorResponse));
                                        break;
                                    case POST:
                                        responses.addApiResponse("400", new ApiResponse().$ref(badRequestResponse));
                                        responses.addApiResponse("500", new ApiResponse().$ref(internalServerErrorResponse));
                                        break;
                                    case PUT:
                                        responses.addApiResponse("400", new ApiResponse().$ref(badRequestResponse));
                                        responses.addApiResponse("500", new ApiResponse().$ref(internalServerErrorResponse));
                                        break;
                                    case DELETE:
                                        responses.addApiResponse("500", new ApiResponse().$ref(internalServerErrorResponse));
                                        break;
                                    default:
                                        responses.addApiResponse("500", new ApiResponse().$ref(internalServerErrorResponse));
                                        break;
                                }
                            })
                    );
        };
    }

    private Map<String, Schema> generateSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();

        Map<String, Schema> problemSchema = ModelConverters.getInstance().read(Problem.class);
        Map<String, Schema> problemObjectSchema = ModelConverters.getInstance().read(Problem.Object.class);

        schemaMap.putAll(problemSchema);
        schemaMap.putAll(problemObjectSchema);

        return schemaMap;
    }

    private Map<String, ApiResponse> generateResponses() {
        final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new MediaType().schema(new Schema<Problem>().$ref("Problema")));

        apiResponseMap.put(badRequestResponse, new ApiResponse()
                .description("Requisição inválida")
                .content(content));

        apiResponseMap.put(notFoundResponse, new ApiResponse()
                .description("Recurso não encontrado")
                .content(content));

        apiResponseMap.put(notAcceptableResponse, new ApiResponse()
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .content(content));

        apiResponseMap.put(internalServerErrorResponse, new ApiResponse()
                .description("Erro interno no servidor")
                .content(content));

        return apiResponseMap;
    }

}

package com.seidor.comerzzia.connector.api.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "ERP to Comerzzia - B1")
public interface IntegracaoB1ErpToCmzControllerOpenApi<T> {

	@Operation(summary = "Inclui um item", responses = {
			@ApiResponse(responseCode = "201", description = "Item cadastrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "400", description = "Dados inválidos",
			content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "401", description = "Não autorizado",
					content = {
							@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "403", description = "Sem permissão",
					content = {
							@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "404", description = "Rota errada ou não encontrado",
					content = {
							@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor",
			content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	T insert();
	
}

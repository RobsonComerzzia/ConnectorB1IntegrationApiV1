package com.seidor.comerzzia.connector.api.v1.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Schema(description = "Modelo da categoria")
public class CategorizacionModel  {

	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;

	@Schema(example = "0101")
	@NotBlank
	@JsonProperty("categoryCode")
	private String codcat;
	
	@Schema(example = "CATEGORIA 01")
	@NotBlank
	@JsonProperty("categoryDescr")
	private String descat;
	
	@Schema(example = "S")
	@NotBlank
	@JsonProperty("active")
	private String activo;
	
	@Schema(example = "1")
	@JsonProperty("version")
	private Long version;
	
	@Schema(example = "2024-09-13T18:01:28")
	@JsonProperty("versionDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaVersion;
	
	@Schema(example = "01")
	@JsonProperty("parentCategoryCode")
	private String codcatPadre;

}

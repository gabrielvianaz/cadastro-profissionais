package tech.gviana.cadastroprofissionais.core;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record SearchParams(@NotBlank(message = "O parâmetro 'q' é obrigatório") @Schema(description = "Filtro de texto a ser aplicado na busca") String q,
                           @Schema(description = "Campos a serem retornados na busca", requiredMode = Schema.RequiredMode.NOT_REQUIRED) List<String> fields) {

}
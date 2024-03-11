package tech.gviana.cadastroprofissionais.modules.profissional;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProfissionalDTO(@NotBlank(message = "O atributo 'nome' é obrigatório") String nome,
                              @NotNull(message = "O atributo 'cargo' é obrigatório") Cargo cargo,
                              @NotNull(message = "O atributo 'nascimento' é obrigatório") LocalDate nascimento) {

}

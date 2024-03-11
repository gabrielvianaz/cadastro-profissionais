package tech.gviana.cadastroprofissionais.modules.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ContatoDTO(@NotBlank(message = "O atributo 'nome' é obrigatório") String nome,
                         @NotBlank(message = "O atributo 'contato' é obrigatório") String contato,
                         @NotNull(message = "O atributo 'profissional' é obrigatório") UUID profissionalId) {

}

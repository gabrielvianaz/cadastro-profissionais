package tech.gviana.cadastroprofissionais.modules.contato;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gviana.cadastroprofissionais.core.SearchParams;
import tech.gviana.cadastroprofissionais.core.base.controller.AbstractController;

import java.util.UUID;

@RestController
@RequestMapping("/contatos")
@Tag(name = "Contatos")
public class ContatoController extends AbstractController<Contato, ContatoDTO> {

    public ContatoController(ContatoService service) {
        super(service);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contatos retornados com sucesso", content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "["
                            + "{"
                            + "    \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\","
                            + "    \"createdDate\": \"2024-03-10T18:57:05.475Z\","
                            + "    \"nome\": \"string\","
                            + "    \"contato\": \"string\","
                            + "    \"profissional\": {"
                            + "        \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\","
                            + "        \"createdDate\": \"2024-03-10T18:57:05.475Z\","
                            + "        \"nome\": \"string\","
                            + "        \"cargo\": \"DESENVOLVEDOR\","
                            + "        \"nascimento\": \"2024-03-10\""
                            + "    }"
                            + "}]"))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content(examples = @ExampleObject(value = "{}"))),
    })
    @Operation(summary = "Busca todos os contatos existentes através dos parâmetros enviados")
    public ResponseEntity<?> findAllBySearchParams(SearchParams searchParams) {
        return super.findAllBySearchParams(searchParams);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato retornado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{}"))),
            @ApiResponse(responseCode = "404", description = "Contato não localizado", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{}")))
    })
    @Operation(summary = "Busca um contato existente")
    public ResponseEntity<Contato> findById(UUID id) {
        return super.findById(id);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contato inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @Operation(summary = "Insere um novo contato")
    public ResponseEntity<Void> insert(ContatoDTO dto) {
        return super.insert(dto);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contato atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content(examples = @ExampleObject(value = "{}"))),
            @ApiResponse(responseCode = "404", description = "Contato não localizado", content = @Content(examples = @ExampleObject(value = "{}")))
    })
    @Operation(summary = "Atualiza um contato existente")
    public ResponseEntity<Void> update(UUID id, ContatoDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contato deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Contato não localizado")
    })
    @Operation(summary = "Deleta um contato existente")
    public ResponseEntity<Void> delete(UUID id) {
        return super.delete(id);
    }

}

package tech.gviana.cadastroprofissionais.modules.profissional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gviana.cadastroprofissionais.core.SearchParams;
import tech.gviana.cadastroprofissionais.core.base.controller.AbstractActivableEntityController;

import java.util.UUID;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController extends AbstractActivableEntityController<Profissional, ProfissionalDTO> {

    public ProfissionalController(ProfissionalService service) {
        super(service);
    }


    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profissionais retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "["
                                    + "{"
                                    + "    \"id\": \"c17bc134-b04e-41e3-bd6f-a1df36ca6c75\","
                                    + "    \"createdDate\": \"2024-03-10T15:08:46.908382\","
                                    + "    \"nome\": \"string\","
                                    + "    \"cargo\": \"DESENVOLVEDOR\","
                                    + "    \"nascimento\": \"2024-03-10\""
                                    + "}]"))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content(examples = @ExampleObject(value = "{}"))),
    })
    @Operation(summary = "Busca todos os profissionais existentes através dos parâmetros enviados")
    public ResponseEntity<?> findAllBySearchParams(SearchParams searchParams) {
        return super.findAllBySearchParams(searchParams);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profissional retornado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{}"))),
            @ApiResponse(responseCode = "404", description = "Profissional não localizado", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{}")))
    })
    @Operation(summary = "Busca um profissional existente")
    public ResponseEntity<Profissional> findById(UUID id) {
        return super.findById(id);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profissional inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @Operation(summary = "Insere um novo profissional")
    public ResponseEntity<Void> insert(ProfissionalDTO dto) {
        return super.insert(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profissional atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content(examples = @ExampleObject(value = "{}"))),
            @ApiResponse(responseCode = "404", description = "Profissional não localizado", content = @Content(examples = @ExampleObject(value = "{}")))
    })
    @Operation(summary = "Atualiza um profissional existente")
    @Override
    public ResponseEntity<Void> update(UUID id, ProfissionalDTO dto) {
        return super.update(id, dto);
    }


    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profissional deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Profissional não localizado")
    })
    @Operation(summary = "Deleta um profissional existente")
    public ResponseEntity<Void> delete(UUID id) {
        return super.delete(id);
    }

    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profissional reativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Profissional não localizado")
    })
    @Operation(summary = "Reativa um profissional inativo")
    public ResponseEntity<Profissional> reactivate(UUID id) {
        return super.reactivate(id);
    }

}

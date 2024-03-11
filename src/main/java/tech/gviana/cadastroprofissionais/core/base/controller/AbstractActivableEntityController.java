package tech.gviana.cadastroprofissionais.core.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractActivableEntity;
import tech.gviana.cadastroprofissionais.core.base.service.AbstractActivableEntityService;

import java.util.UUID;

public class AbstractActivableEntityController<T extends AbstractActivableEntity, S> extends AbstractController<T, S> {

    public AbstractActivableEntityController(AbstractActivableEntityService<T, S> service) {
        super(service);
    }

    @PostMapping("/{id}/reactivate")
    public ResponseEntity<T> reactivate(@PathVariable UUID id) {
        ((AbstractActivableEntityService) service).reactivate(id);
        return ResponseEntity.noContent().build();
    }

}

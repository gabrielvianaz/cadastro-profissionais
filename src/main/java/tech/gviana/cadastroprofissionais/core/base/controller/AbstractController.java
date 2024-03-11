package tech.gviana.cadastroprofissionais.core.base.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.gviana.cadastroprofissionais.core.SearchParams;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractEntity;
import tech.gviana.cadastroprofissionais.core.base.service.AbstractService;

import java.net.URI;
import java.util.UUID;

public abstract class AbstractController<T extends AbstractEntity, S> {

    protected final AbstractService<T, S> service;

    public AbstractController(AbstractService<T, S> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAllBySearchParams(@Valid SearchParams searchParams) {
        return ResponseEntity.ok(service.findAllBySearchParams(searchParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid S dto) {
        T entity = service.insert(dto);
        return ResponseEntity.created(buildUri(entity.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody S dto) {
        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private URI buildUri(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}

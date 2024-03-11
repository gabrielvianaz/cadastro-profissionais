package tech.gviana.cadastroprofissionais.core.base.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractActivableEntity;
import tech.gviana.cadastroprofissionais.core.base.mapper.BaseMapper;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractActivableEntityService<T extends AbstractActivableEntity, S> extends AbstractService<T, S> {

    public AbstractActivableEntityService(EntityManager entityManager, Class<T> entityClass, JpaRepository<T, UUID> repository, BaseMapper<T, S> mapper) {
        super(entityManager, entityClass, repository, mapper);
    }

    @Override
    public T findById(UUID id) {
        return findByID(id, false);
    }

    public T findByID(UUID id, Boolean getInactive) {
        Optional<T> entityOpt = repository.findById(id);

        if (entityOpt.isEmpty()) {
            throw new EntityNotFoundException("Não foi localizado um objeto com o ID informado");
        }

        if (!entityOpt.get().getAtivo() && !getInactive) {
            throw new RuntimeException("O objeto informado se encontra inativo");
        }

        return entityOpt.get();
    }

    @Override
    public void delete(UUID id) {
        T entity = findById(id);
        entity.setAtivo(false);
        save(entity);
    }

    public T reactivate(UUID id) {
        T entity = findByID(id, true);

        if (entity.getAtivo()) {
            throw new RuntimeException("O objeto já se encontra ativo");
        }

        entity.setAtivo(true);

        return save(entity);
    }

}

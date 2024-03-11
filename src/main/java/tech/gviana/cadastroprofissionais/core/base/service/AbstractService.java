package tech.gviana.cadastroprofissionais.core.base.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.gviana.cadastroprofissionais.core.SearchParams;
import tech.gviana.cadastroprofissionais.core.SqlBuilder;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractEntity;
import tech.gviana.cadastroprofissionais.core.base.mapper.BaseMapper;

import java.util.List;
import java.util.UUID;

public abstract class AbstractService<T extends AbstractEntity, S> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;
    protected JpaRepository<T, UUID> repository;
    protected BaseMapper<T, S> mapper;

    public AbstractService(EntityManager entityManager, Class<T> entityClass, JpaRepository<T, UUID> repository, BaseMapper<T, S> mapper) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
        this.repository = repository;
        this.mapper = mapper;
    }

    protected T save(T entity) {
        return repository.save(entity);
    }

    public T insert(S dto) {
        T entity = mapper.toEntity(dto);
        validateInsert(entity, dto);

        return save(entity);
    }

    public T findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi localizado um objeto com o ID informado"));
    }

    public List<Object[]> findAllBySearchParams(SearchParams searchParams) {
        String sql = new SqlBuilder(searchParams, entityClass).build();

        return entityManager.createQuery(sql).getResultList();
    }

    public T update(UUID id, S dto) {
        T entity = findById(id);
        validateUpdate(entity, dto);
        mapper.updateByDTO(entity, dto);

        return save(entity);
    }

    public void delete(UUID id) {
        T entity = findById(id);
        repository.delete(entity);
    }

    protected abstract void validateInsert(T entity, S dto);

    protected abstract void validateUpdate(T entity, S dto);

}

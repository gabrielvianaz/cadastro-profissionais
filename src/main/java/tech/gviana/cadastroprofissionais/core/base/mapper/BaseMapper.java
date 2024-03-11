package tech.gviana.cadastroprofissionais.core.base.mapper;

import org.mapstruct.*;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractEntity;

@MapperConfig(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BaseMapper<T extends AbstractEntity, S> {

    T toEntity(S dto);

    void updateByDTO(@MappingTarget T entity, S dto);

}

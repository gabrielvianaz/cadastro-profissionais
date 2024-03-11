package tech.gviana.cadastroprofissionais.modules.profissional;

import org.mapstruct.Mapper;
import tech.gviana.cadastroprofissionais.core.base.mapper.BaseMapper;

@Mapper(config = BaseMapper.class)
public interface ProfissionalMapper extends BaseMapper<Profissional, ProfissionalDTO> {

}

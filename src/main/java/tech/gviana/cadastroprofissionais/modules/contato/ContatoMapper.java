package tech.gviana.cadastroprofissionais.modules.contato;

import org.mapstruct.Mapper;
import tech.gviana.cadastroprofissionais.core.base.mapper.BaseMapper;

@Mapper(config = BaseMapper.class)
public interface ContatoMapper extends BaseMapper<Contato, ContatoDTO> {

}

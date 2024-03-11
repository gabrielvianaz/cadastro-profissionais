package tech.gviana.cadastroprofissionais.modules.profissional;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import tech.gviana.cadastroprofissionais.core.base.service.AbstractActivableEntityService;

import java.time.LocalDate;

@Service
public class ProfissionalService extends AbstractActivableEntityService<Profissional, ProfissionalDTO> {

    public ProfissionalService(EntityManager entityManager, ProfissionalRepository repository, ProfissionalMapper profissionalMapper) {
        super(entityManager, Profissional.class, repository, profissionalMapper);
    }

    @Override
    protected void validateInsert(Profissional profissional, ProfissionalDTO dto) {
        validateUniqueness(dto.nome(), dto.cargo(), dto.nascimento());
    }

    @Override
    protected void validateUpdate(Profissional profissional, ProfissionalDTO dto) {
        validateUniqueness(dto.nome(), dto.cargo(), dto.nascimento());
    }

    private void validateUniqueness(String nome, Cargo cargo, LocalDate nascimento) {
        if (((ProfissionalRepository) repository).existsByNomeAndCargoAndNascimento(nome, cargo, nascimento)) {
            throw new EntityExistsException("Já existe um profissional cadastrado com o mesmo nome, cargo e data de nascimento informados");
        }
    }

}

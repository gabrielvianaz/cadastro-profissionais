package tech.gviana.cadastroprofissionais.modules.contato;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tech.gviana.cadastroprofissionais.core.base.service.AbstractService;
import tech.gviana.cadastroprofissionais.modules.profissional.ProfissionalService;

import java.util.UUID;

@Service
public class ContatoService extends AbstractService<Contato, ContatoDTO> {

    private final ProfissionalService profissionalService;

    public ContatoService(EntityManager entityManager, ContatoRepository repository, ContatoMapper mapper, ProfissionalService profissionalService) {
        super(entityManager, Contato.class, repository, mapper);
        this.profissionalService = profissionalService;
    }

    @Override
    protected void validateInsert(Contato contato, ContatoDTO dto) {
        validateUniqueness(dto.nome(), dto.contato(), dto.profissionalId());
        setProfissionalById(contato, dto.profissionalId());
    }

    @Override
    protected void validateUpdate(Contato contato, ContatoDTO dto) {
        validateUniqueness(dto.nome(), dto.contato(), dto.profissionalId());

        if (dto.profissionalId() != null && !dto.profissionalId().equals(contato.getProfissional().getId()))
            setProfissionalById(contato, dto.profissionalId());

    }

    private void validateUniqueness(String nome, String contato, UUID profissionalId) {
        if (((ContatoRepository) repository).existsByNomeAndContatoAndProfissionalId(nome, contato, profissionalId)) {
            throw new EntityExistsException("Já existe um contato cadastrado com o mesmo nome, contato e profissional informados");
        }
    }

    private void setProfissionalById(Contato contato, UUID profissionalId) {
        try {
            contato.setProfissional(profissionalService.findById(profissionalId));
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Não foi localizado um profissional com o ID informado");
        } catch (RuntimeException e) {
            throw new RuntimeException("O profissional com o ID informado se encontra inativo");
        }
    }

}

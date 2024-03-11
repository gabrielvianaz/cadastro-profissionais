package tech.gviana.cadastroprofissionais.modules.contato;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {

    Boolean existsByNomeAndContatoAndProfissionalId(String nome, String contato, UUID profissionalId);

}

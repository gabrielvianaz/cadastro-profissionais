package tech.gviana.cadastroprofissionais.modules.profissional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {

    Boolean existsByNomeAndCargoAndNascimento(String nome, Cargo cargo, LocalDate nascimento);

}

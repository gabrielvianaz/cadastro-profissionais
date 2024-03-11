package tech.gviana.cadastroprofissionais.modules.profissional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractActivableEntity;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "profissional_nome_cargo_unique", columnNames = {"nome", "cargo", "nascimento"})
})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Profissional extends AbstractActivableEntity {

    @Column(nullable = false)
    private String nome;

    @Enumerated
    @Column(nullable = false)
    private Cargo cargo;

    @Column(nullable = false)
    private LocalDate nascimento;

    @Override
    public void prePersist() {
        super.prePersist();
        setSearchField(String.format("%s%s%s%s%s", id, createdDate, nome, cargo, nascimento).toLowerCase());
    }

}

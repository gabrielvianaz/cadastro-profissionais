package tech.gviana.cadastroprofissionais.modules.contato;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractEntity;
import tech.gviana.cadastroprofissionais.modules.profissional.Profissional;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "nome_contato_profissional_unique", columnNames = {"nome", "contato", "profissional_id"})
})
public class Contato extends AbstractEntity {

    private String nome;

    private String contato;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Profissional profissional;

    @Override
    public void prePersist() {
        setSearchField(String.format("%s%s%s%s%s", id, createdDate, nome, contato, profissional.getNome()).toLowerCase());
    }

}

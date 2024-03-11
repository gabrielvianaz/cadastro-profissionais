package tech.gviana.cadastroprofissionais.core.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public abstract class AbstractActivableEntity extends AbstractEntity {

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "boolean default true")
    protected Boolean ativo;

    @Override
    public void prePersist() {
        setAtivo(ativo == null || ativo);
    }

}

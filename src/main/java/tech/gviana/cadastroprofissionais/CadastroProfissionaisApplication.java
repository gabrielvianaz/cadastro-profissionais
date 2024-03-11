package tech.gviana.cadastroprofissionais;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "Cadastro de Profissionais", version = "1.0", description = "API para cadastro de profissionais e contatos"))
public class CadastroProfissionaisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CadastroProfissionaisApplication.class, args);
    }

}
